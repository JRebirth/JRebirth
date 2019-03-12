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

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/component/basic/Component.java|snippet=re:(listen\()\|(unlisten)} -->

You can learn more about [Wave](Wave.html) and [Notifier](Notifier.html) on their dedicated documentation pages.

### Sending Waves ###

Any **Component** is able to emit a new **Wave** and let the **Notifier** dispath it to registered **Component**.

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/component/basic/Component.java|snippet=re:(sendWave\()} -->

### Parenthood ###

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/component/basic/Component.java|snippet=re:(rootComponent\()\|(findInnerComponent)} -->

### Ready for Facade usage ###

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/facade/FacadeReady.java|snippet=re:(setup\()\|(release\()} -->

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/facade/FacadeReady.java|snippet=re:(localFacade\()} -->

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/facade/FacadeReady.java|snippet=re:(key\()} -->


### Ready for Command usage ###

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/CommandReady.java|snippet=re:(getCommand)} -->

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/CommandReady.java|snippet=re:(callCommand)} -->


### Ready for Service usage ###

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/ServiceReady.java|snippet=re:(getService)} -->


<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/ServiceReady.java|snippet=re:(returnData)} -->


### Ready for Model usage ###

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/ModelReady.java|snippet=re:(getModel)} -->


<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/link/ModelReady.java|snippet=re:(attachUi)} -->



Behaviored Component Features
-------------------------

### Manage Behavior ###

### Manage Behavior Data ###


Component Registration
-----------------------

