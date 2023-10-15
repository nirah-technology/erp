package io.nirahtech.erp.webapp.persistence.dao.companies;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.persistence.api.database.DatabaseCluster;
import io.nirahtech.erp.webapp.persistence.api.database.DatabaseClusterFactory;
import io.nirahtech.erp.webapp.persistence.api.database.ReadOnlyDatabase;
import io.nirahtech.erp.webapp.persistence.api.database.WriteOnlyDatabase;

public final class SQLiteCompaniesDao implements CompaniesDao {

    private final DatabaseCluster<ReadOnlyDatabase> readOnlyDabtaseCulster;
    private final DatabaseCluster<WriteOnlyDatabase> writeOnlyDabtaseCulster;

    public SQLiteCompaniesDao() {
        try {
            this.readOnlyDabtaseCulster = DatabaseClusterFactory.createReadOnlyCluster(null, null, 0);
            this.writeOnlyDabtaseCulster = DatabaseClusterFactory.createWriteOnlyCluster(null, null, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Collection<Company> findAll() {
        List<Company> companie = new ArrayList<>();
        try {
            final ResultSet resultSet = this.readOnlyDabtaseCulster.executeQuery("SELECT * FROM Company;");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return companie;
    }

    @Override
    public Optional<Company> findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public UUID persist(Company data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'persist'");
    }

    @Override
    public Company update(UUID id, Company data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Company data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
