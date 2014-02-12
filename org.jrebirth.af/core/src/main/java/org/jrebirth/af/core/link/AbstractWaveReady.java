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
package org.jrebirth.af.core.link;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.jrebirth.af.core.command.Command;
import org.jrebirth.af.core.command.CommandBean;
import org.jrebirth.af.core.concurrent.AbstractJrbRunnable;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.exception.JRebirthThreadException;
import org.jrebirth.af.core.facade.JRebirthEventType;
import org.jrebirth.af.core.facade.WaveReady;
import org.jrebirth.af.core.log.JRLogger;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.service.Service;
import org.jrebirth.af.core.ui.Model;
import org.jrebirth.af.core.util.CheckerUtility;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.wave.OnWave;
import org.jrebirth.af.core.wave.Wave;
import org.jrebirth.af.core.wave.WaveBase;
import org.jrebirth.af.core.wave.WaveBean;
import org.jrebirth.af.core.wave.WaveData;
import org.jrebirth.af.core.wave.WaveGroup;
import org.jrebirth.af.core.wave.WaveType;
import org.jrebirth.af.core.wave.WaveTypeBase;
import org.jrebirth.af.core.wave.Wave.Status;
import org.jrebirth.af.core.wave.checker.WaveChecker;

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
 * 
 * @param <R> the class type of the subclass
 */
public abstract class AbstractWaveReady<R extends WaveReady<R>> extends AbstractReady<R> implements WaveReady<R>, LinkMessages {

    /** The default fallback wave handle method. */
    public static final String PROCESS_WAVE_METHOD_NAME = "processWave";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractWaveReady.class);

    /** The return wave type map. */
    private final Map<WaveType, WaveType> returnWaveTypeMap = new HashMap<>();

    /** The return command class map. */
    private final Map<WaveType, Class<? extends Command>> returnCommandClass = new HashMap<>();

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
    public final void listen(final WaveType... waveTypes) {
        // Call the other method with null waveChecker & method
        listen(null, null, waveTypes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void listen(final WaveChecker waveChecker, final WaveType... waveTypes) {
        // Call the other method with null method
        listen(waveChecker, null, waveTypes);
    }

    /**
     * Return the human-readable list of Wave Type.
     * 
     * @param waveTypes the list of wave type
     * 
     * @return the string list of Wave Type
     */
    private String getWaveTypesString(final WaveType[] waveTypes) {
        final StringBuilder sb = new StringBuilder();
        for (final WaveType waveType : waveTypes) {
            sb.append(waveType.toString()).append(" ");
        }
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void listen(final WaveChecker waveChecker, final Method method, final WaveType... waveTypes) {

        // Check API compliance
        CheckerUtility.checkWaveTypeContract(this.getClass(), waveTypes);

        final WaveReady<?> waveReady = this;

        LOGGER.trace(LinkMessages.LISTEN_WAVE_TYPE, getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName());

        // Use the JRebirth Thread to add new subscriptions for given Wave Type
        JRebirth.runIntoJIT(new AbstractJrbRunnable(LISTEN_WAVE_TYPE.getText(getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName())) {
            @Override
            public void runInto() throws JRebirthThreadException {
                getNotifier().listen(waveReady, waveChecker, method, waveTypes);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerCallback(final WaveType callType, final WaveType responseType) {

        // Call the generic method
        registerCallback(null, callType, responseType, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerCallback(final WaveType callType, final WaveType responseType, final Class<? extends Command> returnCommandClass) {

        // Call the generic method
        registerCallback(null, callType, responseType, returnCommandClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void registerCallback(final WaveChecker waveChecker, final WaveType callType, final WaveType responseType, final Class<? extends Command> returnCommandClass) {

        // Perform the subscription
        listen(waveChecker, callType);

        // Store a link between call Wave Type and return wave type that store the result type ino a WaveItem
        this.returnWaveTypeMap.put(callType, responseType);

        // Store the Command Class that will handle the service result (optional)
        if (returnCommandClass != null) {
            this.returnCommandClass.put(callType, returnCommandClass);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final WaveType getReturnWaveType(final WaveType waveType) {
        return this.returnWaveTypeMap.get(waveType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Class<? extends Command> getReturnCommand(final WaveType waveType) {
        return this.returnCommandClass.get(waveType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unlisten(final WaveType... waveTypes) {

        // Store an hard link to be able to use current class into the closure
        final WaveReady waveReady = this;

        LOGGER.trace(LinkMessages.UNLISTEN_WAVE_TYPE, getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName());

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new AbstractJrbRunnable(UNLISTEN_WAVE_TYPE.getText(getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName())) {

            @Override
            protected void runInto() throws JRebirthThreadException {
                getNotifier().unlisten(waveReady, waveTypes);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void sendWave(final Wave wave) {
        // Define the from class if it didn't been done before (manually)
        if (wave.getFromClass() == null) {
            wave.setFromClass(this.getClass());
        }
        sendWaveIntoJit(wave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave sendWave(final WaveType waveType, final WaveData<?>... waveData) {
        return sendWaveIntoJit(createWave(WaveGroup.UNDEFINED, waveType, null, waveData));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <WB extends WaveBean> Wave callCommand(final Class<? extends CommandBean<WB>> commandClass, final WB waveBean) {
        return sendWaveIntoJit(createWave(WaveGroup.CALL_COMMAND, null, commandClass, waveBean));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data) {
        return sendWaveIntoJit(createWave(WaveGroup.CALL_COMMAND, null, commandClass, data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WaveData<?>... data) {
        return sendWaveIntoJit(createWave(WaveGroup.RETURN_DATA, waveType, serviceClass, data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Wave attachUi(final Class<? extends Model> modelClass, final WaveData<?>... data) {
        return sendWaveIntoJit(createWave(WaveGroup.ATTACH_UI, null, modelClass, data));
    }

    /**
     * Send the given wave using the JRebirth Thread.
     * 
     * @param wave the wave to send
     * 
     * @return the wave sent to JIT (with Sent status)
     */
    private Wave sendWaveIntoJit(final Wave wave) {

        CheckerUtility.checkWave(wave);

        wave.setStatus(Status.Sent);

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new AbstractJrbRunnable(SEND_WAVE.getText(wave.toString())) {
            @Override
            public void runInto() throws JRebirthThreadException {
                getNotifier().sendWave(wave);
            }
        });

        return wave;
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
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> relatedClass, final WaveData<?>... waveData) {

        final Wave wave = new WaveBase();

        wave.setWaveGroup(waveGroup);
        wave.setWaveType(waveType);
        wave.setFromClass(this.getClass());
        wave.setRelatedClass(relatedClass);

        for (final WaveData<?> wd : waveData) {
            wave.addData(wd);
        }

        // Track wave creation
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.CREATE_WAVE, this.getClass(), wave.getClass());

        return wave;
    }

    /**
     * Build a wave object with its dedicated WaveBean.
     * 
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param relatedClass the related class if any
     * @param waveBean the wave bean that holds all required wave data
     * 
     * @return the wave built
     */
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> relatedClass, final WaveBean waveBean) {

        final Wave wave = new WaveBase();

        wave.setWaveGroup(waveGroup);
        wave.setWaveType(waveType);
        wave.setFromClass(this.getClass());
        wave.setRelatedClass(relatedClass);

        wave.linkWaveBean(waveBean);

        // Track wave creation
        getLocalFacade().getGlobalFacade().trackEvent(JRebirthEventType.CREATE_WAVE, this.getClass(), wave.getClass());

        return wave;
    }

    // /**
    // * This method is called before each execution of the command.
    // *
    // * It will parse the given wave to store local command properties.
    // *
    // * Wave parsing mechanism is composed by three steps:
    // * <ol>
    // * <li>Parse WaveBean properties and copy them into command ones if they exist (by reflection)</li>
    // * <li>Parse WaveData keys and copy them into command ones if they exist (by reflection)</li>
    // * <li>Call {@link #parseWave(Wave)} method for later customization</li>
    // * </ol>
    // *
    // * @param wave the wave to parse
    // */
    // private void parseInternalWave(final Wave wave) {
    //
    // // Parse WaveBean
    // // WB waveBean = getWaveBean(wave);
    // // if (waveBean != null && !(waveBean instanceof DefaultWaveBean)) {
    // // for (Field f : ClassUtility.retrievePropertyList(waveBean.getClass())) {
    // // try {
    // // tryToSetProperty(f.getName(), f.get(waveBean));
    // // } catch (IllegalArgumentException | IllegalAccessException e) {
    // // LOGGER.error("Fail to get field value " + f.getName() + " from " + waveBean.getClass(), e);
    // // }
    // // }
    // // }
    //
    // // Parse WaveData
    // for (final WaveData<?> wd : wave.getWaveItems()) {
    // tryToSetProperty(wd.getKey().toString(), wd.getValue());
    // }
    //
    // // Call customized method
    // // parseWave(wave);
    // }
    //
    // /**
    // * Try to set the value of the given property for the current class.
    // *
    // * @param fieldName the field to initialize
    // * @param fieldValue the field value to set
    // */
    // private void tryToSetProperty(final String fieldName, final Object fieldValue) {
    // try {
    // this.getClass().getField(fieldName).set(this, fieldValue);
    // } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
    // LOGGER.error("Fail to set value for field " + fieldName, e);
    // }
    // }

    /**
     * Customizable method used to perform more action before command execution.
     * 
     * @param wave the given wave to parser before command execution
     */
    // protected abstract void parseWave(final Wave wave);

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public final void handle(final Wave wave) throws WaveException {
    // try {
    // // Build parameter list of the searched method
    // final List<Object> parameterValues = new ArrayList<>();
    // for (final WaveData<?> wd : wave.getWaveItems()) {
    // // Add only wave items defined as parameter
    // if (wd.getKey().isParameter()) {
    // parameterValues.add(wd.getValue());
    // }
    // }
    // // Add the current wave to process
    // parameterValues.add(wave);
    //
    // // Search the wave handler method
    // final Method method = ClassUtility.getMethodByName(this.getClass(), ClassUtility.underscoreToCamelCase(wave.getWaveType().toString()));
    // if (method != null) {
    // // Call this method with right parameters
    // method.invoke(this, parameterValues.toArray());
    // }
    // } catch (final NoSuchMethodException e) {
    //
    // LOGGER.info(CUSTOM_METHOD_NOT_FOUND, e.getMessage());
    // // If no method was found, call the default method
    // processWave(wave);
    //
    // } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    // LOGGER.error(WAVE_DISPATCH_ERROR, e);
    // // Propagate the wave exception
    // throw new WaveException(wave, e);
    // }
    // }

    /**
     * Process the wave. Typically by using a switch on the waveType.
     * 
     * @param wave the wave received
     */
    protected abstract void processWave(final Wave wave);

    /**
     * Manage {@link OnWave} annotation (defined on type and method).
     */
    protected void manageOnWaveAnnotation() {

        // Retrieve class annotation
        final OnWave clsOnWave = this.getClass().getAnnotation(OnWave.class);
        if (clsOnWave != null) {
            manageUniqueWaveTypeAction(clsOnWave.value(), null);
            manageMultipleWaveTypeAction(clsOnWave, null);
        }

        // Iterate over each annotated Method
        OnWave onWave = null;
        for (final Method method : ClassUtility.getAnnotatedMethods(this.getClass(), OnWave.class)) {
            onWave = method.getAnnotation(OnWave.class);
            manageUniqueWaveTypeAction(onWave.value(), method);
            manageMultipleWaveTypeAction(onWave, method);
        }

    }

    /**
     * Manage unique {@link WaveType} subscription (from value field of {@link OnWave} annotation).
     * 
     * @param waveActionName the {@link WaveType} unique name
     * @param method the wave handler method
     */
    private void manageUniqueWaveTypeAction(final String waveActionName, final Method method) {

        // Get the WaveType from the WaveType registry
        final WaveType wt = WaveTypeBase.getWaveType(waveActionName);
        if (wt != null) {
            // Method is not defined or is the default fallback one
            if (method == null || AbstractWaveReady.PROCESS_WAVE_METHOD_NAME.equals(method.getName())) {
                // Just listen the WaveType
                listen(wt);
            } else {
                // Listen the WaveType and specify the right method handler
                listen(null, method, wt);
            }
        }
    }

    /**
     * Manage several {@link WaveType} subscriptions (from types field of {@link OnWave} annotation).
     * 
     * @param onWave the {@link OnWave} annotation to explore
     * @param method the wave handler method
     */
    private void manageMultipleWaveTypeAction(final OnWave onWave, final Method method) {
        for (final String action : onWave.types()) {
            manageUniqueWaveTypeAction(action, method);
        }
    }
}
