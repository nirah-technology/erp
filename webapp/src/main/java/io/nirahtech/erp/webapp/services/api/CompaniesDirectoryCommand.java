package io.nirahtech.erp.webapp.services.api;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.core.Siren;
import io.nirahtech.erp.core.Siret;

public interface CompaniesDirectoryCommand {
    Company create(final String name, final Siret siret, final Siren siren);
    Company update(final Company company);
    Company delete(final Company company);
}
