package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V item = cache.get(key).get();
        if (item == null) {
            this.load(key);
            return this.get(key);
        }
        if (cache.containsKey(key)) {
            return item;
        }
        this.load(key);
        return this.get(key);
    }

    protected abstract V load(K key);

}