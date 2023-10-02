package io.nirahtech.erp.bank;

import java.text.ParseException;

public final class IBANFactory {
    private IBANFactory() { }

    public static final InternationalBankAccountNumber create(final String iban) throws ParseException {
        return InternationalBankAccountNumber.parse(iban);
    }
}
