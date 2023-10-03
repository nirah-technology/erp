package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;
import java.util.Set;

public record AccountHolder(
    String firstName,
    String lastName,
    LocalDate birthDate,
    Set<BankAccount> bankAccounts
) {


    public void debt(BigDecimal amount, Currency currency, Debtor debtor, Credit credit) {
        if (Objects.isNull(amount) || Objects.isNull(currency) || Objects.isNull(debtor) ||Objects.isNull(credit)) {
            throw new RuntimeException();
        }

        if (this.bankAccounts.contains(debtor)) {
            debtor.debt(amount, currency);
        }
    }

    public void credit(BigDecimal amount, Currency currency, Debtor debtor, Credit credit) {
        if (Objects.isNull(amount) || Objects.isNull(currency) || Objects.isNull(debtor) ||Objects.isNull(credit)) {
            throw new RuntimeException();
        }

        if (this.bankAccounts.contains(credit)) {
            credit.credit(amount, currency);
        }
    }

    public void transferMoney(BankAccount debtor, BankAccount credit, BigDecimal amount, Currency currency) {
        final FinancialTransaction financialTransaction = FinancialTransactionFactory.create(debtor, amount, credit);
        debtor.getBank().financialTransactionExecutor().execute(financialTransaction);
    }

}
