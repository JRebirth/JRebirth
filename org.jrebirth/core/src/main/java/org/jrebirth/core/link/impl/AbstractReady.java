package org.jrebirth.core.link.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.jrebirth.core.application.JRebirthThread;
import org.jrebirth.core.command.Command;
import org.jrebirth.core.event.EventType;
import org.jrebirth.core.exception.CoreException;
import org.jrebirth.core.exception.WaveException;
import org.jrebirth.core.facade.CommandReady;
import org.jrebirth.core.facade.Facade;
import org.jrebirth.core.facade.FacadeReady;
import org.jrebirth.core.facade.ModelReady;
import org.jrebirth.core.facade.ServiceReady;
import org.jrebirth.core.facade.UniqueKey;
import org.jrebirth.core.facade.WaveReady;
import org.jrebirth.core.link.Notifier;
import org.jrebirth.core.link.Wave;
import org.jrebirth.core.link.WaveGroup;
import org.jrebirth.core.link.WaveType;
import org.jrebirth.core.service.Service;
import org.jrebirth.core.ui.Model;
import org.jrebirth.core.util.ClassUtility;

/**
 * 
 * The class <strong>AbstractReady</strong>.
 * 
 * This is the bas class for all of each of JRebirth pattern subclasses.<br />
 * It allow to send waves and retrieve any of available singleton.
 * 
 * @author Sébastien Bordes
 * @version $Revision$ $Date$ $Name$
 * 
 * @since org.jrebirth.core 1.0
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractReady<R extends FacadeReady<R>> implements FacadeReady<R>, ModelReady, CommandReady, ServiceReady, WaveReady {

    /** The facade that manage same kind of object (from Service, Command and Model). */
    private Facade<R> localFacade;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Facade<R> getLocalFacade() {
        return this.localFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setLocalFacade(final Facade<R> localFacade) {
        this.localFacade = localFacade;
    }

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
        getNotifier().listen(this, waveType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unlisten(final WaveType waveType) {
        getNotifier().unlisten(this, waveType);
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
        JRebirthThread.getThread().runAsap(
                new Runnable() {
                    @Override
                    public void run() {
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
            final List<Object> parameterValues = new ArrayList<>();
            for (final WaveData wd : wave.getWaveItems()) {
                parameterValues.add(wd.getValue());
            }
            parameterValues.add(wave);

            final Method method = ClassUtility.getMethodByName(this.getClass(), wave.getWaveType().getAction());
            if (method != null) {
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
    protected abstract void processAction(Wave wave);

    /**
     * {@inheritDoc}
     */
    @Override
    public final <S extends Service> S getService(final Class<S> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_SERVICE, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getServiceFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <C extends Command> C getCommand(final Class<C> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_COMMAND, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getCommandFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <M extends Model> M getModel(final Class<M> clazz, final UniqueKey... key) {
        getLocalFacade().getGlobalFacade().trackEvent(EventType.ACCESS_MODEL, this.getClass(), clazz);
        return getLocalFacade().getGlobalFacade().getUiFacade().retrieve(clazz, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void ready() throws CoreException;

}
