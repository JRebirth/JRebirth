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

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

import org.jrebirth.af.component.ui.Dockable;
import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.ui.beans.TabOrientation;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.util.ObjectUtility;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveItem;
import org.jrebirth.af.core.wave.WaveType;
import org.jrebirth.af.core.wave.WaveTypeBase;
import org.jrebirth.af.core.wave.checker.WaveChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class TabModel is used to.
 *
 * @author Sébastien Bordes
 */
public class TabModel extends DefaultObjectModel<TabModel, TabView, TabConfig> {

    /** The model. */
    public static WaveItem<String> TAB_KEY = new WaveItem<String>() {
    };

    /** The model. */
    public static WaveItem<Model> MODEL = new WaveItem<Model>() {
    };

    /** The add. */
    public static WaveType ADD = WaveTypeBase.build("addTab", MODEL);

    /** The remove. */
    public static WaveType REMOVE = WaveTypeBase.build("removeTab", MODEL);

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TabModel.class);

    /** The counter used to create tabKey for TabConfif that doesn't have one. */
    private static int TAB_COUNTER = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        final WaveChecker waveChecker = wave -> ObjectUtility.equalsOrBothNull(wave.get(TAB_KEY), getObject().tabKey());

        listen(waveChecker, ADD);
        listen(waveChecker, REMOVE);

        if (ObjectUtility.nullOrEmpty(getObject().tabKey())) {
            getObject().tabKey(TabModel.class.getSimpleName() + TAB_COUNTER++);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {

        getObject().orientationPy().addListener(this::onOrientationChanged);

        getObject().tabs().addListener(this::onTabsChanged);
    }

    private void onOrientationChanged(final ObservableValue<? extends TabOrientation> property, final TabOrientation oldValue, final TabOrientation newValue) {

    }

    private void onTabsChanged(final ListChangeListener.Change<? extends Tab> change) {
        while (change.next()) {
            System.err.println(change);
            if (change.wasPermutated()) {
                // getView().removeTab(change.getList().get(change.getFrom()));
                // getView().addTab(0, change.getList().get(change.getFrom()));
            }
            if (change.wasRemoved()) {
                getView().removeTab((List<Tab>) change.getRemoved());
            }
            if (change.wasAdded()) {
                getView().addTab(0, change.getList().get(change.getFrom()));
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
     * @param model the model
     * @param wave the wave
     */
    public <M extends Dockable> void addTab(final M model, final Wave wave) {
        // TO FIX
        insertTab(-1, model, wave);

    }

    /**
     * Removes the tab.
     *
     * @param model the model
     * @param wave the wave
     */
    public void removeTab(final Model model, final Wave wave) {

    }

    /**
     * Insert tab.
     *
     * @param idx the idx
     * @param model the model
     * @param wave the wave
     */
    @SuppressWarnings("unchecked")
    public <M extends Dockable> void insertTab(int idx, final M model, final Wave wave) {
        final Tab<M> t = Tab.create()
                .name(model.modelName())
                .modelKey(model.getKey());

        if (idx < 0) {
            idx = getObject().tabs().isEmpty() ? 0 : getObject().tabs().size() - 1;
        }

        getObject().tabs().add(idx, t);

        // getView().addTab(idx, t);

    }
}
