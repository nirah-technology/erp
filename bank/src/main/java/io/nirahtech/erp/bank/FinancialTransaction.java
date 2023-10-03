package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.UUID;

public record FinancialTransaction(
    UUID id,
    LocalDateTime timestamp,
    BigDecimal amount,
    Currency currency,
    BankAccount credit,
    BankAccount debtor
) {

}
