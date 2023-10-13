package io.nirahtech.erp.webapp.persistence.api.cqrs;

import io.nirahtech.erp.webapp.persistence.api.base.Create;
import io.nirahtech.erp.webapp.persistence.api.base.Delete;
import io.nirahtech.erp.webapp.persistence.api.base.Update;

public interface Command<K, V> extends Create<K, V>, Update<K, V>, Delete<K, V> {
    
}
