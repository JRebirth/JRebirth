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

import java.util.Collections;
import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;

import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.object.ModelObject;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.component.command.dock.AddDockCommand;
import org.jrebirth.af.component.command.dock.DockWaveBean;
import org.jrebirth.af.component.command.dock.RemoveDockCommand;
import org.jrebirth.af.component.ui.beans.DockConfig;
import org.jrebirth.af.component.ui.beans.DockOrientation;
import org.jrebirth.af.component.ui.beans.PartConfig;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.util.ObjectUtility;
import org.jrebirth.af.core.wave.WBuilder;
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
    public static WaveItemBase<Model> MODEL = new WaveItemBase<Model>() {
    };

    /** The add. */
    public static WaveType ADD = WBuilder.waveType("ADD_PART").items(MODEL);

    /** The remove. */
    public static WaveType REMOVE = WBuilder.waveType("REMOVE_PART").items(MODEL);

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DockModel.class);

    /** The counter used to create tabKey for TabConfif that doesn't have one. */
    private static int DOCK_COUNTER = 0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

        final WaveChecker waveChecker = wave -> ObjectUtility.equalsOrBothNull(wave.get(DOCK_KEY), object().key());

        listen(waveChecker, ADD);
        listen(waveChecker, REMOVE);

        if (ObjectUtility.nullOrEmpty(object().key())) {
            object().key(DockModel.class.getSimpleName() + DOCK_COUNTER++);
        }

        for (final PartConfig<?> pc : object().panes()) {

            callCommand(AddDockCommand.class, DockWaveBean.create().dockConfig(object()).parts(pc));

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void bind() {

        object().orientationPy().addListener(this::onOrientationChanged);

        object().panes().addListener(this::onPanesChanged);
    }

    private void onOrientationChanged(final ObservableValue<? extends DockOrientation> property, final DockOrientation oldValue, final DockOrientation newValue) {

    }

    @SuppressWarnings("unchecked")
    private void onPanesChanged(final ListChangeListener.Change<? extends PartConfig<?>> change) {
        while (change.next()) {

            System.out.println(change);

            if (change.wasPermutated()) {
                System.err.println("PERMUTATION -------------------------------------------------------------------");
                // getView().removeTab(change.getList().get(change.getFrom()));
                // getView().addTab(0, change.getList().get(change.getFrom()));
            }

            if (change.wasRemoved()) {
                final List<PartConfig<?>> rList = (List<PartConfig<?>>) change.getRemoved();
                callCommand(RemoveDockCommand.class, DockWaveBean.create().dockConfig(object()).parts(rList.toArray(new PartConfig<?>[0])));
            }

            if (change.wasAdded()) {
                // index change.getFrom()
                callCommand(AddDockCommand.class, DockWaveBean.create().dockConfig(object()).parts(change.getList().get(change.getFrom())));
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {
    }

    public void doAddPart(final ModelObject<PartConfig<?>> model, final Wave wave) {
        if (!object().panes().contains(model.object())) {
            doInsertPart(-1, model, wave);
        }
        view().addItem(-1, model.node());
    }

    public void doRemovePart(final ModelObject<PartConfig<?>> model, final Wave wave) {
        object().panes().remove(model.object());
        view().removeItem(Collections.singletonList(model.node()));
    }

    public void doInsertPart(int idx, final ModelObject<PartConfig<?>> model, final Wave wave) {
        // final TabBB<M> t = TabBB.create()
        // //.name(model.modelName())
        // .modelKey(model.getKey());

        final PartConfig<?> partConfig = model.object();// BehaviorBean(TabBehavior.class);

        if (idx < 0) {
            idx = object().panes().isEmpty() ? 0 : object().panes().size();
        }

        object().panes().add(idx, partConfig);

        // getView().addTab(idx, t);

    }

}
