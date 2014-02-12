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

import javafx.scene.text.Font;

import org.jrebirth.af.core.resource.ResourceBuilders;
import org.jrebirth.af.core.resource.font.FontBuilder;
import org.jrebirth.af.core.resource.font.FontItem;
import org.jrebirth.af.core.resource.font.FontParams;
import org.jrebirth.af.core.resource.font.RealFont;

/**
 * The class <strong>PrezFonts</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public enum PrezFonts implements FontItem {

    /** The first font. */
    FONT_1(new RealFont(TestFontNames.Report_1942, 36)),

    /** The second font. */
    FONT_2(new RealFont(TestFontNames.Turtles, 36));

    /**
     * Default Constructor.
     * 
     * @param fontParams the font size
     */
    PrezFonts(final FontParams fontParams) {
        builder().storeParams(this, fontParams);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Font get() {
        return builder().get(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FontBuilder builder() {
        return ResourceBuilders.FONT_BUILDER;
    }

}
