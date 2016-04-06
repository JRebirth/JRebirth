<head>
<![CDATA[
<title>Thread</title>
<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />]]>
</head>

<div id="catcherTitle">Thread Management</div>
<div id="catcherContent">Writing concurrent application could be painful ! Not with JRebirth</div>

<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} -->

Thread Management
====================

Threading overview
---------------------

JRebirth is multi-threaded, not only by using Task Worker provided by JavaFX APIs. JRebirth has got its own Thread to manage local events (called waves) allowing components to communicate with each others.
It also allows to manage multiple threads in a very simple manner with its included thread pool.

Thus all inner JRebirth tasks are processed into a custom thread and don't infer with the JavaFX Application Thread which have to manage user	events and some UI instantiation.

Lags, UI Freeze ... are lost to history :D

What happens when you debug a JRebirth application ? You can observe a lot of threads, but don't be scared !! Everything is at the right isolated place doing the right thing.

The most important are :

- __JAT__ \- JavaFX Application Thread
- __JIT__ \- JRebirth Internal Thread
- __JTP__ \- JRebirth Thread Pool (name pattern is *JTP Slot 'n'*)
- __HPTP__ \- High Priority Thread Pool (name pattern is *HPTP Slot 'n'*)


Other threads are related to JavaFX platform, JRebirth only	creates one thread with two Thread Pools.

__JRebirth Thread Pool__ and __High Priority Thread Pool__ have the same size.

computed like this: Number of available Core \* threadPoolSizeRatio parameter

The default parameter value is 2, so each thread pool will have a size of 16 on Core i7 (Quad Core with HyperThreading).
You can use a double value to divide this amount if you think that it can
disturb your platform performances
(0.125 value will only provide a thread pool size of 2 on a such platform).

Obviously a minimum size of 1 will be used if your thread pool ratio is too low.

<div class="uml">
	<a href="uml/Concurrent.png" rel="lightbox" title="Thread Class Diagram">
		<img class="redux" src="uml/Concurrent.png" alt="" />
	</a>
	<legend>Concurrent Class Diagram</legend>
</div>


Thread Usage
----------------

Each thread shall manage specific tasks, let's see their aims.

### JAT

__JavaFX Application Thread__
is the graphical thread of the toolkit, you shouldn't perform long task into it,
but you must update all node attached to the displayed scene into it.
It's the equivalent of the former EDT (Event Dispatch Thread) in Swing.
If you try to update a node attached to the main JavaFX scene outside it you will obtain an ugly Exception.


###JIT

__JRebirth Internal Thread__ is the internal thread of JRebirth Application Framework.
It will be used to host the communication engine between all JRebirth Components.
If you start a long task into it it will freeze all communication tasks because all runnable added will be executed synchronously according to FIFO rule (First-In First-Out).


###JTP

__JRebirth Thread Pool__ is designed to perform all other long task your application require, you just have to remember that any graphical update still require to be done into JAT.
Moreover any JRebirth internal configuration must be performed into JIT (ie: listen a WaveType).


###HPTP

__High Priority Thread Pool__ has been added to be able to assign a priority to a long task.
On small platform or when using very long task, the JTP can be overloaded and we must used a higher priority task to trigger an User interface refresh.
The HPTP will be used to start higher priority task when JTP is full.
It has same settings than JTP.
	

Thread Rules
---------------

###JavaFX Application Thread (JAT)
	
JAT is the the graphical thread of the  JavaFX toolkit (identical to Swinf EDT), all UI tasks must be perform into it only if a direct link can be establish between the graphical component and the displayed scene.
So if you call *setText("myText")* on a __Text__ node:

- You must call it into JAT if the node is displayed or linked to the displayed scene.
- You can call in whatever thread you want if the node isn't linked to the displayed scene.


JavaFX toolkit let you prepare some tree of node into a separate thread and then attached it to the displayed scene.
This is how JRebirth load and display any __Model__, the __PrepareModelCommand__ will run into __JTP__ to create the __Model__ root Node with all its children.
Then the __AttachModelCommand__ will be run into JAT to attach the model root node into another node already displayed into the __Scene__.


###JRebirth Internal Thread


###JRebirth Thread Pool
JRebirth provides 2 thread pools to run long task asynchronously without freezing neither the User Interface (JAT) nor JRebirth Messaging (JIT).
	
<!-- 
	
All Wave processing tasks are automatically done into the JIT.
	
When the JRebirth Framework needs to update UI (thanks to
<strong>Model</strong>
layer), it's done
automatically done into the JAT. No matters to have !

But when you call a component directly (synchronous method:
*getCommand,
	getService, getModel
*
), your call is processed into the current thread, so you must pay attention to what you are doing.

###Launch a runnable into JAT
<macro name="include">
<param name="highlight-theme" value="eclipse" />
<param name="source" value="core/src/main/java/org/jrebirth/af/core/component/basic/AbstractComponent.java" />
<param name="snippet" value="re:callCommand" />
</macro>

###Launch a runnable into JIT
<macro name="include">
<param name="source" value="core/src/main/java/org/jrebirth/af/core/component/basic/AbstractComponent.java" />
<param name="snippet" value="re:callCommand" />
</macro>

###Launch a runnable into JTP
<macro name="include">
<param name="source" value="core/src/main/java/org/jrebirth/af/core/component/basic/AbstractComponent.java" />
<param name="snippet" value="re:callCommand" />
</macro>

-->


Examples
-------------

<!-- 				

<span style="text-decoration: underline;">If you
	are into the JIT:
</span>


- getCommand
	=&gt; the code will be processed
	synchronously into the JIT, but if you call the
	*run*
	method of the command it will be run into the thread defined by the command configuration.

- getModel =&gt; all called methods of the model will be
	processed into the JIT ==&gt; Can Break !!! Toolkit will raise an error.



When you are into the JIT you must use
<strong>JRebirth.runIntoJAT</strong>
that call internally
*Platform.runLater*
to perform UI updates.



JRebirth Framework offers some default commands to
do the trick (
*DefaultUiCommand*
). Moreover if you
send a
*Wave*
with a
*WaveType*
listened by a model, it will be automatically processed into the JAT (Cool JRebirth Magic).



<span style="text-decoration: underline;">If you
	are into the JAT:
</span>


- getCommand, getService =&gt; this code will be processed into the JAT
	: Don't perform long task or you will freeze the application !!!

- getModel =&gt; actions will be done into the JAT
	==&gt; OK for short UI actions



When you are in the JAT you must use
<strong>JRebirth.runIntoJIT</strong>
that call internally
*JRebirthThread.runLater*
to run tasks into the core thread.



JRebirth Framework offers some
default commands to force to run into JIT &amp; JTP (
*DefaultCommand*
and
*DefaultPoolCommand*
). You can also send a wave
that will trigger a command, or call a service into another thread that
JAT.



*For example:*


If you want to call a service which make an asynchronous call
to a remote server. You can use the getService method to initiate the
call (By
default this call will be managed into JRebirth
Thread
Pool :
JTP), but the return must be managed into the JAT:


- Either use a wave to send data received from remote server,
	default service will generate a such wave (automatically processed into
	the JAT for
	models that listen it)

- Either call the model from the JAT and access to service
	results (the first way way is the best to use)
	
 -->

<div class="bottomLinks">
	<div class="previousDocPage">
		<a href="Application.html">Create your first Application</a>
	</div>
	<div class="nextDocPage">
		<a href="Logging.html">Configure Logging System</a>
	</div>
	<div class="tocDocPage">
		<a href="Toc.html">TOC</a>
	</div>
</div>