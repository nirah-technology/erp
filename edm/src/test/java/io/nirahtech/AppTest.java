package io.nirahtech;


import java.io.File;
import java.text.ParseException;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.nirahtech.erp.edm.document.Document;
import io.nirahtech.erp.edm.document.DocumentFactory;
import io.nirahtech.erp.edm.repository.DocumentRepository;
import io.nirahtech.erp.edm.repository.DefaultDocumentRepository;

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
        DocumentRepository repository = new DefaultDocumentRepository(new File("database.rpstr"));
        Document doc = DocumentFactory.create(UUID.randomUUID().toString()+".pdf");
        repository.persist(doc);
        repository.findById(doc.getId());
    }
}
