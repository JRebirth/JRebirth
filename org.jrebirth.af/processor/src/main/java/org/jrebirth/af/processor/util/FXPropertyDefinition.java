/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2015
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
package org.jrebirth.af.processor.util;

/**
 * The Class FXPropertyDefinition.
 */
public class FXPropertyDefinition {

    /** The type. */
    private String type;

    /** The name. */
    private String name;

    /** The need property. */
    private boolean needProperty;

    /** The need getter. */
    private boolean needGetter;

    /** The need setter. */
    private boolean needSetter;

    /**
     * Gets the type.
     *
     * @return Returns the type.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Sets the type.
     *
     * @param type The type to set.
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the name.
     *
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name The name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Checks if is need property.
     *
     * @return Returns the needProperty.
     */
    public boolean isNeedProperty() {
        return this.needProperty;
    }

    /**
     * Sets the need property.
     *
     * @param needProperty The needProperty to set.
     */
    public void setNeedProperty(final boolean needProperty) {
        this.needProperty = needProperty;
    }

    /**
     * Checks if is need getter.
     *
     * @return Returns the needGetter.
     */
    public boolean isNeedGetter() {
        return this.needGetter;
    }

    /**
     * Sets the need getter.
     *
     * @param needGetter The needGetter to set.
     */
    public void setNeedGetter(final boolean needGetter) {
        this.needGetter = needGetter;
    }

    /**
     * Checks if is need setter.
     *
     * @return Returns the needSetter.
     */
    public boolean isNeedSetter() {
        return this.needSetter;
    }

    /**
     * Sets the need setter.
     *
     * @param needSetter The needSetter to set.
     */
    public void setNeedSetter(final boolean needSetter) {
        this.needSetter = needSetter;
    }
}
