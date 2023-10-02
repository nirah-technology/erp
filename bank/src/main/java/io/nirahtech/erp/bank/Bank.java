package io.nirahtech.erp.bank;

import java.util.Locale;
import java.util.Set;

public record Bank(
    String name,
    BankCode code,
    Locale country,
    Set<BankAccount> accounts
) {
    
}
