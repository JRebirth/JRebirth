package org.jrebirth.af.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jrebirth.af.api.concurrent.RunnablePriority;

/**
 * The class <strong>MultiMap</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <K> The object used as a Key
 * @param <V> The object stored as a value
 */
public class MultiMap<K, V> implements Map<K, List<V>> {

    /** The internal map that store multiple data per key. */
    private final Map<K, List<V>> map = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.map.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object key) {
        return this.map.containsKey(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(final Object value) {
        boolean res = false;
        for (final List<V> list : this.map.values()) {
            if (list.contains(value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<V> get(final Object key) {
        return this.map.get(key);
    }

    /**
     * Add a new entry.
     *
     * @param key the key of the entry
     * @param value the value of the entry
     *
     * @return true if the operation has succeeded
     */
    public boolean add(final K key, final V value) {
        if (!this.map.containsKey(key)) {
            this.map.put(key, new ArrayList<V>());
        }
        return this.map.get(key).add(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<V> put(final K key, final List<V> value) {
        return this.map.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<V> remove(final Object key) {
        return this.map.remove(key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.map.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
        return this.map.keySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<List<V>> values() {
        return this.map.values();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<java.util.Map.Entry<K, List<V>>> entrySet() {
        return this.map.entrySet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(final Map<? extends K, ? extends List<V>> m) {
        this.map.putAll(m);
    }

    /**
     * The class <strong>Entry</strong>.
     *
     * @author Sébastien Bordes
     *
     * @param <E> the type of the value stored
     */
    public class Entry<E> {

        /** The id of the entry. */
        private final String id;

        /** The description of the entry. */
        private final String description;

        /** The priority of the entry. */
        private final RunnablePriority priority;

        /** The entry value. */
        private final E value;

        /**
         * Default Constructor.
         *
         * @param id the id entry
         * @param description the description entry
         * @param priority the priority entry
         * @param value the value entry
         */
        public Entry(final String id, final String description, final RunnablePriority priority, final E value) {
            super();
            this.id = id;
            this.description = description;
            this.priority = priority;
            this.value = value;
        }

        /**
         * @return the id
         */
        public String getId() {
            return this.id;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * @return the priority
         */
        public RunnablePriority getPriority() {
            return this.priority;
        }

        /**
         * @return the value
         */
        public E getValue() {
            return this.value;
        }

    }

}
