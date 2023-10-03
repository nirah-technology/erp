package io.nirahtech.erp.bank;

import java.util.Collection;

public interface BankAccountManager {
    BankAccount openNewBankAccount(final BankAccountType accountType, final AccountHolder customer);
    void closeBankAccount(final BankAccount bankAccount); 
    Collection<BankAccount> listBankAccounts();
}
