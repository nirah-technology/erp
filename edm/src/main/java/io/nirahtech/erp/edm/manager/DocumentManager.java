package io.nirahtech.erp.edm.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentFactory;
import io.nirahtech.erp.edm.document.DocumentIdentifier;
import io.nirahtech.erp.edm.repository.DefaultDocumentRepository;
import io.nirahtech.erp.edm.repository.DocumentRepository;

public final class DocumentManager {

    private final File storageFolder;
    private final DocumentRepository repository;

    DocumentManager(final File storageFolder) { 
        this.storageFolder = storageFolder;
        this.repository = new DefaultDocumentRepository(new File(this.storageFolder, ".index.db"));
    }

    public final File getStorageFolder() {
        return this.storageFolder;
    }

    public Document createDocument(final String fileName, final String owner) {
        final Document createdDocument = DocumentFactory.create(fileName).owner(owner).build();
        this.repository.persist(createdDocument);
        return createdDocument;
    }

    public void deleteDocument(final Document documentToDelete) {
        this.repository.delete(documentToDelete);
    }

    public void archiveDocument(final Document documentToArchive) {
        documentToArchive.archive();
        DocumentIdentifier id = documentToArchive.getId();
        Optional<Document> storedDocument = this.repository.findById(id);
        if (storedDocument.isPresent()) {
            Document document = storedDocument.get();
            document.archive();
            this.repository.persist(document);
        }
    }

    public void restoreDocument(Document documentToRestore) {
        documentToRestore.restore();
        DocumentIdentifier id = documentToRestore.getId();
        Optional<Document> storedDocument = this.repository.findById(id);
        if (storedDocument.isPresent()) {
            Document document = storedDocument.get();
            document.archive();
            this.repository.persist(document);
        }
    }
    public Collection<Document> getAllDocuments() {
        return this.repository.findAll();
    }

    public  Optional<Document> findDocumentByID(final DocumentIdentifier id) {
        return this.repository.findById(id);
    }

    public void upload(File fileToUpload) {
        if (Objects.nonNull(fileToUpload)) {
            if (fileToUpload.exists() && fileToUpload.isFile()) {
                final File newFile = new File(this.storageFolder, fileToUpload.getName());
                try {
                    Files.copy(fileToUpload.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}
