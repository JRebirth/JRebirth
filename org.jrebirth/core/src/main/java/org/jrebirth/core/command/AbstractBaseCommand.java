package org.jrebirth.core.command;

import org.jrebirth.core.concurent.JRebirth;
import org.jrebirth.core.concurent.JRebirthRunnable;
import org.jrebirth.core.concurent.RunIntoType;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.link.AbstractWaveReady;
import org.jrebirth.core.link.Wave;

/**
 * The class <strong>AbstractBaseCommand</strong>.
 * 
 * Base implementation of the command.
 * 
 * Allow to run the command in different thread according to the runInto field value.
 * 
 * @author Sébastien Bordes
 */
public abstract class AbstractBaseCommand extends AbstractWaveReady<Command> implements Command {

    /**
     * The field that indicate how this command must be launched.
     */
    private final RunIntoType runInto;

    /**
     * Default constructor.
     * 
     * @param runInto the way to launch this command
     */
    public AbstractBaseCommand(final RunIntoType runInto) {
        this.runInto = runInto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void ready() throws CoreException;

    /**
     * {@inheritDoc}
     */
    protected abstract void execute(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    protected abstract void processAction(final Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    public final void run(final Wave wave) {

        // Create the runnable that will be run
        final JRebirthRunnable runnable = new JRebirthRunnable() {

            @Override
            protected void runInto() throws JRebirthThreadException {
                execute(wave);
            }
        };

        // Add the runnable to the runner queue run it as soon as possible
        JRebirth.run(getRunInto(), runnable);
    }

    /**
     * @return Returns the runInto.
     */
    protected final RunIntoType getRunInto() {
        return this.runInto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void finalize() throws Throwable {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.DESTROY_COMMAND, null, this.getClass());
        super.finalize();
    }

}
