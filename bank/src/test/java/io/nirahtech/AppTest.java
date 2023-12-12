package io.nirahtech;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.bank.AccountHolder;
import io.nirahtech.erp.bank.Bank;
import io.nirahtech.erp.bank.BankAccount;
import io.nirahtech.erp.bank.BankAccountType;
import io.nirahtech.erp.bank.BankCode;
import io.nirahtech.erp.bank.BankFactory;
import io.nirahtech.erp.bank.BranchCode;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws ParseException
     */
    @Test
    public void shouldAnswerWithTrue() throws ParseException
    {
        final AccountHolder john = new AccountHolder("John", "DOE", LocalDate.of(1993, 1, 6), new HashSet<>());
        final AccountHolder jane = new AccountHolder("Jane", "DOE", LocalDate.of(1995, 11, 5), new HashSet<>());
        
        final Bank galacticBank = BankFactory.create("Intergalactic Bank", new BankCode("AAAA"), new BranchCode("TOU"), Locale.FRANCE);
        final Bank resistanceBank = BankFactory.create("Resistence Bank", new BankCode("BBBB"), new BranchCode("PAR"), Locale.FRANCE);
        
        galacticBank.registerNewCustomer(john);
        resistanceBank.registerNewCustomer(jane);
        
        final BankAccount ccpJohn = galacticBank.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, john);
        ccpJohn.credit(new BigDecimal(25_000.00F), Currency.getInstance(ccpJohn.getBank().getCountry()));
        final BankAccount ccpJane = resistanceBank.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, jane);
        ccpJane.credit(new BigDecimal(30_000.00F), Currency.getInstance(ccpJohn.getBank().getCountry()));

        assertEquals(new BigDecimal(25_000.00F).floatValue(), ccpJohn.getBalance().floatValue());
        assertEquals(new BigDecimal(30_000.00F).floatValue(), ccpJane.getBalance().floatValue());

        final float amount = 1_000.00F;

        john.transferMoney(ccpJohn, ccpJane, BigDecimal.valueOf(amount), Currency.getInstance(Locale.FRANCE));

        
        assertEquals(new BigDecimal(25_000.00F-amount).floatValue(), ccpJohn.getBalance().floatValue());
        assertEquals(new BigDecimal(30_000.00F+amount).floatValue(), ccpJane.getBalance().floatValue());

    }
}
