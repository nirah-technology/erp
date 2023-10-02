package io.nirahtech;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.bank.AccountHolder;
import io.nirahtech.erp.bank.Bank;
import io.nirahtech.erp.bank.BankAccount;
import io.nirahtech.erp.bank.BankAccountFactory;
import io.nirahtech.erp.bank.BankCode;
import io.nirahtech.erp.bank.BankDetails;
import io.nirahtech.erp.bank.BankDetailsFactory;

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
        Bank bank = new Bank("Test", new BankCode("TST"), Locale.FRANCE, new HashSet<>());
        AccountHolder accountHolder = new AccountHolder("Nicolas", "METIVIER", LocalDate.now(), new HashSet<>());
        BankDetails rib = BankDetailsFactory.create(
            bank,
            accountHolder
        );
        BankAccount account = BankAccountFactory.create(bank, accountHolder);
        System.out.println( account);
    }
}
