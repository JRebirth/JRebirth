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
package org.jrebirth.analyzer.ui.editor;

import org.jrebirth.analyzer.ui.editor.ball.BallModel;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.ui.InnerModels;
import org.jrebirth.core.ui.Model;

/**
 * The enumeration <strong>EditorInnerModels</strong>.
 * 
 * Declare inner models contained by EditorModel
 * 
 * @author Sébastien Bordes
 */
public enum EditorInnerModels implements InnerModels {

    /** The controls UI. */
    BALLS(BallModel.class);

    // /** The properties UI. */
    // WAVE(WaveModel.class)

    /** The model class of the inner model. */
    private final Class<? extends Model> modelClass;

    /**
     * Default Constructor.
     * 
     * @param modelClass the class to set
     */
    EditorInnerModels(final Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey getKey() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Model> getModelClass() {
        return this.modelClass;
    }

}
