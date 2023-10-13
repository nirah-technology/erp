package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class DatabaseClusterFactory {

    private DatabaseClusterFactory() { }

    public static final DatabaseCluster<WriteOnlyDatabase> createWriteOnlyCluster(final File folder, final String databaseName, final int totalNodes) throws IOException {
        Database master = new WriteOnlyDatabase(new File(folder, String.format("master-%s", databaseName)));
        create(master.getFile());
        Database[] nodes = new Database[totalNodes];
        for (int index = 0; index < totalNodes; index++) {
            nodes[index] = new WriteOnlyDatabase(new File(folder, String.format("node-%s-%s", index+1, databaseName)));
            create(nodes[index].getFile());
        }
        return new DatabaseCluster(master, nodes);
    }
    public static final DatabaseCluster<ReadOnlyDatabase> createReadOnlyCluster(final File folder, final String databaseName, final int totalNodes) throws IOException {
        Database master = new ReadOnlyDatabase(new File(folder, String.format("master-%s", databaseName)));
        create(master.getFile());
        Database[] nodes = new Database[totalNodes];
        for (int index = 0; index < totalNodes; index++) {
            nodes[index] = new ReadOnlyDatabase(new File(folder, String.format("node-%s-%s", index+1, databaseName)));
            create(nodes[index].getFile());
        }
        return new DatabaseCluster(master, nodes);
    }

    private static final void create(File file) throws IOException {
        Files.createDirectories(new File(file.getParent()).toPath());
        Files.createFile(file.toPath());
    }
}
