package io.nirahtech.erp.edm.document;

import java.io.Serializable;

public class Metadata implements Serializable {
    private final String name;
    private final Object value;

    public Metadata(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
