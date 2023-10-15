package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;

abstract sealed class FileDatabase implements Database permits ReadOnlyDatabase, WriteOnlyDatabase {
    private final File file;

    protected FileDatabase(final File file) {
        this.file = file;
    }
    @Override
    public File getFile() {
        return this.file;
    }
    
}
