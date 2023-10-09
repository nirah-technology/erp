package io.nirahtech.erp.webapp.services.auth;

import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GoogleProfileMessage {
    private static final Logger LOGGER = LogManager.getLogManager().getLogger("null");

    private enum ProfileProperty {
        SUB,
        GIVEN_NAME,
        NAME,
        FAMILY_NAME,
        EMAIL,
        PICTURE,
        TOKEN;
    }

    public static final boolean isGoogleProfile(final Map<String, Object> json) {
        boolean isValidProfile = true;
        for (ProfileProperty property : ProfileProperty.values()) {
            if (!json.containsKey(property.name().toLowerCase())) {
                LOGGER.info(String.format("Google property profile not found: %s", property.name().toLowerCase()));
                isValidProfile = false;
                break;
            }
        }
        LOGGER.info(String.format("Is Google profile: %s", isValidProfile));
        return isValidProfile;
    }
    
    public static final void parse(final Map<String, Object> json) {
        // RealBiker biker = new RealBiker();
        // biker.setId(String.valueOf(json.get(ProfileProperty.TOKEN.name().toLowerCase())));
        // biker.setFirstName(String.valueOf(json.get(ProfileProperty.GIVEN_NAME.name().toLowerCase())));
        // biker.setLastName(String.valueOf(json.get(ProfileProperty.FAMILY_NAME.name().toLowerCase())));
        // biker.setNickName(String.valueOf(json.get(ProfileProperty.NAME.name().toLowerCase())));
        // biker.setEmail(new RealEmail(String.valueOf(json.get(ProfileProperty.EMAIL.name().toLowerCase()))));
        // return biker;
    }

}