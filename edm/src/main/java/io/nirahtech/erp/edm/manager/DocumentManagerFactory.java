package io.nirahtech.erp.edm.manager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class DocumentManagerFactory {

    private static DocumentManagerFactory instance = null;
    private final Map<File, DocumentManager> managers = new HashMap<>(); 

    private DocumentManagerFactory() { }

    public static DocumentManagerFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DocumentManagerFactory();
        }
        return instance;
    }
    
    public final DocumentManager create(final File storageFolder) {
        this.managers.putIfAbsent(storageFolder, new DocumentManager(storageFolder));
        return this.managers.get(storageFolder);
    }

}
