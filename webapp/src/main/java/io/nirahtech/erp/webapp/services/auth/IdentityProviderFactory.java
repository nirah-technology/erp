package io.nirahtech.erp.webapp.services.auth;

public final class IdentityProviderFactory {
    private IdentityProviderFactory() {
    }

    public static final IdentityProvider create(final IdentityProviders provider, final IdentityProviderConfiguration configuration) {
        IdentityProvider identityProvider;
        if (provider != null) {
            switch (provider) {
                case GOOGLE:
                    identityProvider = GoogleIdentityProvider.configure(configuration);
                    break;
                default:
                    identityProvider = LocalIdentityProvider.configure(configuration);
                    break;
            }
        } else {
            identityProvider = LocalIdentityProvider.configure(configuration);
        }
        return identityProvider;
    }
}

