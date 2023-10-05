package io.nirahtech.erp.edm.repository;

public interface Repository<K, T> extends ReadOnlyRepository<K, T>, WriteOnlyRepository<K, T> {

}
