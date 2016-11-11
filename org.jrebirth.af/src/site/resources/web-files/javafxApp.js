if(typeof JAVAFX_APP_JNLP_SUBDOMAIN === 'undefined'){
	JAVAFX_APP_JNLP_SUBDOMAIN = "apps";
}

function launchApplication(jnlpfile) {
	dtjava.launch(            {
			url : 'http://' +JAVAFX_APP_JNLP_SUBDOMAIN +'.jrebirth.org/' + JAVAFX_APP_JNLP,
			jnlp_content : ''
		},
		{
			javafx : '2.0+'
		},
		{}
	);
	return false;
}
function javafxEmbed() {

	/*var startAppAnchor = document.getElementById("launchApp");
	if(startAppAnchor != null){
		startAppAnchor.onclick = function(){
			return launchApplication();
		}
	}*/
	
	dtjava.embed(
		{
			url : 'http://'+JAVAFX_APP_JNLP_SUBDOMAIN+'.jrebirth.org/' + JAVAFX_APP_JNLP,
			placeholder : 'javafx-app-placeholder',
			width : JAVAFX_APP_WIDTH,
			height : JAVAFX_APP_HEIGHT,
			jnlp_content : ''
		},
		{
			javafx : '2.0+'
		},
		{}
	);
}
<!-- Embed FX application into web page once page is loaded -->
dtjava.addOnloadCallback(javafxEmbed);