package io.nirahtech.erp.webapp.persistence.repositories;

import java.util.Collection;
import java.util.Optional;

import io.nirahtech.cache.Cache;
import io.nirahtech.cache.CacheFactory;
import io.nirahtech.cache.Key;
import io.nirahtech.erp.webapp.persistence.api.dao.Dao;

public abstract class CachedRepository<V> implements Dao<Key, V> {
    private final Dao<Key, V> dao;
    private final Cache<V> cache;

    protected CachedRepository(final Dao<Key, V> dao) {
        this.dao = dao;
        this.cache = CacheFactory.createInMemoryCache();
    }
    @Override
    public Collection<V> findAll() {
        Collection<V> companies = this.cache.findAll();
        if (companies.isEmpty()) {
            companies = this.dao.findAll();
            companies.forEach(this.cache::put);
        }
        return companies;
    }
    @Override
    public Optional<V> findById(K id) {
        Optional<V> companyFound = this.cache.findAll().stream().filter(data -> data.);
        if (companyFound.isEmpty()) {
            companyFound = this.dao.findById(id);
            companyFound.ifPresent(this.cache::persist);
        }
        return companyFound;
    }
    @Override
    public K persist(V data) {
        K id = this.cache.put(data);
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
