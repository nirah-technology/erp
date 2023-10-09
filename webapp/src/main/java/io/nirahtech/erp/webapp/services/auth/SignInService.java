package io.nirahtech.erp.webapp.services.auth;

public interface SignInService {
    AccessToken signIn(final String username, final String password);
}
