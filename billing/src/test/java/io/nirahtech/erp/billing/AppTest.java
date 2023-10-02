package io.nirahtech.erp.billing;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.core.BankAccount;
import io.nirahtech.erp.core.EmailAddress;
import io.nirahtech.erp.core.MailingAddress;
import io.nirahtech.erp.core.PhoneNumber;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        final Customer nicolas = new Customer(
            "Nicolas", 
            "METIVIER", 
            new PhoneNumber(33, 623335703), 
            new EmailAddress("nicolas.a.metivier", "gmail.com"), 
            new MailingAddress());
     
        final BankAccount bankAccount = new BankAccount();       
        final Provider gns = new Provider(
            "GNS Renovation", 
            new PhoneNumber(33, 623335703), 
            new EmailAddress("nicolas.a.metivier", "gmail.com"), 
            new MailingAddress(),
            bankAccount);

        final Invoice invoice = new Invoice(new InvoiceNumber(), gns, nicolas, LocalDateTime.now());
        invoice.addRecords(
            new InvoiceRecordDetail(
                new Service(
                    "Chevrons", 
                    null),
                BigDecimal.valueOf(60.0F),
                40,
                9,
                Currency.getInstance(Locale.FRANCE)));

                System.out.println(invoice.computePriceExcludingTax());
                System.out.println(invoice.computePriceWithTax());
                System.out.println(invoice.computeTax());
                System.out.println(invoice.getInvoiceNumber().getValue());

        assertTrue( true );
    }
}
