package io.nirahtech.erp.webapp.persistence.repositories;

import java.util.Collection;
import java.util.Optional;

import io.nirahtech.erp.webapp.persistence.api.cache.Cache;
import io.nirahtech.erp.webapp.persistence.api.dao.Dao;
import io.nirahtech.erp.webapp.persistence.cache.InMemoryCache;

public abstract class CachedRepository<K, V> implements Dao<K, V> {
    private final Dao<K, V> dao;
    private final Cache<K, V> cache;

    protected CachedRepository(final Dao<K, V> dao) {
        this.dao = dao;
        this.cache = new InMemoryCache<>();
    }
    @Override
    public Collection<V> findAll() {
        Collection<V> companies = this.cache.findAll();
        if (companies.isEmpty()) {
            companies = this.dao.findAll();
            companies.forEach(this.cache::persist);
        }
        return companies;
    }
    @Override
    public Optional<V> findById(K id) {
        Optional<V> companyFound = this.cache.findById(id);
        if (companyFound.isEmpty()) {
            companyFound = this.dao.findById(id);
            companyFound.ifPresent(this.cache::persist);
        }
        return companyFound;
    }
    @Override
    public K persist(V data) {
        K id = this.cache.persist(data);
        this.dao.persist(data);
        return id;
    }
    @Override
    public V update(K id, V data) {
        this.cache.update(id, data);
        this.dao.update(id, data);
        return data;
    }
    @Override
    public void delete(V data) {
        this.cache.delete(data);
        this.dao.delete(data);
    }
    @Override
    public void deleteById(K id) {
        this.cache.deleteById(id);
        this.dao.deleteById(id);
    }
}
