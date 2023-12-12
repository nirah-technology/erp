package io.nirahtech.erp.core;

public final class EmailAddress {
    private final String username;
    private final String domain;

    public EmailAddress(String username, String domain) {
        this.username = username;
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }

}
