package io.nirahtech.erp.webapp.services.auth;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class IdentityProviderConfiguration {
    private final String projectId;
    private final String clientId;
    private final String clientSecret;
    private final URI authorizationCodeUri; 
    private final URI accessTokenUri; 
    private final URI userInfoUri; 
    private final List<URI> redirectUris;

    IdentityProviderConfiguration(String projectId, String clientId, String clientSecret,
            URI authorizationCodeUri, URI accessTokenUri, URI userInfoUri, List<URI> redirectUris) {
        this.projectId = projectId;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCodeUri = authorizationCodeUri;
        this.accessTokenUri = accessTokenUri;
        this.userInfoUri = userInfoUri;
        this.redirectUris = redirectUris;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public URI getAuthorizationCodeUri() {
        return authorizationCodeUri;
    }

    public URI getAccessTokenUri() {
        return accessTokenUri;
    }
    public URI getUserInfoUri() {
        return userInfoUri;
    }

    public List<URI> getRedirectUris() {
        return redirectUris;
    }

    public static final class Builder {
        private String projectId;
        private String clientId;
        private String clientSecret;
        private URI authorizationCodeUri; 
        private URI accessTokenUri; 
        private URI userInfoUri; 
        private List<URI> redirectUris = new ArrayList<>();

        public Builder projectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder clientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder authorizationCodeUri(URI authorizationCodeUri) {
            this.authorizationCodeUri = authorizationCodeUri;
            return this;
        }

        public Builder accessTokenUri(URI accessTokenUri) {
            this.accessTokenUri = accessTokenUri;
            return this;
        }

        public Builder userInfoUri(URI userInfoUri) {
            this.userInfoUri = userInfoUri;
            return this;
        }

        public Builder redirectUri(List<URI> redirectUri) {
            this.redirectUris.addAll(redirectUri);
            return this;
        }

        public IdentityProviderConfiguration build() {
            return new IdentityProviderConfiguration(projectId, clientId, clientSecret, authorizationCodeUri, accessTokenUri, userInfoUri, redirectUris);
        }
    }
}
