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
 * The interface <strong>FamilyFont</strong>.
 * 
 * @author Sébastien Bordes
 */
public class FamilyFont extends AbstractBaseFont {

    /** the font size. */
    private final double size;

    /** the family name. */
    private final String family;

    /** the font posture. */
    private final FontPosture posture;

    /** the font weight. */
    private final FontWeight weight;

    /**
     * Default Constructor.
     * 
     * Normal font weight and regular font posture will be used
     * 
     * @param family the font family
     * @param size the font size
     */
    public FamilyFont(final String family, final double size) {
        this(family, size, FontWeight.NORMAL, FontPosture.REGULAR);
    }

    /**
     * Default Constructor.
     * 
     * Regular font posture will be used
     * 
     * @param family the font family
     * @param size the font size
     * @param weight the font weight {@link FontWeight}
     */
    public FamilyFont(final String family, final double size, final FontWeight weight) {
        this(family, size, weight, FontPosture.REGULAR);
    }

    /**
     * Default Constructor.
     * 
     * Normal font weight will be used
     * 
     * @param family the font family
     * @param size the font size
     * @param posture the font posture {@link FontPosture}
     */
    public FamilyFont(final String family, final double size, final FontPosture posture) {
        this(family, size, FontWeight.NORMAL, posture);
    }

    /**
     * Default Constructor.
     * 
     * @param family the font family
     * @param size the font size
     * @param weight the font weight {@link FontWeight}
     * @param posture the font posture {@link FontPosture}
     */
    public FamilyFont(final String family, final double size, final FontWeight weight, final FontPosture posture) {
        super(null);
        this.family = family;
        this.size = size;
        this.weight = weight;
        this.posture = posture;
    }

    /**
     * Return the font size.
     * 
     * @return the font size
     */
    public double size() {
        return this.size;
    }

    /**
     * Return the family name.
     * 
     * @return the family name
     */
    public String family() {
        return this.family;
    }

    /**
     * Return the font posture.
     * 
     * @return the font posture
     */
    FontPosture posture() {
        return this.posture;
    }

    /**
     * Return the font weight.
     * 
     * @return the font weight
     */
    FontWeight weight() {
        return this.weight;
    }
}
