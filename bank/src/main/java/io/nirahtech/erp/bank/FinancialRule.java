package io.nirahtech.erp.bank;

@FunctionalInterface
public interface FinancialRule {
    boolean isAllowed(final FinancialTransaction financialTransaction);
}
