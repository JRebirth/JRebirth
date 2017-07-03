/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2017
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
package org.jrebirth.af.core.resource.provided;

import javafx.scene.control.Labeled;
import javafx.scene.text.Font;

import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.font.CustomFontName;
import org.jrebirth.af.core.resource.font.RealFont;

/**
 * The Interface IconFont.
 */
public interface IconFont {

    /**
     * Builds the {@link FontItem} used to retrieve the {@link Font} resource.
     *
     * @param iconFontClass the icon font class
     *
     * @return the unique font item with default size
     */
    static FontItem buildItem(final Class<? extends IconFont> iconFontClass) {
        return buildItem(iconFontClass, FontExtension.TTF);
    }

    /**
     * Builds the {@link FontItem} used to retrieve the {@link Font} resource.
     *
     * @param iconFontClass the icon font class
     * @param fontExtension the font extension to use
     *
     * @return the unique font item with default size
     */
    static FontItem buildItem(final Class<? extends IconFont> iconFontClass, final FontExtension fontExtension) {
        return Resources.create(new RealFont(new CustomFontName(iconFontClass.getSimpleName()), 16, fontExtension));
    }

    /**
     * Use current icon for given {@link Labeled}.
     *
     * @param l the label to update with current icon
     */
    default void use(final Labeled l) {
        l.setFont(item().get());
        l.setText(charCode());
    }

    /**
     * Use current icon for given {@link Labeled}.
     *
     * @param l the label to update with current icon
     * @param fontSize the font size to used
     */
    default void use(final Labeled l, final double fontSize) {
        l.setFont(item().get(fontSize));
        l.setText(charCode());
    }

    /**
     * Returns the icon char code.
     *
     * @return the charCode representing the current icon
     */
    String charCode();

    /**
     * Returns the unique {@link FontItem} giving access to {@link Font} resource.
     *
     * @return the unique font item
     */
    FontItem item();

    /**
     * Return the enum entry name.
     *
     * @return the name
     */
    String name();

}
