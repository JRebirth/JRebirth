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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.api.resource.font.FontName;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.core.resource.ResourceBuilders;

/**
 * The interface <strong>FontItemReal</strong> used to provided convenient shortcuts for initializing a {@link Font}.
 *
 * @author Sébastien Bordes
 */
public interface FontItemReal extends FontItem {

    /**
     * {@inheritDoc}
     */
    @Override
    default ResourceBuilder<FontItem, FontParams, Font> builder() {
        return ResourceBuilders.FONT_BUILDER;
    }

    /**
     * The interface <strong>Real</strong> provides shortcuts method used to build and register a {@link RealFont}.
     */
    interface Real extends FontItemReal {

        /**
         * Build and register a {@link RealFont} {@link FontParams}.
         *
         * @param name the name of the font
         * @param size the size of the font
         * @param extension the font extension
         */
        default void real(final FontName name, final double size, final FontExtension extension) {
            set(new RealFont(name, size, extension));
        }

        /**
         * Build and register a {@link RealFont} {@link FontParams}.
         *
         * @param name the name of the font
         * @param size the size of the font
         */
        default void real(final FontName name, final double size) {
            set(new RealFont(name, size));
        }

    }

    /**
     * The interface <strong>Family</strong> provides shortcuts method used to build and register a {@link FamilyFont}.
     */
    interface Family extends FontItemReal {

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param extension the font extension
         */
        default void family(final String family, final double size, final FontExtension extension) {
            set(new FamilyFont(family, size, extension));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param weight the font weight {@link FontWeight}
         * @param extension the font extension
         */
        default void family(final String family, final double size, final FontWeight weight, final FontExtension extension) {
            set(new FamilyFont(family, size, extension, weight));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param extension the font extension
         * @param posture the font posture {@link FontPosture}
         */
        default void family(final String family, final double size, final FontExtension extension, final FontPosture posture) {
            set(new FamilyFont(family, size, extension, posture));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param extension the font extension
         * @param weight the font weight {@link FontWeight}
         * @param posture the font posture {@link FontPosture}
         */
        default void family(final String family, final double size, final FontExtension extension, final FontWeight weight, final FontPosture posture) {
            set(new FamilyFont(family, size, extension, weight, posture));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         */
        default void family(final String family, final double size) {
            set(new FamilyFont(family, size));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param weight the font weight {@link FontWeight}
         */
        default void family(final String family, final double size, final FontWeight weight) {
            set(new FamilyFont(family, size, weight));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param posture the font posture {@link FontPosture}
         */
        default void family(final String family, final double size, final FontPosture posture) {
            set(new FamilyFont(family, size, posture));
        }

        /**
         * Build and register a {@link FamilyFont} {@link FontParams}.
         *
         * @param family the font family
         * @param size the font size
         * @param weight the font weight {@link FontWeight}
         * @param posture the font posture {@link FontPosture}
         */
        default void family(final String family, final double size, final FontWeight weight, final FontPosture posture) {
            set(new FamilyFont(family, size, weight, posture));
        }

    }
}
