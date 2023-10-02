package io.nirahtech.erp.bank;

import java.io.Serializable;
import java.math.BigDecimal;

public final class BankAccount implements Serializable {
    private final BankDetails bankDetails;
    private final Bank bank;
    private final AccountHolder accountHolder;
    private BigDecimal balance = BigDecimal.valueOf(0.0F);

    public BankAccount(final Bank bank, final AccountHolder accountHolder, final BankDetails bankDetails) {
        this.bank = bank;
        this.accountHolder = accountHolder;
        this.bankDetails = bankDetails;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s[bankDetails$%s, bank=%s, balance=%s, accountHolder=%s]", this.getClass().getSimpleName(), this.bankDetails, this.bank, this.balance, this.accountHolder));
        return builder.toString();
    }
}
