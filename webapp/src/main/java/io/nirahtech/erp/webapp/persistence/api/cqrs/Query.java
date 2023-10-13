package io.nirahtech.erp.webapp.persistence.api.cqrs;

import io.nirahtech.erp.webapp.persistence.api.base.Read;

public interface Query<K, V> extends Read<K, V> {
    
}
