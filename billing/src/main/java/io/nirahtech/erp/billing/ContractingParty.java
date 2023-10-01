package io.nirahtech.erp.billing;

import io.nirahtech.erp.core.EmailAddress;
import io.nirahtech.erp.core.MailingAddress;
import io.nirahtech.erp.core.PhoneNumber;

public abstract class ContractingParty {
    
    private final String firstName;
    private final String lastName;
    private final String companyName;

    private final PhoneNumber phoneNumber;
    private final EmailAddress emailAddress;
    private final MailingAddress mailingAddress;

    protected ContractingParty(
        final String firstName,
        final String lastName,
        final String companyName,

        final PhoneNumber phoneNumber,
        final EmailAddress emailAddress,
        final MailingAddress mailingAddress
    ) {
        this.companyName = companyName;
        this.firstName = firstName;
        this.lastName = lastName;

        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.mailingAddress = mailingAddress;
    }

    public final String getCompanyName() {
        return this.companyName;
    }
    public final EmailAddress getEmailAddress() {
        return this.emailAddress;
    }
    public final String getFirstName() {
        return this.firstName;
    }
    public final String getLastName() {
        return this.lastName;
    }
    public final MailingAddress getMailingAddress() {
        return this.mailingAddress;
    }
    public final PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }
}
