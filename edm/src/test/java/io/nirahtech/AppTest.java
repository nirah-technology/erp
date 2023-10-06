package io.nirahtech;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.manager.DocumentManager;
import io.nirahtech.erp.edm.manager.DocumentManagerFactory;

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
        final File storage = new File("storage");
        final DocumentManager manager = DocumentManagerFactory.getInstance().create(storage);
        Document invoice = manager.createDocument("invoice-001.pdf", "nicolas.metivier");
        manager.upload(new File("database.rpstr"));
        assertNotNull(invoice);
    }
}
