package io.nirahtech.erp.edm.document;

import java.util.Set;

public interface Annexable {
    Set<Document> getAnnexes();
    void addAnnexes(Document... annexe);
    void removeAnnexes(Document... annexe);
}
