package io.nirahtech.erp.webapp.persistence.api.database;

import java.io.File;

public sealed interface Database  permits FileDatabase {
    File getFile();
}
