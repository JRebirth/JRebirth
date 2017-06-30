/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.resource.font;

import javafx.scene.text.Font;

import org.jrebirth.af.api.resource.VariantResourceItem;

/**
 * The class <strong>FontItem</strong>.
 *
 * @author Sébastien Bordes
 */
public interface FontItem extends VariantResourceItem<FontItem, FontParams, Font, Double> {

    /**
     * {@inheritDoc}
     */
    @Override
    default FontItem set(final FontParams fontParams) {
        builder().storeParams(this, fontParams);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default Font get() {
        return builder().get(this);
    }

    @Override
    default Font get(final Double size) {
        return builder().get(this, size);
    }

    // @Override
    // default Font get(int fontSize) {
    // FontParams fp = builder().getParam(this);
    // fp.copy();
    //
    // FontItem fi = new FontItemBase();
    //
    //
    // return builder().get(this);
    // }

}
