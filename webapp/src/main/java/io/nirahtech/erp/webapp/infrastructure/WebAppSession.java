package io.nirahtech.erp.webapp.infrastructure;

import java.util.Optional;

import io.nirahtech.libraries.oauth2.data.AccessToken;

public final class WebAppSession {
    
    private static final WebAppSession instance = new WebAppSession();

    private AccessToken accessToken = null;

    private WebAppSession() { }

    
    public Optional<AccessToken> getAccessToken() {
        return Optional.ofNullable(accessToken);
    }
    
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    public static WebAppSession getInstance() {
        return instance;
    }
}
