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
package org.jrebirth.af.api.component.basic;

import java.lang.reflect.Method;

import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>EnhancedComponent</strong>.
 *
 * Define the contract used to manage waves.
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public interface Component<R extends FacadeReady<R>> extends FacadeReady<R> {

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * @param waveType the type(s) to listen
     */
    void listen(final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param method the annotated method concerned
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final Method method, final WaveType... waveType);

    /**
     * Stop to listen the type of wave for the current component.
     *
     * @param waveTypes the type(s) to stop to listen
     */
    void unlisten(final WaveType... waveTypes);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param wave the wave to send
     */
    void sendWave(final Wave wave);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param waveType the type of wave to send
     * @param waveBean the wave bean
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    <WB extends WaveBean> Wave sendWave(final WaveType waveType, final WB waveBean);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param waveType the type of wave to send
     * @param waveData the data (key-value
     *
     * @return the wave created and sent to JIT, be careful when you use a strong reference it can hold a lot of objects
     */
    Wave sendWave(final WaveType waveType, final WaveData<?>... waveData);

    /**
     * Return the root component (for inner component).
     *
     * @return the root component of the inner component
     */
    Component<?> getRootComponent();

    /**
     * Define the root component for an inner component.
     *
     * @param rootComponent The root component to set.
     */
    void setRootComponent(final Component<?> rootComponent);

    /**
     * Add an inner component.
     *
     * It will instantiate the component from the right Facade and store it into the current component.
     *
     * Shall be called from {@link AbstractComponent}.initInnerComponent (protected API)
     *
     * @param innerComponent the entry that describes the inner component
     * @param keyPart additional object that are part of the inner component's unique key
     */
    <C extends Component<?>> void addInnerComponent(final InnerComponent<C> innerComponent);

    /**
     * Get an inner component.
     *
     * If the component isn't registered try to create it
     *
     * @param innerComponent the entry that describes the inner component
     * @param keyPart additional object that are part of the inner component's unique key
     *
     * @return the inner component instance
     */
    <C extends Component<?>> C getInnerComponent(final InnerComponent<C> innerComponent);

}
