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
 * The interface <strong>GrayColor</strong>.
 * 
 * @author Sébastien Bordes
 */
public class GrayColor extends AbstractBaseColor {

    /** The gray value [0.0-1.0]. */
    private final DoubleProperty grayProperty = new SimpleDoubleProperty();

    /**
     * Default Constructor.
     * 
     * @param gray the gray component [0.0-1.0]
     */
    public GrayColor(final double gray) {
        super();
        this.grayProperty.set(gray);
    }

    /**
     * Default Constructor.
     * 
     * @param gray the gray component [0.0-1.0]
     * @param opacity the color opacity [0.0-1.0]
     */
    public GrayColor(final double gray, final double opacity) {
        super(opacity);
        this.grayProperty.set(gray);
    }

    /**
     * Return the gray value.
     * 
     * @return Returns the gray [0.0-1.0].
     */
    public double gray() {
        return this.grayProperty.get();
    }

    /**
     * Return the gray property.
     * 
     * @return Returns the gray property.
     */
    public DoubleProperty grayProperty() {
        return this.grayProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(gray(), opacity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        // Manage gray scale
        if (parameters.length >= 1) {
            grayProperty().set(Double.parseDouble(parameters[0]));
        }
        // Opacity
        if (parameters.length == 2) {
            opacityProperty().set(Double.parseDouble(parameters[1]));
        }
    }

}
