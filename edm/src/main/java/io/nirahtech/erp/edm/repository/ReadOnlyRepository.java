package io.nirahtech.erp.edm.repository;

import java.util.Collection;
import java.util.Optional;

public interface ReadOnlyRepository<K, T> {
    Optional<T> findById(K id);
    Collection<T> findAll();
}
