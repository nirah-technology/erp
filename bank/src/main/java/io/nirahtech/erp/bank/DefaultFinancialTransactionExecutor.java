package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

class DefaultFinancialTransactionExecutor implements FinancialTransactionExecutor {

    private final Set<FinancialRule> financialRules = new HashSet<>();

    DefaultFinancialTransactionExecutor() {
        this.financialRules.add((transaction) -> Objects.nonNull(transaction));
        this.financialRules.add((transaction) -> !transaction.getDebtor().equals(transaction.getCredit()));
        this.financialRules.add((transaction) -> transaction.getAmount().floatValue() > 0.0F);
        this.financialRules.add((transaction) -> transaction.getDebtor().getBalance().compareTo(new BigDecimal(0.0F)) == 1 );
        this.financialRules.add((transaction) -> transaction.getDebtor().getBalance().subtract(transaction.getAmount()).compareTo(new BigDecimal(0.0F)) >= 0);
    }

    @Override
    public void addFinancialRules(FinancialRule... financialRules) {
        this.financialRules.addAll(Arrays.asList(financialRules));
    }

    @Override
    public void execute(FinancialTransaction transaction) {
        final boolean canBeExecuted = this.financialRules
                .stream()
                .filter(rule -> !rule.isAllowed(transaction))
                .collect(Collectors.toList())
                .isEmpty();

        if (canBeExecuted) {
            transaction.getDebtor().debt(transaction.getAmount(), transaction.getCurrency());
            transaction.getDebtor().getFinancialTransactionLogbook().writeTransaction(transaction);
            transaction.getCredit().credit(transaction.getAmount(), transaction.getCurrency());
            transaction.getCredit().getFinancialTransactionLogbook().writeTransaction(transaction);
        }
    }
    
}
