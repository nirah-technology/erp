package io.nirahtech.erp.billing;

import java.math.BigDecimal;
import java.util.Currency;

public class InvoiceRecordDetail {
    private final Offer offer;
    private final BigDecimal unitPriceExcludingTax;
    private final int quantity;
    private final float vat;
    private final Currency currency;

    public InvoiceRecordDetail(
        final Offer offer,
        final BigDecimal unitPriceExcludingTax,
        final int quantity,
        final float vat,
        final Currency currency
    ) {
        this.offer = offer;
        this.unitPriceExcludingTax = unitPriceExcludingTax;
        this.quantity = quantity;
        this.vat = vat;
        this.currency = currency;
    }

    public Offer getOffer() {
        return offer;
    }
    public int getQuantity() {
        return quantity;
    }
    public BigDecimal getUnitPriceExcludingTax() {
        return unitPriceExcludingTax;
    }
    public float getVat() {
        return vat;
    }
    public Currency getCurrency() {
        return currency;
    }

}
