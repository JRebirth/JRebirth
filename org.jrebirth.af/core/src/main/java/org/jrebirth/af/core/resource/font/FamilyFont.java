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

import java.util.Arrays;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * The class <strong>FamilyFont</strong> is used to create a Font by Family type.
 *
 * @author Sébastien Bordes
 */
public class FamilyFont extends AbstractBaseFont {

    /** The family name. */
    private final transient StringProperty familyProperty = new SimpleStringProperty();

    /** The font posture. */
    private final transient ObjectProperty<FontPosture> postureProperty = new SimpleObjectProperty<>();

    /** The font weight. */
    private final transient ObjectProperty<FontWeight> weightProperty = new SimpleObjectProperty<>();

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
        super(null, size);
        this.familyProperty.set(family);
        this.weightProperty.set(weight);
        this.postureProperty.set(posture);
    }

    /**
     * Return the family name.
     *
     * @return the family name
     */
    public String family() {
        return this.familyProperty.get();
    }

    /**
     * Return the family name property.
     *
     * @return the family name property
     */
    public StringProperty familyProperty() {
        return this.familyProperty;
    }

    /**
     * Return the font posture.
     *
     * @return the font posture
     */
    public FontPosture posture() {
        return this.postureProperty.get();
    }

    /**
     * Return the font posture property.
     *
     * @return the font posture property
     */
    public ObjectProperty<FontPosture> postureProperty() {
        return this.postureProperty;
    }

    /**
     * Return the font weight.
     *
     * @return the font weight
     */
    public FontWeight weight() {
        return this.weightProperty.get();
    }

    /**
     * Return the font weight property.
     *
     * @return the font weight property
     */
    public ObjectProperty<FontWeight> weightProperty() {
        return this.weightProperty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String... parameters) {
        if (parameters.length >= 1) {
            familyProperty().set(parameters[0]);
        }
        if (parameters.length >= 2) {
            sizeProperty().set(Double.parseDouble(parameters[1]));
        }
        if (parameters.length >= 3) {
            weightProperty().set(FontWeight.findByName(parameters[2]));
        }
        if (parameters.length >= 4) {
            postureProperty().set(FontPosture.findByName(parameters[3]));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(family(), size(), weight().toString(), posture().toString());
    }

}
