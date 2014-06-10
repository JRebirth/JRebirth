package org.jrebirth.af.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultiMap<K, V> implements Map<K, List<V>> {

    private final Map<K, List<V>> map = new HashMap<>();

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        boolean res = false;
        for (List<V> list : map.values()) {
            if (list.contains(value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public List<V> get(Object key) {
        return map.get(key);
    }

    public boolean putItem(K key, V value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<V>());
        }
        return map.get(key).add(value);
    }

    @Override
    public List<V> put(K key, List<V> value) {
        return map.put(key, value);
    }

    @Override
    public List<V> remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<List<V>> values() {
        return map.values();
    }

    @Override
    public Set<java.util.Map.Entry<K, List<V>>> entrySet() {
        return map.entrySet();
    }

    @Override
    public void putAll(Map<? extends K, ? extends List<V>> m) {
        map.putAll(m);
    }

}
