/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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

import org.jrebirth.af.api.wave.contract.WaveItem;

/**
 * The Interface JRebirthItems.
 */
public interface JRebirthItems {

    /** The Void wave item will be used only into a WaveData to wrap the VOID value when nothing is returned. The Void type cannot be used into a method signature. */
    WaveItem<Void> voidItem = new WaveItemBase<Void>(false) {
    };

    /** The exception wave item (not a parameter). */
    WaveItem<Throwable> exceptionItem = new WaveItemBase<Throwable>(false) {
    };

    /** The class wave item. */
    WaveItem<Class<?>> classItem = new WaveItemBase<Class<?>>() {
    };

    /** The boolean wave item. */
    WaveItem<Boolean> booleanItem = new WaveItemBase<Boolean>() {
    };

    /** The string wave item. */
    WaveItem<String> stringItem = new WaveItemBase<String>() {
    };

    /** The character wave item. */
    WaveItem<Character> characterItem = new WaveItemBase<Character>() {
    };

    /** The byte wave item. */
    WaveItem<Byte> byteItem = new WaveItemBase<Byte>() {
    };

    /** The short wave item. */
    WaveItem<Short> shortItem = new WaveItemBase<Short>() {
    };

    /** The integer wave item. */
    WaveItem<Integer> integerItem = new WaveItemBase<Integer>() {
    };

    /** The long wave item. */
    WaveItem<Long> longItem = new WaveItemBase<Long>() {
    };

    /** The float wave item. */
    WaveItem<Float> floatItem = new WaveItemBase<Float>() {
    };

    /** The double wave item. */
    WaveItem<Double> doubleItem = new WaveItemBase<Double>() {
    };

}
