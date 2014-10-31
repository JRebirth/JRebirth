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

import java.util.Collections;
import java.util.List;

import javafx.scene.text.Font;

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.font.FontItem;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.core.resource.Resources;
import org.jrebirth.af.core.resource.builder.AbstractResourceBuilder;
import org.jrebirth.af.core.resource.provided.JRebirthParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>FontBuilder</strong> is used to build {@link Font} instance and store them with weak references..
 *
 * @author Sébastien Bordes
 */
public final class FontBuilder extends AbstractResourceBuilder<FontItem, FontParams, Font> implements ResourceBuilder<FontItem, FontParams, Font> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FontBuilder.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected Font buildResource(final FontItem fontItem, final FontParams jrFont) {
        Font font = null;
        if (jrFont instanceof RealFont) {
            // Build the requested font
            font = buildRealFont((RealFont) jrFont);
        } else if (jrFont instanceof FamilyFont) {
            // Build a family like font
            font = buildFamilyFont((FamilyFont) jrFont);
        } else {
            // Return the default font
            font = Font.getDefault();
        }
        return font;
    }

    /**
     * Build a real font with name and size.
     *
     * @param rFont the real font enum
     *
     * @return the javafx font
     */
    private Font buildRealFont(final RealFont rFont) {
        checkFontStatus(rFont);
        return javafx.scene.text.FontBuilder.create()
                                            .name(transformFontName(rFont.name().name()))
                                            .size(rFont.size())
                                            .build();
    }

    /**
     * Build a Family Font with name and size.
     *
     * @param familyFont the family font enum
     *
     * @return the javafx font
     */
    private Font buildFamilyFont(final FamilyFont familyFont) {
        Font font = null;
        if (familyFont.posture() == null && familyFont.weight() == null) {
            font = Font.font(transformFontName(familyFont.family()), familyFont.size());
        } else if (familyFont.posture() == null) {
            font = Font.font(transformFontName(familyFont.family()), familyFont.weight(), familyFont.size());
        } else if (familyFont.weight() == null) {
            font = Font.font(transformFontName(familyFont.family()), familyFont.posture(), familyFont.size());
        } else {
            font = Font.font(transformFontName(familyFont.family()), familyFont.weight(), familyFont.posture(), familyFont.size());
        }
        return font;
    }

    /**
     * Transform the font name by replacing _ by space.
     *
     * @param fontName the font name to transform
     *
     * @return the transformed font
     */
    private String transformFontName(final String fontName) {
        return fontName.replace("_", " ");
    }

    /**
     * Load the font file.
     *
     * @param fontParams the name of the font to load
     */
    private void checkFontStatus(final FontParams fontParams) {

        // Try to load system fonts
        final List<String> fonts = Font.getFontNames(transformFontName(fontParams.name().name()));

        Font font = null;
        String fontName = null;

        if (fonts.isEmpty()) {

            final List<String> fontPaths = fontParams instanceof RealFont && ((RealFont) fontParams).skipFontsFolder()
                    ? Collections.singletonList("")
                    : JRebirthParameters.FONT_FOLDER.get();

            for (int i = 0; i < fontPaths.size() && font == null; i++) {

                String fontPath = fontPaths.get(i);
                if (!fontPath.isEmpty()) {
                    fontPath += Resources.PATH_SEP;
                }

                // This variable will hold the 2 alternative font names
                fontName = fontPath + transformFontName(fontParams.name().name()) + JRebirthParameters.TRUE_TYPE_FONT_EXTENSION.get();

                LOGGER.trace("Try to load Transformed Font  {}", fontName);
                font = Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream(fontName), fontParams.size());

                // The font name contains '_' in its file name to replace ' '
                if (font == null) {
                    fontName = fontPath + fontParams.name().name() + JRebirthParameters.TRUE_TYPE_FONT_EXTENSION.get();
                    LOGGER.trace("Try to load Raw Font  {}", fontName);
                    font = Font.loadFont(Thread.currentThread().getContextClassLoader().getResourceAsStream(fontName), fontParams.size());

                    if (font != null) {
                        // Raw font has been loaded
                        LOGGER.info("{} Raw Font loaded", fontName);
                    }
                } else {
                    // Transformed font has been loaded
                    LOGGER.info("{} Transformed Font loaded", fontName);
                }

            }

            if (font == null) {
                // Neither transformed nor raw font has been loaded (with or without '_')
                LOGGER.error("Font : {} not found into base folder: {}", fontParams.name().name(), JRebirthParameters.FONT_FOLDER.get());
            }
        }
    }
}
