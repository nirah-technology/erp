package io.nirahtech.erp.bank;

public final class BankAccountFactory {
    private BankAccountFactory() { }

    public static final BankAccount create(BankAccountType accountType, final Bank bank, final BranchCode branchCode, final AccountHolder accountHolder) {
        final BankDetails bankDetails = BankDetailsFactory.create(bank, branchCode, accountHolder);
        return new BankAccount(accountType, bank, accountHolder, bankDetails);
    }
}
