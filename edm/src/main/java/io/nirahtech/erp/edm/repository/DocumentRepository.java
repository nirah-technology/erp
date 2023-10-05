package io.nirahtech.erp.edm.repository;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentIdentifier;
import io.nirahtech.erp.edm.document.DocumentReader;
import io.nirahtech.erp.edm.document.DocumentWriter;
import io.nirahtech.erp.edm.document.Tag;

public interface DocumentRepository extends Repository<DocumentIdentifier, Document> {
    Optional<Document> findByFile(final File file);

    Collection<Document> findAllByFileName(final String fileName);
    Collection<Document> findAllByExtension(final String extension);
    Collection<Document> findAllByCreationDateTime(final LocalDateTime dateTime);
    Collection<Document> findAllByModificationDateTime(final LocalDateTime dateTime);
    Collection<Document> findAllByOwner(final String owner);
    Collection<Document> findAllByTag(final Tag tag);

    Collection<Document> findAllByDocumentReader(final DocumentReader reader);
    Collection<Document> findAllByDocumentWriter(final DocumentWriter writer);
    Collection<Document> findAllByArchived(final boolean isArchived);
    Collection<Document> findAllWithValue(final String value);

}
