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
package org.jrebirth.component.ui.stack;

import org.jrebirth.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.core.command.basic.showmodel.ShowFadingModelCommand;
import org.jrebirth.core.key.UniqueKey;
import org.jrebirth.core.ui.DefaultModel;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.wave.Wave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StackModel.
 * 
 * @author Sébastien Bordes
 */
public class StackModel extends DefaultModel<StackModel, StackView> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StackModel.class);

    /** Hold the current model displayed as a page. */
    private UniqueKey<? extends Model> currentModelKey;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        listen(StackWaves.SHOW_PAGE_MODEL);
        listen(StackWaves.SHOW_PAGE_ENUM);
    }

    /**
     * Show page.
     * 
     * Called when model received a SHOW_PAGE wave type.
     * 
     * @param page the page
     * 
     * @param wave the wave
     */
    public void doShowPageModel(final UniqueKey<? extends Model> pageModelKey, String stackName, final Wave wave) {

        if (getStackName() != null && getStackName().equals(stackName)) {
            showPage(pageModelKey);
        }

    }

    /**
     * Show page.
     * 
     * Called when model received a SHOW_PAGE wave type.
     * 
     * @param page the page
     * @param wave the wave
     */
    public void doShowPageEnum(final PageEnum pageEnum, final Wave wave) {

        LOGGER.info("Show Page Enum: " + pageEnum.toString());
        if (getPageEnum() != null && getPageEnum().equals(pageEnum.getClass())) {
            showPage(pageEnum.getModelKey());
        }
    }

    @SuppressWarnings("unchecked")
    private Class<? extends PageEnum> getPageEnum() {
        Class<? extends PageEnum> res = null;
        if (getModelObject() instanceof Class && PageEnum.class.isAssignableFrom((Class<?>) getModelObject())) {
            res = (Class<? extends PageEnum>) getModelObject();
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    private String getStackName() {
        String res = null;
        if (getModelObject() instanceof String) {
            res = (String) getModelObject();
        }
        return res;
    }

    /**
     * TODO To complete.
     * 
     * @param pageModelKey
     */
    private void showPage(final UniqueKey<? extends Model> pageModelKey) {
        LOGGER.info("Show Page Model: " + pageModelKey.toString());

        // Create the Wave Bean that will hold all data processed by chained commands
        final DisplayModelWaveBean waveBean = new DisplayModelWaveBean();
        // Define the placeholder that will receive the content
        waveBean.setChidrenPlaceHolder(getView().getRootNode().getChildren());
        // Allow to add element behind the stack to allow transition
        waveBean.setAppendChild(false);

        waveBean.setShowModelKey(pageModelKey);

        waveBean.setHideModelKey(this.currentModelKey);
        this.currentModelKey = waveBean.getShowModelKey();

        callCommand(ShowFadingModelCommand.class, waveBean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // On redisplay show the start page
        // doShowPage(Page.Splash, null);
    }

}
