package io.nirahtech.erp.edm.repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentIdentifier;
import io.nirahtech.erp.edm.document.DocumentReader;
import io.nirahtech.erp.edm.document.DocumentWriter;
import io.nirahtech.erp.edm.document.Tag;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonWriter;

public class JSONDocumentRepository implements DocumentRepository {

    private final Map<DocumentIdentifier, Document> cachedDatasource = new HashMap<>();

    private final File jsonFile;

    public JSONDocumentRepository(final File jsonFile) {
        this.jsonFile = jsonFile;
        this.initialize();
        this.loadDataFromJsonFile();
    }

    private final void initialize() {
        if (Objects.nonNull(this.jsonFile)) {
            if (!this.jsonFile.exists()) {
                this.jsonFile.mkdirs();
            }
        }
    }

    private final void syncronizeJsonFileWithCache() {
        try (JsonWriter jsonWriter = Json.createWriter(new FileWriter(this.jsonFile))) {
            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            // Parcours de chaque objet ElectronicalDocument et ajout au tableau JSON
            this.cachedDatasource.values().forEach((document) -> {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                jsonObjectBuilder.add("file_name", document.getFileName());
                jsonObjectBuilder.add("extension", document.getExtension());
                jsonObjectBuilder.add("owner", document.getOwner());
                jsonObjectBuilder.add("creation_date_time", document.getCreationDateTime().toString());
                jsonObjectBuilder.add("modification_date_time", document.getModificationDateTime().toString());
                jsonObjectBuilder.add("is_archived", document.isArchived());
                jsonObjectBuilder.add("is_deleted", document.isDeleted());
    
                jsonArrayBuilder.add(jsonObjectBuilder.build());
            });
            jsonWriter.writeArray(jsonArrayBuilder.build());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private final void loadDataFromJsonFile() {
        try (final FileReader fileReader = new FileReader(this.jsonFile)) {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Optional<Document> findById(DocumentIdentifier id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Collection<Document> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public DocumentIdentifier persist(Document data) {
        // TODO Auto-generated method stub
        this.syncronizeJsonFileWithCache();
        throw new UnsupportedOperationException("Unimplemented method 'persist'");
    }

    @Override
    public void deleteById(DocumentIdentifier id) {
        // TODO Auto-generated method stub
        this.syncronizeJsonFileWithCache();
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(Document data) {
        // TODO Auto-generated method stub
        this.syncronizeJsonFileWithCache();
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Optional<Document> findByFile(File file) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByFile'");
    }

    @Override
    public Collection<Document> findAllByFileName(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByFileName'");
    }

    @Override
    public Collection<Document> findAllByExtension(String extension) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByExtension'");
    }

    @Override
    public Collection<Document> findAllByCreationDateTime(LocalDateTime dateTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByCreationDateTime'");
    }

    @Override
    public Collection<Document> findAllByModificationDateTime(LocalDateTime dateTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByModificationDateTime'");
    }

    @Override
    public Collection<Document> findAllByOwner(String owner) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByOwner'");
    }

    @Override
    public Collection<Document> findAllByTag(Tag tag) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByTag'");
    }

    @Override
    public Collection<Document> findAllByDocumentReader(DocumentReader reader) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByDocumentReader'");
    }

    @Override
    public Collection<Document> findAllByDocumentWriter(DocumentWriter writer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByDocumentWriter'");
    }

    @Override
    public Collection<Document> findAllByArchived(boolean isArchived) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByArchived'");
    }

    @Override
    public Collection<Document> findAllWithValue(String value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllWithValue'");
    }

}
