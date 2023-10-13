package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;

public final class ReadOnlyDatabase extends FileDatabase {

    public ReadOnlyDatabase(File file) {
        super(file);
    }
    
}
