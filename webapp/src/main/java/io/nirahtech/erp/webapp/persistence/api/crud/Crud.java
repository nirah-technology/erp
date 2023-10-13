package io.nirahtech.erp.webapp.persistence.api.crud;

import io.nirahtech.erp.webapp.persistence.api.cqrs.Command;
import io.nirahtech.erp.webapp.persistence.api.cqrs.Query;

public interface Crud<K, V> extends Query<K, V>, Command<K, V> {
    
}
