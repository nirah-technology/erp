package io.nirahtech.erp.webapp.interfaces.web.filters;

import java.io.IOException;
import java.util.Objects;

import io.nirahtech.erp.webapp.interfaces.web.utils.SessionUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"/api/*"})
public final class AuthenticationFilter implements Filter {

    private static final String ACCESS_TOKEN_KEY = "access_token";
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.out.println("Filter starting");
        // allow anonymous access to static resources and the root index.jsp
        if (this.isStaticAsset(httpRequest) ) {
            chain.doFilter(request, response);
            System.out.println("It's static content: OK");
            return;
        }

        // Check if we have a current user in the session
        if (SessionUtil.isAuthenticated(httpRequest)) {
            chain.doFilter(request, response);
            System.out.println("Is authenticated: OK");
            return;
        }

        // Check if we have a an Access Token
        if (this.containsAuthorizationHeader(httpRequest) || this.containsAccessTokenParameter(httpRequest)) {
            chain.doFilter(request, response);
            System.out.println("Is tokenized: OK");
            return;
        }

        
        System.out.println("Filter say not allowed.");
        // no authenticated user found in session
        // redirect to /authn/login
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

    }
    
    private final boolean isStaticAsset(final HttpServletRequest request) {
        final String path = request.getServletPath();
        return path.startsWith("/static/") || path.equals("/favicon.ico");
    }

    private final boolean containsAuthorizationHeader(final HttpServletRequest request) {
        boolean containsValidAccessToken = false;
        final String bearerKey = "Bearer";
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (Objects.nonNull(authorizationHeader) && !authorizationHeader.isEmpty() && authorizationHeader.startsWith(bearerKey)) {
            String accessToken = authorizationHeader.replace(bearerKey, "").trim();
            if (Objects.nonNull(accessToken) && !accessToken.isEmpty()) {
                containsValidAccessToken = true;
            }
        }
        return containsValidAccessToken;
    }

    private final boolean containsAccessTokenParameter(final HttpServletRequest request) {
        boolean containsValidAccessToken = false;
        if (request.getParameterMap().containsKey(ACCESS_TOKEN_KEY)) {
            String accessToken = request.getParameter(ACCESS_TOKEN_KEY);
            if (Objects.nonNull(accessToken) && !accessToken.isEmpty()) {
                containsValidAccessToken = true;
            }
        }
        return containsValidAccessToken;
    }
}
