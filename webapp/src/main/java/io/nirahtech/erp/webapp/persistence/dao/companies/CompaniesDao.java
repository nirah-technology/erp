package io.nirahtech.erp.webapp.persistence.dao.companies;

import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.persistence.api.dao.Dao;

public interface CompaniesDao extends Dao<UUID, Company> {
    
}
