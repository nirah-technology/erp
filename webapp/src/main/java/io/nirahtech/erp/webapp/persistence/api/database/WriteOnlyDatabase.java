package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;

public final class WriteOnlyDatabase extends FileDatabase   {

    public WriteOnlyDatabase(File file) {
        super(file);
    }
    
}
