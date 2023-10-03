package io.nirahtech.erp.bank;

import java.io.Serializable;
import java.math.BigDecimal;

public final class BankAccount implements Serializable {
    private final BankDetails bankDetails;
    private final BankAccountType accountType;
    private final Bank bank;
    private final AccountHolder accountHolder;
    private BigDecimal balance = BigDecimal.valueOf(0.0F);

    public BankAccount(final BankAccountType accountType, final Bank bank, final AccountHolder accountHolder, final BankDetails bankDetails) {
        this.bank = bank;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.bankDetails = bankDetails;
    }
    public BankAccountType getAccountType() {
        return accountType;
    }

    public final BigDecimal getBalance() {
        return this.balance;
    }
    public final Bank getBank() {
        return this.bank;
    }
    public final BankDetails getBankDetails() {
        return this.bankDetails;
    }

    public final AccountHolder getAccountHolder() {
        return this.accountHolder;
    }

    public final void close() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s[bankDetails$%s, bank=%s, balance=%s, accountHolder=%s]", this.getClass().getSimpleName(), this.bankDetails, this.bank, this.balance, this.accountHolder));
        return builder.toString();
    }
}
