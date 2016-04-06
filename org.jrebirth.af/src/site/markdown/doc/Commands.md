<head>
<![CDATA[
	<title>Command</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />
]]>
</head>

<div id="catcherTitle">Command Area</div>
<div id="catcherContent">Reuse common code and don't be scared by threading issue</div>

<!-- MACRO{toc|section=0|fromDepth=0|toDepth=4} -->
        
Command Overview
=========================

A command is an atomic reusable action that could be triggered from anywhere.
There are two kinds of Commands:

* Single Command
* Multi Command (composed by a set of Single Command)


<div class="uml">
	<a href="uml/Command.png" rel="lightbox" title="Command Class Diagram ">
		<img class="redux" src="uml/Command.png" alt="" />
	</a>
	<legend>Command - Class Diagram</legend>
</div>


Single Command
------------------------

Single Commands are atomic and are run independently.
If you trigger several commands in-a-row you will trigger them in parallel according to their predefined running thread.
JRebirth engine will serialize their instantiation and their startup but they will be processed into JAT, JIT or one of JTP slots.
JAT and JIT will process command one after the other. JTP will act in the same manner but internal engine will dispatch all actions to its pooled threads (by default {2 x Number of CPU core} slots are available by Thread Pool).


MultiCommand
-------------------------

MultiCommand provides the ability to run some Single Commands sequentially or in parallel.

Hereafter you will find an example of MultiCommand used to display a model UI:

<!-- MACRO{include|highlight-theme=eclipse|source=core/src/main/java/org/jrebirth/af/core/command/basic/showmodel/ShowModelCommand.java|snippet=aj:org.jrebirth.af.core.command.basic.showmodel.ShowModelCommand}-->

The multi command code will be run into JIT, but its sub-command will be run respectively into JTP and JAT (according to their own
configuration).


**Why are they using these threads ?**

*ShowModelCommand* use the annotation defined into *DefaultMultiCommand* to run into **JIT**.

*PrepareModelCommand* use the annotation defined into *DefaultPoolCommand* to run into **JTP**.

*ShowModelCommand* use the annotation defined into *DefaultUICommand* to run into **JAT** (it's mandatory to update scene's nodes).


How to trigger a Command
--------------------------------

Commands are designed to be disposable after usage, but they could be retained by strong references to be executed twice or more.
Each call will return a new instance of the command class, because each command was stored with a timestamp key-based Timestamp is added to the default key and also for custom key).

You can create a command by four different ways:

* [Direct way : getCommand(Command.class).run()](#direct)
* [Indirect way (Wave)](#indirect)
* [From Controller by using *linkCommand* method](#link)
* [Component Callback](#callback)

Please note that Commands are JRebirth top components (with Services and Models), they follow the component lifecycle as described in [Facade Page](Facades.html).

Thus *AbstractBaseCommand* extends *BehavioredComponent* and *Component* and their descendants must provide *initCommand()*, *processAction()* and *execute()* methods.



<a name="direct"></a>
### Direct way

It's possible to call a command from any JRebirth component (Command, Service, Model).

You just need to call the **getCommand** method to build an instance. You can provide either the Command Class **or** its unique key (if required).


<!-- {include |source=core/src/main/java/org/jrebirth/af/core/facade/AbstractFacade.java
 |snippet=re:E retrieve" /> } -->

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/CommandReady.java|snippet=re:getCommand}-->


Once you have retrieved your command, you can store it with a strong reference to avoid GC collecting it. Your command life will depend on the
lifetime of your strong reference. Thus you will be able to configure directly your command properties.
Finally to trigger it you must call its **run()** method.
The <a class="apiMethod" href="../apidocs/org/jrebirth/af/core/command/AbstractBaseCommand.html#perform-org.jrebirth.af.api.wave.Wave-">perform</a> method of the command will be executed into the [Thread Type](Thread.html#Threading_overview) chosen (JAT, JIT, JTP).

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/command/Command.java|snippet=re:run\(\)}-->

**Be careful**: As explained [here](#key) each call to **getCommand** could retrieve the same *OR* another instance of the command class depending on key parts provided and instance's strong references.


<a name="indirect"></a>
### Indirect way

You can trigger a command execution by calling **callCommand** from any component.

You can provide some parameters into **WaveData** that will be hold by the wave and so available into the command.

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/component/basic/AbstractComponent.java|snippet=re:callCommand}-->


<a name="link"></a>
### Convenient Link method

Controllers provide a convenient method named *void linkCommand(Node, EventType&lt;E&gt;, Class&lt;? extends Command&gt;, WaveData&lt;?&gt;...)*

This method useful to declare with only one line of code the call of a command triggered when the chosen JavaFX event occurred on a node belonging to the View.

It's also possible to add a callback function, in example to manage double-click detection (see **LinkedCallback**).

<!-- MACRO{include|source=showcase/analyzer/src/main/java/org/jrebirth/af/showcase/analyzer/ui/controls/ControlsController.java|snippet=re:linkCommand}-->


<a name="callback"></a>
### Component Callback way

Any **Command** is a JRebirth component and can benefits of *Observer* features.
It's possible to listen a **WaveType** (registration done into the *initModel*, *initService* and *initCommand* methods) in order to be notified when a such wave is sent.
You can manage custom methods called by reflection to handle waves and can you catch all waves into the *processAction(Wave)* method.

More information is available in [Notifier &amp; Component page](Notifier.html).

Be careful, commands can handle asynchronous wave only if they haven't been collected by the Garbage Collector! So you need to create a instance and to keep a strong references on it somewhere.



Command Properties
------------------------

A command is an atomic action reusable or not that can be run into a predefined thread. A command provides specific features:

* A property indicating	[in which thread](#threading) it must be run
* A	[Component Key](#key), built with Class Name and an additional string
* An [Action to process](#action) (*execute(Wave)* method)

<!-- *  A [#parent">Parent Command]() for chained use case  -->

* A [Wave Bean Generic Type](#wavebean) that will hold all required data to process the action
* [Optional custom properties](#props) (useful for reusable commands)


<a name="threading"></a>
How to manage Threading issues
--------------------------------

Each command will be launch by JRebirth Internal engine and run into a dedicated thread.
Threads involved in a JRebirth application are explained into the [Thread page](Thread.html).

The runner thread can be configured by two ways:

* [Annotation](#annotation)

* [Constructor argument and class inheritance](#inheritance)

The priority rule is : Annotation &gt; Constructor argument &gt; Default value.
The default value is : JIT (JRebirth Internal Thread).
The top-level annotation will be systematically used overriding lower ones and also constructor arguments.


<a name="annotation"></a>
### Annotation usage

To run a command into the JAT (JavaFX Application Thread), use this annotation :
<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/single/ui/DefaultUICommand.java|snippet=re:@RunInto}-->

To run a command into the JIT (JRebirth Internal Thread), use this annotation :
<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/single/internal/DefaultCommand.java|snippet=re:@RunInto}-->

To run a command into JTP (JRebirth Thread Pool, the command will be run into a slot), use this annotation :
<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/single/pool/DefaultPoolCommand.java|snippet=re:@RunInto}-->


<a name="inheritance"></a>
### Class inheritance usage

To run a command into the JAT (JavaFX Application Thread), extends the *DefaultUICommand* class :

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/basic/showmodel/AttachModelCommand.java|snippet=re:extends DefaultUIBeanCommand}-->


To run a command into the JIT (JRebirth Internal Thread), extends the *DefaultCommand* class :

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/basic/ChainWaveCommand.java|snippet=re:extends DefaultCommand}-->

*(implementation of WaveListener is optional)*


To run a command into JTP (JRebirth Thread Pool, the command will be run into a slot), extends the *DefaultPoolCommand* class :

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/basic/showmodel/PrepareModelCommand.java|snippet=re:extends DefaultPoolBeanCommand}-->

### Constructor argument usage

It's also possible to define the runType (The thread which will handle the command) by passing **RunType**, each descendant classes of **AbstractBaseCommand** offer at least one constructor allowing this enum value to the command constructor.

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/command/AbstractBaseCommand.java|snippet=re:RunType runType}-->


<a name="key"></a> 
Component Key
--------------------------------------

The component key as described in the [Facade page](Facades.html) allow storing unique commands.

All objects provided as key part will be serialized (toString call) to build the key.
Warning : When a command is built consequently to a wave reception, the wave UID will be concatenated to the class name instead of using key parts.

<a name="action"></a> 
Execute Code
-----------------------------

Command are run in a custom thread in 3 steps:

* preExecute
* execute
* postExecute

Only the **execute** method must be implemented to perform your action.

<a name="wavebean"></a> 
Wave Bean
------------------------

A **WaveBean** is a Java Bean that allow carrying a lot of named properties into a **Wave**.

Command Classes can declare a generic type that allow to cast the Wave Bean into the right one, it allows to use *getWaveBean().getMyProperty()* into source code which is more convenient than parsing **WaveData** (but it implies to create a dedicated Java Class).


<!-- <subsection name="Parent Command" id="parent">  When you use a command into a **MultiCommand** , this property will hold a strong 
reference to it.   -->

<a name="props"></a> 
Custom properties
------------------------------

Each command is a simple Java Object, you can add fields or JavaFX Properties to help configuring your execution code.
You must pay attention that these values will be kept until the command is disposed (after execution if no strong references exists).

For example you can attach a command to a **Model** and launch it several times while updating its internal properties.


<div class="bottomLinks">
	<div class="previousDocPage">
		[Wave](Facades.html)
	</div>
	<div class="nextDocPage">
		[Services](Services.html)
	</div>
	<div class="tocDocPage">
		[TOC](Toc.html)
	</div>
</div>