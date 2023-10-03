package io.nirahtech;


import java.text.ParseException;
import java.time.LocalDate;
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
        final Bank laBanquePostale = BankFactory.create("La Banque Postale", new BankCode("PSST"), new BranchCode("TOU"), Locale.FRANCE);
        final BankAccount ccp = laBanquePostale.openNewBankAccount(BankAccountType.CURRENT_ACCOUNT, nicolas);
        System.out.println(ccp.getBankDetails().iban());
        System.out.println(ccp.getBankDetails().bic());
    }
}
