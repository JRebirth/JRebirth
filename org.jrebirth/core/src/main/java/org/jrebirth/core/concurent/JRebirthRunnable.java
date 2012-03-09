package org.jrebirth.core.concurent;

import org.jrebirth.core.exception.JRebirthThreadException;

/**
 * The class <strong>AbstractJRebirthRunnable</strong>.
 * 
 * @author Sébastien Bordes
 * 
 */
public class JRebirthRunnable implements Runnable {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run() {
        try {
            runInto();
        } catch (final JRebirthThreadException jte) {
            jte.printStackTrace();
        }

    }

    /**
     * Run task.
     * 
     * @throws JRebirthThreadException it thread error
     */
    protected void runInto() throws JRebirthThreadException {
        // Must be overridden
    }

}
