/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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

import javafx.event.Event;
import javafx.event.EventHandler;

import org.jrebirth.af.core.ui.adapter.EventAdapter;

/**
 * The class <strong>AbstractNamedEventHandler</strong>.
 *
 * @author Sébastien Bordes
 *
 * @param <E> the event to handle
 */
public abstract class AbstractNamedEventHandler<E extends Event, A extends EventAdapter> implements EventHandler<E> {

    /**
     * The name of the event handler. It should give some information about the call context
     */
    private final String name;
    /** The Action adapter. */
    protected final A adapter;

    /**
     * Default Constructor.
     *
     * @param name the name of this event handler
     */
    public AbstractNamedEventHandler(final String name, final A adapter) {
        super();
        this.name = name;
        this.adapter = adapter;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the implementation of the adapter interface.
     *
     * @return Returns the adapter.
     */
    public A adapter() {
        return this.adapter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getName();
    }

}
