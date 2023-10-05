package io.nirahtech.erp.edm.document;

import java.io.File;

public final class DocumentFactory {
    private DocumentFactory() { }

    public static Document create(final String name) {
        return new DocumentBuilder().file(new File(name)).build();
    }

    public static Document create(final File file) {
        return new DocumentBuilder().file(file).build();
    }
}
