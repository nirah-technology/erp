package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class DefaultFinancialTransactionExecutor implements FinancialTransactionExecutor {

    private final Set<FinancialRule> financialRules = new HashSet<>();

    DefaultFinancialTransactionExecutor() {
        this.financialRules.add((transaction) -> Objects.nonNull(transaction));
        this.financialRules.add((transaction) -> !transaction.debtor().equals(transaction.credit()));
        this.financialRules.add((transaction) -> transaction.amount().floatValue() > 0.0F);
        this.financialRules.add((transaction) -> transaction.debtor().getBalance().compareTo(new BigDecimal(0.0F)) == 1 );
        this.financialRules.add((transaction) -> transaction.debtor().getBalance().subtract(transaction.amount()).compareTo(new BigDecimal(0.0F)) >= 0);
    }

    @Override
    public void addFinancialRules(FinancialRule... financialRules) {
        this.financialRules.addAll(Set.of(financialRules));
    }

    @Override
    public void execute(FinancialTransaction transaction) {
        final boolean canBeExecuted = this.financialRules
                .stream()
                .filter(rule -> !rule.isAllowed(transaction))
                .toList()
                .isEmpty();

        if (canBeExecuted) {
            transaction.debtor().debt(transaction.amount(), transaction.currency());
            transaction.debtor().getFinancialTransactionLogbook().writeTransaction(transaction);
            transaction.credit().credit(transaction.amount(), transaction.currency());
            transaction.credit().getFinancialTransactionLogbook().writeTransaction(transaction);
        }
    }
    
}
