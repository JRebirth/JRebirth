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
package org.jrebirth.af.component.ui.dock;

import java.util.List;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

import org.jrebirth.af.api.ui.object.ModelObject;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.component.ui.beans.DockConfig;
import org.jrebirth.af.component.ui.beans.DockOrientation;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.util.ObjectUtility;
import org.jrebirth.af.core.wave.Builders;
import org.jrebirth.af.core.wave.WaveItemBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DockModel is used to.
 *
 * @author Sébastien Bordes
 */
public class DockModel extends DefaultObjectModel<DockModel, DockView, DockConfig> {

    /** The key of the dock model used as filter by wave checker. */
    public static WaveItemBase<String> DOCK_KEY = new WaveItemBase<String>(false) {
    };

    /** The model. */
    public static WaveItemBase<TabConfig> MODEL = new WaveItemBase<TabConfig>() {
    };

    /** The add. */
    public static WaveType ADD = Builders.waveType("ADD_CONTAINER").items(MODEL);

    /** The remove. */
    public static WaveType REMOVE = Builders.waveType("REMOVE_CONTAINER").items(MODEL);

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DockModel.class);

    /** The counter used to create tabKey for TabConfif that doesn't have one. */
    private static int DOCK_COUNTER = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        final WaveChecker waveChecker = wave -> ObjectUtility.equalsOrBothNull(wave.get(DOCK_KEY), getObject().dockKey());

        listen(waveChecker, ADD);
        listen(waveChecker, REMOVE);

        if (ObjectUtility.nullOrEmpty(getObject().dockKey())) {
            getObject().dockKey(DockModel.class.getSimpleName() + DOCK_COUNTER++);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {

        getObject().orientationPy().addListener(this::onOrientationChanged);

        getObject().panes().addListener(this::onPanesChanged);
    }

    private void onOrientationChanged(final ObservableValue<? extends DockOrientation> property, final DockOrientation oldValue, final DockOrientation newValue) {

    }

    @SuppressWarnings("unchecked")
    private void onPanesChanged(final ListChangeListener.Change<? extends TabConfig> change) {
        while (change.next()) {

            System.out.println(change);

            if (change.wasPermutated()) {
                System.err.println("PERMUTATION -------------------------------------------------------------------");
                // getView().removeTab(change.getList().get(change.getFrom()));
                // getView().addTab(0, change.getList().get(change.getFrom()));
            }

            if (change.wasRemoved()) {
                Platform.runLater(
                        () -> getView().removeContainer((List<TabConfig>) change.getRemoved())
                        );
            }

            if (change.wasAdded()) {
                Platform.runLater(
                        () -> getView().addContainer(change.getFrom(), change.getList().get(change.getFrom()))
                        );
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
    }

    public void addContainer(final ModelObject<TabConfig> model, final Wave wave) {
        insertContainer(-1, model, wave);
    }

    public void removeContainer(final ModelObject<TabConfig> model, final Wave wave) {

    }

    public void insertContainer(int idx, final ModelObject<TabConfig> model, final Wave wave) {
        // final TabBB<M> t = TabBB.create()
        // //.name(model.modelName())
        // .modelKey(model.getKey());

        final TabConfig t = model.getObject();// BehaviorBean(TabBehavior.class);

        if (idx < 0) {
            idx = getObject().panes().isEmpty() ? 0 : getObject().panes().size();
        }

        getObject().panes().add(idx, t);

        // getView().addTab(idx, t);

    }

}
