package io.nirahtech.erp.bank;

import java.text.ParseException;

public final class BBANFactory {
    private BBANFactory() { }

    public static final BasicBankAccountNumber create(final String bban) throws ParseException {
        return BasicBankAccountNumber.parse(bban);
    }
}
