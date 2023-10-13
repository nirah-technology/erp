package io.nirahtech.erp.webapp.persistence.repositories.companies;

import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.persistence.dao.companies.CompaniesDao;
import io.nirahtech.erp.webapp.persistence.repositories.CachedRepository;

public final class CompaniesRepositoryImpl extends CachedRepository<UUID, Company> implements CompaniesRepository {

    public CompaniesRepositoryImpl(final CompaniesDao companiesDao) {
        super(companiesDao);
    }

}
