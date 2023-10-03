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
        final AccountHolder nicolas = new AccountHolder("Nicolas", "METIVIER", LocalDate.of(1993, 1, 6), new HashSet<>());
        final AccountHolder rebecca = new AccountHolder("Rébecca", "MOLARET", LocalDate.of(1995, 11, 5), new HashSet<>());
        
        final Bank laBanquePostale = BankFactory.create("La Banque Postale", new BankCode("PSST"), new BranchCode("TOU"), Locale.FRANCE);
        final Bank creditMutuel = BankFactory.create("Credit Mutuel", new BankCode("CRML"), new BranchCode("TOU"), Locale.FRANCE);
        
        laBanquePostale.registerNewCustomer(nicolas);
        creditMutuel.registerNewCustomer(rebecca);
        
        final BankAccount ccpNicolas = laBanquePostale.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, nicolas);
        ccpNicolas.credit(new BigDecimal(25_000.00F), Currency.getInstance(ccpNicolas.getBank().country()));
        final BankAccount ccpRebecca = creditMutuel.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, rebecca);
        ccpRebecca.credit(new BigDecimal(30_000.00F), Currency.getInstance(ccpNicolas.getBank().country()));

        assertEquals(new BigDecimal(25_000.00F).floatValue(), ccpNicolas.getBalance().floatValue());
        assertEquals(new BigDecimal(30_000.00F).floatValue(), ccpRebecca.getBalance().floatValue());

        final float amount = 1_000.00F;

        nicolas.transferMoney(ccpNicolas, ccpRebecca, BigDecimal.valueOf(amount), Currency.getInstance(Locale.FRANCE));

        
        assertEquals(new BigDecimal(25_000.00F-amount).floatValue(), ccpNicolas.getBalance().floatValue());
        assertEquals(new BigDecimal(30_000.00F+amount).floatValue(), ccpRebecca.getBalance().floatValue());

    }
}
