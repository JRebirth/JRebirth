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
package org.jrebirth.af.core.ui.handler;

import javafx.event.ActionEvent;

import org.jrebirth.af.core.ui.adapter.ActionAdapter;

/**
 * The class <strong>ActionHandler</strong>.
 *
 * @author Sébastien Bordes
 */
public final class ActionHandler extends AbstractNamedEventHandler<ActionEvent> {

    /** The Action adapter. */
    private final ActionAdapter actionAdapter;

    /**
     * Default Constructor.
     *
     * @param actionAdapter the adapter to use
     */
    public ActionHandler(final ActionAdapter actionAdapter) {
        super(ActionHandler.class.getSimpleName());
        this.actionAdapter = actionAdapter;
    }

    /**
     * Return the implementation of the action adapter interface.
     *
     * @return Returns the actionAdapter.
     */
    public ActionAdapter getActionAdapter() {
        return this.actionAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final ActionEvent actionEvent) {

        getActionAdapter().action(actionEvent);

    }
}
