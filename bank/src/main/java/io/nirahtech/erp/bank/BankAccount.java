package io.nirahtech.erp.bank;

import java.io.Serializable;
import java.math.BigDecimal;

public final class BankAccount implements Serializable {
    private final BankDetails bankDetails;
    private final Bank bank;
    private BigDecimal balance = BigDecimal.valueOf(0.0F);

    public BankAccount(final Bank bank, final BankDetails bankDetails) {
        this.bank = bank;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%s[bankDetails$%s, bank=%s, balance=%s]", this.getClass().getSimpleName(), this.bankDetails, this.bank, this.balance));
        return builder.toString();
    }
}
