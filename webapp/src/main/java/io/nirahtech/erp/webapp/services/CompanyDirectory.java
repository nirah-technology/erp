package io.nirahtech.erp.webapp.services;

import io.nirahtech.erp.webapp.services.api.CompaniesDirectoryCommand;
import io.nirahtech.erp.webapp.services.api.CompaniesDirectoryQuery;

public sealed interface CompanyDirectory  extends CompaniesDirectoryQuery, CompaniesDirectoryCommand permits DefaultCompanyDirectory {
    
}
