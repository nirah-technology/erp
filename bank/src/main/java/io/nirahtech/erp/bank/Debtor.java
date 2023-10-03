package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.util.Currency;

public interface Debtor {
    void debt(BigDecimal amount, Currency currency);
}
