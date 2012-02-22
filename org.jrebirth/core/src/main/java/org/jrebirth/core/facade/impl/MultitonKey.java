package org.jrebirth.core.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.facade.UniqueKey;

/**
 * The class <strong>MultitonKey</strong>.
 * 
 * The key used to discriminate any multiton part in order to guarantee component unicity.
 * 
 * @author Sébastien Bordes
 * 
 * @version $$Revision$$
 * @since $$Date$$
 */
public class MultitonKey implements UniqueKey {

    /** The key formatted into a string. */
    private String key;

    /** List of keys that are part of the main key. */
    private final List<Object> keys = new ArrayList<>();

    /**
     * Default Constructor.
     * 
     * @param keys a list of immutable objects that guarantee component unicity
     */
    public MultitonKey(final Object... keys) {
        // Store all keys
        for (final Object k : keys) {
            this.keys.add(k);
        }
        rebuildKey();
    }

    /**
     * (Re)-Build the string key by reading the keys list content.
     */
    private void rebuildKey() {
        final StringBuffer sb = new StringBuffer();
        for (final Object k : this.keys) {
            sb.append(k.toString()).append('|');
        }
        this.key = sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        return object != null && this.key.equals(object.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUniqueKey().hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getUniqueKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUniqueKey() {
        if (this.key == null) {
            rebuildKey();
        }
        return this.key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue() {
        return this.keys.get(0); // TODO
    }
}
