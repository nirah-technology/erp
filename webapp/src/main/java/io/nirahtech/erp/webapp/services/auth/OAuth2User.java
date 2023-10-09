package io.nirahtech.erp.webapp.services.auth;

import java.util.Map;

public interface OAuth2User {
    String getEmail();
    String getPrincipal();
    Map<String, Object> getUserInfo();
}
