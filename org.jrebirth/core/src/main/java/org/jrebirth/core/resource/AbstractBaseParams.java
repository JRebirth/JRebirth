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

import org.jrebirth.core.resource.color.ResourceParams;

/**
 * The class <strong>AbstractBaseParams</strong>.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseParams implements ResourceParams {

    /** The flag that indicates if the resource params has changed. */
    private boolean changed;

    /** The dynamic key used to store the resource into a map. */
    private String dynamicKey;

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
    protected double readDouble(String doubleString, double min, double max) {
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
    protected int readInteger(String intString, int min, int max) {
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
    protected boolean readBoolean(String parameter) {

        return parameter != null && "true".equalsIgnoreCase(parameter) || "yes".equalsIgnoreCase(parameter) || "1".equalsIgnoreCase(parameter);
    }

    /**
     * Append a string parameter with its separator.
     * 
     * @param sb the string container
     * @param parameter the parameter to append
     */
    protected void append(StringBuilder sb, String parameter) {
        sb.append(parameter).append(PARAMETER_SEPARATOR);
    }

    /**
     * Append a number parameter with its separator.
     * 
     * @param sb the string container
     * @param parameter the number parameter to append
     */
    protected void append(StringBuilder sb, Number parameter) {
        append(sb, parameter.toString());
    }

    /**
     * Remove the last parameter separator and return the string of the given builder.
     * 
     * @param sb the string builder toc lean
     * 
     * @return the cleaned string
     */
    protected String cleanString(StringBuilder sb) {
        String res = sb.toString();
        return res.substring(0, res.length() - PARAMETER_SEPARATOR.length());
    }

}
