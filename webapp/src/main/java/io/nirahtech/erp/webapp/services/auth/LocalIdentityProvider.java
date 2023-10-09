package io.nirahtech.erp.webapp.services.auth;

public class LocalIdentityProvider extends AbstractIdentityProvider {

    public static final IdentityProvider configure(IdentityProviderConfiguration configuration) {
        final AbstractIdentityProvider identityProvider = new LocalIdentityProvider(configuration);
        identityProvider.initializeOAuth2Server();
        return identityProvider;
    }

    private LocalIdentityProvider(final IdentityProviderConfiguration configuration) {
        super(configuration);
    }

    @Override
    protected void initializeOAuth2Server() {
        
    }
    
    @Override
    public void signIn() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signIn'");
    }

    @Override
    public void signOut() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signOut'");
    }
}

