(function () {
  "use strict";

  var header = document.querySelector(".jr-header");
  var toggle = document.querySelector(".jr-menu-toggle");
  var nav = document.getElementById("jr-site-navigation");
  var copyBtn = document.getElementById("jr-copy-btn");
  var mavenCode = document.getElementById("jr-maven-code");

  function setOpen(open) {
    if (!header || !toggle) return;
    header.classList.toggle("is-open", open);
    toggle.setAttribute("aria-expanded", open ? "true" : "false");
    toggle.setAttribute("aria-label", open ? "Close navigation" : "Open navigation");
  }

  if (toggle) {
    toggle.addEventListener("click", function () {
      setOpen(!header.classList.contains("is-open"));
    });
  }

  if (nav) {
    nav.addEventListener("click", function (event) {
      if (event.target && event.target.tagName === "A") {
        setOpen(false);
      }
    });
  }

  document.addEventListener("keydown", function (event) {
    if (event.key === "Escape") {
      setOpen(false);
    }
  });

  if (copyBtn && mavenCode) {
    copyBtn.addEventListener("click", function () {
      var text = mavenCode.innerText || mavenCode.textContent || "";
      var done = function () {
        var previous = copyBtn.textContent;
        copyBtn.textContent = "Copied";
        window.setTimeout(function () {
          copyBtn.textContent = previous;
        }, 1600);
      };
      if (navigator.clipboard && navigator.clipboard.writeText) {
        navigator.clipboard.writeText(text).then(done).catch(function () {
          fallbackCopy(text, done);
        });
      } else {
        fallbackCopy(text, done);
      }
    });
  }

  function fallbackCopy(text, done) {
    var area = document.createElement("textarea");
    area.value = text;
    area.setAttribute("readonly", "");
    area.style.position = "absolute";
    area.style.left = "-9999px";
    document.body.appendChild(area);
    area.select();
    try {
      document.execCommand("copy");
      done();
    } finally {
      document.body.removeChild(area);
    }
  }
})();
