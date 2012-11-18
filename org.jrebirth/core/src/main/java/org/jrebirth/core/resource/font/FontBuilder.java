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
package org.jrebirth.core.resource.font;

import java.io.File;
import java.util.List;

import javafx.scene.text.Font;

import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.resource.factory.AbstractResourceBuilder;

/**
 * The class <strong>FontBuilder</strong>.
 * 
 * Class used to manage font with weak reference.
 * 
 * @author Sébastien Bordes
 */
public final class FontBuilder extends AbstractResourceBuilder<FontEnum, FontParams, Font> {

    /**
     * The <code>TRUE_TYPE_FONT_EXT</code> field is used to dedine the file extension.
     */
    private static final String TRUE_TYPE_FONT_EXT = ".ttf";

    /** The root folder that store all application fonts. */
    private static String fontsFolder = "font";

    /**
     * {@inheritDoc}
     */
    @Override
    protected Font buildResource(final FontParams jrFont) {
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
        return javafx.scene.text.FontBuilder.create().name(transformFontName(rFont.name().get())).size(rFont.size()).build();
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
     * TRansform the font name by replacing _ by space.
     * 
     * @param fontName the font name to transform
     * @return the transformed font
     */
    private String transformFontName(final String fontName) {
        return fontName.replace("_", " ");
    }

    /**
     * Load the font file.
     * 
     * @param realFont the name of the font to load
     */
    private void checkFontStatus(final RealFont realFont) {

        final List<String> fonts = Font.getFontNames(transformFontName(realFont.name().get()));

        Font font;
        if (fonts.isEmpty()) {
            font = Font.loadFont(
                    Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(fontsFolder + File.separator + transformFontName(realFont.name().get()) + TRUE_TYPE_FONT_EXT), realFont.size());

            // The font name contains '_' in its file name
            if (font == null) {
                font = Font.loadFont(
                        Thread.currentThread().getContextClassLoader()
                                .getResourceAsStream(fontsFolder + File.separator + realFont.name().get() + TRUE_TYPE_FONT_EXT), realFont.size());

            }
            // Font has not been found with or without '_'
            if (font == null) {
                throw new CoreRuntimeException("Font not found " + transformFontName(realFont.name().get()));
            }
        }
    }

}
