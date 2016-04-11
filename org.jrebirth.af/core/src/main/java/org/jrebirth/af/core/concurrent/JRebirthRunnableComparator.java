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

import java.io.Serializable;
import java.util.Comparator;

import org.jrebirth.af.api.concurrent.JRebirthRunnable;

/**
 * The Class JRebirthRunnableComparator.
 */
public class JRebirthRunnableComparator implements Comparator<Runnable>, Serializable {

    /**
     * The <code>serialVersionUID</code> field.
     */
    private static final long serialVersionUID = 6281343905066116850L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Runnable r1, final Runnable r2) {
        int res = 0;

        final JRebirthRunnable jrr1 = (JRebirthRunnable) r1;
        final JRebirthRunnable jrr2 = (JRebirthRunnable) r2;

        if (jrr1 == null) {
            res = jrr2 == null ? 0 : 1;
        } else if (jrr2 == null) {
            res = -1;
        } else if (jrr1.priority().level() == jrr2.priority().level()) {
            // Compare creation time
            res = jrr1.creationTime().compareTo(jrr2.creationTime());
        } else {
            res = ((Integer) jrr1.priority().level()).compareTo(jrr2.priority().level()) * -1;
        }
        return res;
    }

}
