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

import java.util.Observable;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jrebirth.af.component.ui.beans.Tab;
import org.jrebirth.af.component.ui.beans.TabConfig;
import org.jrebirth.af.component.workbench.ui.DockableModel;
import org.jrebirth.af.core.key.UniqueKey;
import org.jrebirth.af.core.ui.DefaultModel;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.ui.object.DefaultObjectModel;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveItem;
import org.jrebirth.af.core.wave.WaveType;
import org.jrebirth.af.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TabModel is used to.
 * 
 * @author Sébastien Bordes
 */
public class TabModel extends DefaultObjectModel<TabModel, TabView, TabConfig> {

    public static WaveItem<Model> MODEL = new WaveItem<Model>() {};

    public static WaveType ADD = WaveTypeBase.build("addTab", MODEL);

    public static WaveType REMOVE = WaveTypeBase.build("removeTab", MODEL);

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TabModel.class);
    
    
    private ObservableList<Tab> tabs = FXCollections.observableArrayList();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initModel() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showView() {

    }

    public void addTab(DockableModel model, Wave wave) {
    	
    	Tab t = Tab.create().name(model.modelName()).modelKey((UniqueKey<? extends DockableModel>) model.getKey());
    	
   		tabs.add(0, t);
    	
    	getView().addTab(t);
    }

    public void removeTab(Model model, Wave wave) {

    }
}
