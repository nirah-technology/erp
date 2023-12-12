package io.nirahtech.erp.projects;

import java.util.UUID;

public class TaskIdentifier {
    private final UUID value;

    public TaskIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
