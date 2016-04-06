<head>
<![CDATA[
	<title>Resources</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />
]]>
</head>

<div id="catcherTitle">Using Resources</div>
<div id="catcherContent">Decrease your memory footprint by using resource wrapper</div>

<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} --> 

Resources
=========================

The JRebirth Framework provides an useful way to deal with your local resources, we currently support :

* Colors
* Fonts
* Images
* Parameters
* CSS (WIP)
* FXML (WIP)

These resources can consume a lot of memory if you don't dispose them (especially big images and fonts) when you stop using them. 
JRebirth provides a mechanism to store them weakly and to rebuild them if necessary in order to	use the less memory as required.
To do this JRebirth stores a **ResourceParam** lightweight object that contains all information to create the heavyweight resource.
This one is linked by a **ResourceItem** to facilitate its usage.

<span style="text-decoration: underline;">UML Diagram Overview:</span>

<div class="uml">
	<a href="uml/Resource.png" rel="lightbox" title="Resources Class Diagram ">
		<img class="redux" src="uml/Resource.png" alt="" />
	</a>
</div>

There are 3 ways to declare resources, each one is available for all kind of resources with special feature for parameters.

* [With Resources.create(ResourceParam)](#create)
* [With Enumeration allowing customization](#enum)
* [Dynamically](#dynamic)


<a name="create"></a>
With Resources.create(ResourceParam)
----------------------------------------
	
The first way is to hold static field declaration instantiated  with custom 'factory' Resources (not named, with an overloaded create method to avoid later cast).
This static fields can be hold in any class you want but we recommend to store them into an **Interface** used 'as' an enum.
It's better to have an interface per resource type to avoid big file that blends all resources.
Another interesting trick, is to add a static import on related static Resource.create method to shorten the resource declaration (Ctrl+Shift+M with Eclipse).
	
Static import declaration:
<!-- MACRO {include|highlight-theme=eclipse|source=core/src/test/java/org/jrebirth/af/core/resource/color/TestColors.java|snippet=re:import static|snippet-start-offset=1}-->

Example of Web color declaration:
<!-- MACRO {include|source=core/src/test/java/org/jrebirth/af/core/resource/color/TestColors.java|snippet=re:TEST_COLOR_WEB_1|snippet-start-offset=1}-->

<a name="enum"></a>
With Enumeration
-----------------------------

The other way to declare is a little bit complex, it implies to create an **enum** that implements a *ResourceItem* interface.
But it requires to add some copy/paste code into implemented methods.
	

<a name="dynamic"></a>
Dynamically
------------------

Not implemented yet

	
To manage these resources we use a *enum* hack to cleanly define them and most important to have a concise way to use them without calling singleton getter or another complex set of methods.

So if you want to handle resources with JRebirth mechanism, you just have to create an enumeration that implement the interface of the resource you want, with a custom constructor.


Color
----------

For example to manage web color, (basic hexadecimal string #00CC00), you have to use this declaration:
<!-- MACRO {include|source=core/src/test/java/org/jrebirth/af/core/resource/color/TestColors.java|snippet=re:TEST_COLOR_WEB_1|snippet-start-offset=1}-->

Hereafter an example of an interface that hold multiple colors.
<!-- MACRO {include|source=core/src/test/java/org/jrebirth/af/core/resource/color/TestColors.java|snippet=aj:..TestColors|expand-snippets=true}-->


<!-- MACRO {include|source=core/src/main/java/org/jrebirth/af/core/resource/color/WebColor.java}-->
<!-- |snippet=aj:*.WebColor|expand-snippets=true -->



But this interface doesn't explain how to register a resource, so let's see an example.
<!-- MACRO {include|source=presentation/src/main/java/org/jrebirth/af/presentation/resources/PrezColors.java}-->
	<!-- |snippet=aj:..PrezColors |expand-snippets=true -->


Font
---------

For example to manage the **Turtles**font, you have to use this declaration:
<!-- MACRO {include|source=core/src/test/java/org/jrebirth/af/core/resource/font/TestFonts.java|snippet=re:TEST_REAL_FONT_1|snippet-start-offset=1}-->

Hereafter an example of an interface that hold multiple colors.

<!-- MACRO {include|source=core/src/test/java/org/jrebirth/af/core/resource/font/TestFonts.java|snippet=aj:..TestFonts|expand-snippets=true}-->

	
Parameters
------------

todo


<div class="bottomLinks">
	<div class="previousDocPage">
		<a href="Behavior.html">Behavior</a>
	</div>
	<!--<div class="nextDocPage"><a href="Notifier.html">Notifier &amp; Wave</a></div> -->
	<div class="tocDocPage">
		<a href="Toc.html">TOC</a>
	</div>
</div>
