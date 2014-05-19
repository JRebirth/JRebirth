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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The class <strong>WebColor</strong> used to create a Web Color.
 *
 * @author Sébastien Bordes
 */
public class WebColor extends AbstractBaseColor {

    /** The hexadecimal string value [0-9A-F]{6} (without 0x or #). */
    private final StringProperty hexProperty = new SimpleStringProperty();

    /**
     * Default Constructor.
     *
     * @param hex the hexadecimal value [0-9A-F]{6} (without 0x or #)
     */
    public WebColor(final String hex) {
        super();
        this.hexProperty.set(hex);
    }

    /**
     * Default Constructor.
     *
     * @param hex the hexadecimal value [0-9A-F]{6} (without 0x or #)
     * @param opacity the color opacity [0.0-1.0]
     */
    public WebColor(final String hex, final double opacity) {
        super(opacity);
        this.hexProperty.set(hex);
    }

    /**
     * Return the hexadecimal string value [0-9A-F]{6} (without 0x or #).
     *
     * @return Returns the hexadecimal value.
     */
    public String hex() {
        return this.hexProperty.get();
    }

    /**
     * @return Returns the hex property.
     */
    public StringProperty hexProperty() {
        return this.hexProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        if (parameters.length >= 1) {

            String hexValue = parameters[0];
            if (hexValue.startsWith("0x")) {
                hexValue = hexValue.substring(2);
            }
            if (hexValue.charAt(0) == '#') {
                hexValue = hexValue.substring(1);
            }

            switch (hexValue.length()) {
            // 0x r g b
                case 3:
                    this.hexProperty.set(hexValue);
                    break;
                // 0x rr gg bb

                // 0x rr gg bb oo
                case 8:
                    // Not managed yet
                    break;
                case 6:
                default:
                    this.hexProperty.set(hexValue);
                    break;

            }

        }
        // Opacity
        if (parameters.length == 2) {
            opacityProperty().set(Double.parseDouble(parameters[1]));
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(hex(), opacity());
    }

}
