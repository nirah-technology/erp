package io.nirahtech.erp.bank;

import java.util.HashSet;
import java.util.Locale;

public final class BankFactory {
    private BankFactory() { }

    public static final Bank create(final String name, final BankCode code, final BranchCode branchCode, final Locale country) {
        return new Bank(name, code, branchCode, country, new HashSet<>(), new HashSet<>());
    }

}
