package io.nirahtech.erp.webapp.persistence.api.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlQuery {
    ResultSet executeQuery(String sqlRequest) throws SQLException;
}
