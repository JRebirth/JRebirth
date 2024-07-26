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
package org.jrebirth.af.showcase.fxml.ui.main;

import javafx.event.ActionEvent;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.component.ui.stack.StackWaves;
import org.jrebirth.af.core.ui.DefaultController;
import org.jrebirth.af.core.wave.WBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>SampleController</strong>.
 *
 * @author
 */
public final class FXMLShowCaseController extends DefaultController<FXMLShowCaseModel, FXMLShowCaseView> {

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FXMLShowCaseController.class);

    /**
     * Default Constructor.
     *
     * @param view the view to control
     *
     * @throws CoreException if an error occurred while creating event handlers
     */
    public FXMLShowCaseController(final FXMLShowCaseView view) throws CoreException {
        super(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initEventAdapters() throws CoreException {

        // WaveData<Class<? extends PageEnum>> stackName = Builders.waveData(StackWaves.STACK_PAGES, FXMLShowCaseModel.STACK_PAGES);

        // Manage Ui Command Button
        linkWave(view().getShowIncluded(), ActionEvent.ACTION, StackWaves.SHOW_STACK_ITEM,
                 WBuilder.waveData(StackWaves.STACK_ITEM, FXMLPage.IncludedFxml));

        linkWave(view().getShowEmbedded(), ActionEvent.ACTION, StackWaves.SHOW_STACK_ITEM,
                 WBuilder.waveData(StackWaves.STACK_ITEM, FXMLPage.ViewEmbeddedFxml));

        linkWave(view().getShowStandalone(), ActionEvent.ACTION, StackWaves.SHOW_STACK_ITEM,
                 WBuilder.waveData(StackWaves.STACK_ITEM, FXMLPage.StandaloneFxml));

        linkWave(view().getShowHybrid(), ActionEvent.ACTION, StackWaves.SHOW_STACK_ITEM,
                 WBuilder.waveData(StackWaves.STACK_ITEM, FXMLPage.HybridFxml));

    }
}
