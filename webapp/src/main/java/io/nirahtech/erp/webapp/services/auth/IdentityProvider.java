package io.nirahtech.erp.webapp.services.auth;

import java.util.Optional;

public interface IdentityProvider {
    void signIn() throws OAuth2Exception;
    boolean isAuthenticated();
    Optional<OAuth2User> getUserInfo();
    void signOut() throws OAuth2Exception;
}

