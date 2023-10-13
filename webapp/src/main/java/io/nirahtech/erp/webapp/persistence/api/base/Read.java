package io.nirahtech.erp.webapp.persistence.api.base;

import java.util.Collection;
import java.util.Optional;

public interface Read<K, V> {
    Collection<V> findAll();
    Optional<V> findById(K id);
}
