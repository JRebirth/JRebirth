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

import javafx.scene.paint.Color;

import org.jrebirth.af.api.resource.color.ColorItem;
import org.jrebirth.af.api.resource.color.ColorParams;
import org.jrebirth.af.core.resource.AbstractResourceItem;

/**
 * The class <strong>ColorItemBase</strong> provides a simple constructor used to build a {@link ColorItem} usable from anywhere.
 *
 * @author Sébastien Bordes
 */
public final class ColorItemBase extends AbstractResourceItem<org.jrebirth.af.api.resource.color.ColorItem, ColorParams, Color> implements ColorItemReal {

    /**
     * Build a new Color Item instance.
     *
     * @return a new {@link ColorItem} instance
     */
    public static ColorItemBase create() {
        return new ColorItemBase();
    }

}
