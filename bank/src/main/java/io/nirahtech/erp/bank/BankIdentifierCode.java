package io.nirahtech.erp.bank;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

public final record BankIdentifierCode (
    BankCode bankNameCode,
    Locale country,
    BranchCode bankBranchCode,
    Optional<String> branchCode
) {

    private static final int MAX_BIC_SIZE = 11;

    public static final BankIdentifierCode parse(final String bic) throws ParseException {
        if (Objects.isNull(bic)) {
            throw new ParseException("BIC cannot be 'null'.", 0);
        }
        if (bic.length() > MAX_BIC_SIZE) {
            throw new ParseException("BIC size cannot be greater than " + MAX_BIC_SIZE, 0);
        }
        for (final char character : bic.toCharArray()) {
            if (!Character.isLetterOrDigit(character)) {
                throw new ParseException("Invalid character detected in BIC : " + character, 0);
            }
        }

        final String bankCodeValue = bic.substring(0, 4);
        final String countryCodeValue = bic.substring(4, 6);
        final String bankBranchCodeValue = bic.substring(6, 8);
        final Optional<String> branchCodeValue = Optional.ofNullable(bic.substring(8));

        final Locale country = Locale.forLanguageTag(countryCodeValue);

        return new BankIdentifierCode(new BankCode(bankCodeValue), country, new BranchCode(bankBranchCodeValue), branchCodeValue);
    }
    
}
