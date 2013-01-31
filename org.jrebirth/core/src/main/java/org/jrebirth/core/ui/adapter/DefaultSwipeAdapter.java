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
package org.jrebirth.core.ui.adapter;

import javafx.scene.input.SwipeEvent;

import org.jrebirth.core.ui.AbstractBaseController;

/**
 * The class <strong>DefaultSwipeAdapter</strong>.
 * 
 * @author Sébastien Bordes
 * 
 * @param <C> The controller class which manage this event adapter
 */
public class DefaultSwipeAdapter<C extends AbstractBaseController<?, ?>> extends AbstractDefaultAdapter<C> implements SwipeAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    public void anySwipe(final SwipeEvent swipeEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swipeDown(final SwipeEvent swipeEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swipeLeft(final SwipeEvent swipeEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swipeRight(final SwipeEvent swipeEvent) {
        // Nothing to do yet must be overridden
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swipeUp(final SwipeEvent swipeEvent) {
        // Nothing to do yet must be overridden
    }

}
