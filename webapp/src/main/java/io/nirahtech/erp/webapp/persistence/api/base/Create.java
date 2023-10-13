package io.nirahtech.erp.webapp.persistence.api.base;

public interface Create<K, V> {
    K persist(V data);
}
