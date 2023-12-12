package io.nirahtech.erp.bank;

import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class Bank implements BankAccountManager, BankCustomerManager {
    private final String name;
    private final BankCode code;
    private final BranchCode branchCode;
    private final Locale country;
    private final Set<BankAccount> accounts;
    private final Set<AccountHolder> customers;
    private final FinancialTransactionExecutor financialTransactionExecutor;

    public Bank(
        String name,
        BankCode code,
        BranchCode branchCode,
        Locale country,
        Set<BankAccount> accounts,
        Set<AccountHolder> customers,
        FinancialTransactionExecutor financialTransactionExecutor
    ) {
        this.name = name;
        this.code = code;
        this.branchCode = branchCode;
        this.country = country;
        this.accounts = accounts;
        this.customers = customers;
        this.financialTransactionExecutor = financialTransactionExecutor;
    }

    public String getName() {
        return name;
    }

    public BankCode getCode() {
        return code;
    }

    public BranchCode getBranchCode() {
        return branchCode;
    }

    public Locale getCountry() {
        return country;
    }

    public Set<BankAccount> getAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    public Set<AccountHolder> getCustomers() {
        return Collections.unmodifiableSet(customers);
    }

    public FinancialTransactionExecutor getFinancialTransactionExecutor() {
        return financialTransactionExecutor;
    }

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
            customer.getBankAccounts().forEach(account -> {
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
