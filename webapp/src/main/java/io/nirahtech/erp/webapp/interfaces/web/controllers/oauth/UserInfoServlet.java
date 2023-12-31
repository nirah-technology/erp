package io.nirahtech.erp.webapp.interfaces.web.controllers.oauth;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import io.nirahtech.erp.webapp.infrastructure.WebAppSession;
import io.nirahtech.erp.webapp.infrastructure.security.OAuth2ConfigurationLoader;
import io.nirahtech.libraries.oauth2.OAuth2;
import io.nirahtech.libraries.oauth2.OAuth2Factory;
import io.nirahtech.libraries.oauth2.configuration.OAuth2Configuration;
import io.nirahtech.libraries.oauth2.data.AccessToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/oauth2/user-info")
public class UserInfoServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogManager().getLogger(UserInfoServlet.class.getName());
    
    private final WebAppSession webAppSession = WebAppSession.getInstance();

    private final OAuth2 oAuth2;

    public UserInfoServlet() {
        final OAuth2Configuration configuration = OAuth2ConfigurationLoader.loadResourceFile("oauth2.json");
        this.oAuth2 = OAuth2Factory.create(configuration);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (Objects.isNull(session)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            if (this.webAppSession.getAccessToken().isPresent()) {
                AccessToken accessToken = this.webAppSession.getAccessToken().get();
                Map<String, String> userInfo = this.oAuth2.retrieveUserInfo(accessToken);
                response.getWriter().println(userInfo.toString());
                response.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }
}
