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

import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The class <strong>TestFonts</strong>.
 * 
 * @author Sébastien Bordes
 */
public interface TestFonts {

    /**************************************************************************************/
    /** ___________________________________Real Fonts.___________________________________ */
    /**************************************************************************************/

    /** The real font. */
    FontItem TEST_REAL_FONT_1 = FontItemBase.build(new RealFont(TestFontNames.Turtles, 10.0));

    /** The real font. */
    FontItem TEST_REAL_FONT_2 = FontItemBase.build(new RealFont(TestFontNames.Turtles, 12.0));

    /**************************************************************************************/
    /** _________________________________Family Fonts.___________________________________ */
    /**************************************************************************************/

    /** The family font. */
    FontItem TEST_FAMILY_FONT_1 = FontItemBase.build(new FamilyFont("Arial", 10.0, FontWeight.BOLD, FontPosture.ITALIC));

    /** The family font. */
    FontItem TEST_FAMILY_FONT_2 = FontItemBase.build(new FamilyFont("Verdana", 16.0, FontWeight.BOLD));

    /** The family font. */
    FontItem TEST_FAMILY_FONT_3 = FontItemBase.build(new FamilyFont("Arial", 17.0, FontPosture.ITALIC));

    /** The family font. */
    FontItem TEST_FAMILY_FONT_4 = FontItemBase.build(new FamilyFont("Verdana", 8.0));

}
