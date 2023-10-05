package io.nirahtech.erp.edm.document;

public interface Archivable {
    void archive();
    void delete();
    boolean isDeleted();
    boolean isArchived();
}
