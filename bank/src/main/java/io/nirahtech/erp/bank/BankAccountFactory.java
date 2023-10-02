package io.nirahtech.erp.bank;

public final class BankAccountFactory {
    private BankAccountFactory() { }

    public static final BankAccount create(final Bank bank, final AccountHolder accountHolder) {
        final BankDetails bankDetails = BankDetailsFactory.create(bank, accountHolder);
        return new BankAccount(bank, bankDetails);
    }
}
