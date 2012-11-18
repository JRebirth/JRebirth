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
 * The interface <strong>AbstractBaseColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseColor implements ColorParams {

    /** The property used to store the opacity of the color. */
    private final SimpleDoubleProperty opacityProperty = new SimpleDoubleProperty();

    /**
     * Default Constructor.
     */
    public AbstractBaseColor() {
        this(1.0);
    }

    /**
     * Default Constructor.
     * 
     * @param opacity the opacity to use.
     */
    public AbstractBaseColor(final Double opacity) {
        super();
        this.opacityProperty.set(opacity);
    }

    /**
     * @return Returns the opacity.
     */
    public Double opacity() {
        return this.opacityProperty.get();
    }

    /**
     * @return Returns the opacity.
     */
    public DoubleProperty opacityProperty() {
        return this.opacityProperty;
    }

}
