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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import org.jrebirth.af.api.resource.font.FontExtension;
import org.jrebirth.af.api.resource.font.FontName;
import org.jrebirth.af.api.resource.font.FontParams;
import org.jrebirth.af.core.resource.AbstractBaseParams;

/**
 * The class <strong>AbstractBaseFont</strong> is the base class for other {@link FontParams} classes.
 *
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseFont extends AbstractBaseParams implements FontParams {

    /** The opacity of the color. */
    private final ObjectProperty<FontName> nameProperty = new SimpleObjectProperty<>();

    /** The font size. */
    private final DoubleProperty sizeProperty = new SimpleDoubleProperty();

    /** The font extension. */
    private final ObjectProperty<FontExtension> extensionProperty = new SimpleObjectProperty<>();

    /**
     * Default Constructor.
     *
     * @param name the name to use
     * @param size the default font size
     * @param extension the font extension
     */
    public AbstractBaseFont(final FontName name, final double size, final FontExtension extension) {
        super();
        this.nameProperty.set(name);
        this.sizeProperty.set(size);
        this.extensionProperty.set(extension);
    }

    /**
     * Default Constructor.
     *
     * @param name the name to use
     * @param size the default font size
     */
    public AbstractBaseFont(final FontName name, final double size) {
        this(name, size, FontExtension.TTF);
    }

    /**
     * @return Returns the font name.
     */
    @Override
    public FontName name() {
        return this.nameProperty.get();
    }

    /**
     * @return Returns the font name property.
     */
    public ObjectProperty<FontName> nameProperty() {
        return this.nameProperty;
    }

    /**
     * Return the font size.
     *
     * @return the font size
     */
    @Override
    public double size() {
        return this.sizeProperty.get();
    }

    /**
     * Return the font size property.
     *
     * @return the font size property
     */
    public DoubleProperty sizeProperty() {
        return this.sizeProperty;
    }

    /**
     * Return the font extension.
     *
     * @return the font extension
     */
    @Override
    public FontExtension extension() {
        return this.extensionProperty.get();
    }

    /**
     * Return the font extension property.
     *
     * @return the font extension property
     */
    public ObjectProperty<FontExtension> extensionProperty() {
        return this.extensionProperty;
    }
}
