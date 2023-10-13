package io.nirahtech.erp.webapp.interfaces.web.utils;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;

public final class SessionUtil {
    private SessionUtil() { }

    public static final boolean isAuthenticated(HttpServletRequest request) {
        return Objects.nonNull(request) && Objects.nonNull(request.getSession(false));
    }
}
