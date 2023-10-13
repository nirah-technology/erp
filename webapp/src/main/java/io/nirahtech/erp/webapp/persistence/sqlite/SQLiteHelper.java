package io.nirahtech.erp.webapp.persistence.sqlite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.nirahtech.erp.webapp.persistence.api.database.DatabaseCluster;
import io.nirahtech.erp.webapp.persistence.api.database.ReadOnlyDatabase;
import io.nirahtech.erp.webapp.persistence.api.database.WriteOnlyDatabase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public final class SQLiteHelper {
    private static final String JDBC_SCHEME = "jdbc:sqlite:%s";

    private SQLiteHelper() { }
    
    public static final Connection createSession() throws SQLException, IOException {
        File databaseFile = new File("db/nirahtech-erp-webapp.db");
        Files.createDirectories(Path.of(databaseFile.getParent()));
        Connection connection = DriverManager.getConnection(String.format(JDBC_SCHEME, databaseFile));

        final DatabaseCluster<WriteOnlyDatabase> writeOnlyDatabaseCluster = new DatabaseCluster<>(null, null);
        final DatabaseCluster<ReadOnlyDatabase> readOnlyDatabaseCluster = new DatabaseCluster<>(null, null);

        writeOnlyDatabaseCluster.master();

        EntityManagerFactory entityManagerFactory = SQLiteEntityManagerFactory.getInstance();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return null;
    }
}
