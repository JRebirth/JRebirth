package org.jrebirth.core.link;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.command.Command;
import org.jrebirth.core.concurent.JRebirth;
import org.jrebirth.core.concurent.JRebirthRunnable;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.JRebirthThreadException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.util.ClassUtility;

/**
 * 
 * The class <strong>AbstractWaveReady</strong>.
 * 
 * This is the base class for all of each of JRebirth pattern subclasses.<br />
 * It allow to send waves.
 * 
 * All things related to wave management must be execute into the JRebirth Thread
 * 
 * @author Sébastien Bordes
 * @version $Revision$ $Date$ $Name$
 * 
 * @since org.jrebirth.core 1.0
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractWaveReady<R extends FacadeReady<R>> extends AbstractReady<R> implements WaveReady {

    /**
     * Short cut method used to retrieve the notifier.
     * 
     * @return the notifier retrieved from global facade
     */
    private Notifier getNotifier() {
        return getLocalFacade().getGlobalFacade().getNotifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void listen(final WaveType waveType) {

        final WaveReady waveReady = this;

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJET(
                new JRebirthRunnable() {
                    @Override
                    public void runInto() throws JRebirthThreadException {
                        getNotifier().listen(waveReady, waveType);
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unlisten(final WaveType waveType) {

        final WaveReady waveReady = this;

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJET(
                new JRebirthRunnable() {

                    @Override
                    protected void runInto() throws JRebirthThreadException {
                        getNotifier().unlisten(waveReady, waveType);
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void send(final WaveType waveType, final WaveData... waveData) {
        buildAndSendWave(WaveGroup.UNDEFINED, waveType, null, waveData);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void callCommand(final Class<? extends Command> commandClass, final WaveData... data) {
        buildAndSendWave(WaveGroup.CALL_COMMAND, null, commandClass, data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void returnData(final Class<? extends Command> serviceClass, final WaveData... data) {
        buildAndSendWave(WaveGroup.RETURN_DATA, null, serviceClass, data);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void displayUi(final Class<? extends Model> modelClass, final WaveData... data) {
        buildAndSendWave(WaveGroup.DISPLAY_UI, null, modelClass, data);
    }

    /**
     * Build a new Wave Object and send it using the JRebirth Thread.
     * 
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param relatedClass the related class if any
     * @param waveData wave data to use
     */
    private void buildAndSendWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> relatedClass, final WaveData... waveData) {

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJET(
                new JRebirthRunnable() {
                    @Override
                    public void runInto() throws JRebirthThreadException {
                        getNotifier().sendWave(createWave(waveGroup, waveType, relatedClass, waveData));
                    }
                });
    }

    /**
     * Build a wave object.
     * 
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param relatedClass the related class if any
     * @param waveData wave data to use
     * 
     * @return the wave built
     */
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> relatedClass, final WaveData... waveData) {
        final Wave wave = new WaveImpl();
        wave.setWaveGroup(waveGroup);
        wave.setWaveType(waveType);
        wave.setRelatedClass(relatedClass);
        for (final WaveData wd : waveData) {
            wave.add(wd.getKey(), wd);
        }

        // Track wave creation
        getLocalFacade().getGlobalFacade().trackEvent(EventType.CREATE_WAVE, this.getClass(), wave.getClass());

        return wave;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Wave wave) throws WaveException {
        try {
            // Build parameter list of the searched method
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData wd : wave.getWaveItems()) {
                parameterValues.add(wd.getValue());
            }
            // Add the current wave to process
            parameterValues.add(wave);

            // Search the wave handler method
            final Method method = ClassUtility.getMethodByName(this.getClass(), wave.getWaveType().getAction());
            if (method != null) {
                // Call this method with right parameters
                method.invoke(this, parameterValues.toArray());
            }
        } catch (final NoSuchMethodException e) {
            // If no method was found, call the default method
            processAction(wave);

        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // Propagate the wave exception
            throw new WaveException(wave, e);
        }
    }

    /**
     * Process the wave. Typically by using a switch on the waveType.
     * 
     * @param wave the wave received
     * 
     * @since org.jrebirth.core 1.0
     */
    protected abstract void processAction(final Wave wave);

}
