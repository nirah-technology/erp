package io.nirahtech.erp.billing;

import java.time.LocalDateTime;

public final class InvoiceNumber {
    private final String value;

    public InvoiceNumber(final String value) {
        this.value = value;
    }

    public InvoiceNumber() {
        final LocalDateTime now = LocalDateTime.now();
        this.value = String.format("%s%s%s%s%s%s%s", now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getNano());
    }

    public String getValue() {
        return this.value;
    }
}
