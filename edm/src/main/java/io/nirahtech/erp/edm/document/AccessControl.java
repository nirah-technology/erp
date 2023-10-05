package io.nirahtech.erp.edm.document;

public interface AccessControl {
    void allowToRead(DocumentReader... readers);
    void allowToWrite(DocumentWriter... writers);
}
