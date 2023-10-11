package io.nirahtech.erp.webapp.infrastructure.security;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;

public final class OAuth2ConfigurationLoader {
    private OAuth2ConfigurationLoader() { }

    public static final OAuth2Configuration loadResourceFile(final String fileName) {
        String projectId = null;
        String clientId = null;
        String clientSecret = null;
        URI authorizationCodeUri = null; 
        URI accessTokenUri = null; 
        URI userInfoUri = null; 
        URI authorizationCodeRedirectUri = null; 
        URI accessTokenRedirectUri = null; 
        URI userInfoRedirectUri = null; 
        List<URI> redirectUris = new ArrayList<>();
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
            Object listOfRedirect = configuration.get("redirect_uris");
            if (listOfRedirect instanceof Collection) {
                ((Collection<String>) listOfRedirect).forEach((redirectUri) -> redirectUris.add(URI.create(redirectUri)));
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
            .build();
    }
}
