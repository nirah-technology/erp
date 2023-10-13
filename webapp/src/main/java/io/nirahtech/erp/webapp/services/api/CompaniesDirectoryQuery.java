package io.nirahtech.erp.webapp.services.api;

import java.util.Optional;
import java.util.Set;

import io.nirahtech.erp.core.Company;
import io.nirahtech.erp.core.Siren;
import io.nirahtech.erp.core.Siret;

public interface CompaniesDirectoryQuery {
    Optional<Company> searchBySiren(final Siren siren);
    Optional<Company> searchBySiret(final Siret siret);
    Set<Company> searchByName(final String name);
    Set<Company> findAll();

}
