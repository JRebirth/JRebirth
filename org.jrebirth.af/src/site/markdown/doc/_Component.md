JRebirth Components
=========================

Components definition
----------------------------

A component is a top-level actor of the __wB-CS-Mvc__ pattern.

Each component can communicate with other in a simple way. 
They are loosely coupled with each other and with the engine itself.

There **are** 3+1 kinds of components:

* Command
* Service
* Model
* Behavior

Wave, View and Controller are not JRebirth Components.

<div class="uml">
	<a href="uml/ComponentOverview.png" rel="lightbox" title="Component Overview Class Diagram ">
		<img class="redux" src="uml/ComponentOverview.png" alt="" />
	</a>
	<legend>Component Overview - Class Diagram</legend>
</div>

Component Features
-------------------------

### Listening Waves ###

Each **Component** is able to receive **Wave** notifications, for doing that they shall listen a **WaveType** to get registered into the **Notifier**.
 
When a **Wave** is emitted from elsewhere in the application, the **Notifier** will call right handling method of the **Component** registered.

It's possible to use a **WaveChecker** to filter **Wave** using a same **WaveType**.

The *listen* method taking a **Method** as parameter is used in conjuction with **@OnWave** annotation. 

***Warning:*** This registration creates a strong reference to **Notifier** and so avoid the **Component** to be released and collected by the garbage collector, this can creates memory leaks so don't forget to call *unlisten* method when you don't want to retain the **Component**. 

Please note that if developerMode *(developerMode=true)* is activated, **Wave** contract will be checked at runtime and will throw an exception if errors are detected in order to let developers fix it as soon as possible, this detection is only performed when *listen* methods are called.


```java
package org.jrebirth.af.api.component.basic;

import java.lang.reflect.Method;

import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>EnhancedComponent</strong>.
 *
 * Define the contract used to manage waves.
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public interface Component<R extends FacadeReady<R>> extends FacadeReady<R> {

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * @param waveType the type(s) to listen
     */
    void listen(final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param method the annotated method concerned
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final Method method, final WaveType... waveType);

    /**
     * Stop to listen the type of wave for the current component.
     *
     * @param waveTypes the type(s) to stop to listen
     */
    void unlisten(final WaveType... waveTypes);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param wave the wave to send
     */
    void sendWave(final Wave wave);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param waveType the type of wave to send
     * @param waveBean the wave bean
     *
    // ...
```

You can learn more about [Wave](Wave.html) and [Notifier](Notifier.html) on their dedicated documentation pages.

### Sending Waves ###

Any **Component** is able to emit a new **Wave** and let the **Notifier** dispath it to registered **Component**.


```java
    void sendWave(final Wave wave);
```

### Parenthood ###


```java
package org.jrebirth.af.api.component.basic;

import java.lang.reflect.Method;

import org.jrebirth.af.api.facade.FacadeReady;
import org.jrebirth.af.api.wave.Wave;
import org.jrebirth.af.api.wave.WaveBean;
import org.jrebirth.af.api.wave.checker.WaveChecker;
import org.jrebirth.af.api.wave.contract.WaveData;
import org.jrebirth.af.api.wave.contract.WaveType;

/**
 * The interface <strong>EnhancedComponent</strong>.
 *
 * Define the contract used to manage waves.
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public interface Component<R extends FacadeReady<R>> extends FacadeReady<R> {

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * @param waveType the type(s) to listen
     */
    void listen(final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final WaveType... waveType);

    /**
     * Begin to listen the type of wave for the current component.
     *
     * Wave Contract will be checked if {@link org.jrebirth.af.core.resource.provided.JRebirthParameters.DEVELOPER_MODE} parameter is true
     *
     * The wave checker is used to filter the wave if the checker returns false
     *
     * @param waveChecker the wave checker used to forward the wave only if the checker return true
     * @param method the annotated method concerned
     * @param waveType the type(s) to listen
     */
    void listen(final WaveChecker waveChecker, final Method method, final WaveType... waveType);

    /**
     * Stop to listen the type of wave for the current component.
     *
     * @param waveTypes the type(s) to stop to listen
     */
    void unlisten(final WaveType... waveTypes);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param wave the wave to send
     */
    void sendWave(final Wave wave);

    /**
     * Send a wave to the notifier.
     *
     * The wave will automatically be sent from JRebirthThread.
     *
     * @param waveType the type of wave to send
     * @param waveBean the wave bean
     *
    // ...
```

### Ready for Facade usage ###


```java
package org.jrebirth.af.api.facade;

import org.jrebirth.af.api.exception.CoreException;
import org.jrebirth.af.api.key.UniqueKey;
import org.jrebirth.af.api.link.ModelReady;

/**
 * The interface <strong>FacadeReady</strong>.
 *
 * This interface let the object to be managed into its facade type.
 *
 * @author Sébastien Bordes
 *
 * @param <R> A type that implements FacadeReady
 */
public interface FacadeReady<R extends FacadeReady<R>> extends ModelReady {

    /**
     * Launch the initialization of the component.
     *
     * This method is always called into JIT
     *
     * @throws CoreException if the initialization fails
     */
    void setup() throws CoreException;

    /**
     * Return the local facade used to manage singleton.
     *
     * @return the local facade
     */
    LocalFacade<R> localFacade();

    /**
     * Attach the local facade for this object type.
     *
     * @param localFacade the local facade to set
     */
    void localFacade(final LocalFacade<R> localFacade);

    /**
     * @return Returns the key.
     */
    UniqueKey<R> key();

    /**
     * @param key The key to set.
     */
    void key(final UniqueKey<R> key);

    /**
     * Release the component by deleting this key used by the WeakHashMap.
     *
     * @return true if the release has been performed and false when the released has been rejected/delayed
     */
    boolean release();

}
```


```java
    LocalFacade<R> localFacade();
```


```java
    UniqueKey<R> key();
```


### Ready for Command usage ###


```java
    <C extends Command> C getCommand(final Class<C> clazz, final Object... keyPart);
```


```java
    Wave callCommand(final Class<? extends Command> commandClass, final WaveData<?>... data);
```


### Ready for Service usage ###


```java
    <S extends Service> S getService(final Class<S> clazz, final Object... keyPart);
```



```java
    <WB extends WaveBean> Wave returnData(final Class<? extends Service> serviceClass, final WaveType waveType, final WB waveBean);
```


### Ready for Model usage ###


```java
    <M extends Model> M getModel(final Class<M> clazz, final Object... keyPart);
```



```java
    <WB extends WaveBean> Wave attachUi(final Class<? extends Model> modelClass, final WB waveBean);
```



Behaviored Component Features
-------------------------

### Manage Behavior ###

Any **BehavioredComponent** can have behaviors attached and queried at runtime.

Use **addBehavior** to attach a behavior, either by class or by providing a **BehaviorData** instance. The data-based approach allows a single call to attach every behavior referenced by that data object.

Once attached, behaviors can be queried:

* `hasBehavior(MyBehavior.class)` &mdash; checks whether a given behavior type is present.
* `getBehavior(MyBehavior.class)` &mdash; retrieves the behavior instance.
* `getBehaviorsAware(MyAwareClass.class)` &mdash; returns all behaviors implementing a filtering interface.


```java
package org.jrebirth.af.api.component.behavior;

import java.util.List;

import org.jrebirth.af.api.component.basic.Component;

/**
 * The interface <strong>BehavioredComponent</strong>.
 *
 * Define the contract used to let {@link EnhancedComponent} manage {@link Behavior}s.
 *
 * @author Sébastien Bordes
 *
 * @param <C> A type that implements Component
 */
public interface BehavioredComponent<C extends Component<C>> extends Component<C> {

    /**
     * Return true if the component contains the {@link Behavior} class provided.
     *
     * @param behaviorClass the class of the {@link Behavior} to check
     *
     * @return true if the component has got this behavior
     */
    boolean hasBehavior(final Class<? extends Behavior<?, ?>> behaviorClass);

    /**
     * Add a behavior to the component by providing the {@link Behavior} class.
     *
     * @param behaviorClass the class of the behavior to add
     *
     * @return the current component to chain method call
     */
    <BD extends BehaviorData, B extends Behavior<BD, ?>> C addBehavior(final Class<B> behaviorClass);

    /**
     * Add a behavior to the component by providing a {@link BehaviorData}.
     *
     * The BehaviorData allow to add several {@link Behavior} if many of them rare reference by the BehaviorData.
     *
     * @param data the Behavior Data that references Behavior classes to add
     *
     * @return the current component to chain method call
     */
    <BD extends BehaviorData> C addBehavior(final BD data);

    /**
     * Return the {@link Behavior} instance created for the given Behavior type.
     *
     * @param behaviorClass the Behavior type to get
     *
     * @return the Behavior instance attached to the component
     */
    <BD extends BehaviorData, B extends Behavior<BD, ?>> B getBehavior(final Class<B> behaviorClass);

    /**
     * Return the list of {@link BehaviorAware} filtered using given parameter.
     * 
     * @param awareClass the class that shall be implemented by behaviors returned
     * 
     * @return the list of {@link BehaviorAware}
     */
    <BA extends BehaviorAware> List<BA> getBehaviorsAware(final Class<BA> awareClass);

    /**
     * Return the {@link BehaviorData} used by the given Behavior type.
     *
     * @param behaviorClass the Behavior type that use the searched data
     *
     * @return the Behavior Data used by the given Behavior
     */
    <BD extends BehaviorData, B extends Behavior<BD, ?>> BD getBehaviorData(final Class<B> behaviorClass);

}
```

### Manage Behavior Data ###

**BehaviorData** is the state container for a behavior. Each behavior has a strongly-typed data object accessible via `data()`.

From outside the behavior, the component exposes **getBehaviorData**:


```java
    <BD extends BehaviorData, B extends Behavior<BD, ?>> BD getBehaviorData(final Class<B> behaviorClass);
```

A BehaviorData class lists the Behavior types it supports. The framework resolves them automatically when `addBehavior(dataInstance)` is called. To create your own, extend **BehaviorDataBase** and annotate with **@BehaviorDataFor**:


```java
    List<Class<? extends Behavior<?, ?>>> getBehaviors();
```


Component Registration
-----------------------

JRebirth provides an annotation-driven registration mechanism that decouples interfaces from implementations at compile time.

### @RegistrationPoint ###

Mark an interface with **@RegistrationPoint** to declare it as an extensible contract. The `exclusive` flag controls whether only one implementation (the highest priority) is used, or if multiple implementations coexist. The `reverse` flag inverts priority ordering.


```java
/**
 * The Interface RegistrationPoint is used to declare that this type can be extended.
 *
 * Implementations of this interface shall be declared using {@link Register} annotation.
```

### @Register ###

Mark an implementation class with **@Register(value=InterfaceClass.class)** to bind it to its registration point. You can set a `priority` level and an additional `weight` for fine-grained ordering.


```java
/**
 * The Interface Register.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Register {

    /**
     * Value.
     *
     * @return the class
     */
    Class<?> value() default Class.class;

    /**
     * Priority.
     *
     * @return the registration priority
     */
    PriorityLevel priority() default PriorityLevel.None;

    /**
     * Additional Weight to perform priority calculation.
     *
     * Shall be strictly lower than 1000
     *
     * @return the registration priority
     */
    int weight() default 0;

}
```

### Example: TodoService ###

The Todos showcase demonstrates the pattern. `TodoService` is the contract (registration point), and `TodoServiceImpl` is the registered implementation:


```java
@RegistrationPoint
```


```java
@Register(value = TodoService.class)
```

At compile time, the JRebirth annotation processor picks up these annotations and generates a **ModuleStarter** class that wires everything together. See the [Modularization](Modularization.html) page for details.

