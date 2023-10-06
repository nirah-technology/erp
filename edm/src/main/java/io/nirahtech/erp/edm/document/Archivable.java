package io.nirahtech.erp.edm.document;

public interface Archivable {
    void archive();
    void delete();
    void restore();
    boolean isDeleted();
    boolean isArchived();
}
