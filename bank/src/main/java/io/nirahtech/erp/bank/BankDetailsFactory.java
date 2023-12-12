package io.nirahtech.erp.bank;

import java.util.Optional;
import java.util.UUID;

public final class BankDetailsFactory {
    private BankDetailsFactory() { }

    public static final BankDetails create(final Bank bank, final BranchCode branchCode, final AccountHolder accountHolder) {
        final UUID accountNumberUUID = UUID.randomUUID();
        final AccountNumber accountNumber = new AccountNumber(accountNumberUUID.toString().replace("-", "").substring(0, 30));
        return new BankDetails(
            accountHolder, 
            bank.getName(), 
            bank.getCode(), 
            "null", 
            accountNumber, 
            "null", 
            new InternationalBankAccountNumber(
                bank.getCountry(),
                76,
                new BasicBankAccountNumber(
                    bank.getCode(),
                    branchCode,
                    accountNumber
                )
            ), 
            new BankIdentifierCode(
                bank.getCode(),
                bank.getCountry(),
                branchCode,
                Optional.empty()
            ));
    }
}
