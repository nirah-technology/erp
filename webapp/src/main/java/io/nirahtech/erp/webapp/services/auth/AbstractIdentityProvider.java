package io.nirahtech.erp.webapp.services.auth;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class AbstractIdentityProvider implements IdentityProvider {
    protected final IdentityProviderConfiguration configuration; 
    protected OAuth2User user = null;

    // protected static HttpServer oAuth2Server;

    protected String accessToken = null;
    protected String authorizationCode = null;
    protected final HttpClient httpClient;

    protected final ExecutorService executorService;


    protected AbstractIdentityProvider(final IdentityProviderConfiguration configuration) {
        this.configuration = configuration;
        this.httpClient = HttpClient.newHttpClient();
        // if (AbstractIdentityProvider.oAuth2Server == null) {
        //     AbstractIdentityProvider.oAuth2Server = new HttpServer();
        //     AbstractIdentityProvider.oAuth2Server.listen(9010);
        // }
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public boolean isAuthenticated() {
        return this.user != null;
    }

    @Override
    public Optional<OAuth2User> getUserInfo() {
        return Optional.ofNullable(this.user);
    }

    protected abstract void initializeOAuth2Server();

}

