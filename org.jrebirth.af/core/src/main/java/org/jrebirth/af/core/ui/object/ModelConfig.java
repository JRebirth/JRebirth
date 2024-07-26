/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2024
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
package org.jrebirth.af.core.ui.object;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.object.ModelDescriptor;

public class ModelConfig<M extends Model, MC extends ModelConfig<M, MC>> implements ModelDescriptor<M, MC> {

    public static final String UNDETERMINED = "Undetermined";

    private String id = UNDETERMINED;

    private String style = UNDETERMINED;

    private String styleClass = UNDETERMINED;

    protected final Class<M> modelClass;

    public static <M extends Model, MC extends ModelConfig<M, MC>> ModelConfig<M, MC> create(final Class<M> modelClass) {
        return new ModelConfig<M, MC>(modelClass);
    }

    public ModelConfig(final Class<M> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Class<M> modelClass() {
        return this.modelClass;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MC id(final String id) {
        this.id = id;
        return (MC) this;
    }

    @Override
    public String style() {
        return this.style;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MC style(final String style) {
        this.style = style;
        return (MC) this;
    }

    @Override
    public String styleClass() {
        return this.styleClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MC styleClass(final String styleClass) {
        this.styleClass = styleClass;
        return (MC) this;
    }

    @Override
    public String toString() {
        return this.modelClass.getName() + "|" + this.id;
    }

}
