package io.nirahtech.erp.edm.document;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

public interface Document extends Tagable, Commentable, Annexable, Hierarchical, Searchable, AccessControl, Archivable, History, Serializable {
    DocumentIdentifier getId();
    String getFileName();
    File getFile();
    String getExtension();
    LocalDateTime getCreationDateTime();
    LocalDateTime getModificationDateTime();
    String getOwner();
    Version getVersion();
}
