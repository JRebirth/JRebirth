/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2013
 * Contact : sebastien.bordes@jrebirth.org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrebirth.core.wave;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * The class <strong>WaveItem</strong>.
 * 
 * Wave item is used to identify an object into a wave.
 * 
 * @author Sébastien Bordes
 * 
 * @param <T> the type of the object mapped by this WaveItem
 */
public class WaveItem<T> {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave item. */
    private int uid;

    /** The type of the related object registered by this wave item. */
    private final Type itemType;

    private String name;

    /**
     * Private Constructor.
     */
    public WaveItem() {
        this.itemType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // Ensure that the uid will be unique at runtime
        synchronized (WaveItem.class) {
            setUid(++idGenerator);
        }
    }

    /**
     * Private Constructor.
     */
    public WaveItem(final String name) {
        this();
        this.name = name;
    }

    // /**
    // * Build a wave item.
    // *
    // * @return a new fresh wave item object
    // *
    // * @param <T> the type of the object wrapped by this WaveData
    // */
    // public static <TT> WaveItem<TT> build(final Class<?>... cls) {
    //
    // final Type waveItemType = new ParameterizedType() {
    // public Type getRawType() {
    // return cls[0];
    // }
    //
    // public Type getOwnerType() {
    // return null;
    // }
    //
    // public Type[] getActualTypeArguments() {
    //
    // final Type[] subtypes = new Type[cls.length - 1];
    // for (int i = 1; i < cls.length; i++) {
    // subtypes[i - 1] = cls[i];
    // }
    // return subtypes;
    // }
    // };
    //
    // final WaveItem<TT> waveItem = new WaveItem<TT>() {
    // };
    //
    // waveItem.setItemClass(waveItemType);
    // // Ensure that the uid will be unique at runtime
    // synchronized (WaveItem.class) {
    // waveItem.setUid(++idGenerator);
    // }
    // return waveItem;
    // }
    // public void setItemClass(final Type itemClass) {
    // this.itemClass = itemClass;
    // }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * @param uid The uid to set.
     */
    private void setUid(final int uid) {
        this.uid = uid;
    }

    /**
     * @return Returns the itemType.
     */
    public Type getItemType() {
        return this.itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveItem) {
        return waveItem instanceof WaveItem && getUid() == ((WaveItem<?>) waveItem).getUid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return getUid();
    }

    public static void init(final WaveItems waveItems, final WaveItem<?> wi) {
        wi.setName(waveItems.toString());

    }

    private void setName(final String name) {
        this.name = name;
    }

}
