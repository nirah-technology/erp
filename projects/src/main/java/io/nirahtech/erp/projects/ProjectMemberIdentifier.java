package io.nirahtech.erp.projects;

import java.util.UUID;

public class ProjectMemberIdentifier {
    private final UUID value;

    public ProjectMemberIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
