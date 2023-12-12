package io.nirahtech.erp.projects;

import java.util.UUID;

public class WorkPackageIdentifier {
    private final UUID value;

    public WorkPackageIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
