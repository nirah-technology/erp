package io.nirahtech.erp.webapp.interfaces.web.controllers.oauth;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import io.nirahtech.erp.webapp.infrastructure.security.OauthConfiguration;
import io.nirahtech.libraries.sso.providers.IdentityProvider;
import io.nirahtech.libraries.sso.providers.google.GoogleIdentityProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login/oauth2/code/google")
public class OAuth2GoogleAuthorizationCodeServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger("");
    private final IdentityProvider identityProvider;

    public OAuth2GoogleAuthorizationCodeServlet() {
        this.identityProvider = GoogleIdentityProvider.configure(OauthConfiguration.loadGoogleConfiguration());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorizationCode = URLDecoder.decode(request.getParameterMap().get("code")[0], StandardCharsets.UTF_8);
        System.out.println(authorizationCode);
        response.getWriter().println("AuthCode: " + authorizationCode);
        response.setStatus(200);
        // this.futureFirstSSOResponse.complete(super.authorizationCode);
    }
}
