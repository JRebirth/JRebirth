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
package org.jrebirth.af.core.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.concurrent.JRebirthRunnable;

/**
 * The Class SyncRunnable is used to perform a task synchronously in the current thread.
 */
public class SyncRunnable implements JRebirthRunnable {

    /** The default timeout value. */
    public static final Long DEFAULT_TIME_OUT = 1000L;

    /** The default sleep time. */
    public static final Long SLEEP_TIME = 100L;

    /** The internal runnable that shall be synchronized. */
    private final JRebirthRunnable innerRunnable;

    /** The bullet-proof flag taht indicates if the runnable has finished. */
    private final AtomicBoolean hasRun = new AtomicBoolean(false);

    /**
     * Instantiates a new sync runnable.
     *
     * @param innerRunnable the inner runnable
     */
    public SyncRunnable(final JRebirthRunnable innerRunnable) {
        super();
        this.innerRunnable = innerRunnable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.innerRunnable.run();
        this.hasRun.set(true);
    }

    /**
     * Wait the end of the runnable.
     *
     * @param timeout the maximum to wait to avoid to freeze the thread
     */
    public void waitEnd(final long... timeout) {

        long start = System.currentTimeMillis();
        if (timeout.length == 1) {
            start += timeout[0];
        } else {
            start += DEFAULT_TIME_OUT;
        }

        while (!this.hasRun.get()) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (final InterruptedException e) {
                break;
            }
            if (System.currentTimeMillis() > start) {
                break; // Break the loop after 1s to avoid freezing the thread
            }
        }

    }

}
