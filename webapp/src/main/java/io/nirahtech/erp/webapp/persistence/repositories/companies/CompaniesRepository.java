package io.nirahtech.erp.webapp.persistence.repositories.companies;

import java.util.UUID;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.webapp.persistence.api.dao.Dao;

public sealed interface CompaniesRepository extends Dao<UUID, Company> permits CompaniesRepositoryImpl {

}
