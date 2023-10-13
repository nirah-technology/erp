package io.nirahtech.erp.webapp.persistence.api.base;

public interface Update<K, V> {
    V update(K id, V data);
}
