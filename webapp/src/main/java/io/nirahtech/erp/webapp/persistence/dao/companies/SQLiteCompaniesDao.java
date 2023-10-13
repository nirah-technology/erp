package io.nirahtech.erp.webapp.persistence.dao.companies;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.persistence.sqlite.SQLiteHelper;

public final class SQLiteCompaniesDao implements CompaniesDao {


    public SQLiteCompaniesDao() {

    }

    @Override
    public Collection<Company> findAll() {

        try (Connection connection = SQLiteHelper.createSession()) {
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
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
