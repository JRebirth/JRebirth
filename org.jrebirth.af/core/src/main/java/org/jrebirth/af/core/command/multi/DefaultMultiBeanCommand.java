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
package org.jrebirth.af.core.command.multi;

import java.util.List;

import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.concurrent.RunInto;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;

/**
 * The class <strong>DefaultMultiBeanCommand</strong>.
 *
 * @param <WB> The WaveBean type used for this command (by default you can use the WaveBean interface)
 *
 * @author Sébastien Bordes
 */
@RunInto(RunType.JIT)
public class DefaultMultiBeanCommand<WB extends WaveBean> extends AbstractMultiCommand<WB> {

    /**
     * Default Constructor. Annotation will be used to define run type (default is JIT)
     */
    public DefaultMultiBeanCommand() {
        super(true);
    }

    /**
     * Default Constructor. Annotation will be used to define run type (default is JIT)
     *
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public DefaultMultiBeanCommand(final boolean sequential) {
        super(sequential);
    }

    /**
     * Default Constructor.
     *
     * @param runInto The run into thread type
     */
    public DefaultMultiBeanCommand(final RunType runInto) {
        super(runInto, true);
    }

    /**
     * Default Constructor.
     *
     * @param runInto The run into thread type
     * @param sequential indicate if commands must be run sequentially(true) or in parallel(false)
     */
    public DefaultMultiBeanCommand(final RunType runInto, final boolean sequential) {
        super(runInto, sequential);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initCommand() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<UniqueKey<? extends Command>> defineSubCommand() {
        throw new CoreRuntimeException(this.getClass() + " shall override List<UniqueKey<? extends Command>> defineSubCommand()");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initInnerComponents() {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void before(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void after(final Wave wave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void beforeEach(final Wave wave, final Wave childWave) {
        // Nothing to do yet
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void afterEach(final Wave wave, final Wave childWave) {
        // Nothing to do yet
    }

}
