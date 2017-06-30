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
package org.jrebirth.af.core.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.core.resource.AbstractResourceItem;

/**
 * The class <strong>FontItemBase</strong> provides a simple constructor used to build a {@link FontItem} usable from anywhere.
 *
 * @author Sébastien Bordes
 */
public final class FontItemImpl extends AbstractResourceItem<FontItem, FontParams, Font> implements FontItemBase {

    /**
     * Build a new Font Item instance.
     *
     * @return a new {@link FontItem} instance
     */
    public static FontItemImpl create() {
        return new FontItemImpl();
    }

}
