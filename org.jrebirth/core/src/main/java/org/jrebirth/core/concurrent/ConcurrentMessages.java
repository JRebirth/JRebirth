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
package org.jrebirth.core.concurrent;

import static org.jrebirth.core.resource.Resources.create;

import org.jrebirth.core.log.JRebirthMarkers;
import org.jrebirth.core.resource.i18n.LogMessage;
import org.jrebirth.core.resource.i18n.MessageItem;

/**
 * The class <strong>ConcurrentMessages</strong>.
 * 
 * Messages used by the Concurrent package.
 * 
 * @author Sébastien Bordes
 */
public interface ConcurrentMessages {

    /** JRebirthThread. */

    /** "Run> {0}". */
    MessageItem RUN_IT = create(new LogMessage("concurrent.runIt", JRebirthMarkers.CONCURRENT));

    /** "Thread error : {0} ". */
    MessageItem THREAD_ERROR = create(new LogMessage("concurrent.threadError", JRebirthMarkers.CONCURRENT));

    /** "Runnable submitted with hashCode={}" . */
    MessageItem JTP_QUEUED = create(new LogMessage("concurrent.jtpQueued", JRebirthMarkers.CONCURRENT));

    /** "An exception occured during JRebirth BootUp" . */
    MessageItem BOOT_UP_ERROR = create(new LogMessage("concurrent.bootUpError", JRebirthMarkers.CONCURRENT));

    /** AbstractJrbRunnable. */

    /** "An exception occured into the JRebirth Thread". */
    MessageItem JIT_ERROR = create(new LogMessage("concurrent.jitError", JRebirthMarkers.CONCURRENT));

    /** "An error occurred while shuting down the application ". */
    MessageItem SHUTDOWN_ERROR = create(new LogMessage("concurrent.shutdownError", JRebirthMarkers.CONCURRENT));

    /** JrebirthThreadPoolExecutor. */

    /** "JTP returned an error" . */
    MessageItem JTP_ERROR = create(new LogMessage("concurrent.jtpError", JRebirthMarkers.CONCURRENT));

    /** "JTP returned an error with rootCause =>". */
    MessageItem JTP_ERROR_EXPLANATION = create(new LogMessage("concurrent.jtpErrorExplanation", JRebirthMarkers.CONCURRENT));

    /** "Future (hashcode={}) returned object : {}". */
    MessageItem FUTURE_DONE = create(new LogMessage("concurrent.futureDone", JRebirthMarkers.CONCURRENT));

}
