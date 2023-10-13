package io.nirahtech.erp.webapp.persistence.api.cache;

import io.nirahtech.erp.webapp.persistence.api.crud.Crud;

public interface Cache<K, T> extends Crud<K, T> {
    boolean exists(T data);
    boolean isEmpty();
}
