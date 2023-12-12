package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.util.UUID;

public class DocumentIdentifier implements Serializable {
    private final UUID value;

    public DocumentIdentifier(UUID value) {
        this.value = value;
    }

    public UUID getValue() {
        return value;
    }
}
