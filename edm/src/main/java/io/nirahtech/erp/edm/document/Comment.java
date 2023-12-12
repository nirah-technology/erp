package io.nirahtech.erp.edm.document;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {
    private final String author;
    private final LocalDateTime dateTime;
    private final String text;

    public Comment(String author, LocalDateTime dateTime, String text) {
        this.author = author;
        this.dateTime = dateTime;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getText() {
        return text;
    }
}
