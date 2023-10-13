package io.nirahtech.erp.webapp.persistence.cache;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.nirahtech.erp.webapp.persistence.api.cache.Cache;

public final class InMemoryCache<K, T> implements Cache<K, T> {

    private static final Duration DATA_LIFE_DURATION = Duration.ofMinutes(10); 
    private static final Duration CLEANUP_INTERVAL_DURATION = Duration.ofMinutes(30); 

    private final Map<K, CachedData<K, T>> cachedValues = new HashMap<>();
    private final Runnable dataCleaner;
    private final ScheduledExecutorService scheduledExecutorService;
    private ScheduledFuture<?> resultFuture = null;

    public InMemoryCache() {
        this.dataCleaner = () -> {
            synchronized(this.cachedValues) {
                final Set<K> keyToRemove = this.cachedValues.entrySet()
                        .stream()
                        .filter(data -> data.getValue().from().plus(DATA_LIFE_DURATION).isBefore(LocalDateTime.now()))
                        .map(Entry::getKey)
                        .collect(Collectors.toSet());
                keyToRemove.forEach(this.cachedValues::remove);
            }
        };
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.initializeAndRunProcessCleaner();
    }
    
    private final void initializeAndRunProcessCleaner() {
        this.resultFuture = this.scheduledExecutorService.schedule(this.dataCleaner, CLEANUP_INTERVAL_DURATION.toMinutes(), TimeUnit.MINUTES);

    }

    public final void purgeObsoletData() {
        this.dataCleaner.run();
    }

    @Override
    public Collection<T> findAll() {
        return Collections.unmodifiableCollection(this.cachedValues.values().stream().map(CachedData::data).toList());
    }

    @Override
    public Optional<T> findById(K id) {
        Optional<T> data = Optional.empty();
        final CachedData<K, T> cachedData = this.cachedValues.get(id);
        if (Objects.nonNull(cachedData)) {
            data = Optional.ofNullable(cachedData.data());
        }
        return data;
    }

    @Override
    public K persist(T data) {
        final CachedData<K, T> cachedData = CachedData.prepare(data);
        this.cachedValues.put(cachedData.key(), cachedData);
        return cachedData.key();
    }

    @Override
    public T update(K id, T data) {
        final CachedData<K, T> oldData = this.cachedValues.get(id);
        if (Objects.nonNull(oldData)) {
            this.deleteById(id);
            this.cachedValues.put(id, new CachedData(id, data, LocalDateTime.now()));
        }
        return data;
    }

    @Override
    public void delete(T data) {
        final Optional<Entry<K, CachedData<K, T>>> potentialEntry = this.cachedValues.entrySet().stream().filter(pair -> pair.getValue().data().equals(data)).findFirst();
        if (potentialEntry.isPresent()) {
            this.deleteById(potentialEntry.get().getKey());
        }
    }

    @Override
    public void deleteById(K id) {
        this.cachedValues.remove(id);
    }

    @Override
    public boolean exists(T data) {
        return this.cachedValues.values().stream().anyMatch(cachedValue -> cachedValue.data().equals(data));
    }

    @Override
    public boolean isEmpty() {
        return this.cachedValues.isEmpty();
    }
    
}
