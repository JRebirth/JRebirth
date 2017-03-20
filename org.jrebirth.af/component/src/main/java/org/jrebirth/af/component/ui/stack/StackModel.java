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

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.component.ui.beans.StackConfig;
import org.jrebirth.af.core.command.basic.showmodel.DisplayModelWaveBean;
import org.jrebirth.af.core.command.basic.showmodel.ShowFadingModelCommand;
import org.jrebirth.af.core.service.ServiceTaskReturnWaveListener;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.wave.JRebirthWaves;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.checker.ClassWaveChecker;
import org.jrebirth.af.core.wave.checker.DefaultWaveChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class StackModel is used to manage a StackPane that show other Model.
 *
 * @author Sébastien Bordes
 */
public class StackModel extends DefaultObjectModel<StackModel, StackView, StackConfig> {

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
            showPage(pageModelKey, wave);
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
            showPage(pageEnum.getModelKey(), wave);
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
        if (object() != null && object().pageEnumClass() != null) {
            res = (Class<PageEnum>) object().pageEnumClass();
        } else if (getFirstKeyPart() instanceof Class && PageEnum.class.isAssignableFrom((Class<?>) getFirstKeyPart())) {
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
        if (object() != null && object().stackName() != null) {
            res = object().stackName();
        } else if (getFirstKeyPart() instanceof String) {
            res = (String) getFirstKeyPart();
        }
        return res;
    }

    /**
     * Private method used to show another page.
     *
     * @param pageModelKey the mdoelKey for the page to show
     */
    private void showPage(final UniqueKey<? extends Model> pageModelKey, final Wave wave) {
        if (pageModelKey != null && !pageModelKey.equals(this.currentModelKey)) {

            LOGGER.info("Show Page Model: " + pageModelKey.toString());

            // Create the Wave Bean that will hold all data processed by chained commands
            final DisplayModelWaveBean waveBean = DisplayModelWaveBean.create()
                                                                      // Define the placeholder that will receive the content
                                                                      .childrenPlaceHolder(view().node().getChildren())
                                                                      // Allow to add element behind the stack to allow transition
                                                                      .appendChild(false)
                                                                      .showModelKey(pageModelKey)
                                                                      .hideModelKey(this.currentModelKey);

            this.currentModelKey = waveBean.showModelKey();

            if (wave != null) {
                // The method was called after having received a wave
                // It will show the next page by sending a regular command, processed asynchronously into JAT after a JIT cycle
                // This call is non-blocking
                sendWave(WBuilder.callCommand(ShowFadingModelCommand.class)
                                 .waveBean(waveBean)
                                 .relatedWave(wave)
                                 .addWaveListener(new ServiceTaskReturnWaveListener()));
            } else {
                // The showPage method has been called programmatically without any wave provided
                // This is the first call used to initialize the stack so we perform the show transition immediately
                final Wave showWave = WBuilder.callCommand(ShowFadingModelCommand.class)
                                              .waveBean(waveBean)
                                              .addDatas(JRebirthWaves.FORCE_SYNC);
                // Run synchronously into JAT immediately without a waiting a JIT cycle
                // This call will block current thread until page is shown
                getCommand(ShowFadingModelCommand.class, showWave.wUID()).run(showWave);

            }

        } else {
            LOGGER.debug("Page Model currently displayed: " + pageModelKey.toString());
        }
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

    /**
     * @param defaultPageModelKey the defaultPageModelKey to set
     */
    protected void setDefaultPageModelKey(UniqueKey<? extends Model> defaultPageModelKey) {
        this.defaultPageModelKey = defaultPageModelKey;
    }

}
