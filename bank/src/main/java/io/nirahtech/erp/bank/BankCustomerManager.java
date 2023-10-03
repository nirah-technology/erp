package io.nirahtech.erp.bank;

import java.util.Collection;

public interface BankCustomerManager {
    void registerNewCustomer(final AccountHolder customer);
    void disaffiliateCustomer(final AccountHolder customer); 
    Collection<AccountHolder> listCustomers();
}
