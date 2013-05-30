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
package org.jrebirth.core.ui;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.CoreRuntimeException;
import org.jrebirth.core.key.MultitonKey;
import org.jrebirth.core.util.ClassUtility;

/**
 * The interface <strong>AbstractObjectModel</strong>.
 * 
 * Base implementation of the model.
 * 
 * @author Sébastien Bordes
 * 
 * @param <M> the class type of the current model
 * @param <V> the class type of the view managed by this model
 * @param <O> the class type of the bindable object
 */
public abstract class AbstractObjectModel<M extends Model, V extends View<?, ?, ?>, O extends Object> extends AbstractModel<M, V> {

    /** The dedicated view component. */
    private O object;

    /**
     * Return the bindable object and create it if null
     * 
     * @return the bindable object
     * 
     * @throws CoreException if instantiation has failed
     */
    public final O getObject() {
        if (this.object == null) {
            buildObject();
        }
        return this.object;
    }

    /**
     * Create the default bindable object.
     */
    @SuppressWarnings("unchecked")
    protected void buildObject() {

        // Build the current view by reflection
        try {
            this.object = (O) ClassUtility.buildGenericType(this.getClass(), 2);
        } catch (final CoreException e) {
            throw new CoreRuntimeException("Failure while building the bindable object for model " + getClass(), e);
        }
    }

    /**
     * @param object The object to set.
     */
    public void setObject(final O object) {
        this.object = object;

        // Rebind current object
        bindInternal();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    protected final void bindObject() {

        // If the model object has been passed as part of the key
        if (getKey() instanceof MultitonKey<?>
                && ((MultitonKey<?>) getKey()).getValue() != null
                && ClassUtility.getGenericClass(this.getClass(), 2).isAssignableFrom(((MultitonKey<?>) getKey()).getValue().getClass())) {
            this.object = (O) ((MultitonKey<?>) getKey()).getValue();
        }
    }
}
