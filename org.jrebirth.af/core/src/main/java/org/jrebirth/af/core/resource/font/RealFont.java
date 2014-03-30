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

/**
 * The interface <strong>RealFont</strong>.
 * 
 * @author Sébastien Bordes
 */
public class RealFont extends AbstractBaseFont {

    /**
     * Default Constructor.
     * 
     * @param name the font name
     * @param size the default font size
     */
    public RealFont(final FontName name, final double size) {
        super(name, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parse(final String[] parameters) {
        if (parameters.length >= 1) {
            nameProperty().set(new CustomFontName(parameters[0]));
        }
        if (parameters.length == 2) {
            sizeProperty().set(Double.parseDouble(parameters[1]));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<? extends Object> getFieldValues() {
        return Arrays.asList(name().toString(), size());
    }

}
