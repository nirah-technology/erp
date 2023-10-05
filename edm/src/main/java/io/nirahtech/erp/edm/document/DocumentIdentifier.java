package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.util.UUID;

public record DocumentIdentifier(UUID value) implements Serializable {
    
}
