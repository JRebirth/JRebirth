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
package org.jrebirth.core.resource;

import java.lang.reflect.Field;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.jrebirth.core.resource.color.ResourceParams;
import org.jrebirth.core.util.ObjectUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>AbstractBaseParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseParams implements ResourceParams {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBaseParams.class);

    /** The flag that indicates if the resource params has changed. */
    private boolean changed;

    /** The dynamic key used to store the resource into a map. */
    private String dynamicKey;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void activateAutoRefresh() {

        final ChangeListener<Object> changeListener = new ChangeListener<Object>() {

            /**
             * Called when the value is updated.
             * 
             * @param value the observable value
             * @param oldValue the old value
             * @param newValue the new value
             */
            @Override
            public void changed(final ObservableValue<? extends Object> value, final Object oldValue, final Object newValue) {
                if (ObjectUtility.equalsOrNull(oldValue, newValue)) {
                    hasChanged(true);
                }
            }
        };

        // Iterate over each field that is a JavaFX Property
        for (final Field field : this.getClass().getDeclaredFields()) {
            if (Property.class.isAssignableFrom(field.getDeclaringClass())) {

                try {
                    ((Property<Object>) field.get(this)).addListener(changeListener);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    LOGGER.error("Cannot access to property " + field.getName() + " of class " + this.getClass(), e);
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasChanged() {
        // Nothing to do yet
        return this.changed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hasChanged(final boolean changed) {
        this.changed = changed;
    }

    /**
     * @return Returns the dynamicKey.
     */
    @Override
    public String getDynamicKey() {
        return this.dynamicKey;
    }

    /**
     * @param dynamicKey The dynamicKey to set.
     */
    @Override
    public void setDynamicKey(final String dynamicKey) {
        this.dynamicKey = dynamicKey;
    }

    /**
     * Read a double string value.
     * 
     * @param doubleString the double value
     * @param min the minimum value allowed
     * @param max the maximum value allowed
     * 
     * @return the value parsed according to its range
     */
    protected double readDouble(final String doubleString, final double min, final double max) {
        return Math.max(Math.min(Double.parseDouble(doubleString), max), min);
    }

    /**
     * Read ab integer string value.
     * 
     * @param intString the integer value
     * @param min the minimum value allowed
     * @param max the maximum value allowed
     * 
     * @return the value parsed according to its range
     */
    protected int readInteger(final String intString, final int min, final int max) {
        return Math.max(Math.min(Integer.parseInt(intString), max), min);
    }

    /**
     * Read a boolean string value.<br />
     * Values allowed are (case insensitive):
     * <ul>
     * <li>true</li>
     * <li>yes</li>
     * <li>1</li>
     * </ul>
     * 
     * @param parameter the boolean string value
     * 
     * @return the boolean or false
     */
    protected boolean readBoolean(final String parameter) {

        return parameter != null && "true".equalsIgnoreCase(parameter) || "yes".equalsIgnoreCase(parameter) || "1".equalsIgnoreCase(parameter);
    }

    /**
     * Append a string parameter with its separator.
     * 
     * @param sb the string container
     * @param parameter the parameter to append
     */
    protected void append(final StringBuilder sb, final String parameter) {
        sb.append(parameter).append(PARAMETER_SEPARATOR);
    }

    /**
     * Append a number parameter with its separator.
     * 
     * @param sb the string container
     * @param parameter the number parameter to append
     */
    protected void append(final StringBuilder sb, final Number parameter) {
        append(sb, parameter.toString());
    }

    /**
     * Remove the last parameter separator and return the string of the given builder.
     * 
     * @param sb the string builder toc lean
     * 
     * @return the cleaned string
     */
    protected String cleanString(final StringBuilder sb) {
        final String res = sb.toString();
        return res.substring(0, res.length() - PARAMETER_SEPARATOR.length());
    }

}
