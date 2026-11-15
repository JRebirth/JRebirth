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

  /* Label Previous / Next / TOC; mirror pager at top; fixed Top → #top */
  (function decorateDocPagers() {
    function decoratePager(root) {
      if (!root || root.getAttribute("data-jr-nav-ready") === "1") return;

      function decorate(box, dirLabel) {
        if (!box) return;
        var link = box.querySelector("a");
        if (!link || link.querySelector(".jr-doc-nav-dir")) return;
        var title = (link.textContent || "").replace(/\s+/g, " ").trim();
        if (!title) return;
        link.textContent = "";
        var dir = document.createElement("span");
        dir.className = "jr-doc-nav-dir";
        dir.textContent = dirLabel;
        var name = document.createElement("span");
        name.className = "jr-doc-nav-title";
        name.textContent = title;
        link.appendChild(dir);
        link.appendChild(name);
      }

      decorate(root.querySelector(".previousDocPage"), "Previous");
      decorate(root.querySelector(".tocDocPage"), "Contents");
      decorate(root.querySelector(".nextDocPage"), "Next");

      var prev = root.querySelector(".previousDocPage");
      var toc = root.querySelector(".tocDocPage");
      var next = root.querySelector(".nextDocPage");
      if (toc) root.appendChild(toc);
      if (next) root.appendChild(next);
      if (prev) root.insertBefore(prev, root.firstChild);
      root.setAttribute("data-jr-nav-ready", "1");
    }

    var bottom = document.querySelector(".bottomLinks");
    decoratePager(bottom);

    Array.prototype.forEach.call(document.querySelectorAll(".topLinks"), decoratePager);

    /* Clone bottom pager to top when header pager is missing */
    if (bottom && !document.querySelector(".topLinks")) {
      var top = bottom.cloneNode(true);
      top.className = "topLinks";
      top.removeAttribute("data-jr-nav-ready");
      Array.prototype.forEach.call(top.querySelectorAll("[data-jr-nav-ready]"), function (el) {
        el.removeAttribute("data-jr-nav-ready");
      });
      decoratePager(top);

      var shell = document.querySelector(".jr-doc-shell");
      var main = shell && shell.querySelector(".jr-doc-main");
      var host = main || shell;
      if (host) {
        var sidebarToc = host.querySelector(":scope > ul.toc, :scope > .jr-doc-layout");
        var firstSection = host.querySelector(":scope > section, :scope > h1");
        var anchor = document.createElement("div");
        anchor.id = "jr-doc-top";
        anchor.className = "jr-doc-top-anchor";
        if (sidebarToc && sidebarToc.parentNode === host) {
          host.insertBefore(anchor, sidebarToc);
          host.insertBefore(top, sidebarToc);
        } else if (firstSection && firstSection.parentNode === host) {
          host.insertBefore(anchor, firstSection);
          host.insertBefore(top, firstSection);
        } else {
          host.insertBefore(anchor, host.firstChild);
          host.insertBefore(top, host.firstChild.nextSibling);
        }
      }
    } else if (!document.getElementById("jr-doc-top")) {
      var shell2 = document.querySelector(".jr-doc-shell");
      if (shell2) {
        var a = document.createElement("div");
        a.id = "jr-doc-top";
        a.className = "jr-doc-top-anchor";
        shell2.insertBefore(a, shell2.firstChild);
      }
    }

    /* Fixed Top control — stays on screen, scrolls to page top anchor */
    if (document.body.classList.contains("jr-docs") && !document.querySelector(".jr-back-to-top")) {
      var topBtn = document.createElement("a");
      topBtn.className = "jr-back-to-top";
      topBtn.href = "#top";
      topBtn.setAttribute("aria-label", "Back to top");
      topBtn.innerHTML = '<span class="jr-back-to-top-dir">Top</span><span class="jr-back-to-top-icon" aria-hidden="true">↑</span>';
      topBtn.addEventListener("click", function (event) {
        event.preventDefault();
        var target =
          document.getElementById("top") ||
          document.getElementById("jr-doc-top") ||
          document.querySelector(".jr-header") ||
          document.body;
        if (target && target.scrollIntoView) {
          target.scrollIntoView({ behavior: "smooth", block: "start" });
        } else {
          window.scrollTo({ top: 0, behavior: "smooth" });
        }
        if (history && history.pushState) {
          history.pushState(null, "", "#top");
        }
      });
      topBtn.classList.add("is-visible");
      document.body.appendChild(topBtn);
    }
  })();

  /* Left sticky TOC + working # anchors on all /doc/* pages that ship a Doxia TOC */
  (function enableSidebarToc() {
    var path = (window.location.pathname || "").replace(/\\/g, "/");
    if (!/\/doc\/[^/]+\.html$/i.test(path)) return;
    if (/\/doc\/Toc\.html$/i.test(path)) return;

    var shell = document.querySelector(".jr-doc-shell");
    var toc = shell && shell.querySelector(":scope > ul.toc");
    if (!shell || !toc) return;

    function norm(text) {
      return String(text || "")
        .replace(/\s+/g, " ")
        .replace(/\u00a0/g, " ")
        .trim()
        .toLowerCase();
    }

    function scrollToId(id, behavior) {
      if (!id || id === "null") return false;
      var target = document.getElementById(id);
      if (!target) return false;
      target.scrollIntoView({ behavior: behavior || "smooth", block: "start" });
      return true;
    }

    /* Doxia TOC hrefs (#Foo_Bar) exist, but headings often have no id — wire them by label order */
    var headings = Array.prototype.slice.call(
      shell.querySelectorAll("h1, h2, h3, h4, h5, h6")
    );
    var claimed = [];
    Array.prototype.forEach.call(toc.querySelectorAll('a[href^="#"]'), function (link) {
      var href = link.getAttribute("href") || "";
      var id = href.slice(1);
      var label = norm(link.textContent);
      if (!id || id === "null" || !label) {
        var emptyLi = link.closest("li");
        if (emptyLi && !label) {
          emptyLi.classList.add("jr-toc-empty");
        }
        return;
      }
      var heading = null;
      for (var i = 0; i < headings.length; i++) {
        if (claimed.indexOf(headings[i]) !== -1) continue;
        if (norm(headings[i].textContent) === label) {
          heading = headings[i];
          break;
        }
      }
      if (!heading) return;
      claimed.push(heading);
      if (heading.id !== id) {
        heading.id = id;
      }
    });

    /* Robust 2-column layout: sidebar | main (works on AllInOne with hundreds of sections) */
    if (!shell.querySelector(":scope > .jr-doc-layout")) {
      var layout = document.createElement("div");
      layout.className = "jr-doc-layout";
      var sidebar = document.createElement("aside");
      sidebar.className = "jr-doc-sidebar";
      sidebar.setAttribute("aria-label", "On this page");
      var main = document.createElement("div");
      main.className = "jr-doc-main";

      var nodes = Array.prototype.slice.call(shell.childNodes);
      nodes.forEach(function (node) {
        if (node === toc) {
          sidebar.appendChild(toc);
        } else {
          main.appendChild(node);
        }
      });
      layout.appendChild(sidebar);
      layout.appendChild(main);
      shell.appendChild(layout);
    }

    shell.classList.add("jr-has-sidebar-toc");
    toc.classList.add("jr-sidebar-toc");

    toc.addEventListener("click", function (event) {
      var link = event.target && event.target.closest ? event.target.closest('a[href^="#"]') : null;
      if (!link || !toc.contains(link)) return;
      var id = (link.getAttribute("href") || "").slice(1);
      if (!id || id === "null") {
        event.preventDefault();
        return;
      }
      if (scrollToId(id, "smooth")) {
        event.preventDefault();
        if (history && history.pushState) {
          history.pushState(null, "", "#" + id);
        } else {
          window.location.hash = id;
        }
      }
    });

    if (window.location.hash) {
      var initialId = window.location.hash.slice(1);
      window.setTimeout(function () {
        scrollToId(initialId, "auto");
      }, 0);
    }
  })();
})();
