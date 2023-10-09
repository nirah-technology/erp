package io.nirahtech.erp.webapp.interfaces.web.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    static final String USER_SESSION_KEY = null;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getServletPath();

        // allow anonymous access to static resources and anything under /authn/ and the root index.jsp
        if (this.isStaticAsset(httpRequest) || path.startsWith("/authn/") ) {
            chain.doFilter(request, response);
            return;
        }

        // check if we have a current user in the session
        if (isAuthenticated(httpRequest)) {
            chain.doFilter(request, response);
            return;
        }

        // no authenticated user found in session
        // redirect to /authn/login
        httpResponse.sendRedirect("/authn/login");

    }
    private final boolean isAuthenticated(final HttpServletRequest request) {
        return request.getSession(false) != null 
            && request.getSession().getAttribute(USER_SESSION_KEY) != null;    
    }

    private final boolean isStaticAsset(final HttpServletRequest request) {
        final String path = request.getServletPath();
        return path.startsWith("/static/") || path.equals("/favicon.ico");
    }
}
