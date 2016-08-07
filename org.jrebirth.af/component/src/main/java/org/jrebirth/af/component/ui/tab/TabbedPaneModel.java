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
package org.jrebirth.af.component.ui.tab;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.component.behavior.dockable.data.Dockable;
import org.jrebirth.af.component.ui.beans.TabbedPaneConfig;
import org.jrebirth.af.component.ui.beans.TabbedPaneOrientation;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.util.ObjectUtility;
import org.jrebirth.af.core.wave.WBuilder;
import org.jrebirth.af.core.wave.WaveItemBase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TabbedPaneModel is used to.
 *
 * @author Sébastien Bordes
 */
public class TabbedPaneModel extends DefaultObjectModel<TabbedPaneModel, TabbedPaneView, TabbedPaneConfig> {

    /** The key of the tab model used as filter by wave checker. */
    public static WaveItemBase<String> TAB_KEY = new WaveItemBase<String>(false) {
    };

    /** The model. */
    public static WaveItemBase<Dockable> TAB = new WaveItemBase<Dockable>() {
    };

    /** The add. */
    public static WaveType ADD = WBuilder.waveType("ADD_TAB").items(TAB);

    /** The remove. */
    public static WaveType REMOVE = WBuilder.waveType("REMOVE_TAB").items(TAB);

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TabbedPaneModel.class);

    /** The counter used to create tabKey for TabConfif that doesn't have one. */
    private static int TAB_COUNTER = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        final WaveChecker waveChecker = wave -> ObjectUtility.equalsOrBothNull(wave.get(TAB_KEY), object().id());

        listen(waveChecker, ADD);
        listen(waveChecker, REMOVE);

        if (ObjectUtility.nullOrEmpty(object().id())) {
            object().id(TabbedPaneModel.class.getSimpleName() + TAB_COUNTER++);
        }

        // Manage Style
        node().getStyleClass().add(this.getClass().getSimpleName());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {

        object().orientationPy().addListener(this::onOrientationChanged);

        object().tabs().addListener(this::onTabsChanged);
    }

    private void onOrientationChanged(final ObservableValue<? extends TabbedPaneOrientation> property, final TabbedPaneOrientation oldValue, final TabbedPaneOrientation newValue) {

        view().reloadButtonBar();

        for (final Dockable tab : object().tabs()) {
            view().addTab(object().tabs().size(), tab);
        }

    }

    @SuppressWarnings("unchecked")
    private void onTabsChanged(final ListChangeListener.Change<? extends Dockable> change) {
        while (change.next()) {

            System.out.println(change);

            if (change.wasPermutated()) {
                System.err.println("PERMUTATION -------------------------------------------------------------------");
                // getView().removeTab(change.getList().get(change.getFrom()));
                // getView().addTab(0, change.getList().get(change.getFrom()));
            }

            if (change.wasRemoved()) {
                Platform.runLater(
                                  () -> view().removeTab((List<Dockable>) change.getRemoved()));
            }

            if (change.wasAdded()) {
                Platform.runLater(
                                  () -> view().addTab(change.getFrom(), change.getList().get(change.getFrom())));
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {

    }

    /**
     * Adds the tab.
     *
     * @param tab the tab
     * @param wave the wave
     */
    public void doAddTab(final Dockable tab, final Wave wave) {
        // FIXME
        // if(model.hasBehavior()){
        doInsertTab(-1, tab, wave);
        // }

    }

    /**
     * Removes the tab.
     *
     * @param tab the tab
     * @param wave the wave
     */
    public void doRemoveTab(final Dockable tab, final Wave wave) {

    }

    /**
     * Insert tab.
     *
     * @param idx the idx
     * @param tab the tab
     * @param wave the wave
     */
    public void doInsertTab(int idx, final Dockable tab, final Wave wave) {
        // final TabBB<M> t = TabBB.create()
        // //.name(model.modelName())
        // .modelKey(model.getKey());

        // Tab t = model.getBehaviorBean(TabBehavior.class);

        if (idx < 0) {
            idx = object().tabs().isEmpty() ? 0 : object().tabs().size();
        }
        // view().addTab(idx, tab);

        object().tabs().add(idx, tab);

    }
}
