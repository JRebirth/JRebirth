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
package org.jrebirth.af.core.service.basic;

import static org.jrebirth.af.core.wave.WBuilder.waveTypeDo;

import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.annotation.OnWave;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.wave.JRebirthItems;

/**
 * The class <strong>StageService</strong>.
 *
 * @author Sébastien Bordes
 */
public interface StageService extends Service {

    public static final String STAGE_OPENED = "STAGE_OPENED";

    public static final String STAGE_CLOSED = "STAGE_CLOSED";

    public static final String STAGE_DESTROYED = "STAGE_DESTROYED";

    public static final String DESTROY_STAGE = "DESTROY_STAGE";

    public static final String CLOSE_STAGE = "CLOSE_STAGE";

    public static final String OPEN_STAGE = "OPEN_STAGE";

    /** Wave type use to load events. */
    WaveType DO_OPEN_STAGE = waveTypeDo(OPEN_STAGE).returnAction(STAGE_OPENED).returnItem(JRebirthItems.voidItem);

    /** Wave type use to load events. */
    WaveType DO_CLOSE_STAGE = waveTypeDo(CLOSE_STAGE).returnAction(STAGE_CLOSED).returnItem(JRebirthItems.voidItem);

    /** Wave type use to load events. */
    WaveType DO_DESTROY_STAGE = waveTypeDo(DESTROY_STAGE).returnAction(STAGE_DESTROYED).returnItem(JRebirthItems.voidItem);

    interface StageOpenedAware {
        @OnWave(STAGE_OPENED)
        void doStageOpened(Wave wave);
    }

    interface StageClosedAware {
        @OnWave(STAGE_CLOSED)
        void doStageClosed(Wave wave);
    }

    interface StageDestroyedAware {
        @OnWave(STAGE_DESTROYED)
        void doStageDestroyed(Wave wave);
    }

    interface StageAware extends StageOpenedAware, StageClosedAware, StageDestroyedAware {

    }

    /**
     * Open a stage.
     *
     * @param wave the source wave
     */
    @OnWave(OPEN_STAGE)
    @RunInto(RunType.JAT)
    void doOpenStage(final Wave wave);

    /**
     * Close a stage. (Hide it)
     *
     * @param wave the source wave
     */
    @OnWave(CLOSE_STAGE)
    @RunInto(RunType.JAT)
    void doCloseStage(final Wave wave);

    /**
     * Destroy the stage and dereference it.
     *
     * @param wave the source wave
     */
    @OnWave(DESTROY_STAGE)
    @RunInto(RunType.JAT)
    void doDestroyStage(final Wave wave);

}
