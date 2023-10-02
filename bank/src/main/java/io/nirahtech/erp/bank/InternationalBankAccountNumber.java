package io.nirahtech.erp.bank;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public final record InternationalBankAccountNumber(
    Locale country,
    int checkDigit,
    BasicBankAccountNumber accountNumber
) {

    private static final int MAX_IBAN_SIZE = 34;
    
    public static final InternationalBankAccountNumber parse(final String iban) throws ParseException {
        if (Objects.isNull(iban)) {
            throw new ParseException("IBAN cannot be 'null'.", 0);
        }
        if (iban.length() > MAX_IBAN_SIZE) {
            throw new ParseException("IBAN size cannot be greater than " + MAX_IBAN_SIZE, 0);
        }
        for (final char character : iban.toCharArray()) {
            if (!Character.isLetterOrDigit(character)) {
                throw new ParseException("Invalid character detected in IBAN : " + character, 0);
            }
        }

        final String countryCodeValue = iban.substring(0, 2);
        final String checkCodeValue = iban.substring(2, 4);
        
        for (final char character : checkCodeValue.toCharArray()) {
            if (!Character.isDigit(character)) {
                throw new ParseException("Invalid Check Code detected in IBAN : " + character, 0);
            }
        }

        final String accountNumberValue = iban.substring(4);

        final Locale country = Locale.forLanguageTag(countryCodeValue);
        final BasicBankAccountNumber bban = BasicBankAccountNumber.parse(accountNumberValue);

        return new InternationalBankAccountNumber(country, Integer.parseInt(checkCodeValue), bban);

    }
}
