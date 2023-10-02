package io.nirahtech.erp.bank;

import java.util.Optional;
import java.util.UUID;

public final class BankDetailsFactory {
    private BankDetailsFactory() { }

    public static final BankDetails create(final Bank bank, final AccountHolder accountHolder) {
        final UUID accountNumberUUID = UUID.randomUUID();
        final AccountNumber accountNumber = new AccountNumber(accountNumberUUID.toString().replace("-", "").substring(0, 30));
        final BranchCode branchCode = new BranchCode("TST");
        return new BankDetails(
            accountHolder, 
            bank.name(), 
            bank.code(), 
            "null", 
            accountNumber, 
            "null", 
            new InternationalBankAccountNumber(
                bank.country(),
                12,
                new BasicBankAccountNumber(
                    bank.code(),
                    branchCode,
                    accountNumber
                )
            ), 
            new BankIdentifierCode(
                bank.code(),
                bank.country(),
                branchCode,
                Optional.empty()
            ));
    }
}
