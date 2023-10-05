package io.nirahtech;


import java.io.File;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentFactory;
import io.nirahtech.erp.edm.repository.DocumentRepository;
import io.nirahtech.erp.edm.repository.JSONDocumentRepository;

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
        DocumentRepository repository = new JSONDocumentRepository(new File("database.json"));
        Document doc = DocumentFactory.create("test.pdf");
        repository.persist(doc);
        repository.findById(doc.getId());
    }
}
