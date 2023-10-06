package io.nirahtech.erp.edm.document;

import java.io.File;

public final class DocumentFactory {
    private DocumentFactory() { }

    public static DocumentBuilder create(final String name) {
        return new DocumentBuilder().file(new File(name));
    }

    public static DocumentBuilder create(final File file) {
        return new DocumentBuilder().file(file);
    }
}
