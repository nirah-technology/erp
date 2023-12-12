package io.nirahtech.erp.projects;

import java.util.UUID;

public class TeamIdentifier {
    private final UUID value;

    public TeamIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
