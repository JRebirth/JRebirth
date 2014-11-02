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
package org.jrebirth.af.api.concurrent;

import java.util.concurrent.ExecutorService;

/**
 *
 * The class <strong>JRebirthThreadPoolExecutor</strong> is used to to manage the JRebirth Thread Pool (<b>JTP</b>).
 *
 * @author Sébastien Bordes
 */
public interface IJRebirthThreadPoolExecutor extends ExecutorService {

    /**
     * Check if a slot is available for the given task priority.
     *
     * @param taskPriority the priority to check
     *
     * @return true if this priority can be run right now
     */
    boolean checkAvailability(final RunnablePriority taskPriority);

}
