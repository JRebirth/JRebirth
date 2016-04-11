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
package org.jrebirth.af.core.wave;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.jrebirth.af.api.wave.contract.WaveItem;

/**
 * The class <strong>WaveItem</strong>.
 *
 * Wave item is used to identify an object into a wave or a parameter.
 *
 * @author Sébastien Bordes
 *
 * @param <T> the type of the object mapped by this WaveItem
 */
public class WaveItemBase<T> implements WaveItem<T> {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave item. */
    private int uid;

    /** The type of the related object registered by this wave item. */
    private final Type itemType;

    /** The WaveItem unique name. */
    private String name;

    /** Flag that indicates if the WaveItem must be considered as a method parameter. */
    private boolean isParameter;

    /**
     * Default Constructor.
     *
     * Set the name to null and the the isParameter flag to true
     */
    protected WaveItemBase() {
        this(null, true);
    }

    /**
     * Default Constructor.
     *
     * Set the isParameter flag to true
     *
     * @param name the unique name of this wave item
     */
    protected WaveItemBase(final String name) {
        this(name, true);
    }

    /**
     * Default Constructor.
     *
     * Set the name to null
     *
     * @param isParameter the flag that indicates if this WaveItem must be considered by wave handlers
     */
    protected WaveItemBase(final boolean isParameter) {
        this(null, isParameter);
    }

    /**
     * Constructor with all parameters.
     *
     * @param name the unique name of this wave item
     * @param isParameter the flag that indicates if this WaveItem must be considered by wave handlers
     */
    protected WaveItemBase(final String name, final boolean isParameter) {
        this.itemType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        isParameter(isParameter);
        setName(name);

        // Ensure that the uid will be unique at runtime
        synchronized (WaveItemBase.class) {
            setUid(++idGenerator);
        }
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
    @Override
    public String name() {
        return this.name;
    }

    /**
     * @return Returns the uid.
     */
    @Override
    public int uid() {
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
    @Override
    public Type type() {
        return this.itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object waveItem) {
        return waveItem instanceof WaveItemBase && uid() == ((WaveItemBase<?>) waveItem).uid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return uid();
    }

    /**
     * Set the name (optionally).
     *
     * @param name the name to set
     */
    private void setName(final String name) {
        this.name = name;
    }

    /**
     * @return Returns the isParameter.
     */
    @Override
    public boolean isParameter() {
        return this.isParameter;
    }

    /**
     * @param isParameter The isParameter to set.
     */
    @Override
    public void isParameter(final boolean isParameter) {
        this.isParameter = isParameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        if (type() != null) {
            if (type() instanceof ParameterizedType) {
                sb.append(((ParameterizedType) type()).toString());
            } else {
                sb.append(type().toString());
            }
        }

        if (name() != null) {
            sb.append(" ").append(name());
        }

        return sb.toString();
    }

    /**
     * Initialize a WaveItem for a WaveItems.
     *
     * @param waveItemEnum the enumerated wave item
     * @param wi the real waveItem
     */
    public static void initEnum(final WaveItemEnum waveItemEnum, final WaveItemBase<?> wi) {
        wi.setName(waveItemEnum.name());
    }

}
