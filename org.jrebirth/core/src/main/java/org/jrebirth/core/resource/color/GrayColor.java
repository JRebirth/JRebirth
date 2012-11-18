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
package org.jrebirth.core.resource.color;

/**
 * The interface <strong>GrayColor</strong>.
 * 
 * @author Sébastien Bordes
 */
public class GrayColor extends AbstractBaseColor {

    /** The gray value. */
    private final int gray;

    /**
     * Default Constructor.
     * 
     * @param gray the gray component
     */
    public GrayColor(final int gray) {
        super();
        this.gray = gray;
    }

    /**
     * Default Constructor.
     * 
     * @param gray the gray component
     * @param opacity the color opacity
     */
    public GrayColor(final int gray, final double opacity) {
        super(opacity);
        this.gray = gray;
    }

    /**
     * Return the gray value.
     * 
     * @return Returns the gray.
     */
    public int gray() {
        return this.gray;
    }

}
