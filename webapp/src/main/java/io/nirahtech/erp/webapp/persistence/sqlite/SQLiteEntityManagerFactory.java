package io.nirahtech.erp.webapp.persistence.sqlite;

import java.util.Map;
import java.util.Objects;

import jakarta.persistence.Cache;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.persistence.Query;
import jakarta.persistence.SynchronizationType;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;

public final class SQLiteEntityManagerFactory implements EntityManagerFactory {

    private static EntityManagerFactory instance;

    public static EntityManagerFactory getInstance() {
        if (Objects.isNull(SQLiteEntityManagerFactory.instance)) {
            SQLiteEntityManagerFactory.instance = new SQLiteEntityManagerFactory();
        }
        return SQLiteEntityManagerFactory.instance;
    }

    private SQLiteEntityManagerFactory() { }

    @Override
    public EntityManager createEntityManager() {
        return new SQLiteEntityManager();
    }

    @Override
    public EntityManager createEntityManager(Map map) {
        return new SQLiteEntityManager();
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType) {
        return this.createEntityManager();
    }

    @Override
    public EntityManager createEntityManager(SynchronizationType synchronizationType, Map map) {
        return this.createEntityManager(map);
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCriteriaBuilder'");
    }

    @Override
    public Metamodel getMetamodel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMetamodel'");
    }

    @Override
    public boolean isOpen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOpen'");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    @Override
    public Map<String, Object> getProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProperties'");
    }

    @Override
    public Cache getCache() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCache'");
    }

    @Override
    public PersistenceUnitUtil getPersistenceUnitUtil() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersistenceUnitUtil'");
    }

    @Override
    public void addNamedQuery(String name, Query query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNamedQuery'");
    }

    @Override
    public <T> T unwrap(Class<T> cls) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unwrap'");
    }

    @Override
    public <T> void addNamedEntityGraph(String graphName, EntityGraph<T> entityGraph) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addNamedEntityGraph'");
    }
    
}
