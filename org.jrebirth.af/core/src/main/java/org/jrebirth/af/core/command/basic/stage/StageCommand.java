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

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.core.command.single.ui.DefaultUIBeanCommand;
import org.jrebirth.af.core.service.basic.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>OpenStageCommand</strong>.
 *
 * @author Sébastien Bordes
 */
public class StageCommand extends DefaultUIBeanCommand<StageWaveBean> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StageCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void perform(final Wave wave) {

        final StageService ss = getService(StageService.class);

        LOGGER.info("Trigger stage action " + getWaveBean(wave).action());

        switch (getWaveBean(wave).action()) {
            case show:
                ss.doOpenStage(wave);
                break;
            case hide:
                ss.doCloseStage(wave);
                break;
            case destroy:
                ss.doDestroyStage(wave);
                break;
            default:
                LOGGER.error("Undefined StageAction");
        }

    }

    /**
     * Get the wave bean and cast it.
     *
     * @param wave the wave that hold the bean
     *
     * @return the casted wave bean
     */
    @Override
    public StageWaveBean getWaveBean(final Wave wave) {
        return wave.waveBean(StageWaveBean.class);
    }

}
