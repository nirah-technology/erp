package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Modification implements Serializable {
    private final DocumentIdentifier documentIdentifier;
    private final LocalDateTime dateTime;
    private final String author;

    public Modification(DocumentIdentifier documentIdentifier, LocalDateTime dateTime, String author) {
        this.documentIdentifier = documentIdentifier;
        this.dateTime = dateTime;
        this.author = author;
    }

    public DocumentIdentifier getDocumentIdentifier() {
        return documentIdentifier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getAuthor() {
        return author;
    }
}
