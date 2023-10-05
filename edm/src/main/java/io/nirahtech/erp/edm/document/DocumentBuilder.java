package io.nirahtech.erp.edm.document;

import java.io.File;
import java.lang.Runtime.Version;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class DocumentBuilder {
    private UUID id;
    private File file;
    private Version version;
    private String owner;
    private Document parent = null;
    private boolean isArchived;
    private LocalDateTime defaultCreationDateTime = LocalDateTime.now();
    private Set<Modification> modifications = new HashSet<>();
    private Set<Document> annexes = new HashSet<>();
    private Set<Tag> tags = new HashSet<>();
    private Set<Comment> comments = new HashSet<>();

    private Set<DocumentReader> readers = new HashSet<>();
    private Set<DocumentWriter> writers = new HashSet<>();

    public final DocumentBuilder id(UUID id) {
        this.id = id;
        return this;
    }
    public final DocumentBuilder file(File file) {
        this.file = file;
        return this;
    }

    public final DocumentBuilder version(Version version) {
        this.version = version;
        return this;
    }

    public final DocumentBuilder owner(String owner) {
        this.owner = owner;
        return this;
    }

    public final DocumentBuilder parent(Document parent) {
        this.parent = parent;
        return this;
    }

    public final DocumentBuilder isArchived(boolean isArchived) {
        this.isArchived = isArchived;
        return this;
    }

    public final Document build() {
        return new ElectronicalDocument(new DocumentIdentifier(id), file, version, owner, parent, isArchived, modifications, annexes, tags, comments, readers, writers);
    }
}
