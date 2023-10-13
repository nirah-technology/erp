package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DatabaseSynchronizer {

    private File readOnlyDatabase;
    private final File writeOnlyDatabase;

    public DatabaseSynchronizer(final File readOnlyDatabase, final File writeOnlyDatabase) {
        this.readOnlyDatabase = readOnlyDatabase;
        this.writeOnlyDatabase = writeOnlyDatabase;
    }

    public final void synchronize() throws IOException {
        final File defaultReadOnlyDatabase = this.readOnlyDatabase;
        final File copyOfTheWriteOnlyDatabase = this.copyWriteOnlyDatabase();
        this.setReadOnlyDatabase(copyOfTheWriteOnlyDatabase);
        Files.delete(this.readOnlyDatabase.toPath());
        final boolean isRenamed = copyOfTheWriteOnlyDatabase.renameTo(defaultReadOnlyDatabase);
        if (!isRenamed) {
            throw new IOException("Copy of the Write Only file not renamed to original Read Only File");
        }
    }

    private final File copyWriteOnlyDatabase() throws IOException {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss");
        String newFileName = String.format("erp-webapp_%s.wodb", LocalDateTime.now().format(dateTimeFormatter));
        return Files.copy(this.writeOnlyDatabase.toPath(), new File(this.writeOnlyDatabase.getParent(), newFileName).toPath(), StandardCopyOption.REPLACE_EXISTING).toFile();
    }

    public final File getReadOnlyDatabase() {
        return readOnlyDatabase;
    }

    public final File getWriteOnlyDatabase() {
        return writeOnlyDatabase;
    }

    private final void setReadOnlyDatabase(final File readOnlyDatabase) {
        this.readOnlyDatabase = readOnlyDatabase;
    }
    

}
