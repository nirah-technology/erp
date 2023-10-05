package io.nirahtech.erp.edm.document;

import java.util.Set;

public interface History {
    Set<Modification> getModifications();
    void addModifications(Modification... modifications);
}
