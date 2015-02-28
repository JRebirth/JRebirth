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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The interface <strong>RGB01Color</strong>.
 *
 * @author Sébastien Bordes
 */
public class RGB01Color extends AbstractBaseColor {

    /** The green value [0.0-1.0]. */
    private final DoubleProperty redProperty = new SimpleDoubleProperty();

    /** The green value [0.0-1.0]. */
    private final DoubleProperty greenProperty = new SimpleDoubleProperty();

    /** The blue value [0.0-1.0]. */
    private final DoubleProperty blueProperty = new SimpleDoubleProperty();

    /**
     * Default Constructor.
     *
     * @param red the red value [0.0-1.0]
     * @param green the green value [0.0-1.0]
     * @param blue the blue value [0.0-1.0]
     */
    public RGB01Color(final double red, final double green, final double blue) {
        super();
        this.redProperty.set(red);
        this.greenProperty.set(green);
        this.blueProperty.set(blue);
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
        this.redProperty.set(red);
        this.greenProperty.set(green);
        this.blueProperty.set(blue);
    }

    /**
     * Return the red value [0.0-1.0].
     *
     * @return Returns the red.
     */
    public double red() {
        return this.redProperty.get();
    }

    /**
     * Return the red property.
     *
     * @return Returns the red property.
     */
    public DoubleProperty redProperty() {
        return this.redProperty;
    }

    /**
     * Return the green value [0.0-1.0].
     *
     * @return Returns the green.
     */
    public double green() {
        return this.greenProperty.get();
    }

    /**
     * Return the green property.
     *
     * @return Returns the green property.
     */
    public DoubleProperty greenProperty() {
        return this.greenProperty;
    }

    /**
     * Return the blue value [0.0-1.0].
     *
     * @return Returns the blue.
     */
    public double blue() {
        return this.blueProperty.get();
    }

    /**
     * Return the blue property.
     *
     * @return Returns the blue property.
     */
    public DoubleProperty blueProperty() {
        return this.blueProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String... parameters) {
        // Manage red composite
        if (parameters.length >= 1) {
            redProperty().set(Double.parseDouble(parameters[0]));
        }
        // Manage green composite
        if (parameters.length >= 2) {
            greenProperty().set(Double.parseDouble(parameters[1]));
        }
        // Manage blue composite
        if (parameters.length >= 3) {
            blueProperty().set(Double.parseDouble(parameters[2]));
        }
        // Manage opacity
        if (parameters.length >= 4) {
            opacityProperty().set(Double.parseDouble(parameters[3]));
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
