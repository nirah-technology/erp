package io.nirahtech.erp.bank;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class FinancialTransactionLogbook {
    private boolean isReadOnly = false;
    private final Set<FinancialTransaction> transactions = new HashSet<>();

    public FinancialTransactionLogbook() {

    }

    public final Collection<FinancialTransaction> getTransactions() {
        return Collections.unmodifiableCollection(this.transactions);
    }

    public final Collection<FinancialTransaction> listTransactions(LocalDateTime from, LocalDateTime to) {
        return Collections.unmodifiableCollection(
                this.transactions.stream().filter((transaction) -> {
                    return transaction.getTimestamp().isAfter(from) && transaction.getTimestamp().isBefore(to);
                })
            .collect(Collectors.toList()));
    }

    public final void writeTransaction(final FinancialTransaction financialTransaction) {
        if (!this.isReadOnly) {
            this.transactions.add(financialTransaction);
        }
    }

    public final void writeAllTransactions(final FinancialTransaction... financialTransactions) {
        if (!this.isReadOnly) {
            this.transactions.addAll(Arrays.asList(financialTransactions));
        }
    }

    public final void writeAllTransactions(final Collection<FinancialTransaction> financialTransactions) {
        if (!this.isReadOnly) {
            this.transactions.addAll(financialTransactions);
        }
    }

    public void close() {
        this.isReadOnly = true;
    }

}
