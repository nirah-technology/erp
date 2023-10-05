package io.nirahtech.erp.edm.document;

public interface Hierarchical {
    Document getParent();
    void setParent(Document document);
    void removeParent(Document document);
}
