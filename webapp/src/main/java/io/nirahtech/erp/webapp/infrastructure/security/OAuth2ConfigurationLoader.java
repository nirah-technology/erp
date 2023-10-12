package io.nirahtech.erp.webapp.infrastructure.security;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import io.nirahtech.libraries.oauth2.data.Scope;

public final class OAuth2ConfigurationLoader {
    private OAuth2ConfigurationLoader() { }

    public static final OAuth2Configuration loadResourceFile(final String fileName) {
        String projectId = null;
        String clientId = null;
        String clientSecret = null;
        URI authorizationCodeUri = null; 
        URI authorizationCodeRedirectUri = null; 
        URI accessTokenUri = null; 
        URI accessTokenRedirectUri = null; 
        URI userInfoUri = null; 
        URI userInfoRedirectUri = null; 
        String accessType = null;
        Set<Scope> scopes = new HashSet<>();
        InputStream inputStream = OAuth2ConfigurationLoader.class.getResourceAsStream("/"+fileName);
        if (inputStream != null) {
            String text = null;
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                text = scanner.useDelimiter("\\A").next();
            }
            Gson gson = new Gson();
            TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {};
            Map<String, Object> configuration = gson.fromJson(text, typeToken.getType());
            projectId = configuration.get("project_id").toString();
            clientId = configuration.get("client_id").toString();
            clientSecret = configuration.get("client_secret").toString();
            authorizationCodeUri = URI.create(configuration.get("auth_uri").toString());
            accessTokenUri = URI.create(configuration.get("token_uri").toString());
            userInfoUri = URI.create(configuration.get("user_info_uri").toString());
            authorizationCodeRedirectUri = URI.create(configuration.get("auth_redirect_uri").toString());
            accessTokenRedirectUri = URI.create(configuration.get("token_redirect_uri").toString());
            userInfoRedirectUri = URI.create(configuration.get("user_info_redirect_uri").toString());
            accessType = configuration.get("access_type").toString();
            Object listOfScopes = configuration.get("scopes");
            if (listOfScopes instanceof Collection) {
                ((Collection<String>) listOfScopes).forEach((scope) -> scopes.add(new Scope(scope)));
                // LOGGER.info("Google credentials confgiguration successfully loaded");
            } else {
                // LOGGER.severe(String.format("Fail to process configuration parameter: %s", listOfRedirect));
            }
        } else {
            // LOGGER.severe(String.format("Resource file not found: %s", fileName));
        }
        return new OAuth2Configuration.Builder()
            .projectId(projectId)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .authorizationCodeUri(authorizationCodeUri)
            .authorizationCodeRedirectUri(authorizationCodeRedirectUri)
            .accessTokenUri(accessTokenUri)
            .accessTokenRedirectUri(accessTokenRedirectUri)
            .userInfoUri(userInfoUri)
            .userInfoRedirectUri(userInfoRedirectUri)
            .accessType(accessType)
            .scopes(scopes.toArray(new Scope[scopes.size()]))
            .build();
    }
}
