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
package org.jrebirth.af.core.component.basic;

import static org.jrebirth.af.core.wave.WBuilder.wave;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jrebirth.af.api.annotation.AfterInit;
import org.jrebirth.af.api.annotation.BeforeInit;
import org.jrebirth.af.api.annotation.OnRelease;
import org.jrebirth.af.api.annotation.PriorityLevel;
import org.jrebirth.af.api.annotation.Releasable;
import org.jrebirth.af.api.annotation.SkipAnnotation;
import org.jrebirth.af.api.command.Command;
import org.jrebirth.af.api.command.CommandBean;
import org.jrebirth.af.api.component.basic.Component;
import org.jrebirth.af.api.component.basic.InnerComponent;
import org.jrebirth.af.api.component.behavior.Behavior;
import org.jrebirth.af.api.concurrent.RunType;
import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.exception.CoreRuntimeException;
import org.jrebirth.af.api.facade.JRebirthEventType;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.link.Notifier;
import org.jrebirth.af.api.log.JRLogger;
import org.jrebirth.af.api.service.Service;
import org.jrebirth.af.api.ui.Model;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.Wave.Status;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.WaveGroup;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveType;
import org.jrebirth.af.core.concurrent.JRebirth;
import org.jrebirth.af.core.concurrent.JrbReferenceRunnable;
import org.jrebirth.af.core.concurrent.SyncRunnable;
import org.jrebirth.af.core.link.AbstractReady;
import org.jrebirth.af.core.link.ComponentEnhancer;
import org.jrebirth.af.core.link.LinkMessages;
import org.jrebirth.af.core.log.JRLoggerFactory;
import org.jrebirth.af.core.util.CheckerUtility;
import org.jrebirth.af.core.util.ClassUtility;
import org.jrebirth.af.core.util.ObjectUtility;
import org.jrebirth.af.core.wave.JRebirthItems;

/**
 *
 * The class <strong>AbstractComponent</strong>.
 *
 * This is the base class for all of each of JRebirth pattern subclasses.<br />
 * It allow to send waves.
 *
 * All things related to wave management must be execute into the JRebirth Thread
 *
 * @author Sébastien Bordes
 *
 * @param <C> the class type of the subclass
 */
@SkipAnnotation(false)
public abstract class AbstractComponent<C extends Component<C>> extends AbstractReady<C> implements Component<C>, JRebirthItems, LinkMessages {

    /** The default fallback wave handle method. */
    public static final String PROCESS_WAVE_METHOD_NAME = "processWave";

    /** The class logger. */
    private static final JRLogger LOGGER = JRLoggerFactory.getLogger(AbstractComponent.class);

    /** Flag indicating if processWave handle method will manage uncaught wave (but obviously listened). */
    protected boolean processUncaughtWave = false;

    /** A map that store all annotated methods to call sorted by lifecycle phase. */
    private Map<String, List<Method>> lifecycleMethod;

    /** The root component not null for inner component. */
    protected Component<?> rootComponent;

    /** The map that store inner models loaded. */
    protected Map<InnerComponent<?>, Component<?>> innerComponentMap;

    /** The list that store sorted inner models loaded. */
    // protected List<Component<?>> innerComponentList;

    /**
     * Short cut method used to retrieve the notifier.
     *
     * @return the notifier retrieved from global facade
     */
    private Notifier getNotifier() {
        return localFacade().getGlobalFacade().notifier();
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
        if (!this.processUncaughtWave) {
            CheckerUtility.checkWaveTypeContract(this.getClass(), waveTypes);
        }

        final Component<?> waveReady = this;

        LOGGER.trace(LinkMessages.LISTEN_WAVE_TYPE, getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName());

        // Use the JRebirth Thread to add new subscriptions for given Wave Type
        JRebirth.runIntoJIT(new JrbReferenceRunnable(
                                                     LISTEN_WAVE_TYPE.getText(getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName()), () -> {
                                                         getNotifier().listen(waveReady, waveChecker, method, waveTypes);
                                                     }));
    }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public final void registerCallback(final WaveType callType, final WaveType responseType) {
    //
    // // Call the generic method
    // registerCallback(null, callType, responseType, null);
    // }

    // /**
    // * {@inheritDoc}
    // */
    // @Override
    // public final WaveType getReturnWaveType(final WaveType waveType) {
    // return this.returnWaveTypeMap.get(waveType);
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unlisten(final WaveType... waveTypes) {

        // Store an hard link to be able to use current class into the closure
        final Component<C> waveReady = this;

        LOGGER.trace(LinkMessages.UNLISTEN_WAVE_TYPE, getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName());

        // Use the JRebirth Thread to manage Waves
        JRebirth.runIntoJIT(new JrbReferenceRunnable(
                                                     UNLISTEN_WAVE_TYPE.getText(getWaveTypesString(waveTypes), waveReady.getClass().getSimpleName()),
                                                     () -> getNotifier().unlisten(waveReady, waveTypes)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void sendWave(final Wave wave) {
        // Define the from class if it didn't been done before (manually)
        if (wave.fromClass() == null) {
            wave.fromClass(this.getClass());
        }
        sendWaveIntoJit(wave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final <WB extends WaveBean> Wave sendWave(final WaveType waveType, final WB waveBean) {
        return sendWaveIntoJit(createWave(WaveGroup.UNDEFINED, waveType, (Class<?>) null, waveBean));
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
    public final <WB extends WaveBean> Wave returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WB waveBean) {
        return sendWaveIntoJit(createWave(WaveGroup.RETURN_DATA, waveType, serviceClass, waveBean));
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
    public final <WB extends WaveBean> Wave attachUi(final Class<? extends Model> modelClass, final WB waveBean) {
        return sendWaveIntoJit(createWave(WaveGroup.ATTACH_UI, null, modelClass, waveBean));
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

        wave.status(Status.Sent);

        final PriorityLevel priority = wave.contains(JRebirthItems.priority) ? wave.get(JRebirthItems.priority) : PriorityLevel.Normal;
        final RunType runType = wave.contains(JRebirthItems.runSynchronously) && wave.get(JRebirthItems.runSynchronously) ? RunType.JIT_SYNC : RunType.JIT;
        final Long timeout = wave.contains(JRebirthItems.syncTimeout) ? wave.get(JRebirthItems.syncTimeout) : SyncRunnable.DEFAULT_TIME_OUT;

        // Use the JRebirth Thread to manage Waves
        JRebirth.run(runType, new JrbReferenceRunnable(SEND_WAVE.getText(wave.toString()), priority, () -> getNotifier().sendWave(wave)), timeout);

        return wave;
    }

    /**
     * Build a wave object.
     *
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param componentClass the component class if any
     * @param waveData wave data to use
     *
     * @return the wave built
     */
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> componentClass, final WaveData<?>... waveData) {

        final Wave wave = wave()
                                .waveGroup(waveGroup)
                                .waveType(waveType)
                                .fromClass(this.getClass())
                                .componentClass(componentClass)
                                .addDatas(waveData);

        // Track wave creation
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.CREATE_WAVE, this.getClass(), wave.getClass());

        return wave;
    }

    /**
     * Build a wave object with its dedicated WaveBean.
     *
     * @param waveGroup the group of the wave
     * @param waveType the type of the wave
     * @param componentClass the component class if any
     * @param waveBean the wave bean that holds all required wave data
     *
     * @return the wave built
     */
    private Wave createWave(final WaveGroup waveGroup, final WaveType waveType, final Class<?> componentClass, final WaveBean waveBean) {

        final Wave wave = wave()
                                .waveGroup(waveGroup)
                                .waveType(waveType)
                                .fromClass(this.getClass())
                                .componentClass(componentClass)
                                .waveBean(waveBean);

        // Track wave creation
        localFacade().getGlobalFacade().trackEvent(JRebirthEventType.CREATE_WAVE, this.getClass(), wave.getClass());

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
     * Private method used to grab the right WaveType<?> java type.
     *
     * @return the this instance with the right generic type
     */
    private Component<?> getWaveReady() {
        return this;
    }

    /**
     * Call annotated methods corresponding at given lifecycle annotation.
     *
     * @param annotationClass the annotation related to the lifecycle
     */
    private void callAnnotatedMethod(final Class<? extends Annotation> annotationClass) {
        if (this.lifecycleMethod.get(annotationClass.getName()) != null) {
            for (final Method method : this.lifecycleMethod.get(annotationClass.getName())) {

                try {
                    ClassUtility.callMethod(method, this);
                } catch (final CoreException e) {
                    LOGGER.error(CALL_ANNOTATED_METHOD_ERROR, e);
                }
            }
        }
    }

    /**
     * The component is now ready to do custom initialization tasks.
     *
     * @throws CoreException if an error occurred
     */
    protected abstract void ready() throws CoreException;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setup() throws CoreException {

        final boolean canProcessAnnotation = ComponentEnhancer.canProcessAnnotation((Class<? extends Component<?>>) this.getClass());
        if (canProcessAnnotation) {

            // Search Singleton and Multiton annotation on field
            ComponentEnhancer.injectComponent(this);

            // Attach custom method configured with custom Lifecycle annotation
            this.lifecycleMethod = ComponentEnhancer.defineLifecycleMethod(this);

            // Search OnWave annotation to manage auto wave handler setup
            ComponentEnhancer.manageOnWaveAnnotation(this);

        }

        callAnnotatedMethod(BeforeInit.class);

        manageOptionalData();

        if (canProcessAnnotation) {
            ComponentEnhancer.injectInnerComponent(this);
        }

        // Initialize all inner components
        initInternalInnerComponents();

        // Prepare the current component
        ready();

        callAnnotatedMethod(AfterInit.class);
    }

    /**
     *
     */
    protected abstract void manageOptionalData();

    // {
    //
    // for (final Object data : getKey().getOptionalData()) {
    //
    // if (data instanceof BehaviorData) {
    //
    // addBehavior((BehaviorData) data);
    //
    // } else if (data instanceof Class && ((Class<?>) data).isAssignableFrom(Behavior.class)) {
    //
    // addBehavior((Class<Behavior<BehaviorData>>) data);
    //
    // }
    //
    // }
    //
    // }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean release() {

        boolean released = false;
        // Check if some method annotated by Releasable annotation are available
        if (ObjectUtility.checkAllMethodReturnTrue(this, ClassUtility.getAnnotatedMethods(this.getClass(), Releasable.class))) {

            // Release the component and its internal components into JIT
            JRebirth.runIntoJIT(new JrbReferenceRunnable("Release " + this.getClass().getCanonicalName(), this::internalRelease));

            released = true;
        }

        return released;
    }

    /**
     * Perform the internal release.
     */
    private void internalRelease() {

        // try {

        // setKey(null);

        // getNotifier().unlistenAll(getWaveReady());

        callAnnotatedMethod(OnRelease.class);

        if (localFacade() != null) {
            localFacade().unregister(key());
        }

        // thisObject.ready = false;

        // Shall also release all InnerComponent
        if (getInnerComponentMap().isPresent()) {

            final List<Component<?>> toRemove = new ArrayList<>();
            for (final Component<?> innerComponent : getInnerComponentMap().get().values()) {

                // Release the InnerComponent
                innerComponent.release();

                // Store it to avoid co-modification error
                toRemove.add(innerComponent);
            }

            for (final Component<?> innerComponent : toRemove) {

                // Remove it from the list
                // getInnerComponentList().get().remove(innerComponent);

                // Then remove it from map
                getInnerComponentMap().get().remove(innerComponent.key());
            }
        }

        // } catch (final JRebirthThreadException jte) {
        // LOGGER.error(COMPONENT_RELEASE_ERROR, jte);
        // }

    }

    // public boolean isReady() {
    // return ready;
    // }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    private <IC extends Component<?>> void addInnerComponent(final InnerComponent<IC> innerComponent) {

        if (!getInnerComponentMap().isPresent()) {
            // Initialize default storage objects only on demand (to save some bits)
            this.innerComponentMap = new IdentityHashMap<>(10);
            // this.innerComponentList = new ArrayList<Component<?>>();
        }

        // If the inner model hasn't been loaded before, build it from UIFacade
        if (!this.innerComponentMap.containsKey(innerComponent)) {

            final Class<IC> innerComponentClass = innerComponent.key().classField();

            IC childComponent = null;
            // Use the right facade according to the inner component type
            if (Command.class.isAssignableFrom(innerComponentClass)) {
                // Initialize the Inner Command
                childComponent = (IC) localFacade().getGlobalFacade().commandFacade().retrieve((UniqueKey<Command>) innerComponent.key());
            } else if (Service.class.isAssignableFrom(innerComponentClass)) {
                // Initialize the Inner Service
                childComponent = (IC) localFacade().getGlobalFacade().serviceFacade().retrieve((UniqueKey<Service>) innerComponent.key());
            } else if (Model.class.isAssignableFrom(innerComponentClass)) {
                // Initialize the Inner Model
                childComponent = (IC) localFacade().getGlobalFacade().uiFacade().retrieve((UniqueKey<Model>) innerComponent.key());
            } else if (Behavior.class.isAssignableFrom(innerComponentClass)) {
                // Cannot initialize Inner Behavior, they cannot be nested
                throw new CoreRuntimeException("Behaviors can not be used as Inner EnhancedComponent"); // FIXME add MessageItem
            }

            // Inner EnhancedComponent creation has failed throw an exception
            if (childComponent == null) {
                throw new CoreRuntimeException("Impossible to create Inner EnhancedComponent"); // FIXME add MessageItem
            }

            // Store the component into the multitonKey map
            this.innerComponentMap.put(innerComponent, childComponent);
            // this.innerComponentList.add(childComponent);

            // Link the current root model
            childComponent.rootComponent(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public final <IC extends Component<?>> IC findInnerComponent(final InnerComponent<IC> innerComponent) {

        if (!getInnerComponentMap().isPresent() || !getInnerComponentMap().get().containsKey(innerComponent)) {

            // This InnerModel should be initialized into the initInnerModel method instead
            // but in some cases a late initialization can help
            addInnerComponent(innerComponent);
        }
        return (IC) getInnerComponentMap().get().get(innerComponent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component<?> rootComponent() {
        return this.rootComponent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rootComponent(final Component<?> rootComponent) {
        this.rootComponent = rootComponent;
    }

    /**
     * Initialize the included models.
     *
     * This method is a hook to manage generic code before initializing inner models.
     *
     * You must implement the {@link #initInnerComponents()} method to setup your inner models.
     */
    protected final void initInternalInnerComponents() {
        // Do generic stuff

        // Do custom stuff
        initInnerComponents();
    }

    /**
     * Initialize method that should wrap all creation of {@link InnerComponent}.
     *
     * It shall contain only {@link EnhancedComponent}.addInnerComponent calls.
     */
    protected abstract void initInnerComponents();

    /**
     * Return the InnerComponent map wrapped into an Optional because it's initialized only on demand.
     *
     * @return the innerComponentMap.
     */
    protected Optional<Map<InnerComponent<?>, Component<?>>> getInnerComponentMap() {
        return Optional.ofNullable(this.innerComponentMap);
    }

    /**
     * Return the InnerComponent list wrapped into an Optional because it's initialized only on demand.
     *
     * @return the innerComponentList.
     */
    // protected Optional<List<Component<?>>> getInnerComponentList() {
    // return Optional.ofNullable(getInnerComponentMap().isPresent() ? getInnerComponentMap().get().values().as : null);
    // }

}
