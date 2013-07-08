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

/**
 * The interface <strong>RealFont</strong>.
 * 
 * @author Sébastien Bordes
 */
public class RealFont extends AbstractBaseFont {

    /** the font size. */
    private final double size;

    /**
     * Default Constructor.
     * 
     * @param name the font name
     * @param size the default font size
     */
    public RealFont(final FontName name, final double size) {
        super(name);
        this.size = size;
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append(name().toString()).append(PARAMETER_SEPARATOR);
        sb.append(this.size);

        return sb.toString();
    }

    /**
     * Parse the serialized real font string to build a fresh instance.
     * 
     * @param serializedRealFont the serialized string
     * 
     * @return a new fresh instance of {@link RealFont}
     */
    public static RealFont parseFont(final String serializedRealFont) {

        final String[] parameters = extractParameters(serializedRealFont);

        return new RealFont(new CustomFontName(parameters[0]), Double.parseDouble(parameters[1]));
    }
}
