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
package org.jrebirth.af.core.command.ref;

import org.jrebirth.af.api.command.ref.CommandRunner;
import org.jrebirth.af.api.command.ref.CommandWaveRunner;
import org.jrebirth.af.api.wave.Wave;

/**
 * The Class SingleRef represents a single reference command.
 *
 * @author Sébastien Bordes
 */
public final class SingleRef extends AbstractRef<SingleRef> {

    /** The command runner without wave. */
    private CommandRunner commandRunner;

    /** The command runner with wave. */
    private CommandWaveRunner commandWaveRunner;

    /**
     * Creates a SingleRef object.
     *
     * @return the single ref
     */
    public static SingleRef create() {
        return new SingleRef();
    }

    /**
     * Return the referenced method to run without {@link Wave}.
     *
     * @return the command runner
     */
    public CommandRunner run() {
        return this.commandRunner;
    }

    /**
     * Run.
     *
     * @param commandRunner the command runner
     * @return the single ref
     */
    public SingleRef run(final CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        return this;
    }

    /**
     * Run wave.
     *
     * @return the command wave runner
     */
    public CommandWaveRunner runWave() {
        return this.commandWaveRunner;
    }

    /**
     * Run wave.
     *
     * @param commandWaveRunner the command wave runner
     * @return the single ref
     */
    public SingleRef runWave(final CommandWaveRunner commandWaveRunner) {
        this.commandWaveRunner = commandWaveRunner;
        return this;
    }

}
