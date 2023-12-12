package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;
import java.util.Set;

public class AccountHolder {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final Set<BankAccount> bankAccounts;

    public AccountHolder(final String firstName, final String lastName, final LocalDate birthDate, final Set<BankAccount> bankAccounts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.bankAccounts = bankAccounts;
    }

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
        debtor.getBank().getFinancialTransactionExecutor().execute(financialTransaction);
    }
    /**
     * @return the bankAccounts
     */
    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
    /**
     * @return the birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

}
