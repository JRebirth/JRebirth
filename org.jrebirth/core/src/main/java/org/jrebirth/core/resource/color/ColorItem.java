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

import javafx.scene.paint.Color;

import org.jrebirth.core.resource.ResourceBuilders;

/**
 * The class <strong>ColorItem</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class ColorItem implements ColorEnum {

    /** The generator of unique id. */
    private static int idGenerator;

    /** The unique identifier of the wave type. */
    private int uid;

    /**
     * Private Constructor.
     * 
     * @param colorParams the primitive values for the color
     */
    private ColorItem(final ColorParams colorParams) {
        factory().storeParams(this, colorParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color get() {
        return factory().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorBuilder factory() {
        return ResourceBuilders.COLOR_BUILDER;
    }

    /**
     * Build a color item.
     * 
     * @param colorParams the primitive values for the color
     * 
     * @return a new fresh color item object
     */
    public static ColorItem build(final ColorParams colorParams) {
        final ColorItem waveType = new ColorItem(colorParams);

        // Ensure that the uid will be unique at runtime
        synchronized (ColorItem.class) {
            waveType.setUid(++idGenerator);
        }
        return waveType;
    }

    /**
     * Gets the uid.
     * 
     * @return Returns the uid.
     */
    public int getUid() {
        return this.uid;
    }

    /**
     * Sets the uid.
     * 
     * @param uid The uid to set.
     */
    public void setUid(final int uid) {
        this.uid = uid;
    }

}
