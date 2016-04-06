<head>
<![CDATA[
	<title>Notifier &amp; Components</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />
]]>
</head>

<div id="catcherTitle">Notifier &amp; Components</div>
<div id="catcherContent">Provide a communication means between top-level components</div>

<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} -->
        
Component definition
=========================

A component is a top-level actor of the __wB-CS-Mvc__ pattern.

Each component can communicate with other in a simple way. 
They are loosely coupled with each other and with the engine itself.

There are three kinds of components:

* Command
* Service
* Model


Wave, View and Controller are not JRebirth Components.

<div class="uml">
	<a href="uml/Link.png" rel="lightbox" title="Command Class Diagram ">
		<img class="redux" src="uml/Link.png" alt="" />
	</a>
	<legend>Communication Engine - Class Diagram</legend>
</div>


Notifier aim
---------------

JRebirth has got its own Notification Engine to allow each component to communicate with each other.
All these communications are processed into a dedicated thread.
The notification engine uses custom objects to transport data : the Waves.
	
<span style="text-decoration: underline;">Short UML Diagram:</span>
<div class="uml">
	<a href="uml/Link.png" rel="lightbox[uml]" title="Notifier Class Diagram ">
		<img class="redux" src="uml/Link.png" alt="" />
	</a>
</div>


<div class="bottomLinks">
	<div class="previousDocPage">
		<a href="Facades.html">How Facades work</a>
	</div>
	<div class="nextDocPage">
		<a href="Wave.html">Wave</a>
	</div>
	<div class="tocDocPage">
		<a href="Toc.html">TOC</a>
	</div>
</div>