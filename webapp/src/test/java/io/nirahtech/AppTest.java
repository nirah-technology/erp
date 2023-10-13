package io.nirahtech;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.webapp.persistence.api.database.DatabaseCluster;
import io.nirahtech.erp.webapp.persistence.api.database.DatabaseClusterFactory;
import io.nirahtech.erp.webapp.persistence.api.database.ReadOnlyDatabase;
import io.nirahtech.erp.webapp.persistence.sqlite.SQLiteHelper;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     */
    @Test
    void shouldAnswerWithTrue() throws IOException {
        try {
            SQLiteHelper.createSession();
            DatabaseCluster<ReadOnlyDatabase> cluster = DatabaseClusterFactory.createReadOnlyCluster(new File("databases"), "nicolas.db", 10);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertTrue(true);
    }
}
