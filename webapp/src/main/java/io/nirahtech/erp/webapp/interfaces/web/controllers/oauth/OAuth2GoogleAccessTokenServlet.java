package io.nirahtech.erp.webapp.interfaces.web.controllers.oauth;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/login/oauth2/code/google")
public class OAuth2GoogleAccessTokenServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger("");

    public OAuth2GoogleAccessTokenServlet() {

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
