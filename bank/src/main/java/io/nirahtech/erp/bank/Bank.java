package io.nirahtech.erp.bank;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public final record Bank(
    String name,
    BankCode code,
    BranchCode branchCode,
    Locale country,
    Set<BankAccount> accounts,
    Set<AccountHolder> customers
) implements BankAccountManager, BankCustomerManager {

    @Override
    public void registerNewCustomer(AccountHolder customer) {
        if (Objects.nonNull(customer)) {
            this.customers.add(customer);
        }
    }

    @Override
    public void disaffiliateCustomer(AccountHolder customer) {
        if (Objects.nonNull(customer)) {
            this.customers.remove(customer);
            customer.bankAccounts().forEach(account -> {
                this.closeBankAccount(account);
            });
        }

    }

    @Override
    public Collection<AccountHolder> listCustomers() {
        return Collections.unmodifiableCollection(this.customers);
    }

    @Override
    public BankAccount openNewBankAccount(final BankAccountType accountType, final AccountHolder customer) {
        BankAccount account = BankAccountFactory.create(accountType, this, branchCode, customer);
        this.accounts.add(account);
        return account;
    }

    @Override
    public void closeBankAccount(BankAccount bankAccount) {
        if (Objects.nonNull(bankAccount)) {
            bankAccount.close();
        }
    }

    @Override
    public Collection<BankAccount> listBankAccounts() {
        return Collections.unmodifiableCollection(this.accounts);
    }
    
}
