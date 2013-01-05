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

import org.jrebirth.core.resource.AbstractBaseParams;

/**
 * The interface <strong>AbstractBaseColor</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public abstract class AbstractBaseFont extends AbstractBaseParams implements FontParams {

    /** The opacity of the color. */
    private final FontName name;

    /**
     * Default Constructor.
     * 
     * @param name the name to use.
     */
    public AbstractBaseFont(final FontName name) {
        super();
        this.name = name;
    }

    /**
     * @return Returns the font name.
     */
    protected FontName name() {
        return this.name;
    }

}
