package io.nirahtech.erp.bank;

import java.text.ParseException;

public final class BICFactory {
    private BICFactory() { }

    public static final BankIdentifierCode create(final String bic) throws ParseException {
        return BankIdentifierCode.parse(bic);
    }
}
