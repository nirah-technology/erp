package io.nirahtech.erp.bank;

import java.time.LocalDate;
import java.util.Set;

public record AccountHolder(
    String firstName,
    String lastName,
    LocalDate birthDate,
    Set<BankAccount> bankAccounts
) implements Credit, Debtor {
    
}
