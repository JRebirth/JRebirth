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
    WaveItem<Class<?>> classItem = new WaveItem<Class<?>>() {
    };

    /** The boolean wave item. */
    WaveItem<Boolean> booleanItem = new WaveItem<Boolean>() {
    };

    /** The string wave item. */
    WaveItem<String> stringItem = new WaveItem<String>() {
    };

    /** The character wave item. */
    WaveItem<Character> characterItem = new WaveItem<Character>() {
    };

    /** The byte wave item. */
    WaveItem<Byte> byteItem = new WaveItem<Byte>() {
    };

    /** The short wave item. */
    WaveItem<Short> shortItem = new WaveItem<Short>() {
    };

    /** The integer wave item. */
    WaveItem<Integer> integerItem = new WaveItem<Integer>() {
    };

    /** The long wave item. */
    WaveItem<Long> longItem = new WaveItem<Long>() {
    };

    /** The float wave item. */
    WaveItem<Float> floatItem = new WaveItem<Float>() {
    };

    /** The double wave item. */
    WaveItem<Double> doubleItem = new WaveItem<Double>() {
    };

}
