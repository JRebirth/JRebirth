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
package org.jrebirth.af.component.ui.stack;

import org.jrebirth.af.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.af.core.command.basic.showmodel.ShowFadingModelCommand;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.checker.ClassWaveChecker;
import org.jrebirth.af.core.wave.checker.DefaultWaveChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StackModel is used to manage a StackPane that show other Model.
 *
 * @author Sébastien Bordes
 */
public class StackModel extends DefaultModel<StackModel, StackView> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StackModel.class);

    /** Hold the current model displayed as a page. */
    private UniqueKey<? extends Model> currentModelKey;

    /** The default page model key to display first. */
    private UniqueKey<? extends Model> defaultPageModelKey;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        // Listen StackWaves to be aware of any changes
        listen(new DefaultWaveChecker<>(StackWaves.STACK_NAME, getStackName()), StackWaves.SHOW_PAGE_MODEL);
        listen(new ClassWaveChecker<>(StackWaves.PAGE_ENUM, getPageEnumClass()), StackWaves.SHOW_PAGE_ENUM);
    }

    /**
     * Show page.
     *
     * Called when model received a SHOW_PAGE wave type.
     *
     * @param pageModelKey the modelKey for the page to show
     * @param stackName the unique string tha t identify the stack
     *
     * @param wave the wave
     */
    public void doShowPageModel(final UniqueKey<? extends Model> pageModelKey, final String stackName, final Wave wave) {

        if (getStackName() != null && getStackName().equals(stackName)) {
            showPage(pageModelKey);
        }

    }

    /**
     * Show page.
     *
     * Called when model received a SHOW_PAGE wave type.
     *
     * @param pageEnum the page enum for the model to show
     * @param wave the wave
     */
    public void doShowPageEnum(final PageEnum pageEnum, final Wave wave) {

        LOGGER.info("Show Page Enum: " + pageEnum.toString());
        if (getPageEnumClass() != null && getPageEnumClass().equals(pageEnum.getClass())) {
            showPage(pageEnum.getModelKey());
        }
    }

    /**
     * Returns the page enum class associated to this model.
     *
     * Checks the modelObject and return it only if it extends {@link PageEnum}
     *
     * @return the page enum class
     */
    @SuppressWarnings("unchecked")
    private Class<PageEnum> getPageEnumClass() {
        Class<PageEnum> res = null;
        if (getFirstKeyPart() instanceof Class && PageEnum.class.isAssignableFrom((Class<?>) getFirstKeyPart())) {
            res = (Class<PageEnum>) getFirstKeyPart();
        }
        return res;
    }

    /**
     * Returns the current stack name associated to this model.
     *
     * Checks the modelObject and return it only if it is a String
     *
     * @return the stack name
     */
    private String getStackName() {
        String res = null;
        if (getFirstKeyPart() instanceof String) {
            res = (String) getFirstKeyPart();
        }
        return res;
    }

    /**
     * Private method used to show another page.
     *
     * @param pageModelKey the mdoelKey for the page to show
     */
    private void showPage(final UniqueKey<? extends Model> pageModelKey) {
        LOGGER.info("Show Page Model: " + pageModelKey.toString());

        // Create the Wave Bean that will hold all data processed by chained commands
        final DisplayModelWaveBean waveBean = new DisplayModelWaveBean();

        // Define the placeholder that will receive the content
        waveBean.childrenPlaceHolder(getView().getRootNode().getChildren());

        // Allow to add element behind the stack to allow transition
        waveBean.appendChild(false);

        waveBean.showModelKey(pageModelKey);

        waveBean.hideModelKey(this.currentModelKey);
        this.currentModelKey = waveBean.showModelKey();

        callCommand(ShowFadingModelCommand.class, waveBean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
        // On redisplay show the start page only if no page is displayed
        if (this.currentModelKey == null) {
            // Manage default page for Page Enum
            if (getPageEnumClass() != null && getPageEnumClass().isEnum() && getPageEnumClass().getEnumConstants().length > 0) {
                doShowPageEnum(getPageEnumClass().getEnumConstants()[0], null);
            }
            // Manage default page for pageModelKey
            if (getDefaultPageModelKey() != null) {
                doShowPageModel(getDefaultPageModelKey(), getStackName(), null);
            }
        }
    }

    /**
     * @return the default PageModel Key
     */
    public UniqueKey<? extends Model> getDefaultPageModelKey() {
        return this.defaultPageModelKey;
    }

}
