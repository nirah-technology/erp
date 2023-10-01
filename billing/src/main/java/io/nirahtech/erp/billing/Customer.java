package io.nirahtech.erp.billing;

import io.nirahtech.erp.core.EmailAddress;
import io.nirahtech.erp.core.MailingAddress;
import io.nirahtech.erp.core.PhoneNumber;

public final class Customer extends ContractingParty {

    public Customer(String firstName, String lastName, PhoneNumber phoneNumber,
            EmailAddress emailAddress, MailingAddress mailingAddress) {
        super(firstName, lastName, null, phoneNumber, emailAddress, mailingAddress);
    }

    public Customer(String companyName, PhoneNumber phoneNumber,
            EmailAddress emailAddress, MailingAddress mailingAddress) {
        super(null, null, companyName, phoneNumber, emailAddress, mailingAddress);
    }
    
}
