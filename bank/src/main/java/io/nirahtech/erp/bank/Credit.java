package io.nirahtech.erp.bank;

import java.math.BigDecimal;
import java.util.Currency;

public interface Credit {
    void credit(BigDecimal amount, Currency currency);
}
