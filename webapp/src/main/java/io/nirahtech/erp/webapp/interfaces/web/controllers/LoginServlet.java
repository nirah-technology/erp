package io.nirahtech.erp.webapp.interfaces.web.controllers;

import java.io.IOException;

import io.nirahtech.erp.webapp.infrastructure.security.OauthConfiguration;
import io.nirahtech.libraries.sso.providers.IdentityProvider;
import io.nirahtech.libraries.sso.providers.google.GoogleIdentityProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final IdentityProvider identityProvider;

    public LoginServlet() {
        this.identityProvider = GoogleIdentityProvider.configure(OauthConfiguration.loadGoogleConfiguration());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(this.identityProvider.getAuthorizationCodeUri().toString());
    }


}
