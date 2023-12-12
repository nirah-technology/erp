package io.nirahtech.erp.projects;

import java.util.UUID;

public class MilestoneIdentifier {
    private final UUID value;

    public MilestoneIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
