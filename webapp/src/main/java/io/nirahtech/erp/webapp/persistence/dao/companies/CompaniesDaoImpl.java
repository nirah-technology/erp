package io.nirahtech.erp.webapp.persistence.dao.companies;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.libraries.database.DatabasesClusterFactory;
import io.nirahtech.libraries.database.HybridCluster;

public final class CompaniesDaoImpl implements CompaniesDao {

    private final HybridCluster hybridCulster;

    public CompaniesDaoImpl() {
        this.hybridCulster = DatabasesClusterFactory.createHybridCluster(null, null, 0);
    }

    @Override
    public Collection<Company> findAll() {
        return this.hybridCulster.readOnlyCluster().select(Company.class);
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
