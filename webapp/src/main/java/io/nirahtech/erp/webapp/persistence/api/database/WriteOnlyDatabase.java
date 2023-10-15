package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class WriteOnlyDatabase extends FileDatabase implements SqlCommand {

    public WriteOnlyDatabase(File file) {
        super(file);
    }
    
    @Override
    public int executeUpdate(String sqlRequest) throws SQLException {
        final Connection connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", super.getFile().getAbsolutePath()));
        final Statement statement = connection.createStatement();
        return statement.executeUpdate(sqlRequest);
    }
}
