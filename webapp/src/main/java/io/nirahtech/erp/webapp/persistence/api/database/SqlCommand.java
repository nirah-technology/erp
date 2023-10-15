package io.nirahtech.erp.webapp.persistence.api.database;

import java.sql.SQLException;

public interface SqlCommand {
    int executeUpdate(String sqlRequest) throws SQLException;
}
