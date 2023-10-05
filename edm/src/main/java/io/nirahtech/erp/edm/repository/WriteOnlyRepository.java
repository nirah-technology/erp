package io.nirahtech.erp.edm.repository;

public interface WriteOnlyRepository<K, T> {
    K persist(T data);
    void deleteById(K id);
    void delete(T data);
}
