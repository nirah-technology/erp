package io.nirahtech.erp.webapp.services.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GoogleIdentityProvider extends AbstractIdentityProvider {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger("");

    public static final IdentityProvider configure(IdentityProviderConfiguration configuration) {
        final AbstractIdentityProvider identityProvider = new GoogleIdentityProvider(configuration);
        identityProvider.initializeOAuth2Server();
        return identityProvider;
    }

    private CompletableFuture<String> futureFirstSSOResponse;

    private GoogleIdentityProvider(final IdentityProviderConfiguration configuration) {
        super(configuration);
    }

    private String retrieveAccessToken(final URI accessTokenEndpoint, final String clientId, final String clientSecret,
            final String code, final String redirectUrl, final String grantType) throws OAuth2Exception {
        String accessToken = null;
        final String requestBody = Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "code", code,
                "redirect_uri", redirectUrl,
                "grant_type", grantType).entrySet().stream()
                .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(accessTokenEndpoint)
                .header("content-type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = null;
        try {
            response = this.httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (Exception exception) {
            throw new OAuth2Exception(exception);
        }
        if (response == null) {
            throw new OAuth2Exception("httpResponse is null");
        }
            String responseBody = response.body();
            Gson gson = new Gson();
            TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {
            };
            Map<String, Object> json = gson.fromJson(responseBody, typeToken.getType());
            if (json.keySet().contains("access_token")) {
                accessToken = json.get("access_token").toString();
            }
        if (accessToken == null) {
            throw new OAuth2Exception("accessToken is null");
        }
        return accessToken;

    }


    private Map<String, Object> retrieveUserInfo(URI userInfoEndpoint, String accessToken) throws OAuth2Exception {
        final HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(userInfoEndpoint)
                .header("Authorization", String.format("Bearer %s", accessToken))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = this.httpClient.send(httpRequest, BodyHandlers.ofString());
        } catch (Exception exception) {
            throw new OAuth2Exception(exception);
        }
        if (response == null) {
            throw new OAuth2Exception("httpResponse is null");
        }
        Gson gson = new Gson();
        TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
        Map<String, Object> json = gson.fromJson(response.body(), typeToken.getType());
        
        if (json == null) {
            throw new OAuth2Exception("json is null");
        }
        return json;
    }

    @Override
    protected void initializeOAuth2Server() {
        LOGGER.info("Initializing local OAuth2 Server...");
        super.oAuth2Server.get("/login/oauth2/code/google", (request, response) -> {
            super.authorizationCode = URLDecoder.decode(request.getParameters().get("code").toString(), StandardCharsets.UTF_8);
            this.futureFirstSSOResponse.complete(super.authorizationCode);
        });
    }

    private String retrieveAuthorizationCode() throws OAuth2Exception {
        this.oAuth2Server.listen(9010);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.configuration.getAuthorizationCodeUri().toString());
        stringBuilder.append("?");
        stringBuilder.append("scope=");
        stringBuilder.append(String.format("%s", "profile"));
        stringBuilder.append("&");
        stringBuilder.append("access_type=online");
        stringBuilder.append("&");
        stringBuilder.append(String.format("redirect_uri=%s", this.configuration.getRedirectUris().get(0)));
        stringBuilder.append("&");
        stringBuilder.append(String.format("response_type=%s", "code"));
        stringBuilder.append("&");
        stringBuilder.append(String.format("client_id=%s", this.configuration.getClientId()));
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(URI.create(stringBuilder.toString()));
            } catch (IOException exception) {
                throw new OAuth2Exception(exception);
            }
        }
        String code;
        try {
            code = this.futureFirstSSOResponse.get();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new OAuth2Exception(exception);
        }
        return code;
    }

    @Override
    public void signIn() throws OAuth2Exception {
        this.executorService.submit(() -> {
            Map<String, Object> userInfo = null;
            this.authorizationCode = this.retrieveAuthorizationCode();
            this.accessToken = this.retrieveAccessToken(
            this.configuration.getAccessTokenUri(), 
            this.configuration.getClientId(), 
            this.configuration.getClientSecret(), 
            super.authorizationCode, 
            this.configuration.getRedirectUris().get(0).toString(), "authorization_code");
            userInfo = this.retrieveUserInfo(this.configuration.getUserInfoUri(), this.accessToken);
            this.user = new DefaultOAuth2User(null, null, userInfo);
            return this.user;
        });

    }

    @Override
    public void signOut() throws OAuth2Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signOut'");
    }

}

