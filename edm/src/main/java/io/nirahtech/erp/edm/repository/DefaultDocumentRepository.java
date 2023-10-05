package io.nirahtech.erp.edm.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentIdentifier;
import io.nirahtech.erp.edm.document.Tag;

public class DefaultDocumentRepository implements DocumentRepository {

    private final Map<DocumentIdentifier, Document> cachedDatasource = new HashMap<>();

    private final File dataSourceFile;

    public DefaultDocumentRepository(final File dataSourceFile) {
        this.dataSourceFile = dataSourceFile;
        this.initialize();
        this.loadDataFromdataSourceFile();
    }

    private final void initialize() {
        if (Objects.nonNull(this.dataSourceFile)) {
            String dataSourceFileName = this.dataSourceFile.getName();
            File folder = new File(this.dataSourceFile.getAbsolutePath().replace(dataSourceFileName, ""));
            if (!folder.exists()) {
                folder.mkdirs();
            }
            if (!this.dataSourceFile.exists()) {
                try {
                    this.dataSourceFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private final void syncronizedataSourceFileWithCache() {
        try (final ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this.dataSourceFile, true))) {
            outputStream.writeObject(this.cachedDatasource.values());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private final void loadDataFromdataSourceFile() {
        try (final ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream((this.dataSourceFile)))) {
            final List<Document> objectsFromFile = (List<Document>) inputStream.readObject();
            objectsFromFile.forEach(document -> {
                this.cachedDatasource.put(document.getId(), document);
            });
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Optional<Document> findById(DocumentIdentifier id) {
        return Optional.ofNullable(this.cachedDatasource.get(id));
    }

    @Override
    public Collection<Document> findAll() {
        return this.cachedDatasource.values();
    }

    @Override
    public DocumentIdentifier persist(Document data) {
        this.cachedDatasource.put(data.getId(), data);
        this.syncronizedataSourceFileWithCache();
        return data.getId();
    }

    @Override
    public void deleteById(DocumentIdentifier id) {
        this.cachedDatasource.remove(id);
        this.syncronizedataSourceFileWithCache();
    }

    @Override
    public void delete(Document data) {
        this.cachedDatasource.remove(data.getId());
        this.syncronizedataSourceFileWithCache();
    }

    @Override
    public Optional<Document> findByFile(File file) {
        return this.cachedDatasource.values().stream().filter(document -> document.getFile().getAbsolutePath().equalsIgnoreCase(file.getAbsolutePath())).findFirst();
    }

    @Override
    public Collection<Document> findAllByFileName(String fileName) {
        return this.cachedDatasource.values().stream().filter(document -> document.getFileName().equalsIgnoreCase(fileName)).toList();
    }

    @Override
    public Collection<Document> findAllByExtension(String extension) {
        return this.cachedDatasource.values().stream().filter(document -> document.getExtension().equalsIgnoreCase(extension)).toList();
    }

    @Override
    public Collection<Document> findAllByCreationDateTime(LocalDateTime dateTime) {
        return this.cachedDatasource.values().stream().filter(document -> document.getCreationDateTime().equals(dateTime)).toList();
    }

    @Override
    public Collection<Document> findAllByModificationDateTime(LocalDateTime dateTime) {
        return this.cachedDatasource.values().stream().filter(document -> document.getModificationDateTime().equals(dateTime)).toList();
    }

    @Override
    public Collection<Document> findAllByOwner(String owner) {
        return this.cachedDatasource.values().stream().filter(document -> document.getOwner().equals(owner)).toList();
    }

    @Override
    public Collection<Document> findAllByTag(Tag tag) {
        return this.cachedDatasource.values().stream().filter(document -> document.getTags().contains(tag)).toList();
    }

    @Override
    public Collection<Document> findAllByArchived(boolean isArchived) {
        return this.cachedDatasource.values().stream().filter(document -> document.isArchived() == isArchived).toList();
    }

    @Override
    public Collection<Document> findAllWithValue(String value) {
        return this.cachedDatasource.values().stream().filter(document -> document.search(value)).toList();
    }

}
