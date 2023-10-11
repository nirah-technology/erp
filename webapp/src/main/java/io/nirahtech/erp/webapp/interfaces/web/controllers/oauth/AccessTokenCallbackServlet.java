package io.nirahtech.erp.webapp.interfaces.web.controllers.oauth;

import java.io.IOException;

import io.nirahtech.erp.webapp.infrastructure.security.OAuth2ConfigurationLoader;
import io.nirahtech.libraries.oauth2.OAuth2;
import io.nirahtech.libraries.oauth2.OAuth2Factory;
import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/oauth2/token")
public class AccessTokenCallbackServlet extends HttpServlet {
    private final OAuth2 oAuth2;

    public AccessTokenCallbackServlet() {
        final OAuth2Configuration configuration = OAuth2ConfigurationLoader.loadResourceFile("oauth2.json");
        this.oAuth2 = OAuth2Factory.create(configuration);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
