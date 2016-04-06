<head>
<![CDATA[
	<title>Understand Facades</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />
]]>
</head>

<div id="catcherTitle">Facades Area</div>
<div id="catcherContent">Simplify component accessibility.</div>

<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} -->
        
Facade(s)
=========================

JRebirth provides 4+1 **Facade**s
 used to manage its dedicated pattern **wB-CS-Mvc**.

Overview
-------------

<span style="text-decoration: underline;">Short UML Diagram:</span>

<div class="uml">
	<a title="Facade Class Diagram " rel="lightbox" href="uml/Facade.png">
		<img class="redux" alt="" src="uml/Facade.png"/>
	</a>
</div>

Global Facade
=================

*One Facade to rule them all*

JRebirth uses a global facade automatically created by JRebirth	__AbstractApplication__	class.


Internal Communication
---------------------------
	
The global facade create automatically the JRebirth notification engine. You should read the [Notifier](Notifier.html) page to have more informations.
	

Linked with Application
---------------------------

The global facade also allow to communicate with the Application class and therefore with its stage	and scene.
	


Manage Local Facades
-----------------------------

It allows to manage the three main facades:

* CommandFacade
* ServiceFacade
* UiFacade
* BehaviorFacade


The __GlobaleFacadeBase__ allow to get each of these facades by calling appropriate getters:

<!-- MACRO{include|highlight-theme=eclipse|source=api/src/main/java/org/jrebirth/af/api/facade/GlobalFacade.java|snippet=re:LocalFacade}-->

This link is bidirectionnal because global facade is accessible from the 3 main facades by calling :
	*getGlobalFacade()*
	(each facade extends the
	__AbstractGlobalReady__ abstract class which implement __GlobalReady__ interface.


<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/facade/GlobalReady.java|snippet=re:getGlobalFacade}-->



Local Facades
===================

There are 4 __LocalFacade__, each one is responsible of its layer components.


Basic Features
--------------------

Each Facade can manage its components (also called readyObject) throught some public methods.

* register : store a pre-built component
* unregister : remove a component
* exists : check if the component is already registered, it could be released
* retrieve : used to get a component (build and register it if needed)

<!-- MACRO{include|source=api/src/main/java/org/jrebirth/af/api/facade/Facade.java|snippet=re:E extends R}-->


Component Key
-------------------

Each Component (CSM) are registered into the facade using a [UniqueKey](../apidocs/org/jrebirth/af/api/key/UniqueKey.html), there are 2 options:

* [ClassKey](#ClassKey)
* [MultitonKey](#MultitonKey)

### ClassKey

The simplest key is the [ClassKey](../apidocs/org/jrebirth/af/core/key/ClassKey.html), it's only composed by the class object of the component, thus the can only be stored once into the facade (=singleton).


### MultitonKey

The [MultitonKey](../apidocs/org/jrebirth/af/core/key/MultitonKey.html) uses also the class of the component but add a variable part to be able to store several instances of a same Component.

### Convenient Builder

JRebirth API have a lot of method that take the Component Class with additional objects as key parts.
But the API also has a convenient class that provides method tused to build key.

<!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/key/Key.java|snippet=re:static}-->
	

Provide Tracking
--------------------

JRebirth allows to track all creation &amp; finalization of each component (Command, Service, Model, View) and also track the emission of waves and their path.
	
They are logged in order to be processed by the JRebirthAnalyzer tool.


<div class="bottomLinks">
	<div class="previousDocPage">
		<a href="Logging.html">Configure Logging system</a>
	</div>
	<div class="nextDocPage">
		<a href="Notifier.html">Notifier &amp; Components</a>
	</div>
	<div class="tocDocPage">
		<a href="Toc.html">TOC</a>
	</div>
</div>	