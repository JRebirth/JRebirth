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
package org.jrebirth.af.showcase.analyzer.ui.editor;

import org.jrebirth.af.core.concurrent.JRebirthThread;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.InnerModel;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.showcase.analyzer.ui.editor.ball.BallModel;

/**
 * The enumeration <strong>EditorInnerModels</strong>.
 * 
 * Declare inner models contained by EditorModel
 * 
 * @author Sébastien Bordes
 */
public enum EditorInnerModels implements InnerModel {

    /** The controls UI. */
    BALLS(BallModel.class);

    // /** The properties UI. */
    // WAVE(WaveModel.class)

    /** The unique key of the inner model. */
    private final UniqueKey<? extends Model> modelKey;

    /**
     * Default Constructor.
     * 
     * @param modelClass the class to set
     */
    EditorInnerModels(final Class<? extends Model> modelClass) {
        this.modelKey = JRebirthThread.getThread().getFacade().getUiFacade().buildKey(modelClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<? extends Model> getKey() {
        return this.modelKey;
    }

}
