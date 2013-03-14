var XML_CHAR_MAP = {
'<': '&lt;',
'>': '&gt;',
'&': '&amp;',
'"': '&quot;',
"'": '&apos;'
};
 
function escapeXml (s) {
	return s.replace(/[<>&"']/g, function (ch) {
		return XML_CHAR_MAP[ch];
	});
}
var HTML_CHAR_MAP = {
'<': '&lt;',
'>': '&gt;',
'&': '&amp;',
'"': '&quot;',
"'": '&#39;'
};
 
function escapeHtml (s) {
	return s.replace(/[<>&"']/g, function (ch) {
		return HTML_CHAR_MAP[ch];
	});
}

function swap(id){


}

$(document).ready(function() {
	
	$(".lang-xml").each(function() {
			this.innerHTML = escapeXml(this.innerHTML);
	});
	
	$(".lang-html").each(function() {
			this.innerHTML = escapeHtml(this.innerHTML);
	});
	
	prettyPrint();
});