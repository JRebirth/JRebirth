/**
 * Copyright JRebirth.org © 2011-2012 
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
 * The interface <strong>RGB255Color</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class RGB255Color extends AbstractBaseColor {

    /** The green value 0-255. */
    private final int red;

    /** The green value 0-255. */
    private final int green;

    /** The blue value 0-255. */
    private final int blue;

    /**
     * Default Constructor.
     * 
     * @param red the red component (0 - 255)
     * @param green the green component (0 - 255)
     * @param blue the blue component (0 - 255)
     */
    public RGB255Color(final int red, final int green, final int blue) {
        super();
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Default Constructor.
     * 
     * @param red the red component (0 - 255)
     * @param green the green component (0 - 255)
     * @param blue the blue component (0 - 255)
     * @param opacity the color opacity (0 - 1.0)
     */
    public RGB255Color(final int red, final int green, final int blue, final double opacity) {
        super(opacity);
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * @return Returns the red.
     */
    public int red() {
        return this.red;
    }

    /**
     * @return Returns the green.
     */
    public int green() {
        return this.green;
    }

    /**
     * @return Returns the blue.
     */
    public int blue() {
        return this.blue;
    }

}
