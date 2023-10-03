```java

package io.nirahtech.erp.workingtime;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Program {
    public static void main(String[] args) {
        
        final AccountHolder john = new AccountHolder("John", "DOE", LocalDate.of(1993, 1, 6), new HashSet<>());
        final AccountHolder jane = new AccountHolder("Jane", "DOE", LocalDate.of(1995, 11, 5), new HashSet<>());
        
        final Bank galacticBank = BankFactory.create("Intergalactic Bank", new BankCode("AAAA"), new BranchCode("TOU"), Locale.FRANCE);
        final Bank resistanceBank = BankFactory.create("Resistence Bank", new BankCode("BBBB"), new BranchCode("PAR"), Locale.FRANCE);
        
        galacticBank.registerNewCustomer(john);
        resistanceBank.registerNewCustomer(jane);
        
        final BankAccount ccpJohn = galacticBank.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, john);
        ccpJohn.credit(new BigDecimal(25_000.00F), Currency.getInstance(ccpJohn.getBank().country()));
        final BankAccount ccpJane = resistanceBank.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, jane);
        ccpJane.credit(new BigDecimal(30_000.00F), Currency.getInstance(ccpJohn.getBank().country()));

        final float amount = 1_000.00F;

        john.transferMoney(ccpJohn, ccpJane, BigDecimal.valueOf(amount), Currency.getInstance(Locale.FRANCE));

    }
}

```