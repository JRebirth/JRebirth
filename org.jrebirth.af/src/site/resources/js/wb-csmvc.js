/**
 * wB-CSMvc — pure SVG layout (skeleton + links + nodes in ONE coordinate space).
 * First node = GF. Hover → autoplay. Waves only when complete.
 */
(function () {
  "use strict";

  var DEG = Math.PI / 180;
  var REDUCE = window.matchMedia && window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  var STEP_MS = REDUCE ? 0 : 340;
  var PLAY_GAP = REDUCE ? 30 : 180;

  function polar(cx, cy, r, deg) {
    var a = deg * DEG;
    return { x: cx + r * Math.sin(a), y: cy - r * Math.cos(a) };
  }

  function evenAngles(n, a0, a1) {
    if (n <= 0) return [];
    if (n === 1) return [(a0 + a1) / 2];
    var out = [];
    for (var i = 0; i < n; i++) out.push(a0 + (i / (n - 1)) * (a1 - a0));
    return out;
  }

  function buildModel() {
    return {
      id: "gf",
      label: "GF",
      kind: "dot",
      variant: "gf",
      tip: "GlobalFacade — created by the Application · owns all facades · creates the Notifier",
      children: [
        { id: "n", label: "N", kind: "dot", variant: "n", tip: "Notifier — central wave bus · routes messages between components" },
        {
          id: "bf",
          label: "BF",
          kind: "dot",
          variant: "bf",
          tip: "BehaviorFacade — manages Behaviors attached to Commands, Services and Models",
          childRadius: 0.55,
          childSector: [-62, 62],
          children: [
            { id: "b-c", label: "B", kind: "beh", variant: "c", tip: "Behavior — cross-cutting concern attached to a Command" },
            { id: "b-s", label: "B", kind: "beh", variant: "s", tip: "Behavior — cross-cutting concern attached to a Service" },
            { id: "b-m", label: "B", kind: "beh", variant: "m", tip: "Behavior — cross-cutting concern attached to a Model" }
          ]
        },
        {
          id: "cf",
          label: "CF",
          kind: "dot",
          variant: "cf",
          tip: "CommandFacade — creates and manages Commands (Simple and Multi)",
          childRadius: 0.70,
          // Compact leftward arc — stay clear of UF Models below
          childSector: [-145, -70],
          children: [
            {
              id: "mc",
              label: "MC",
              kind: "comp",
              variant: "c",
              tip: "MultiCommand — composite command that runs child Simple Commands",
              behaviors: 1,
              childRadius: 0.40,
              childSector: [-155, -105],
              children: [
                { id: "mc-sc1", label: "SC", kind: "comp", variant: "c", tip: "SimpleCommand — atomic action · child of a MultiCommand", small: true },
                { id: "mc-sc2", label: "SC", kind: "comp", variant: "c", tip: "SimpleCommand — atomic action · child of a MultiCommand", small: true }
              ]
            },
            { id: "sc1", label: "SC", kind: "comp", variant: "c", tip: "SimpleCommand — atomic action", behaviors: 2 },
            { id: "sc2", label: "SC", kind: "comp", variant: "c", tip: "SimpleCommand — atomic action", behaviors: 1 }
          ]
        },
        {
          id: "sf",
          label: "SF",
          kind: "dot",
          variant: "sf",
          tip: "ServiceFacade — creates and manages Services for long-running work",
          childRadius: 0.78,
          // Rightward only — leave bottom clear for Models
          childSector: [50, 100],
          children: [
            {
              id: "s1",
              label: "S",
              kind: "comp",
              variant: "s",
              tip: "Service — can own nested Services for long-running work",
              behaviors: 2,
              childRadius: 0.42,
              childSector: [55, 105],
              children: [
                { id: "s1a", label: "S", kind: "comp", variant: "s", tip: "Service — nested child of a parent Service", small: true },
                { id: "s1b", label: "S", kind: "comp", variant: "s", tip: "Service — nested child of a parent Service", small: true },
                { id: "s1c", label: "S", kind: "comp", variant: "s", tip: "Service — nested child of a parent Service", small: true }
              ]
            },
            { id: "s2", label: "S", kind: "comp", variant: "s", tip: "Service — long-running / asynchronous work", behaviors: 1 }
          ]
        },
        {
          id: "uf",
          label: "UF",
          kind: "dot",
          variant: "uf",
          tip: "UiFacade — creates and manages Models and their UI attachments",
          childRadius: 0.78,
          // Spread Models horizontally — leave a bit of margin vs CF on the left
          childSector: [112, 218],
          children: [
            {
              id: "m-fx",
              label: "M",
              kind: "comp",
              variant: "m",
              tip: "Model is managing the view — FXML + FXMLController",
              behaviors: 1,
              childRadius: 0.48,
              childSector: [95, 135],
              children: [
                { id: "m-fx-fxml", label: "FXML", kind: "ui", tip: "FXML — declarative JavaFX view" },
                { id: "m-fx-fxc", label: "FXC", kind: "ui", tip: "FXMLController — controller for an FXML view" }
              ]
            },
            {
              id: "m-vc",
              label: "M",
              kind: "comp",
              variant: "m",
              tip: "Model is managing the view — View + Controller",
              behaviors: 1,
              childRadius: 0.46,
              childSector: [160, 200],
              children: [
                { id: "m-vc-v", label: "V", kind: "ui", tip: "View — JavaFX scene-graph UI" },
                { id: "m-vc-c", label: "C", kind: "ui", tip: "Controller — handles user events" }
              ]
            },
            {
              id: "m-fxml",
              label: "M",
              kind: "comp",
              variant: "m",
              tip: "Model is managing the view — standalone FXML",
              childRadius: 0.46,
              childSector: [175, 185],
              children: [{ id: "m-fxml-only", label: "FXML", kind: "ui", tip: "FXML — declarative JavaFX view" }]
            },
            {
              id: "m-v",
              label: "M",
              kind: "comp",
              variant: "m",
              tip: "Model is managing the view",
              behaviors: 1,
              childRadius: 0.46,
              childSector: [185, 200],
              children: [{ id: "m-v-only", label: "V", kind: "ui", tip: "View — UI without a Controller" }]
            }
          ]
        }
      ]
    };
  }

  function flattenSteps(root) {
    var steps = [];
    function walk(node, parentId) {
      steps.push({
        id: node.id,
        label: node.label,
        kind: node.kind,
        variant: node.variant,
        tip: node.tip,
        sub: node.sub,
        small: !!node.small,
        bare: !!node.bare,
        parentId: parentId,
        behaviors: node.behaviors || 0,
        _childRadius: node.childRadius,
        _childSector: node.childSector,
        _children: node.children || []
      });
      (node.children || []).forEach(function (ch) {
        walk(ch, node.id);
      });
    }
    walk(root, null);
    return steps;
  }

  /** Single source of truth for façade positions (stage pixels). */
  function computeAnchors(w, h) {
    var cx = w * 0.5;
    // Lower the whole tetrahedron so BF + Behavior diamonds are not clipped
    var cy = h * 0.42;
    var R = Math.min(w, h) * 0.22;
    return {
      R: R,
      gf: { x: cx, y: cy },
      n: { x: cx, y: cy + R * 0.22 },
      bf: { x: cx, y: cy - R * 0.88 },
      cf: { x: cx - R * 0.88, y: cy + R * 0.52 },
      sf: { x: cx + R * 0.88, y: cy + R * 0.52 },
      uf: { x: cx, y: cy + R * 0.88 }
    };
  }

  function ns(name) {
    return document.createElementNS("http://www.w3.org/2000/svg", name);
  }

  function WbCsmvc(rootEl) {
    this.root = rootEl;
    this.stage = rootEl.querySelector(".jr-pyr-stage");
    this.progressEl = rootEl.querySelector(".jr-pyr-progress");
    this.playBtn = rootEl.querySelector(".jr-pyr-btn--play");
    this.prevBtn = rootEl.querySelector(".jr-pyr-btn--prev");
    this.nextBtn = rootEl.querySelector(".jr-pyr-btn--next");
    this.resetBtn = rootEl.querySelector(".jr-pyr-btn--reset");
    this.model = buildModel();
    this.steps = flattenSteps(this.model);
    this.index = 0;
    this.playing = false;
    this.busy = false;
    this.nodes = Object.create(null);
    this.links = Object.create(null);
    this.svg = null;
    this.skel = null;
    this.linkLayer = null;
    this.nodeLayer = null;
    this.waves = null;
    this.tipEl = null;
    this._timer = null;
    this._hoverArmed = true;
    this._bind();
    this.reset();
  }

  WbCsmvc.prototype._bind = function () {
    var self = this;
    if (this.playBtn) {
      this.playBtn.addEventListener("click", function (e) {
        e.stopPropagation();
        self.play();
      });
    }
    if (this.prevBtn) {
      this.prevBtn.addEventListener("click", function (e) {
        e.stopPropagation();
        self.stepBack();
      });
    }
    if (this.nextBtn) {
      this.nextBtn.addEventListener("click", function (e) {
        e.stopPropagation();
        self.stepForward();
      });
    }
    if (this.resetBtn) {
      this.resetBtn.addEventListener("click", function (e) {
        e.stopPropagation();
        self.reset();
        self._hoverArmed = true;
      });
    }
    this.root.addEventListener("mouseenter", function () {
      if (!self._hoverArmed || self.index > 0 || self.playing) return;
      self._hoverArmed = false;
      self.play();
    });
    window.addEventListener("resize", function () {
      self._relayoutVisible();
    });
  };

  WbCsmvc.prototype._size = function () {
    var r = this.stage.getBoundingClientRect();
    return { w: Math.max(400, Math.floor(r.width)), h: Math.max(440, Math.floor(r.height || 560)) };
  };

  WbCsmvc.prototype._anchors = function () {
    var s = this._size();
    return computeAnchors(s.w, s.h);
  };

  WbCsmvc.prototype._ensure = function () {
    if (this.svg) return;
    // Remove leftover object / old layers
    var stale = this.stage.querySelectorAll(".jr-pyr-svg, .jr-wb-svg, .jr-wb-nodes");
    for (var i = 0; i < stale.length; i++) stale[i].parentNode.removeChild(stale[i]);

    var svg = ns("svg");
    svg.setAttribute("class", "jr-wb-svg");
    svg.setAttribute("aria-hidden", "true");
    this.stage.appendChild(svg);
    this.svg = svg;

    this.skel = ns("g");
    this.skel.setAttribute("class", "jr-wb-skel");
    svg.appendChild(this.skel);

    this.linkLayer = ns("g");
    this.linkLayer.setAttribute("class", "jr-wb-link-layer");
    svg.appendChild(this.linkLayer);

    this.waves = ns("g");
    this.waves.setAttribute("class", "jr-wb-waves");
    this.waves.setAttribute("opacity", "0");
    svg.appendChild(this.waves);

    this.nodeLayer = ns("g");
    this.nodeLayer.setAttribute("class", "jr-wb-node-layer");
    svg.appendChild(this.nodeLayer);

    var tip = document.createElement("div");
    tip.className = "jr-wb-tip";
    tip.setAttribute("role", "tooltip");
    tip.hidden = true;
    this.stage.appendChild(tip);
    this.tipEl = tip;
  };

  WbCsmvc.prototype._sync = function () {
    var s = this._size();
    this.svg.setAttribute("width", String(s.w));
    this.svg.setAttribute("height", String(s.h));
    this.svg.setAttribute("viewBox", "0 0 " + s.w + " " + s.h);
  };

  WbCsmvc.prototype._drawSkeleton = function () {
    var a = this._anchors();
    var g = this.skel;
    while (g.firstChild) g.removeChild(g.firstChild);

    function face(pts, cls) {
      var p = ns("polygon");
      p.setAttribute("points", pts.map(function (pt) { return pt.x + "," + pt.y; }).join(" "));
      p.setAttribute("class", cls);
      g.appendChild(p);
    }
    function edge(p1, p2, cls) {
      var l = ns("line");
      l.setAttribute("x1", p1.x);
      l.setAttribute("y1", p1.y);
      l.setAttribute("x2", p2.x);
      l.setAttribute("y2", p2.y);
      l.setAttribute("class", cls || "jr-wb-edge");
      g.appendChild(l);
    }

    face([a.bf, a.cf, a.gf], "jr-wb-face jr-wb-face--l");
    face([a.bf, a.sf, a.gf], "jr-wb-face jr-wb-face--r");
    face([a.cf, a.sf, a.uf, a.gf], "jr-wb-face jr-wb-face--f");
    face([a.cf, a.sf, a.uf], "jr-wb-face jr-wb-face--base");

    edge(a.bf, a.cf);
    edge(a.bf, a.sf);
    edge(a.bf, a.uf);
    edge(a.cf, a.sf);
    edge(a.cf, a.uf);
    edge(a.sf, a.uf);

    // Structural spokes (always on) — GF hub
    edge(a.gf, a.bf, "jr-wb-core");
    edge(a.gf, a.cf, "jr-wb-core");
    edge(a.gf, a.sf, "jr-wb-core");
    edge(a.gf, a.uf, "jr-wb-core");

    ["bf", "cf", "sf", "uf"].forEach(function (id) {
      var c = ns("circle");
      c.setAttribute("cx", a[id].x);
      c.setAttribute("cy", a[id].y);
      c.setAttribute("r", 3.5);
      c.setAttribute("class", "jr-wb-vtx jr-wb-vtx--" + id);
      g.appendChild(c);
    });

    this._rebuildWaves(a);
  };

  WbCsmvc.prototype._rebuildWaves = function (a) {
    var g = this.waves;
    while (g.firstChild) g.removeChild(g.firstChild);
    function wave(d, color, dur, begin) {
      var c = ns("circle");
      c.setAttribute("r", "4");
      c.setAttribute("fill", color);
      c.setAttribute("class", "jr-wb-wave-dot");
      var am = ns("animateMotion");
      am.setAttribute("dur", dur);
      am.setAttribute("repeatCount", "indefinite");
      if (begin) am.setAttribute("begin", begin);
      am.setAttribute("path", d);
      c.appendChild(am);
      g.appendChild(c);
    }
    wave("M" + a.cf.x + " " + a.cf.y + " Q " + a.gf.x + " " + a.gf.y + " " + a.sf.x + " " + a.sf.y, "#ff6e20", "4.2s");
    wave("M" + a.sf.x + " " + a.sf.y + " Q " + a.gf.x + " " + a.gf.y + " " + a.uf.x + " " + a.uf.y, "#31baff", "5s", "1.2s");
    wave("M" + a.uf.x + " " + a.uf.y + " Q " + a.gf.x + " " + a.gf.y + " " + a.bf.x + " " + a.bf.y, "#ffb447", "3.8s", "0.6s");
  };

  WbCsmvc.prototype._clear = function () {
    if (this._timer) {
      clearTimeout(this._timer);
      this._timer = null;
    }
    this.playing = false;
    this.busy = false;
    this.nodes = Object.create(null);
    this.links = Object.create(null);
    if (this.svg && this.svg.parentNode) this.svg.parentNode.removeChild(this.svg);
    if (this.tipEl && this.tipEl.parentNode) this.tipEl.parentNode.removeChild(this.tipEl);
    this.svg = this.skel = this.linkLayer = this.nodeLayer = this.waves = null;
    this.tipEl = null;
    this.index = 0;
    this.root.classList.remove("is-complete");
    this._updateProgress();
  };

  WbCsmvc.prototype.reset = function () {
    this._clear();
    this._ensure();
    this._sync();
    this._drawSkeleton();
    this._setWaves(false);
    this._setButtons();
  };

  WbCsmvc.prototype._setWaves = function (on) {
    if (this.waves) this.waves.setAttribute("opacity", on ? "1" : "0");
  };

  WbCsmvc.prototype._onComplete = function () {
    this.root.classList.add("is-complete");
    this._setWaves(true);
    this._setButtons();
  };

  WbCsmvc.prototype._updateProgress = function () {
    if (this.progressEl) this.progressEl.textContent = this.index + " / " + this.steps.length;
  };

  WbCsmvc.prototype._setButtons = function () {
    var done = this.index >= this.steps.length;
    var atStart = this.index <= 0;
    if (this.prevBtn) this.prevBtn.disabled = atStart || this.playing || this.busy;
    if (this.nextBtn) this.nextBtn.disabled = done || this.playing || this.busy;
    if (this.playBtn) {
      this.playBtn.disabled = done && !this.playing;
      this.playBtn.textContent = this.playing ? "Pause" : "Play";
    }
  };

  WbCsmvc.prototype._targetFor = function (step) {
    var a = this._anchors();
    if (a[step.id]) return a[step.id];
    var parent = this.nodes[step.parentId];
    if (!parent) return a.gf;
    var siblings = parent.meta._children || [];
    var idx = -1;
    for (var i = 0; i < siblings.length; i++) {
      if (siblings[i].id === step.id) {
        idx = i;
        break;
      }
    }
    var sector = parent.meta._childSector || [-40, 40];
    // childRadius is a fraction of tetrahedron R → scales with stage, homogeneous spacing
    var frac = parent.meta._childRadius;
    if (frac == null || frac > 2) frac = 0.45;
    var radius = a.R * frac;
    var angles = evenAngles(Math.max(siblings.length, 1), sector[0], sector[1]);
    var deg = idx >= 0 ? angles[idx] : (sector[0] + sector[1]) / 2;
    return polar(parent.x, parent.y, radius, deg);
  };

  WbCsmvc.prototype._radiusOf = function (step) {
    if (step.kind === "dot") {
      if (step.variant === "gf") return 18;
      if (step.variant === "n") return 12;
      return 15;
    }
    if (step.kind === "beh") return 10;
    if (step.kind === "ui") return 14;
    if (step.small) return 12;
    return 15;
  };

  WbCsmvc.prototype._showTip = function (text, clientX, clientY) {
    if (!this.tipEl || !text) return;
    var stage = this.stage.getBoundingClientRect();
    this.tipEl.textContent = text;
    this.tipEl.hidden = false;
    var tw = this.tipEl.offsetWidth || 160;
    var th = this.tipEl.offsetHeight || 40;
    var left = clientX - stage.left - tw / 2;
    var top = clientY - stage.top - th - 14;
    if (left < 8) left = 8;
    if (left + tw > stage.width - 8) left = stage.width - tw - 8;
    if (top < 8) top = clientY - stage.top + 18;
    this.tipEl.style.left = Math.round(left) + "px";
    this.tipEl.style.top = Math.round(top) + "px";
  };

  WbCsmvc.prototype._hideTip = function () {
    if (this.tipEl) this.tipEl.hidden = true;
  };

  WbCsmvc.prototype._bindTip = function (g, tip) {
    var self = this;
    if (!tip) return;
    g.style.cursor = "help";
    g.addEventListener("pointerenter", function (e) {
      self._showTip(tip, e.clientX, e.clientY);
    });
    g.addEventListener("pointermove", function (e) {
      self._showTip(tip, e.clientX, e.clientY);
    });
    g.addEventListener("pointerleave", function () {
      self._hideTip();
    });
  };

  WbCsmvc.prototype._createNode = function (step, x, y) {
    var self = this;
    var g = ns("g");
    g.setAttribute("class", "jr-wb-node jr-wb-node--" + step.kind + (step.variant ? " jr-wb-node--" + step.variant : "") + (step.small ? " is-small" : "") + (step.bare ? " is-bare" : ""));
    g.setAttribute("data-id", step.id);
    g.setAttribute("data-tip", step.tip || step.label);
    g.setAttribute("transform", "translate(" + x + "," + y + ")");
    g.style.opacity = "0";
    g.style.pointerEvents = "auto";

    var r = this._radiusOf(step);

    if (step.kind === "beh") {
      var dia = ns("rect");
      dia.setAttribute("x", -8);
      dia.setAttribute("y", -10);
      dia.setAttribute("width", 16);
      dia.setAttribute("height", 16);
      dia.setAttribute("rx", 2);
      dia.setAttribute("transform", "rotate(45)");
      dia.setAttribute("class", "jr-wb-shape jr-wb-shape--beh");
      g.appendChild(dia);
      // Label under the diamond — readable against the dark stage
      var bt = ns("text");
      bt.setAttribute("y", 18);
      bt.setAttribute("text-anchor", "middle");
      bt.setAttribute("dominant-baseline", "hanging");
      bt.setAttribute("class", "jr-wb-label jr-wb-label--beh");
      bt.textContent = "B";
      g.appendChild(bt);
    } else if (step.kind === "dot") {
      var c = ns("circle");
      c.setAttribute("r", r);
      c.setAttribute("class", "jr-wb-shape jr-wb-shape--dot");
      g.appendChild(c);
      var t = ns("text");
      t.setAttribute("text-anchor", "middle");
      t.setAttribute("dominant-baseline", "central");
      t.setAttribute("class", "jr-wb-label");
      t.textContent = step.label;
      g.appendChild(t);
    } else {
      var w = step.kind === "ui" || step.label.length > 2 ? 36 : 28;
      if (step.small) w = 24;
      var h = step.small ? 16 : 20;
      var rect = ns("rect");
      rect.setAttribute("x", -w / 2);
      rect.setAttribute("y", -h / 2);
      rect.setAttribute("width", w);
      rect.setAttribute("height", h);
      rect.setAttribute("rx", step.kind === "ui" ? 10 : 5);
      rect.setAttribute("class", "jr-wb-shape jr-wb-shape--comp");
      g.appendChild(rect);
      var lt = ns("text");
      lt.setAttribute("text-anchor", "middle");
      lt.setAttribute("dominant-baseline", "central");
      lt.setAttribute("class", "jr-wb-label");
      lt.textContent = step.label;
      g.appendChild(lt);

      // behavior badges on host
      var nBeh = step.behaviors | 0;
      for (var i = 0; i < nBeh; i++) {
        var bx = w / 2 - 2 + i * 10;
        var by = -h / 2 - 2;
        var bg = ns("g");
        bg.setAttribute("class", "jr-wb-badge");
        bg.setAttribute("transform", "translate(" + bx + "," + by + ")");
        bg.style.pointerEvents = "auto";
        bg.style.cursor = "help";
        var br = ns("rect");
        br.setAttribute("x", -5);
        br.setAttribute("y", -5);
        br.setAttribute("width", 10);
        br.setAttribute("height", 10);
        br.setAttribute("rx", 1.5);
        br.setAttribute("transform", "rotate(45)");
        br.setAttribute("class", "jr-wb-shape jr-wb-shape--badge");
        bg.appendChild(br);
        self._bindTip(bg, "Behavior attached to this " + (step.variant === "c" ? "Command" : step.variant === "s" ? "Service" : "Model"));
        g.appendChild(bg);
      }
    }

    this._bindTip(g, step.tip || step.label);
    this.nodeLayer.appendChild(g);
    return { el: g, r: r };
  };

  WbCsmvc.prototype._moveNode = function (el, x, y) {
    el.setAttribute("transform", "translate(" + Math.round(x) + "," + Math.round(y) + ")");
  };

  WbCsmvc.prototype._linkEnds = function (ax, ay, bx, by, ra, rb) {
    var dx = bx - ax;
    var dy = by - ay;
    var len = Math.sqrt(dx * dx + dy * dy) || 1;
    var ux = dx / len;
    var uy = dy / len;
    return {
      x1: ax + ux * ra,
      y1: ay + uy * ra,
      x2: bx - ux * rb,
      y2: by - uy * rb
    };
  };

  WbCsmvc.prototype._spawn = function (step, done) {
    var self = this;
    this._ensure();
    this._sync();
    this.busy = true;

    var parent = step.parentId ? this.nodes[step.parentId] : null;
    var anchors = this._anchors();
    var target = this._targetFor(step);
    var startX = parent ? parent.x : target.x;
    var startY = parent ? parent.y : target.y;

    var created = this._createNode(step, startX, startY);
    var el = created.el;
    var r = created.r;

    var line = null;
    if (parent) {
      line = ns("line");
      var linkVar = step.variant || parent.meta.variant || "gf";
      line.setAttribute("class", "jr-wb-link jr-wb-link--" + linkVar);
      var e0 = this._linkEnds(parent.x, parent.y, startX, startY, parent.r, r);
      line.setAttribute("x1", e0.x1);
      line.setAttribute("y1", e0.y1);
      line.setAttribute("x2", e0.x1);
      line.setAttribute("y2", e0.y1);
      this.linkLayer.appendChild(line);
      this.links[step.id] = line;
    }

    var rec = { el: el, x: target.x, y: target.y, r: r, meta: step };
    this.nodes[step.id] = rec;

    function finish() {
      rec.x = target.x;
      rec.y = target.y;
      self._moveNode(el, target.x, target.y);
      el.style.opacity = "1";
      el.classList.add("is-settled");
      if (line && parent) {
        var e = self._linkEnds(parent.x, parent.y, rec.x, rec.y, parent.r, r);
        line.setAttribute("x1", e.x1);
        line.setAttribute("y1", e.y1);
        line.setAttribute("x2", e.x2);
        line.setAttribute("y2", e.y2);
      }
      self.busy = false;
      if (self.index >= self.steps.length) self._onComplete();
      if (done) done();
    }

    if (REDUCE || STEP_MS === 0 || !parent) {
      finish();
      return;
    }

    el.style.opacity = "1";
    var t0 = performance.now();
    function frame(now) {
      var p = Math.min(1, (now - t0) / STEP_MS);
      var e = 1 - Math.pow(1 - p, 3);
      var x = startX + (target.x - startX) * e;
      var y = startY + (target.y - startY) * e;
      self._moveNode(el, x, y);
      if (line && parent) {
        var ends = self._linkEnds(parent.x, parent.y, x, y, parent.r, r);
        line.setAttribute("x1", ends.x1);
        line.setAttribute("y1", ends.y1);
        line.setAttribute("x2", ends.x2);
        line.setAttribute("y2", ends.y2);
      }
      if (p < 1) requestAnimationFrame(frame);
      else finish();
    }
    requestAnimationFrame(frame);
  };

  WbCsmvc.prototype._pausePlay = function () {
    if (!this.playing) return;
    this.playing = false;
    if (this._timer) {
      clearTimeout(this._timer);
      this._timer = null;
    }
  };

  /** Advance one transition (Next). */
  WbCsmvc.prototype.stepForward = function () {
    var self = this;
    this._pausePlay();
    if (this.busy || this.index >= this.steps.length) {
      this._setButtons();
      return;
    }
    var step = this.steps[this.index++];
    this._updateProgress();
    this._setButtons();
    this._spawn(step, function () {
      self._setButtons();
    });
  };

  /** Rewind one transition (Previous) — remove last spawned node + link. */
  WbCsmvc.prototype.stepBack = function () {
    this._pausePlay();
    if (this.busy || this.index <= 0) {
      this._setButtons();
      return;
    }
    this.index -= 1;
    var step = this.steps[this.index];
    var rec = this.nodes[step.id];
    if (rec && rec.el && rec.el.parentNode) rec.el.parentNode.removeChild(rec.el);
    delete this.nodes[step.id];
    var line = this.links[step.id];
    if (line && line.parentNode) line.parentNode.removeChild(line);
    delete this.links[step.id];
    this.root.classList.remove("is-complete");
    this._setWaves(false);
    this._hideTip();
    this._updateProgress();
    this._setButtons();
  };

  // Back-compat alias
  WbCsmvc.prototype.step = function () {
    this.stepForward();
  };

  WbCsmvc.prototype.play = function () {
    var self = this;
    if (this.playing) {
      this.playing = false;
      if (this._timer) {
        clearTimeout(this._timer);
        this._timer = null;
      }
      this._setButtons();
      return;
    }
    if (this.index >= this.steps.length) return;
    this.playing = true;
    this._hoverArmed = false;
    this._setButtons();

    function next() {
      if (!self.playing) return;
      if (self.index >= self.steps.length) {
        self.playing = false;
        self._onComplete();
        return;
      }
      if (self.busy) {
        self._timer = setTimeout(next, 40);
        return;
      }
      var step = self.steps[self.index++];
      self._updateProgress();
      self._spawn(step, function () {
        if (!self.playing) {
          self._setButtons();
          return;
        }
        self._timer = setTimeout(next, PLAY_GAP);
      });
    }
    next();
  };

  WbCsmvc.prototype._relayoutVisible = function () {
    if (!this.svg) return;
    this._sync();
    this._drawSkeleton();
    this._setWaves(this.root.classList.contains("is-complete"));
    if (this.index === 0) return;
    var self = this;
    var visible = this.steps.slice(0, this.index);
    visible.forEach(function (step) {
      var rec = self.nodes[step.id];
      if (!rec) return;
      var t = self._targetFor(step);
      rec.x = t.x;
      rec.y = t.y;
      self._moveNode(rec.el, t.x, t.y);
    });
    visible.forEach(function (step) {
      if (!step.parentId) return;
      var parent = self.nodes[step.parentId];
      var child = self.nodes[step.id];
      var line = self.links[step.id];
      if (!parent || !child || !line) return;
      var e = self._linkEnds(parent.x, parent.y, child.x, child.y, parent.r, child.r);
      line.setAttribute("x1", e.x1);
      line.setAttribute("y1", e.y1);
      line.setAttribute("x2", e.x2);
      line.setAttribute("y2", e.y2);
    });
  };

  function boot() {
    var el = document.getElementById("jr-wb-csmvc");
    if (!el) return;
    el._wb = new WbCsmvc(el);
  }

  if (document.readyState === "loading") document.addEventListener("DOMContentLoaded", boot);
  else boot();
})();
