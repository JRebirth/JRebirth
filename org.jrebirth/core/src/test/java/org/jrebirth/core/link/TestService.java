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
package org.jrebirth.core.link;

import java.util.List;
import java.util.Set;

import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.service.ServiceBase;
import org.jrebirth.core.wave.WaveBase;
import org.jrebirth.core.wave.WaveItem;
import org.jrebirth.core.wave.WaveTypeBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The class <strong>ImageSlicerService</strong>.
 * 
 * @author Sébastien Bordes
 */
public class TestService extends ServiceBase {

    /** The file containing all events serialized. */
    public static final WaveItem<List<String>> STRINGS = new WaveItem<List<String>>() {
    };

    /** The file containing all events serialized. */
    public static final WaveItem<Set<String>> SUBSTRINGS = new WaveItem<Set<String>>() {
    };

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_JOB = WaveTypeBase.build("PERFORM_JOB", STRINGS);

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_JOB_DONE = WaveTypeBase.build("JOB_PERFORMED", SUBSTRINGS);

    /** The class logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TestService.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void ready() throws CoreException {
        super.ready();

        listen(RE_JOB_DONE);

        registerCallback(DO_JOB, RE_JOB_DONE);
    }

    /**
     * Do it.
     */
    public Set<String> performJob(final List<String> stringList, final WaveBase wave) {
        return null;
    }

    /**
     * Do it.
     */
    public void jobDone(final Set<String> stringSet, final WaveBase wave) {
        return;
    }
}
