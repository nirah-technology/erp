package io.nirahtech.erp.webapp.interfaces.web.controllers.oauth;

import java.io.IOException;
import java.util.Objects;

import io.nirahtech.erp.webapp.infrastructure.security.OAuth2ConfigurationLoader;
import io.nirahtech.libraries.oauth2.OAuth2;
import io.nirahtech.libraries.oauth2.OAuth2Factory;
import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import io.nirahtech.libraries.oauth2.data.AccessToken;
import io.nirahtech.libraries.oauth2.data.AuthorizationCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/oauth2/google")
public class OAuth2GoogleCallbackServlet extends HttpServlet {
    private static final String CODE_PARAMETER = "code";
    private final OAuth2 oAuth2;
    
    public OAuth2GoogleCallbackServlet() {
        final OAuth2Configuration configuration = OAuth2ConfigurationLoader.loadResourceFile("oauth2.json");
        this.oAuth2 = OAuth2Factory.create(configuration);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameterMap().containsKey(CODE_PARAMETER)) {
            response.setStatus(400);
            response.getWriter().println(String.format("Missing parameter: %s", CODE_PARAMETER));
        } else {
            final String code = request.getParameter(CODE_PARAMETER);
            AccessToken accessToken = this.oAuth2.generateAccessToken(new AuthorizationCode(code));
            if (Objects.nonNull(accessToken)) {
                System.out.println("OK");
                response.setStatus(200);
                response.getWriter().println("Access Token: " + accessToken.value());
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(accessToken.expiresIn());
                session.setAttribute("token", accessToken.value());
                // request.getRequestDispatcher("/test.html").forward(request, response);
                response.sendRedirect(request.getContextPath() + "/dashboard");


            } else {
                System.out.println("KO");
                response.setStatus(500);
                response.getWriter().println("Access Token is null. Maybe an error ?");
            }
        }
    }
}
