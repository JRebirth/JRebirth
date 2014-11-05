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
package org.jrebirth.af.core.link;

import java.util.HashMap;
import java.util.Map;

import org.jrebirth.af.api.annotation.SkipAnnotation;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.component.enhanced.EnhancedComponent;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.wave.WaveType;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.core.log.JRLoggerFactory;

/**
 *
 * The class <strong>AbstractComponent</strong>.
 *
 * This is the base class for all of each of JRebirth pattern subclasses.<br />
 * It allow to send waves.
 *
 * All things related to wave management must be execute into the JRebirth Thread
 *
 * @author Sébastien Bordes
 *
 * @param <C> the class type of the subclass
 */
@SkipAnnotation(false)
public abstract class AbstractEnhancedComponent<C extends EnhancedComponent<C>> extends AbstractComponent<C> implements EnhancedComponent<C>, LinkMessages {

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractEnhancedComponent.class);

    /** The return command class map. */
    private Map<WaveType, Class<? extends Command>> returnCommandClass;

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerCallback(final WaveType callType, final Class<? extends Command> returnCommandClass) {

        // Call the generic method
        registerCallback(null, callType, returnCommandClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerCallback(final WaveChecker waveChecker, final WaveType callType, final Class<? extends Command> returnCommandClass) {

        // Perform the subscription
        listen(waveChecker, callType);

        // Store the Command Class that will handle the service result (optional)
        if (returnCommandClass != null) {
            if (this.returnCommandClass == null) {
                this.returnCommandClass = new HashMap<>();
            }
            this.returnCommandClass.put(callType, returnCommandClass);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<? extends Command> getReturnCommand(final WaveType waveType) {
        return this.returnCommandClass == null ? null : this.returnCommandClass.get(waveType);
    }

}
