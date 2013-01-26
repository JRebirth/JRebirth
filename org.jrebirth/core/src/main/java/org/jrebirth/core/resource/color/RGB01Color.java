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
 * The interface <strong>RGB01Color</strong>.
 * 
 * @author Sébastien Bordes
 */
public class RGB01Color extends AbstractBaseColor {

    /** The green value [0.0-1.0]. */
    private final double red;

    /** The green value [0.0-1.0]. */
    private final double green;

    /** The blue value [0.0-1.0]. */
    private final double blue;

    /**
     * Default Constructor.
     * 
     * @param red the red value [0.0-1.0]
     * @param green the green value [0.0-1.0]
     * @param blue the blue value [0.0-1.0]
     */
    public RGB01Color(final double red, final double green, final double blue) {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Default Constructor.
     * 
     * @param red the red value [0.0-1.0]
     * @param green the green value [0.0-1.0]
     * @param blue the blue value [0.0-1.0]
     * @param opacity the color opacity component [0.0-1.0]
     */
    public RGB01Color(final double red, final double green, final double blue, final double opacity) {
        super(opacity);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Return the red value [0.0-1.0].
     * 
     * @return Returns the red.
     */
    public double red() {
        return this.red;
    }

    /**
     * Return the green value [0.0-1.0].
     * 
     * @return Returns the green.
     */
    public double green() {
        return this.green;
    }

    /**
     * Return the blue value [0.0-1.0].
     * 
     * @return Returns the blue.
     */
    public double blue() {
        return this.blue;
    }

}
