package io.nirahtech.erp.webapp.services.auth;

public class OAuth2Exception extends Exception {

    public OAuth2Exception(Exception exception) {
        super(exception);
    }

    public OAuth2Exception(String message) {
        super(message);
    }
    
}
