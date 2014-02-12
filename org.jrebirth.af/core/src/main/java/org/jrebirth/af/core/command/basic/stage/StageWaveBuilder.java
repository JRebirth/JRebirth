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
package org.jrebirth.af.core.command.basic.stage;

import org.jrebirth.af.core.command.CommandWaveBuilder;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.WaveBase;

/**
 * The class <strong>StageWaveBuilder</strong>. is used to build a new Stage Wave.
 * 
 * @author Sébastien Bordes
 */
public final class StageWaveBuilder extends CommandWaveBuilder<StageWaveBuilder, StageWaveBean> {

    /** The unique key used to identify a stage. */
    private String stageKey;

    /** The action to perform for this stage. */
    private StageAction action;

    /** The root model class to show attached to the stage. */
    private Class<? extends Model> rootModelClass;

    /**
     * Private constructor.
     */
    private StageWaveBuilder() {
        super(StageCommand.class, StageWaveBean.class);
    }

    /**
     * Static method to build a default builder.
     * 
     * @return a new fresh OpenStageWaveBuilder instance
     */
    public static StageWaveBuilder create() {
        return new StageWaveBuilder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(final WaveBase paramWave) {
        super.applyTo(paramWave);

        if (hasBit(0)) {
            getWaveBean(paramWave).setStageKey(this.stageKey);
        }
        if (hasBit(1)) {
            getWaveBean(paramWave).setAction(this.action);
        }
        if (hasBit(2)) {
            getWaveBean(paramWave).setRootModelClass(this.rootModelClass);
        }
    }

    /**
     * Define action to process.
     * 
     * @param stageKey the stage key
     * 
     * @return the builder
     */
    public StageWaveBuilder key(final String stageKey) {
        this.stageKey = stageKey;
        addBit(0);
        return this;
    }

    /**
     * Define action to process.
     * 
     * @param action the action to perform {@link StageAction}
     * 
     * @return the builder
     */
    public StageWaveBuilder action(final StageAction action) {
        this.action = action;
        addBit(1);
        return this;
    }

    /**
     * Define the root model class.
     * 
     * @param rootModelClass the root model class
     * 
     * @return the builder
     */
    public StageWaveBuilder rootModelClass(final Class<? extends Model> rootModelClass) {
        this.rootModelClass = rootModelClass;
        addBit(2);
        return this;
    }

}
