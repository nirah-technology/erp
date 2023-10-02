package io.nirahtech.erp.bank;

import java.text.ParseException;
import java.util.Objects;

public record BasicBankAccountNumber(
    BankCode bankCode,
    BranchCode branchCode,
    AccountNumber accountNumber
) {
    
    private static final int MAX_IBAN_SIZE = 30;

    public static final BasicBankAccountNumber parse(final String bban)  throws ParseException {
        if (Objects.isNull(bban)) {
            throw new ParseException("BBAN cannot be 'null'.", 0);
        }
        if (bban.length() > MAX_IBAN_SIZE) {
            throw new ParseException("BBAN size cannot be greater than " + MAX_IBAN_SIZE, 0);
        }
        for (final char character : bban.toCharArray()) {
            if (!Character.isLetterOrDigit(character)) {
                throw new ParseException("Invalid character detected in BBAN : " + character, 0);
            }
        }

        final String bankCodeValue = bban.substring(0, 4);
        final String branchCodeValue = bban.substring(4, 9);
        final String accountNumberValue = bban.substring(9);
        
        return new BasicBankAccountNumber(new BankCode(bankCodeValue), new BranchCode(branchCodeValue), new AccountNumber(accountNumberValue));
    }

}
