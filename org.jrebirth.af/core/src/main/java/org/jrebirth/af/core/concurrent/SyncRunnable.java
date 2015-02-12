package org.jrebirth.af.core.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

import org.jrebirth.af.api.concurrent.JRebirthRunnable;

public class SyncRunnable implements JRebirthRunnable {

    private final JRebirthRunnable innerRunnable;

    private final AtomicBoolean hasRun = new AtomicBoolean(false);

    public SyncRunnable(final JRebirthRunnable innerRunnable) {
        super();
        this.innerRunnable = innerRunnable;
    }

    @Override
    public void run() {
        this.innerRunnable.run();
        this.hasRun.set(true);
    }

    public void waitEnd(final long... timeout) {

        long start = System.currentTimeMillis();
        if (timeout.length == 1) {
            start += timeout[0];
        } else {
            start += 1000;
        }

        while (!this.hasRun.get()) {
            try {
                Thread.sleep(100);
            } catch (final InterruptedException e) {
                break;
            }
            if (System.currentTimeMillis() > start) {
                break; // Break the loop after 1s to avoid freezing the thread
            }
        }

    }

}
