<head>
<![CDATA[
	<title>3-minute tutorial with Maven</title>
    <link rel="stylesheet" type="text/css" href="../css/shCoreEclipse.css" media="all"/>
]]>
</head>


<div id="catcherTitle">3-minute tutorial with Maven</div>
<div id="catcherContent">Create a JRebirth Application by using Maven Archetype</div>


3-minute Tutorial
===========================
				
<div id="ytplayer"></div>

<script>
// <![CDATA[]
	// Load the IFrame Player API code asynchronously.
	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/player_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

	// Replace the 'ytplayer' element with an < iframe > and
	// YouTube player after the API code downloads.
	var player;
	function onYouTubePlayerAPIReady() {
		player = new YT.Player('ytplayer', {
			height: '400',
			width: '600',
			videoId: 'Ii2Rf7qinpM'
		});
	}
	//]]>
</script>



Command Line Tutorial
==============================

For Maven Gurus, here you have command lines to type to do the job.


Full Auto
----------

<a href="images/tuto/Maven_Archetype_CLI_OneLine.png" rel="lightbox">
	![CLI OneLine](images/icons/view.gif)
</a>
mvn archetype:generate -DinteractiveMode=false -DarchetypeCatalog=http://www.jrebirth.org/archetype/jrebirth-catalog.xml -DarchetypeGroupId=org.jrebirth.af
-DarchetypeArtifactId=archetype
-DarchetypeVersion=${project.version} -DarchetypeRepository=http://oss.jfrog.org/artifactory/simple/oss-release-local/ -DgroupId=org.jrebirth.af -DartifactId=sample -Dversion=1.0.0-SNAPSHOT
-Dpackage=org.jrebirth.af.sample


Interactive pre-filled
----------------------

mvn archetype:generate -DarchetypeCatalog=http://www.jrebirth.org/archetype/jrebirth-catalog.xml -DgroupId=org.jrebirth.af -DartifactId=sample -Dversion=1.0.0-SNAPSHOT
-Dpackage=org.jrebirth.af.sample
	

Full Interactive
----------------

<a href="images/tuto/Maven_Archetype_CLI_interactive.png" rel="lightbox">
	![CLI Interactive](images/icons/view.gif)
</a>
mvn archetype:generate -DarchetypeCatalog=http://www.jrebirth.org/archetype/jrebirth-catalog.xml


				
<!-- <div class="bottomLinks"> <div class="previousDocPage"> <a href="Installation.html">Installation</a> </div> <div class="nextDocPage"> <a href="Application.html">Create your first Application</a> 
</div> <div class="tocDocPage"> <a href="index.html">TOC</a> </div> </div> -->


<div style="clear:both"></div>
