package io.nirahtech.erp.webapp.persistence.cache;

import java.time.LocalDateTime;
import java.util.UUID;

public final record CachedData<K, T>(
        K key,
        T data,
        LocalDateTime from) {

            public static final <K, T> CachedData<K, T> prepare(T data) {
        return new CachedData(UUID.randomUUID(), data, LocalDateTime.now());
    }
}
