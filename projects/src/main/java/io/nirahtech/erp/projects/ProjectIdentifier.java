package io.nirahtech.erp.projects;

import java.util.UUID;

public class ProjectIdentifier {
    private final UUID value;

    public ProjectIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
