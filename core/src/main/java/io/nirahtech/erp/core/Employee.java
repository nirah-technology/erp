package io.nirahtech.erp.core;

import java.time.LocalDate;

public class Employee extends Human {
    private final PhoneNumber phoneNumber;
    private final EmailAddress emailAddress;
    private final MailingAddress mailingAddress;
    private String jobTitle;
    private final LocalDate hiringDate;

    public Employee(
            final String firstName, 
            final String lastName, 
            final LocalDate birthDate, 
            final Gender gender,
            final PhoneNumber phoneNumber,
            final EmailAddress emailAddress,
            final MailingAddress mailingAddress,
            final String jobTitle,
            final LocalDate hiringDate
            ) {
        super(firstName, lastName, birthDate, gender);
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.mailingAddress = mailingAddress;
        this.jobTitle = jobTitle;
        this.hiringDate = hiringDate;
    }

    public final EmailAddress getEmailAddress() {
        return this.emailAddress;
    }
    public final MailingAddress getMailingAddress() {
        return this.mailingAddress;
    }
    public final PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }
    public final String getJobTitle() {
        return this.jobTitle;
    }
    public final LocalDate getHiringDate() {
        return this.hiringDate;
    }
}
