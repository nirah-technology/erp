package io.nirahtech.erp.edm.document;

import java.io.Serializable;

public class Tag implements Serializable {
    private final String value;

    public Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
