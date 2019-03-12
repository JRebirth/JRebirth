Modularization
====================

Module overview
---------------------

<div class="uml">
	<a href="uml/Module.png" rel="lightbox" title="Module Class Diagram">
		<img class="redux" src="uml/Module.png" alt="" />
	</a>
	<legend>Module Class Diagram</legend>
</div>

JRebirth modules are available thanks to Java Service Provider Interface (SPI).

A __JRebirth Module__ is any jar artifact that provides an implementation of the service __org.jrebirth.af.api.module.ModuleStarter__ using SPI engine.


Automatic Module
----------------------

To automate management of modules, JRebirth simply uses an annotation processor (at compilation time), thus you just have to add this maven dependency into any artifact build configuration.

<!-- MACRO{include|source=../includes/Basic_Dependencies.xml|snippet=xp:/dependencies/dependency[4]|set-first-line=1}-->					

This annotation processor will generate a class extending **ModuleStarter** that will be loaded using Java **ServiceLoader**.
A file named 'org.jrebirth.af.api.module.ModuleStarter'  will be added into META-INF/services folder and will contain the fully qualified named of the generated class that specializes ModuleStarter interface in order to activate this JRebirth Module. 


Modules Features
----------------------

JRebirth Module engine provides 4 annotations used to manage interactions between part of your application splitted into several jars. 

* Preloadable
* BootComponent
* RegistrationPoint
* Register

###  Preloadable

Any class of your application can be preloaded when the module is started, you shall add the **@Preloadable** annotation to its definition.
It means that the class definition will be loaded by the **ClassLoader**; it's really useful to load resources or static properties like **WaveItem**.


### BootComponent

Any **Component** of your application can be initialized when the module is started, you shall add the **@BootComponent** annotation to its definition.
It means that the component will be retrieved from its facade and so will be initialized; it's really useful to start some Components taht will listen some **Wave**.


### Component Registration

JRebirth components supports modularization by linking one or more __Component Implementation__ to one __Component Definition__, each of them could be stored into different JRebirth modules (basically a jar artifact).

#### Component Definition

A __Component Definition__ shall be registered using **@RegistrationPoint** annotation.
The Component is an interface or an abstract class. Its class will be used to retrieve all implementations.

The annotation takes two parameters:

* exclusive : true to indicate that there will be only one implementation. 
* reverse : true to  retrieve component using the inverse priority order

<!-- MACRO {include|source=api/src/main/java/org/jrebirth/af/api/ui/ModuleModel.java|snippet=aj:ModuleModel|snippet-start-offset=1}-->

#### Component Implementation

A __Component Implementation__ shall be registered by using the **@Register** annotation.

The annotation can take up to three parameters:

* value : the registration point Class
* priority : a PriorityLevel defining the loading priority 
* weight : an integer used to distinguish implementations that have same priority;
    
All implementations will be sorted according to respectively their:

* PriorityLevel
* Weight
* Name

<!-- MACRO {include|source=showcase/fxml/src/main/java/org/jrebirth/af/showcase/fxml/ui/main/FXMLShowCaseModel.java|snippet=aj:FXMLShowCaseModel|snippet-start-offset=1}-->   
    
Module Sample Case
---------------------

The ShowCase demo application is using severals Model components loaded using the same registration point **ModuleModel**.
Each implementation is stored into a different artifact and loaded dynamically using service entries.

When the main application starts, it will retrieve all implementations for the given registration point. As these implementations are **Model** components they can be attached to the main UI node, giving real UI modularization.

<!-- MACRO {include|source=showcase/demo/src/main/java/org/jrebirth/af/showcase/demo/ui/MainModel.java|snippet=aj:MainModel}-->
    
Customizing Module Name
---------------------