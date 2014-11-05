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
package org.jrebirth.af.core.ui.object;

import javafx.scene.Node;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.ui.Controller;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.ui.View;
import org.jrebirth.af.api.ui.object.ModelObject;
import org.jrebirth.af.core.ui.AbstractModel;
import org.jrebirth.af.core.util.ClassUtility;

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
public abstract class AbstractObjectModel<M extends Model, V extends View<?, ?, ?>, O extends Object> extends AbstractModel<M, V> implements ModelObject<O> {

    /** The list of type to exclude in order to find the object type from generics declaration. */
    private static final Class<?>[] OBJECT_EXCLUDED_CLASSES = new Class<?>[] { Model.class, View.class, Node.class, Controller.class };

    /** The dedicated view component. */
    private O object;

    /**
     * Return the bindable object and create it if null.
     *
     * @return the bindable object
     */
    @Override
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

        final Class<?> objectType = ClassUtility.findGenericClass(this.getClass(), OBJECT_EXCLUDED_CLASSES);

        // If not generic type is defined for Object, object field will remain null
        if (objectType != null) {
            Object keyPart = null;
            boolean found = false;
            for (int i = 0; !found && i < getListKeyPart().size(); i++) {
                keyPart = getListKeyPart().get(i);

                if (objectType.isAssignableFrom(keyPart.getClass())) {

                    this.object = (O) keyPart;
                    found = true;
                }
            }

            if (this.object == null) {
                // Build the current default object by reflection if it hadn't been provided into the key
                try {
                    this.object = (O) ClassUtility.buildGenericType(this.getClass(), OBJECT_EXCLUDED_CLASSES);
                } catch (final CoreException e) {
                    throw new CoreRuntimeException("Failure while building the bindable object for model " + getClass(), e);
                }
            }
        }
    }

    /**
     * @param object The object to set.
     */
    @Override
    public void setObject(final O object) {
        this.object = object;

        // Rebind current object
        bindInternal();
    }

}
