package io.nirahtech.erp.bank;

public interface FinancialTransactionExecutor {
    void addFinancialRules(FinancialRule... financialRules);
    void execute(FinancialTransaction transaction);
}
