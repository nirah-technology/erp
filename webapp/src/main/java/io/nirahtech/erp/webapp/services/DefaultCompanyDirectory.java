package io.nirahtech.erp.webapp.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.core.Siren;
import io.nirahtech.erp.core.Siret;
import io.nirahtech.erp.webapp.persistence.repositories.companies.CompaniesRepository;

final class DefaultCompanyDirectory implements CompanyDirectory {

    private final CompaniesRepository companiesRepository;

    DefaultCompanyDirectory(final CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @Override
    public Optional<Company> searchBySiren(Siren siren) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchBySiren'");
    }

    @Override
    public Optional<Company> searchBySiret(Siret siret) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchBySiret'");
    }

    @Override
    public Set<Company> searchByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByName'");
    }

    @Override
    public Set<Company> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Company create(String name, Siret siret, Siren siren) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public Company update(Company company) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Company delete(Company company) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }


}
