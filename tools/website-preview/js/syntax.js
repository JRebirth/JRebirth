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

$(document).ready(function() {

	$(".lang-xml").each(function() {
		this.innerHTML = escapeXml(this.innerHTML);
	});

	$(".lang-html").each(function() {
		this.innerHTML = escapeHtml(this.innerHTML);
	});

	// Doxia markdown emits <pre><code class="language-java"> — map to google-code-prettify
	$("pre > code[class*='language-']").each(function() {
		var langMatch = this.className.match(/language-(\w+)/);
		var pre = this.parentNode;
		pre.className = (pre.className + " prettyprint").trim();
		if (langMatch) {
			pre.className = (pre.className + " lang-" + langMatch[1]).trim();
		}
	});

	$("pre.prettyprint").each(function() {
		if (!this.className.match(/lang-/)) {
			this.className = (this.className + " lang-java").trim();
		}
	});

	if (typeof prettyPrint === "function") {
		prettyPrint();
	}
});
