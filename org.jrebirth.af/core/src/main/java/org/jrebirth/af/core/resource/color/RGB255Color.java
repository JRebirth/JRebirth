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
package org.jrebirth.af.core.resource.color;

import java.util.Arrays;
import java.util.List;

/**
 * The interface <strong>RGB255Color</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class RGB255Color extends AbstractBaseColor {

    /** The green value [0-255]. */
    private int redProperty = 0;

    /** The green value [0-255]. */
    private int greenProperty = 0;

    /** The blue value [0-255]. */
    private int blueProperty = 0;

    /**
     * Default Constructor.
     * 
     * @param red the red component [0-255]
     * @param green the green component [0-255]
     * @param blue the blue component [0-255]
     */
    public RGB255Color(final int red, final int green, final int blue) {
        super();
        this.redProperty = red;
        this.greenProperty = green;
        this.blueProperty = blue;
    }

    /**
     * Default Constructor.
     * 
     * @param red the red component [0-255]
     * @param green the green component [0-255]
     * @param blue the blue component [0-255]
     * @param opacity the color opacity [0.0-1.0]
     */
    public RGB255Color(final int red, final int green, final int blue, final double opacity) {
        super(opacity);
        this.redProperty = red;
        this.greenProperty = green;
        this.blueProperty = blue;
    }

    /**
     * @return Returns the red [0-255].
     */
    public int red() {
        return this.redProperty;
    }

    // /**
    // * @return Returns the red property.
    // */
    // public IntegerProperty redProperty() {
    // return this.redProperty;
    // }

    /**
     * @return Returns the green [0-255].
     */
    public int green() {
        return this.greenProperty;
    }

    // /**
    // * @return Returns the green property.
    // */
    // public IntegerProperty greenProperty() {
    // return this.greenProperty;
    // }

    /**
     * @return Returns the blue [0-255].
     */
    public int blue() {
        return this.blueProperty;
    }

    // /**
    // * @return Returns the blue property.
    // */
    // public IntegerProperty blueProperty() {
    // return this.blueProperty;
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        // Manage red composite
        if (parameters.length >= 1) {
            redProperty = readInteger(parameters[0], 0, 255);
        }
        // Manage green composite
        if (parameters.length >= 2) {
            greenProperty = readInteger(parameters[1], 0, 255);
        }
        // Manage blue composite
        if (parameters.length >= 3) {
            blueProperty = readInteger(parameters[2], 0, 255);
        }
        // Manage opacity
        if (parameters.length >= 4) {
            opacityProperty().set(readDouble(parameters[3], 0.0, 1.0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(red(), green(), blue(), opacity());
    }

}
