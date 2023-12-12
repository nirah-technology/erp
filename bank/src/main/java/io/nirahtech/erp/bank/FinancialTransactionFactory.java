package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public final class FinancialTransactionFactory {
    private FinancialTransactionFactory() { }

    public static final FinancialTransaction create(BankAccount debtor, BigDecimal amount, BankAccount credit) {
        return FinancialTransactionFactory.create(debtor, amount, Currency.getInstance(((BankAccount)debtor).getBank().getCountry()), credit);
    }
    
    public static final FinancialTransaction create(BankAccount debtor, BigDecimal amount, Currency currency, BankAccount credit) {
        return new FinancialTransaction(UUID.randomUUID(), LocalDateTime.now(), amount, currency, credit, debtor);
    }
}
