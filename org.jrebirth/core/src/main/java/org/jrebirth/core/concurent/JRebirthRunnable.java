package org.jrebirth.core.concurent;

import org.jrebirth.core.exception.JRebirthThreadException;

/**
 * The class <strong>JRebirthRunnable</strong>. TODO To complete
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public abstract class JRebirthRunnable implements Runnable {

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
    protected abstract void runInto() throws JRebirthThreadException;

}
