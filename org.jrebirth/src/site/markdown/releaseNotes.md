<head><title>Release Notes</title></head> 

Release Notes
==============

0.7.6 - ["Testarossa Autodrive"](https://www.youtube.com/watch?v=YWIX3UuWdxo‎) Release - 2013-11-30
-----------------------------------------

This version provides several threading enhancements.


- Manage Thread Pool Size Parameter  [#82](https://github.com/JRebirth/JRebirth/issues/82)<br/>
 You can now define your ration by setting the threadPoolSizeRatio parameter, the rule is threadPoolSizeRatio x nb of available processor	 


- WaveItem is now an abstract class [#79](https://github.com/JRebirth/JRebirth/issues/79)<br/>
 It forces the addition of brackets to define a nested class, thanks to Christophe from dooApp.


- Add a second Thread Pool [#77](https://github.com/JRebirth/JRebirth/issues/77)<br/>
 The second Thread Pool is named HPTP (High Priority Thread Pool). It will run jobs that have an higher priority
 than those currently running into all JTP slots


- Manage Runnable Priority [#76](https://github.com/JRebirth/JRebirth/issues/76)<br/>
  All Command and ServiceTask are now supporting Priority by using @RunInto and @Priority annotations
  or dedicated constructor.


- Improve JIT enhancement [#45](https://github.com/JRebirth/JRebirth/issues/45)<br/>
 JRebirth Internal Thread has been improved to reduced waiting and sleeping time in order to be more reactive and support Task Priority.


- Add JRebirth Preloader [#29](https://github.com/JRebirth/JRebirth/issues/29)<br/>
 A module has been added to show a JRebirth preloader splash when launching application with Java WebStart
 It's also possible to call the preloader programmatically by calling the preloadAndLaunch method.


- Provide concise UI view [#9](https://github.com/JRebirth/JRebirth/issues/9)<br/>
 This issue was fixed in a previous released by adding AbstractSimpleModel class.



0.7.5 - ["Aerodynamite"](https://www.youtube.com/watch?v=9FMDb6PBpIM) Release - 2013-09-30
-----------------------------------------

This version provides several priceless enhancements, you should consider migration.

- Force callCommand to use right WaveBean class feature<br/>
Now the callCommand require to use the right WaveBean type according to the command class used


- Allow to catch orphan waves feature &amp; Improve WaveBean usability duplicate<br/>
Orphan waves will be caught by an handler that could be parameterized, the default one will throw a RuntimeException only if DeveloperMode is activated  


- Manage default services feature<br/>
The service component has now a fully operational service task used to follow progression with binding.  
A bug was also fixed that was leading to a pause into the JIT waiting the result of the service. Now services are fully parallelized (running into JTP) and don't block the JIT.  
Thanks to Antoine for having disclosed [this problem](https://groups.google.com/d/msg/jrebirth-users/pFp4HcGzvpI/4RPI3ZXNiLUJ "Antoine's explanation") (related to dead code).  


- Add resource parameter support<br/>
All resources can be parameterized into JRebirth properties files.


- Provide Undo/Redo support<br/>
An Undo/Redo module has been added to demonstrate that Command layer is versatile and can manage complex workflow.  
A Showcase has been also added and deployed on the website: [Undo/Redo Showcase](showcases/UndoRedo.html "UndoRedo Showcase").   
Thanks to Bastien for [bug fix](https://github.com/JRebirth/JRebirth/pull/65 "Bastien's Pull Request").


- Allow Wave filtering according to a filter Data<br/>
When a component listen a generic wave, it will receive all waves even those related to other components.  
Now it's possible add a filter (defined as a callback) to receive only right waves.  
A sample is available into the StackModel component (of components module).


- Allow customization of component instantiation<br/>
It's now possible to define its own factory class in order to build JRebirth top-level components (Command, Service, Model).  
For example you can plug a injector (like Guice) into your factory to build a outstanding pluggable software.  


- Bad Wave Contract Check with several parameters<br/>
When application is start with developerMode parameter to true, all wave handler method are checked at runtime, this feature is now fully operational


- Boolean Property is not overridable bug fix<br/>
The default value of some parameters couldn't be updated by properties file, it's fixed


- Override programmatically a Parameter<br/>
It's now possible to override programmatically a parameter by calling the define method, parameters values will be set according to this order : default, properties file, define call.


- Only service method that return nothing (void) will not send a return wave<br/>
Service method that return null will send a return wave with the null value.


- Provide i18 Support<br/>
Now it's possible to manage l10n properties files to translate your application 


- Provide Smart logging capabilities<br/>
Internal log are now localized, and use log markers. Custom hook has been added to support line number for log back 


- Add getFXMLController method<br/>
Add this method into AbstractFXMLModel class to allow getting the FXML controller 


- Introduce JRebirth Runnable Priority<br/>
Priority can be set for Commands and Service Task and any runnable run into JIT and JTP 



0.7.4 - Core Release - 2013-07-22
---------------------------------

This version must be used instead of 0.7.3 because a lot of API changes and bug fixes is provided.  
JRebirth's Team is growing and we are now proud to have our own Forge plugin to build application faster (Thanks to [Raj](/team-list.html#jr3) & [Guru](/team-list.html#jr4))


- API changes, a lot of method have been rename to have a more concise name<br/>
CustomXXX method have been replaced, and now in example you just need to implement initModel instead of customInitialize method.


- Manage Image & StyleSheet as Resources feature<br/>
It's now possible to load these resources with JRebvith resource engine it's also possible to use them as JRebirth properties.


- Properties that define default folder for images and style sheets have been added<br/>
By default images and style sheets will be loaded from these folder .


- Parameterize Default CSS File feature<br/>
Define a default CSS file to load if none have been defined.


- Parameterize Application Name<br/>
Application name shown into the title bar of the stage can be parameterized as a properties instead of overriding the Application method getApplicationTitle.


- Provide Event Annotation for view's root node feature<br/>
We can now attach event handler on the view's root node by using a View annotation.


- Define id of the view's root node with an annotation<br/>
Add @RootNodeId annotation to define id of the view's root node.


- Manage default bindable object<br/>
Each Model class manage a default object and provides specific method to bound all properties.


- Improve internal commands<br/>
Internal command such AttachCommand and DetachCommand have been improved to be more robust.


- Fix a regression related to fonts preloading<br/>
Font preloading had been broken and so fixed.


- Fix deadlock generator<br/>
While using runInto method, deadlocks can occured in some specific cases, everything goes right now :)


- Improve overall concurrency<br/>
Some parts have been refactored to improve race condition with AtomicBoolean.	 


0.7.3 - 'Doc&Site' version - 2013-04-03
---------------------------------------

- JRebirth is now available on [JCenter](http://jcenter.bintray.com/org/jrebirth) repository hosted by bintray.


- JRebirth has now its own Logo (Special Thanks to [XoXi](http://www.xoxi.fr)).


- JRebirth provides a real Zip archive with all important files (also available in tar.gz and tar.bz2)


- JRebirth Web site is now entirely generated with Maven Doxia engine.<br/>
	All documentation is written with Doxia and could be updated on GitHub.<br/>
	Every one can send a pull request to add information or to correct mistakes.<br/>
	PDF Documentation is delayed or will be manually built.


- The website is now managed with rolling version:

	- [www.jrebirth.org](http://www.jrebirth.org) for latest stable version
	
	- [latest.jrebirth.org](http://latest.jrebirth.org) for latest development version
	
	- [sites.jrebirth.org](http://sites.jrebirth.org) will list all versions (current and archives)


- Javadoc API (and other reports) follow the same rules:

	- [api.jrebirth.org](http://api.jrebirth.org) Javadoc for latest stable version (all modules mixed)
	
	- [latest-api.jrebirth.org](http://latest-api.jrebirth.org) Javadoc for latest development version (all modules mixed)
	
	- [api-core.jrebirth.org](http://api-core.jrebirth.org) Javadoc for latest stable version of core module only
	
	- [latest-api-core.jrebirth.org](http://latest-api-core.jrebirth.org) Javadoc for latest development version of core module only


- JRebirth Catalog used to declare JRebirth archetype is now available online for each version.<br/>
The latest one is available here :  [Latest catalog](http://latest.jrebirth.org/archetype/jrebirth-catalog.xml)


0.7.2 - Fix version - 2013-02-22
--------------------------------

- JRebirth Maven Repository is up to date
Artifactory server was sometimes offline due to a lot of file corruption, Jenkins had broken a lot of build due to severals Java (64bit) VM fatal errors.


- Update archetype to 0.7.2 version
The 0.7.2 archetype has been upgraded to fit with new command and application classes


- Add OnAction annotation
JRebirth provides a new feature that allow handling events with annotation, a sample is available here : [Annotation Junit tests](https://github.com/JRebirth/JRebirth/tree/master/org.jrebirth/core/src/test/java/org/jrebirth/core/ui/annotation).


- Add Touch and Gesture events
All newest touch and gesture event handlers have been added (with annotation and standard way)


- Manage @RunInto
Thread management are now configurable with annotation, you can still use class inheritance


- Impossible to retrieve a component from ready method
Main facades had a bug in their own lifecycle, everything is ok now, (Special Thanks to Heiko who send his remarks on mailing list)


- Manage Multiple Stage
A feature has been added to manage Stage (open, close etc...) with a command and a service


- Improve Wave Contract
WaveType usage has been improved (most convenient ot use)


- Provide FXMLModel
FXML integration has been simplified, you can just use a FXMLModel with a fxml file, check a sample here : [FXML Junit test](https://github.com/JRebirth/JRebirth/tree/master/org.jrebirth/core/src/test/java/org/jrebirth/core/ui/fxml).


- Merge Event Tracking with new Logging way
Internal logger has been discarded, and internal event tracker has been upgraded, it allows JRebirth Analyzer tool to replay all JRebirth events to analyze application behaviour


- Add JRebirth properties management
A new mecanism to load properties has been created, it allows to use custom parameters (JRebirth already use some customizable properties)


- Refactor Resources Management
Usage of resources has been improved (font, colors, parameters), check sample test classes to learn more : [Junit Tests](https://github.com/JRebirth/JRebirth/tree/master/org.jrebirth/core/src/test/java/org/jrebirth/core/resource).


- Improve site deployment
JRebirth maven web site has been repaired, it provides Javadoc API, you should visit these urls : [api.jrebirth.org] [site.jrebirth.org] [sites.jrebirth.org]

