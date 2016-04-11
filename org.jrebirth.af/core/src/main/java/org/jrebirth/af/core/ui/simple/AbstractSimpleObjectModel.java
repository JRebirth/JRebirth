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
package org.jrebirth.af.core.ui.simple;

import javafx.scene.Node;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.NullView;
import org.jrebirth.af.api.ui.annotation.RootNodeId;
import org.jrebirth.af.core.ui.object.AbstractObjectModel;
import org.jrebirth.af.core.util.ClassUtility;

/**
 *
 * The interface <strong>AbstractSimpleModel</strong>.
 *
 * Base implementation for simple model without View-Controller part.
 *
 * @author Sébastien Bordes
 *
 * @param <N> the root node type
 * @param <O> the class type of the bindable object
 */
public abstract class AbstractSimpleObjectModel<N extends Node, O extends Object> extends AbstractObjectModel<Model, NullView, O> {

    /** The root node. */
    private N rootNode;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void prepareView() {
        try {
            // Prepare the root node
            this.rootNode = (N) ClassUtility.buildGenericType(this.getClass(), Node.class);

            // Find the RootNodeId annotation
            final RootNodeId rni = ClassUtility.getLastClassAnnotation(this.getClass(), RootNodeId.class);
            if (rni != null) {
                node().setId(rni.value().isEmpty() ? this.getClass().getSimpleName() : rni.value());
            }

            initSimpleView();
        } catch (final CoreException ce) {
            throw new CoreRuntimeException(ce);
        }

    }

    /**
     * Prepare the visual node hierarchy for this simple model.
     *
     * This method is equivalent to View.initView
     *
     * With simple model no View neither Controller are created.<br />
     *
     * You must manage them yourself.
     */
    protected abstract void initSimpleView();

    /**
     * {@inheritDoc}
     */
    @Override
    public N node() {
        return this.rootNode;
    }

}
