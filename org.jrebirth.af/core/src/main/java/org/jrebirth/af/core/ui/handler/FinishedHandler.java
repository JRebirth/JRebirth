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

import org.jrebirth.af.core.ui.adapter.FinishedAdapter;

/**
 * The class <strong>FinishedHandler</strong>.
 * 
 * @author Sébastien Bordes
 */
public final class FinishedHandler extends AbstractNamedEventHandler<ActionEvent> {

    /** The Finished adapter. */
    private final FinishedAdapter finishedAdapter;

    /**
     * Default Constructor.
     * 
     * @param finishedAdapter the adapter to use
     */
    public FinishedHandler(final FinishedAdapter finishedAdapter) {
        super(FinishedHandler.class.getSimpleName());
        this.finishedAdapter = finishedAdapter;
    }

    /**
     * Return the implementation of the finished adapter interface.
     * 
     * @return Returns the finishedAdapter.
     */
    public FinishedAdapter getFinishedAdapter() {
        return this.finishedAdapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final ActionEvent actionEvent) {

        getFinishedAdapter().action(actionEvent);

    }
}
