package io.nirahtech.erp.webapp.interfaces.web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.nirahtech.erp.webapp.services.auth.GoogleIDPConfiguration;
import io.nirahtech.erp.webapp.services.auth.IdentityProviderConfiguration;
import io.nirahtech.erp.webapp.services.auth.IdentityProviderFactory;
import io.nirahtech.erp.webapp.services.auth.IdentityProviders;
import io.nirahtech.erp.webapp.services.auth.OAuth2Exception;
import io.nirahtech.erp.webapp.services.auth.OAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger("");
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/login.jsp");
    }

    private final GoogleIDPConfiguration loadGoogleConfigurationFile() {
        String projectId = null;
        String clientId = null;
        String clientSecret = null;
        URI authorizationCodeUri = null; 
        URI accessTokenUri = null; 
        URI userInfoUri = null; 
        List<URI> redirectUris = new ArrayList<>();
        InputStream inputStream = LoginServlet.class.getResourceAsStream("/google-credentials.json");
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
            Object listOfRedirect = configuration.get("redirect_uris");
            if (listOfRedirect instanceof Collection) {
                ((Collection<String>) listOfRedirect).forEach((redirectUri) -> redirectUris.add(URI.create(redirectUri)));
                LOGGER.info("Google credentials confgiguration successfully loaded");
            } else {
                LOGGER.error("Fail to process configuration parameter: %s", listOfRedirect);
            }
        } else {
            LOGGER.error("Resource file not found: %s", "google-credentials.json");
        }
        return new GoogleIDPConfiguration.Builder()
            .projectId(projectId)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .accessTokenUri(accessTokenUri)
            .redirectUri(redirectUris)
            .userInfoUri(userInfoUri)
            .authorizationCodeUri(authorizationCodeUri)
            .build();
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        GoogleIDPConfiguration configuration = this.loadGoogleConfigurationFile();
                this.identityProvider = IdentityProviderFactory.create(
                        IdentityProviders.GOOGLE,
                        (new IdentityProviderConfiguration.Builder())
                            .accessTokenUri(configuration.getAccessTokenUri())
                            .authorizationCodeUri(configuration.getAuthorizationCodeUri())
                            .userInfoUri(configuration.getUserInfoUri())
                            .clientId(configuration.getClientId())
                            .clientSecret(configuration.getClientSecret())
                            .projectId(configuration.getProjectId())
                            .userInfoUri(configuration.getUserInfoUri())
                            .redirectUri(configuration.getRedirectUris())
                            .build());
                OAuth2User user = null;
                try {
                    this.identityProvider.signIn();
                } catch (OAuth2Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    LOGGER.error("Google authentication failed");
                }



        response.getWriter().write("OK");
    }
}
