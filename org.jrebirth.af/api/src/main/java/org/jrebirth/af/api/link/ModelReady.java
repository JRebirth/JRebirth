/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2014
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
package org.jrebirth.af.api.link;

import java.util.List;

import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.contract.WaveData;

/**
 * The interface <strong>ModelReady</strong> allows to retrieve a {@link Model} instance.
 *
 * @author Sébastien Bordes
 */
public interface ModelReady extends ServiceReady {

    /**
     * Return the model singleton or part of multiton according to key parts provided.
     *
     * @param clazz the model class to find
     * @param keyPart key parts for multiton model (in option), singleton
     *
     * @param <M> a sub class of Model
     *
     * @return a model instance
     */
    <M extends Model> M getModel(final Class<M> clazz, final Object... keyPart);

    /**
     * Return the model singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param modelKey the key that describe the searched Model component
     *
     * @param <M> a sub class of Model
     *
     * @return a model instance
     */
    <M extends Model> M getModel(final UniqueKey<M> modelKey);

    /**
     * Return the list of model singleton or part of multiton according to key parts provided.
     *
     * @param clazz the model class to find
     * @param keyPart key parts for multiton model (in option), singleton
     *
     * @param <M> a sub class of Model
     *
     * @return a list of model instance
     */
    <M extends Model> List<M> getModels(final Class<M> clazz, final Object... keyPart);

    /**
     * Return the list of model singleton or part of multiton according to {@link UniqueKey}.
     *
     * @param modelKey the key that describe the searched Model component
     *
     * @param <M> a sub class of Model
     *
     * @return a list of model instance
     */
    <M extends Model> List<M> getModels(final UniqueKey<M> modelKey);

    /**
     * Send a wave used to display an UI model.
     *
     * The command will be called from JRebirthThread but will execute itself from the JavaFX Application thread.
     *
     * @param modelClass the model class to display
     * @param data the data to transport
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    Wave attachUi(final Class<? extends Model> modelClass, final WaveData<?>... data);

}
