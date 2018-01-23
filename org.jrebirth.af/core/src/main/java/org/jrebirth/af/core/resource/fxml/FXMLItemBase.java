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
package org.jrebirth.af.core.resource.fxml;

import org.jrebirth.af.api.resource.builder.ResourceBuilder;
import org.jrebirth.af.api.resource.fxml.FXMLItem;
import org.jrebirth.af.api.resource.fxml.FXMLParams;
import org.jrebirth.af.api.ui.fxml.FXMLComponent;
import org.jrebirth.af.core.resource.ResourceBuilders;

/**
 * The class <strong>FXMLItem</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FXMLItemBase extends FXMLItem {

    /**
     * {@inheritDoc}
     */
    @Override
    default ResourceBuilder<FXMLItem, FXMLParams, FXMLComponent> builder() {
        return ResourceBuilders.FXML_BUILDER;
    }

    /**
     * Build a new FXMLComponent without storing it.
     *
     * If you call 'n' times this method you will obtain 'n' {@link FXMLComponent} instances.
     *
     * @return a new {@link FXMLComponent} instance
     */
    @Override
    default FXMLComponent getNew() {
        return ((FXMLBuilder) builder()).buildResource(this, builder().getParam(this));
    }

}
