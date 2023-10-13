package io.nirahtech.erp.webapp.services;

import java.util.Objects;

import io.nirahtech.erp.webapp.persistence.dao.companies.CompaniesDao;
import io.nirahtech.erp.webapp.persistence.dao.companies.SQLiteCompaniesDao;
import io.nirahtech.erp.webapp.persistence.repositories.companies.CompaniesRepository;
import io.nirahtech.erp.webapp.persistence.repositories.companies.CompaniesRepositoryImpl;

public final class CompanyDirectoryFactory {
    private CompanyDirectoryFactory() { }

    private static CompanyDirectory companyDirectory = null;
    
    public static final CompanyDirectory getCompanyDirectory() {
        
        if (Objects.isNull(companyDirectory)) {
            final CompaniesDao companiesDao = new SQLiteCompaniesDao();
            final CompaniesRepository companiesRepository = new CompaniesRepositoryImpl(companiesDao);
            companyDirectory = new DefaultCompanyDirectory(companiesRepository);
        }
        return companyDirectory;
    }
}
