package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.time.LocalDateTime;

public record Modification(
    DocumentIdentifier documentIdentifier,
    LocalDateTime dateTime,
    String author
) implements Serializable {
    
}
