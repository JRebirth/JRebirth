/**
 * Get more info at : www.jrebirth.org .
 * Copyright JRebirth.org © 2011-2016
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
package org.jrebirth.af.api.concurrent;

/**
 * The class <strong>RunType</strong>.
 *
 * The different ways to run a task.
 *
 * @author Sébastien Bordes
 */
public enum RunType {

    /** Run into Java Application Thread. */
    JAT,

    JAT_SYNC,

    /** Run into JRebirth Internal Thread. */
    JIT,

    JIT_SYNC,

    /** Queue and run into the JRebirth Thread Pool. */
    JTP,

    JTP_SYNC,

    SAME;

}
