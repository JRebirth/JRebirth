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

/**
 * The Interface JRebirthItems.
 */
public interface JRebirthItems {

    /** The class wave item. */
    WaveItemBase<Class<?>> classItem = new WaveItemBase<Class<?>>() {
    };

    /** The boolean wave item. */
    WaveItemBase<Boolean> booleanItem = new WaveItemBase<Boolean>() {
    };

    /** The string wave item. */
    WaveItemBase<String> stringItem = new WaveItemBase<String>() {
    };

    /** The character wave item. */
    WaveItemBase<Character> characterItem = new WaveItemBase<Character>() {
    };

    /** The byte wave item. */
    WaveItemBase<Byte> byteItem = new WaveItemBase<Byte>() {
    };

    /** The short wave item. */
    WaveItemBase<Short> shortItem = new WaveItemBase<Short>() {
    };

    /** The integer wave item. */
    WaveItemBase<Integer> integerItem = new WaveItemBase<Integer>() {
    };

    /** The long wave item. */
    WaveItemBase<Long> longItem = new WaveItemBase<Long>() {
    };

    /** The float wave item. */
    WaveItemBase<Float> floatItem = new WaveItemBase<Float>() {
    };

    /** The double wave item. */
    WaveItemBase<Double> doubleItem = new WaveItemBase<Double>() {
    };

}
