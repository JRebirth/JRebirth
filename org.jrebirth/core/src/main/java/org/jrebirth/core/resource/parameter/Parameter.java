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
package org.jrebirth.core.resource.parameter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * The interface <strong>Parameter</strong>.
 * 
 * @author Sébastien Bordes
 */
public class Parameter<T> {

    /** The generator of unique id. */
    // private static int idGenerator;

    private String name;
    private T value;

    /** The unique identifier of the wave item. */
    private int uid;

    /** The type of the related object registered by this parameter. */
    private final Type parameterType;

    /**
     * Private Constructor.
     */
    public Parameter(final String name, final T value) {
        this.parameterType = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        // Ensure that the uid will be unique at runtime
        // synchronized (Parameter.class) {
        // setUid(++idGenerator);
        // }
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
     * @return Returns the uid.
     */
    // public int getUid() {
    // return this.uid;
    // }
    //
    // /**
    // * @param uid The uid to set.
    // */
    // private void setUid(final int uid) {
    // this.uid = uid;
    // }

    /**
     * @return Returns the itemType.
     */
    public Type getItemType() {
        return this.parameterType;
    }

    /**
     * {@inheritDoc}
     */
    // @Override
    // public boolean equals(final Object parameter) {
    // return parameter instanceof Parameter && getUid() == ((Parameter<?>) parameter).getUid();
    // }

    /**
     * {@inheritDoc}
     */
    // @Override
    // public int hashCode() {
    // return getUid();
    // }

}
