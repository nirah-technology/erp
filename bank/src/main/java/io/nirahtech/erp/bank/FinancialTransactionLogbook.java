package io.nirahtech.erp.bank;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
                    return transaction.timestamp().isAfter(from) && transaction.timestamp().isBefore(to);
                })
            .toList());
    }

    public final void writeTransaction(final FinancialTransaction financialTransaction) {
        if (!this.isReadOnly) {
            this.transactions.add(financialTransaction);
        }
    }

    public final void writeAllTransactions(final FinancialTransaction... financialTransactions) {
        if (!this.isReadOnly) {
            this.transactions.addAll(Set.of(financialTransactions));
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
