<head>
<![CDATA[
	<title> User Interface</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />]]>
</head>
	
<div id="catcherTitle">User Interface Area</div>
<div id="catcherContent">Use a custom MVC pattern with a lot of convenient tricks</div>

<div class="toc">
<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} -->
</div>

        
UI Roles
===========

The User Interface layer is composed by three main parts :

* Model
* View
* Controller


Each of these must do predefined tasks to maintain a good Separation of Concerns (SoC).

Models are JRebirth Components like Commands and Services, they are mandatory whereas Views and Controllers are optionals.


<p>
	<span style="text-decoration: underline;">Short UML Diagram:</span>
</p>
<div style="text-align: center;">
	<img class="redux" src="uploads/images/uml/UI.png" alt="" />
</div>
	
Models
--------------------------

Model are JRebirth Components, they are retrievable from UiFacade and they can be dynamically attached to any placeholder.
They are able to listen Wave and to communicate with other components.

Models store business objects with custom method that allow to bound an object to a model and then to use its internal properties to bound them to UI widget.

Models' aim is to manage UI lifecycle and communication, you can place into some part of your business logic. 

Views
-------------------------------

Views are automatically created by Models according to generic type used, their main objectives is to build and wrap the graphical root Node.

You can manage Model-View interaction in both direction: Model-to-View by adding some 'package' Node getters into the view (preferred way for binding declarations), or View-to-Model by adding some 'package' methods into Model (only for some call).


Controllers
---------------

Controllers are dedicated to manage event handling by providing several ways to facilitate developers' life. They are automatically created by the view according to generic type used.

Event Handler can be attached from View-to-Controller, or from Controller-to-View (preferred way, to centralize event handlers declarations).


About Generics
------------------

You can ask why do MVC objects use so much generics type that hurt eyes !! The reason is really simple: To avoid lot of cast !

When you want to call a method from another part you will have a method to grab the part with the right type, so no cast are needed.

An alternative would be to write ourselves getter method with right cast but it's really painful and doesn't have any value. 

So you will write once this quite complex (especially when you use intermediate classes) class declaration with its generic type and you will enjoy coding without having to cast them.



Main Usage
--------------

User Interface layer is versatile and will adapt itself to your use case to avoid boiler plate code.
Hereafter you will find a list of all possible ways to use a Model:

* M \- Only Model: rely on SimpleModel implementation
* MV \- Model with a View: Controller is omitted
* MVC \- Model, View, Controller: Basic Implementation
* FMFFC \- FxmlModel , Fxml, FxmlController: View and Controller are respectively replaced by Fxml and FxmlController
* MVCFFC \- FxmlModel, View, Controller, Fxml, FxmlController: Basic + Fxml files

	

Model Overview
===============

<p>Models are directly synchronized with the UIFacade and can send &amp; receive Waves, they can also use any other components.</p>
<p>The goal of Models is to retrieve data from other layers, and to define Business Logic (business rules, authorizations ...).</p>
<p>The Model automatically build its attached view (except SimpleModel).</p>

<!-- MACRO{include|source=sample/src/main/java/org/jrebirth/af/sample/ui/SampleModel.java|snippet=aj:SampleModel|highlight-theme=eclipse} -->


InnerModels
------------------
	

Views Overview
==================

As explained before, the main goal of a View is to create the rootNode attached to the Model.

Fortunately the rootNode is automatically created according to the generic type used in the View header.
	
View's initialization
-------------------------

The View's initialization code perform several operations:


1. Link the model (strong reference)
2. Create the root Node object according to the first generic type that extends Node.class
3. Create the Controller according to the first generic type that extends Controller.class

If the construction fails, mainly due to Controller error, a custom error Node is created in order to display the stack trace of the exception.			

<!-- MACRO{include|source=sample/src/main/java/org/jrebirth/af/sample/ui/SampleView.java} -->
<!-- 
		<param name="snippet" value="aj:SampleView" />
-->

Annotation Event Handler
--------------------------
		


Controllers
=============

<!-- MACRO{include|source=sample/src/main/java/org/jrebirth/af/sample/ui/SampleController.java} -->
<!-- 
		<param name="snippet" value="aj:..SampleController" />
-->
	
Adapters & Handlers
--------------------------



<div class="bottomLinks">
	<div class="previousDocPage">
		<a href="Services.html">Services</a>
	</div>
	<div class="nextDocPage">
		<a href="Behavior.html">Behavior</a>
	</div>
	<div class="tocDocPage">
		<a href="Toc.html">TOC</a>
	</div>
</div>
