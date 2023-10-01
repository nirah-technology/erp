package io.nirahtech.erp.billing;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Invoice {
    private final InvoiceNumber invoiceNumber;
    private final Provider provider;
    private final Customer customer;
    private final LocalDateTime emitTimestamp;
    private final Set<InvoiceRecordDetail> records = new HashSet<>();
 
    public Invoice(
        final InvoiceNumber invoiceNumber,
        final Provider provider,
        final Customer customer,
        final LocalDateTime emitTimestamp
    ) {
        this.invoiceNumber = invoiceNumber;
        this.provider = provider;
        this.customer = customer;
        this.emitTimestamp = emitTimestamp;
    }

    public final InvoiceNumber getInvoiceNumber() {
        return invoiceNumber;
    }
    public final Provider getProvider() {
        return provider;
    }
    public final Customer getCustomer() {
        return customer;
    }
    public final LocalDateTime getEmitTimestamp() {
        return emitTimestamp;
    }
    public final Collection<InvoiceRecordDetail> getRecords() {
        return Collections.unmodifiableCollection(this.records);
    }
    public final void addRecords(final InvoiceRecordDetail... recordsToAdd) {
        if (Objects.nonNull(recordsToAdd)) {
            this.records.addAll(Arrays.asList(recordsToAdd));
        }
    }
    public final void deleteRecords(final InvoiceRecordDetail... recordsToDelete) {
        if (Objects.nonNull(recordsToDelete)) {
            this.records.removeAll(Arrays.asList(recordsToDelete));
        }
    }

    public final BigDecimal computePriceExcludingTax() {
        final double price = this.records
                .stream()
                .map(record -> record.getUnitPriceExcludingTax().multiply(BigDecimal.valueOf(record.getQuantity())))
                .mapToDouble(recordPrice -> recordPrice.doubleValue())
                .sum();
        return BigDecimal.valueOf(price);
    }

    public final BigDecimal computePriceWithTax() {

        final double price = this.records
                .stream()
                .map(record -> {
                    final double unitTax = ((record.getUnitPriceExcludingTax().doubleValue() * record.getVat()) / 100);
                    return record.getUnitPriceExcludingTax().add(BigDecimal.valueOf(unitTax)).multiply(BigDecimal.valueOf(record.getQuantity()));
                })
                .mapToDouble(recordPrice -> recordPrice.doubleValue())
                .sum();
        return BigDecimal.valueOf(price);
    }

    public final BigDecimal computeTax() {
        final double price = this.records
                .stream()
                .map(record -> {
                    final double unitTax = ((record.getUnitPriceExcludingTax().doubleValue() * record.getVat()) / 100);
                    return unitTax * record.getQuantity();
                })
                .mapToDouble(recordPrice -> recordPrice.doubleValue())
                .sum();
        return BigDecimal.valueOf(price);
    }

}
