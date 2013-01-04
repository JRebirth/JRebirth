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

    /** The gray value [0.0-1.0]. */
    private final double gray;

    /**
     * Default Constructor.
     * 
     * @param gray the gray component [0.0-1.0]
     */
    public GrayColor(final double gray) {
        super();
        this.gray = gray;
    }

    /**
     * Default Constructor.
     * 
     * @param gray the gray component [0.0-1.0]
     * @param opacity the color opacity [0.0-1.0]
     */
    public GrayColor(final double gray, final double opacity) {
        super(opacity);
        this.gray = gray;
    }

    /**
     * Return the gray value.
     * 
     * @return Returns the gray [0.0-1.0].
     */
    public double gray() {
        return this.gray;
    }

}
