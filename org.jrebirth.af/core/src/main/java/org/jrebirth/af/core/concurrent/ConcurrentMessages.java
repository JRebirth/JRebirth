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
package org.jrebirth.af.core.concurrent;

import static org.jrebirth.af.core.resource.Resources.create;

import org.jrebirth.af.api.log.JRLevel;
import org.jrebirth.af.api.resource.i18n.MessageItem;
import org.jrebirth.af.core.log.JRebirthMarkers;
import org.jrebirth.af.core.resource.i18n.LogMessage;
import org.jrebirth.af.core.resource.i18n.MessageContainer;

/**
 * The class <strong>ConcurrentMessages</strong>.
 *
 * Messages used by the Concurrent package.
 *
 * @author Sébastien Bordes
 */
public interface ConcurrentMessages extends MessageContainer {

    /** JRebirthThread. */

    /** "Runnable submitted to JTP with hashCode={0}" . */
    MessageItem JTP_QUEUED = create(new LogMessage("jrebirth.concurrent.jtpQueued", JRLevel.Trace, JRebirthMarkers.CONCURRENT));

    /** "Runnable submitted to HPTP with hashCode={0}" . */
    MessageItem HPTP_QUEUED = create(new LogMessage("jrebirth.concurrent.hptpQueued", JRLevel.Trace, JRebirthMarkers.CONCURRENT));

    /** "An exception occurred during JRebirth BootUp" . */
    MessageItem BOOT_UP_ERROR = create(new LogMessage("jrebirth.concurrent.bootUpError", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** "An exception occured into the JRebirth Thread". */
    MessageItem JIT_ERROR = create(new LogMessage("jrebirth.concurrent.jitError", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** "An error occurred while shuting down the application ". */
    MessageItem SHUTDOWN_ERROR = create(new LogMessage("jrebirth.concurrent.shutdownError", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** AbstractJrbRunnable. */

    /** "Run> {0}". */
    MessageItem RUN_IT = create(new LogMessage("jrebirth.concurrent.runIt", JRLevel.Trace, JRebirthMarkers.CONCURRENT));

    /** "Thread error : {0} ". */
    MessageItem THREAD_ERROR = create(new LogMessage("jrebirth.concurrent.threadError", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** JRebirthThreadPoolExecutor. */

    /** "JTP returned an error" . */
    MessageItem JTP_ERROR = create(new LogMessage("jrebirth.concurrent.jtpError", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** "JTP returned an error with rootCause =>". */
    MessageItem JTP_ERROR_EXPLANATION = create(new LogMessage("jrebirth.concurrent.jtpErrorExplanation", JRLevel.Error, JRebirthMarkers.CONCURRENT));

    /** "Future (hashcode={}) returned object : {0}". */
    MessageItem FUTURE_DONE = create(new LogMessage("jrebirth.concurrent.futureDone", JRLevel.Trace, JRebirthMarkers.CONCURRENT));

}
