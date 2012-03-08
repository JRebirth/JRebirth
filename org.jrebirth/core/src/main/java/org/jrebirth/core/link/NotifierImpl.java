package org.jrebirth.core.link;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.concurent.JRebirth;
import org.jrebirth.core.concurent.JRebirthRunnable;
import org.jrebirth.core.concurent.JRebirthThread;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.AbstractGlobalReady;
import org.jrebirth.core.facade.GlobalFacade;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.ui.Model;

/**
 * 
 * The class <strong>NotifierImpl</strong>.
 * 
 * An implementation that allow to send and to rpocess wave message.
 * 
 * @author Sébastien Bordes
 * 
 * @version $Revision$ $Author$
 * @since $Date$
 */
public class NotifierImpl extends AbstractGlobalReady implements Notifier {

    /** The map that store link between wave type and objects interested. */
    private final Map<WaveType, List<WaveReady>> notifierMap = new HashMap<>();

    /**
     * Default Constructor.
     * 
     * @param globalFacade the global facade of the application
     */
    public NotifierImpl(final GlobalFacade globalFacade) {
        super(globalFacade);
        getGlobalFacade().trackEvent(EventType.CREATE_NOTIFIER, globalFacade.getClass(), this.getClass());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendWave(final Wave wave) throws JRebirthThreadException {

        JRebirthThread.checkJRebirthThread();

        try {
            switch (wave.getWaveGroup()) {
                case CALL_COMMAND:
                    callCommand(wave);
                    break;
                case RETURN_DATA:
                    break;
                case DISPLAY_UI:
                    displayUi(wave);
                    break;
                case UNDEFINED:
                default:
                    sendUndefinedWave(wave);
            }
        } catch (final WaveException e) {
            getGlobalFacade().getLogger().error("Failed to send Wave");
        }
    }

    /**
     * Call dynamically a command.
     * 
     * @param wave the wave that contain all information
     */
    @SuppressWarnings("unchecked")
    private void callCommand(final Wave wave) {
        final Command command = getGlobalFacade().getCommandFacade().retrieve((Class<? extends Command>) wave.getRelatedClass());

        // TODO parse arguments !!!!!!!! like for model events

        command.run(wave);
    }

    /**
     * Display dynamically an Ui model.
     * 
     * @param wave the wave that contain all information
     */
    @SuppressWarnings("unchecked")
    private void displayUi(final Wave wave) {
        final Model model = getGlobalFacade().getUiFacade().retrieve((Class<? extends Model>) wave.getRelatedClass());
        model.getView().getRootNode();

        // TODO parse arguments !!!!!!!!
    }

    /**
     * Dispatch a standard wave which could be handled by a custom method of the component.
     * 
     * @param wave the wave that contain all information
     * 
     * @throws WaveException if wave dispatching fails
     */
    private void sendUndefinedWave(final Wave wave) throws WaveException {
        // Retrieve all interested object from the map
        final List<WaveReady> list = this.notifierMap.get(wave.getWaveType());
        // For each object process the action
        for (final WaveReady linked : list) {

            // If the notified class is part of the UI
            // We must perform this action into the JavaFX Application Thread
            if (linked instanceof Model) {
                JRebirth.runIntoJAT(new JRebirthRunnable() {

                    @Override
                    protected void runInto() throws JRebirthThreadException {
                        try {
                            linked.handle(wave);
                        } catch (final WaveException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                // Otherwise can perform it right now into the current thread (JRebirthThread)
                linked.handle(wave);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void listen(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException {

        JRebirthThread.checkJRebirthThread();

        for (final WaveType nt : waveType) {
            List<WaveReady> list;
            if (this.notifierMap.containsKey(nt)) {
                list = this.notifierMap.get(nt);
            } else {
                list = new ArrayList<WaveReady>();
                this.notifierMap.put(nt, list);
            }
            if (list.isEmpty() || !list.contains(linkedObject)) {
                list.add(linkedObject);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unlisten(final WaveReady linkedObject, final WaveType... waveType) throws JRebirthThreadException {

        JRebirthThread.checkJRebirthThread();

        for (final WaveType nt : waveType) {
            List<WaveReady> list;
            if (this.notifierMap.containsKey(nt)) {
                list = this.notifierMap.get(nt);
                list.remove(linkedObject);
                if (list.isEmpty()) {
                    this.notifierMap.remove(nt);
                }
            }
        }
    }
}
