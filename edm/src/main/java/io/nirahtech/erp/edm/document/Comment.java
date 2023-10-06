package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.time.LocalDateTime;

public record Comment(
    String author,
    LocalDateTime dateTime,
    String text
) implements Serializable {
    
}
