package io.nirahtech.erp.core;

import java.time.LocalDate;
import java.util.Set;

public record Company (
    String name,
    LocalDate creationDate,
    MailingAddress mailingAddress,
    PhoneNumber phoneNumber,
    EmailAddress emailAddress,
    String siret,
    String siren,
    Set<Employee> employees,
    Set<WorkActivity> activities
) implements Entity {

}
