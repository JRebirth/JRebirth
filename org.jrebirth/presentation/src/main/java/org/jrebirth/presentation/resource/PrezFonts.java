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
package org.jrebirth.presentation.resource;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.resource.font.FontItem;
import org.jrebirth.core.resource.font.RealFont;

/**
 * The class <strong>PrezFonts</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface PrezFonts {

    /** The splash font. */
    FontItem PAGE = create(new RealFont(PrezFontNames.BorisBlackBloxx, 36));

    /** The slide title font. */
    FontItem SLIDE_TITLE = create(new RealFont(PrezFontNames.Harabara, 45));

    /** The slide title font. */
    FontItem SLIDE_SUB_TITLE = create(new RealFont(PrezFontNames.Harabara, 30));

    /** The slide title font. */
    FontItem PREZ_TITLE = create(new RealFont(PrezFontNames.OliJo, 20));

    /** The typewriter font. */
    FontItem TYPEWRITER = create(new RealFont(PrezFontNames.Report_1942, 72));

    /** The splash font. */
    FontItem SPLASH = create(new RealFont(PrezFontNames.BorisBlackBloxx, 30));

}
