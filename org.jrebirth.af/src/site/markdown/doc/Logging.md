<head>
<![CDATA[
	<title>Configure Logging</title>
	<link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all" />
]]>
</head>

<div id="catcherTitle">To log or not to log !</div>
<div id="catcherContent">Logging is so important to diagnose UI bugs.</div>


<!-- MACRO{toc|section=0|fromDepth=1|toDepth=4} -->
        
Logging
=========================

My first idea about logging for JavaFX was to build myself a small lightweight and powerful module to add log capability to JRebirth Framework.

But after some hundred of lines of code written, I realized that using a real logging library could be interesting and not too heavy to embed.


Logging Facade
--------------------
		
I choose to add dependency to slf4j-api :
<a href="http://www.slf4j.org/">Simple Logging Facade 4 Java</a>


This API is lightweight because the jar is near to 10 kb.

<!-- MACRO{include|highlight-theme=eclipse|source=../includes/Logging_Dependencies.xml|snippet=xp:/dependencies/dependency[1]|first-line=1}-->


NOP
--------

The slf4j api allow to declare logger for JRebirth Framework but doesn't provide any logger implementation.

By default all logs are rejected and you will have this message into the Java console at startup:

<p style="color: red;">
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See <a target="_blank" href="http://www.slf4j.org/codes.html#StaticLoggerBinder">http://www.slf4j.org/codes.html#StaticLoggerBinder</a> for further details.
</p>

To avoid this error message to appear you can add the No-OPeration dependency:

<!-- MACRO{include|source=../includes/Logging_Dependencies.xml|snippet=xp:/dependencies/dependency[2]|set-first-line=1}-->


LOGback
-------------

Personally I choose LOGback implementation because it represents a good	compromise between performances and customization.
It was finely integrated because it was written by the same team as slf4j.

LOGBack jars are quite heavy and weight more than 800 kb so it could be a problem for tiny applications.
To use it you must add this dependency into your pom.xml :

<!-- MACRO{include|source=../includes/Logging_Dependencies.xml|snippet=xp:/dependencies/dependency[3]|set-first-line=1}-->



Then you must add a configuration file like this one into the application classpath:

<!-- MACRO{include|source=../../../showcase/analyzer/src/main/resources/logback.xml}-->
		

SimpleLogger
-------------------------

If you don't want to embed 800 kb of jar to provide logging feature you	can use the simple logger provided by slf4j, it's only a 10 kb jar file.


You just have to add this dependency to your pom.xml:

<!-- MACRO{include|source=../includes/Logging_Dependencies.xml|snippet=xp:/dependencies/dependency[4]|set-first-line=1}-->
		

JRebirth Logs
=====================


All JRebirth logs use an internationalized engine allowing us to provide extensible log messaging.

It could be seen as an overkill feature but it has some advantages:
 
* Provide localized log message
* Manage log level activation automatically
* Parameterize log level, to increase or decrease it by custo
* Disable Message resolution for high performance

 Note that you can still use your logger basic features.

 JRebirth provides its own LoggerFactory that you can initialize like this:

 <!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/concurrent/JRebirthThread.java|snippet=re:JRLoggerFactory.getLogger|snippet-start-offset=1}-->

 Then you can log your message like this:

 <!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/concurrent/JRebirthThread.java|snippet=re:LOGGER\.}-->

 All these log messages are store into an interface implemented by the class to let them accessible

 <!-- MACRO{include|source=core/src/main/java/org/jrebirth/af/core/concurrent/ConcurrentMessages.java|snippet=aj:ConcurrentMessages}-->


<div class="bottomLinks">
    <div class="previousDocPage">
        <a href="Thread.html">Thread Management</a>
    </div>
    <div class="nextDocPage">
        <a href="Facades.html">How Facades work</a>
    </div>
    <div class="tocDocPage">
        <a href="Toc.html">TOC</a>
    </div>
</div>
