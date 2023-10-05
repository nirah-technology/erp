package io.nirahtech.erp.edm.document;

import java.io.File;
import java.io.IOException;
import java.lang.Runtime.Version;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class ElectronicalDocument implements Document {

    private final File file;
    private final Version version;
    private final String owner;
    private Document parent = null;
    private boolean isArchived;
    private LocalDateTime defaultCreationDateTime = LocalDateTime.now();
    private final Set<Modification> modifications = new HashSet<>();
    private final Set<Document> annexes = new HashSet<>();
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Comment> comments = new HashSet<>();

    private final Set<DocumentReader> readers = new HashSet<>();
    private final Set<DocumentWriter> writers = new HashSet<>();


    public ElectronicalDocument(
        final File file,
        final Version version,
        final String owner,
        final Document parent,
        final boolean isArchived,
        final Set<Modification> modifications,
        final Set<Document> annexes,
        final Set<Tag> tags,
        final Set<Comment> comments,
        final Set<DocumentReader> readers,
        final Set<DocumentWriter> writers
    ) {
        this.file = file;
        this.version = version;
        this.owner = owner;
        this.parent = parent;
        this.isArchived = isArchived;
        this.modifications.addAll(modifications);
        this.annexes.addAll(annexes);
        this.tags.addAll(tags);
        this.comments.addAll(comments);
        this.readers.addAll(readers);
        this.writers.addAll(writers);
    }


    @Override
    public Set<Tag> getTags() {
        return this.tags;
    }

    @Override
    public void addTags(Tag... tags) {
        if (Objects.nonNull(tags)) {
            this.tags.addAll(Set.of(tags));
        }
    }

    @Override
    public void deleteTags(Tag... tags) {
        if (Objects.nonNull(tags)) {
            this.tags.removeAll(Set.of(tags));
        }
    }

    @Override
    public Set<Comment> getComments() {
        return this.comments;
    }

    @Override
    public void addComments(Comment... comments) {
        if (Objects.nonNull(comments)) {
            this.comments.addAll(Set.of(comments));
        }
    }

    @Override
    public void deleteComments(Comment... comments) {
        if (Objects.nonNull(comments)) {
            this.comments.removeAll(Set.of(comments));
        }
    }

    @Override
    public Set<Document> getAnnexes() {
        return this.annexes;
    }

    @Override
    public void addAnnexes(Document... annexes) {
        if (Objects.nonNull(annexes)) {
            this.annexes.addAll(Set.of(annexes));
        }
    }

    @Override
    public void removeAnnexes(Document... annexes) {
        if (Objects.nonNull(annexes)) {
            this.annexes.removeAll(Set.of(annexes));
        }
    }

    @Override
    public Document getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Document document) {
        this.parent = document;
    }

    @Override
    public void removeParent(Document document) {
        if (this.parent == document) {
            this.parent = null;
        }
    }

    @Override
    public boolean search(String... data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    public void allowToRead(DocumentReader... readers) {
        if (Objects.nonNull(readers)) {
            this.readers.addAll(Set.of(readers));
        }
    }

    @Override
    public void allowToWrite(DocumentWriter... writers) {
        if (Objects.nonNull(readers)) {
            this.writers.addAll(Set.of(writers));
        }
    }

    @Override
    public void archive() {
        this.file.setReadOnly();
    }

    @Override
    public void delete() {
        this.file.delete();
    }

    @Override
    public Set<Modification> getModifications() {
        return this.modifications;
    }

    @Override
    public void addModifications(Modification... modifications) {
        if (Objects.nonNull(modifications)) {
            this.modifications.addAll(Set.of(modifications));
        }
    }

    @Override
    public String getFileName() {
        return this.file.getName();
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public String getExtension() {
        return this.getFileName().split("\\.")[1];
    }

    @Override
    public LocalDateTime getCreationDateTime() {
        LocalDateTime localDateTime;
        try {
            BasicFileAttributes attr = Files.readAttributes(this.file.toPath(), BasicFileAttributes.class);
            localDateTime = LocalDateTime.ofInstant(attr.creationTime().toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            localDateTime = this.defaultCreationDateTime;
        }
        return localDateTime;
    }

    @Override
    public LocalDateTime getModificationDateTime() {
        LocalDateTime localDateTime;
        try {
            BasicFileAttributes attr = Files.readAttributes(this.file.toPath(), BasicFileAttributes.class);
            localDateTime = LocalDateTime.ofInstant(attr.lastModifiedTime().toInstant(), ZoneId.systemDefault());
        } catch (IOException e) {
            localDateTime = this.defaultCreationDateTime;
        }
        return localDateTime;
    }

    @Override
    public String getOwner() {
        return this.owner;
    }

    @Override
    public Version getVersion() {
        return this.version;
    }
    @Override
    public boolean isArchived() {
        return this.isArchived;
    }
    @Override
    public boolean isDeleted() {
        return this.file.exists();
    }
    @Override
    public boolean searchInCommentsOnly(String... data) {
        return false;
    }
    @Override
    public boolean searchInMetadatasOnly(String... data) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean searchInTagsOnly(String... data) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean searchInfileOnly(String... data) {
        // TODO Auto-generated method stub
        return false;
    }
}
