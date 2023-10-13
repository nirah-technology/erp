package io.nirahtech.erp.webapp.interfaces.web.dto;

import io.nirahtech.erp.core.Siren;
import io.nirahtech.erp.core.Siret;

public record CreateCompanyRequestDto (
    String name,
    Siren siren,
    Siret siret
) {
    
}
