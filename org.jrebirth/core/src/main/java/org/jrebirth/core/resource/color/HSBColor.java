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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * The class <strong>HSBColor</strong> used to create an HSB color.
 * 
 * @author Sébastien Bordes
 */
public class HSBColor extends AbstractBaseColor {

    /** The hue value [0.0-360.0] in degrees. */
    private final DoubleProperty hue = new SimpleDoubleProperty();

    /** The saturation value [0.0-1.0]. */
    private final DoubleProperty saturation = new SimpleDoubleProperty();

    /** The brightness value [0.0-1.0]. */
    private final DoubleProperty brightness = new SimpleDoubleProperty();

    /**
     * Default Constructor.
     * 
     * @param hue the color hue [0.0-360.0] in degrees
     * @param saturation the color saturation [0.0-1.0]
     * @param brightness the color brightness [0.0-1.0]
     */
    public HSBColor(final double hue, final double saturation, final double brightness) {
        super();
        this.hue.set(hue);
        this.saturation.set(saturation);
        this.brightness.set(brightness);
    }

    /**
     * Default Constructor.
     * 
     * @param hue the color hue [0.0-360.0] in degrees
     * @param saturation the color saturation [0.0-1.0]
     * @param brightness the color brightness [0.0-1.0]
     * @param opacity the color opacity [0.0-1.0]
     */
    public HSBColor(final double hue, final double saturation, final double brightness, final double opacity) {
        super(opacity);
        this.hue.set(hue);
        this.saturation.set(saturation);
        this.brightness.set(brightness);
    }

    /**
     * Return the hue value [0.0-360.0] in degrees.
     * 
     * @return Returns the hue.
     */
    public double hue() {
        return this.hue.get();
    }

    /**
     * Return the hue property.
     * 
     * @return Returns the hue property.
     */
    public DoubleProperty hueProperty() {
        return this.hue;
    }

    /**
     * Return the saturation value 0.0-1.0.
     * 
     * @return Returns the saturation.
     */
    public double saturation() {
        return this.saturation.get();
    }

    /**
     * Return the saturation property.
     * 
     * @return Returns the saturation property.
     */
    public DoubleProperty saturationProperty() {
        return this.saturation;
    }

    /**
     * Return the brightness value 0.0-1.0.
     * 
     * @return Returns the brightness.
     */
    public double brightness() {
        return this.brightness.get();
    }

    /**
     * Return the brightness property.
     * 
     * @return Returns the brightness property.
     */
    public DoubleProperty brightnessProperty() {
        return this.brightness;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        append(sb, hue());
        append(sb, saturation());
        append(sb, brightness());
        append(sb, opacity());

        return cleanString(sb);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        // Manage hue composite
        if (parameters.length >= 1) {
            hueProperty().set(readDouble(parameters[0], 0.0, 360.0));
        }
        // Manage saturation composite
        if (parameters.length >= 2) {
            saturationProperty().set(readDouble(parameters[1], 0.0, 1.0));
        }
        // Manage brightness composite
        if (parameters.length >= 3) {
            brightnessProperty().set(readDouble(parameters[2], 0.0, 1.0));
        }
        // Manage opacity
        if (parameters.length >= 4) {
            opacityProperty().set(readDouble(parameters[3], 0.0, 1.0));
        }
    }

}
