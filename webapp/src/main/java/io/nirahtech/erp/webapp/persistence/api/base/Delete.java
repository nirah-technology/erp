package io.nirahtech.erp.webapp.persistence.api.base;

public interface Delete<K, V> {
    void delete(V data);
    void deleteById(K id);
}
