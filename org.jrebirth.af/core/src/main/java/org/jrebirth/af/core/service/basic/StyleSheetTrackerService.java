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
package org.jrebirth.af.core.service.basic;

import java.io.File;

import javafx.scene.Scene;

import org.jrebirth.af.core.service.DefaultService;
import org.jrebirth.af.core.wave.WaveTypeBase;

/**
 * The class <strong>StyleSheetTrackerService</strong>.
 *
 * @author Sébastien Bordes
 */
public class StyleSheetTrackerService extends DefaultService {

    /** Wave type use to load events. */
    public static final WaveTypeBase DO_OPEN_STAGE = WaveTypeBase.build("OPEN_STAGE");

    /** Wave type to return events loaded. */
    public static final WaveTypeBase RE_STAGE_OPENED = WaveTypeBase.build("STAGE_OPENED");

    /** The class logger. */
    // private static final Logger LOGGER = LoggerFactory.getLogger(StyleSheetTrackerService.class);

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public void ready() throws CoreException {
    // super.ready();
    //
    // // registerCallback(DO_OPEN_STAGE, RE_STAGE_OPENED);
    // }

    /**
     * Listen new style sheet path.
     *
     * @param styleSheetPath the style hseet path
     * @param scene the root scene
     */
    public void listen(final String styleSheetPath, final Scene scene) {

        final File file = new File(styleSheetPath);

        file.lastModified();

        // StyleManager.getInstance().reloadStylesheets(scene);

    }

    /**
     * Start the service.
     */
    public void start() {
        // Nothing to do yet

    }

}
