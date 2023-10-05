package io.nirahtech.erp.edm.document;

import java.util.Set;

public interface Metadatable {
    
    Set<Metadata> getMetadatas();
    void addMetadatas(Metadata... metadatas);
    void removeMetadatas(Metadata... metadatas);

}
