package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public class FinancialTransaction {
    private final UUID id;
    private final LocalDateTime timestamp;
    private final BigDecimal amount;
    private final Currency currency;
    private final BankAccount credit;
    private final BankAccount debtor;

    public FinancialTransaction(UUID id, LocalDateTime timestamp, BigDecimal amount, Currency currency,
                                 BankAccount credit, BankAccount debtor) {
        this.id = id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.currency = currency;
        this.credit = credit;
        this.debtor = debtor;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BankAccount getCredit() {
        return credit;
    }

    public BankAccount getDebtor() {
        return debtor;
    }
}
