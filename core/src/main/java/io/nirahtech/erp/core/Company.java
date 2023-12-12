package io.nirahtech.erp.core;

import java.time.LocalDate;
import java.util.Set;

public class Company implements Entity {
    private final String name;
    private final LocalDate creationDate;
    private final MailingAddress mailingAddress;
    private final PhoneNumber phoneNumber;
    private final EmailAddress emailAddress;
    private final Siret siret;
    private final Siren siren;
    private final Set<Employee> employees;
    private final Set<WorkActivity> activities;

    public Company(String name, LocalDate creationDate, MailingAddress mailingAddress, PhoneNumber phoneNumber,
                   EmailAddress emailAddress, Siret siret, Siren siren, Set<Employee> employees,
                   Set<WorkActivity> activities) {
        this.name = name;
        this.creationDate = creationDate;
        this.mailingAddress = mailingAddress;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.siret = siret;
        this.siren = siren;
        this.employees = employees;
        this.activities = activities;
    }

    // Getters for fields
    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public MailingAddress getMailingAddress() {
        return mailingAddress;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public Siret getSiret() {
        return siret;
    }

    public Siren getSiren() {
        return siren;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<WorkActivity> getActivities() {
        return activities;
    }
}
