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
package org.jrebirth.core.resource;

import org.jrebirth.core.resource.color.ColorBuilder;
import org.jrebirth.core.resource.factory.ResourceBuilder;
import org.jrebirth.core.resource.font.FontBuilder;

/**
 * The class <strong>ResourceBuilders</strong>.
 * 
 * @author Sébastien Bordes
 */
public enum ResourceBuilders {

    /** The factory used to manage colors. */
    COLOR_BUILDER(new ColorBuilder()),

    /** The factory used to manage fonts. */
    FONT_BUILDER(new FontBuilder());

    /** The factory singleton. */
    private ResourceBuilder<?, ?, ?> builder;

    /**
     * Private Constructor.
     * 
     * @param builder the singleton factory
     */
    private ResourceBuilders(final ResourceBuilder<?, ?, ?> builder) {
        this.builder = builder;
    }

    /**
     * @return the singleton of the builder
     */
    public ResourceBuilder<?, ?, ?> use() {
        return this.builder;
    }
}
