package io.nirahtech.erp.billing;

import io.nirahtech.erp.core.BankAccount;
import io.nirahtech.erp.core.EmailAddress;
import io.nirahtech.erp.core.MailingAddress;
import io.nirahtech.erp.core.PhoneNumber;

public final class Provider extends ContractingParty {

    private final BankAccount bankAccount;

    public Provider(
            final String firstName, 
            final String lastName, 
            final PhoneNumber phoneNumber,
            final EmailAddress emailAddress, 
            final MailingAddress mailingAddress, 
            final BankAccount bankAccount) {
        super(firstName, lastName, null, phoneNumber, emailAddress, mailingAddress);
        this.bankAccount = bankAccount;
    }

    public Provider(
            final String companyName, 
            final PhoneNumber phoneNumber,
            final EmailAddress emailAddress, 
            final MailingAddress mailingAddress, 
            final BankAccount bankAccount) {
        super(null, null, companyName, phoneNumber, emailAddress, mailingAddress);
        this.bankAccount = bankAccount;
    }

    public final BankAccount getBankAccount() {
        return this.bankAccount;
    }
}
