$ErrorActionPreference = "Stop"

$bridgeRoot = "D:/DEV/GIT/JRebirth/org.jrebirth.af/iconfont-bridge"
$modules = Get-ChildItem -Path $bridgeRoot -Directory | Where-Object { Test-Path (Join-Path $_.FullName "src/main/java") } | Sort-Object Name

function Escape-ToCodepoint([string]$esc) {
    $m = [regex]::Matches($esc, "\\u([0-9a-fA-F]{4})")
    if ($m.Count -eq 1) { return [Convert]::ToInt32($m[0].Groups[1].Value, 16) }
    if ($m.Count -eq 2) {
        $high = [Convert]::ToInt32($m[0].Groups[1].Value, 16)
        $low = [Convert]::ToInt32($m[1].Groups[1].Value, 16)
        return (($high - 0xD800) * 0x400) + ($low - 0xDC00) + 0x10000
    }
    return 0
}

function HexToCodepoint([string]$hex) {
    return [Convert]::ToInt32($hex, 16)
}

$catalogEntries = New-Object System.Collections.Generic.List[object]

foreach ($module in $modules) {
    $moduleRoot = $module.FullName
    $javaRoot = Join-Path $moduleRoot "src/main/java"

    $enumFiles = Get-ChildItem -Path $javaRoot -Recurse -Filter "*.java" | Where-Object {
        (Get-Content -Raw -Path $_.FullName) -match "public enum\s+[A-Za-z0-9_]+\s+implements\s+IconFont"
    }
    if ($enumFiles.Count -eq 0) { continue }

    $fontFiles = Get-ChildItem -Path (Join-Path $moduleRoot "src/main/resources/fonts") -File -ErrorAction SilentlyContinue |
        Where-Object { $_.Extension -in ".ttf", ".otf" }

    $fontFaces = New-Object System.Collections.Generic.List[object]
    foreach ($ff in $fontFiles) {
        $family = [System.IO.Path]::GetFileNameWithoutExtension($ff.Name)
        $fontFaces.Add([pscustomobject]@{
            family = $family
            path = "src/main/resources/fonts/$($ff.Name)"
        })
    }

    $groups = New-Object System.Collections.Generic.List[object]
    foreach ($f in ($enumFiles | Sort-Object Name)) {
        $content = Get-Content -Raw -Path $f.FullName
        if ($content -notmatch "public enum\s+([A-Za-z0-9_]+)\s+implements\s+IconFont") { continue }
        $enumName = $matches[1]

        $icons = New-Object System.Collections.Generic.List[object]
        $mmEscaped = [regex]::Matches($content, "(?m)^\s*([A-Za-z_][A-Za-z0-9_]*)\(""((?:\\u[0-9a-fA-F]{4}){1,2})""\)(,|;)")
        foreach ($m in $mmEscaped) {
            $name = $m.Groups[1].Value
            $esc = $m.Groups[2].Value
            $cp = Escape-ToCodepoint $esc
            if ($cp -le 0) { continue }
            $icons.Add([pscustomobject]@{
                name = $name
                codepoint = $cp
                hex = ("{0:x}" -f $cp)
            })
        }

        $mmUnicode = [regex]::Matches($content, "(?m)^\s*([A-Za-z_][A-Za-z0-9_]*)\(""U\+([0-9a-fA-F]{4,6})""\)(,|;)")
        foreach ($m in $mmUnicode) {
            $name = $m.Groups[1].Value
            $cp = HexToCodepoint $m.Groups[2].Value
            if ($cp -le 0) { continue }
            $icons.Add([pscustomobject]@{
                name = $name
                codepoint = $cp
                hex = ("{0:x}" -f $cp)
            })
        }

        if ($icons.Count -gt 0) {
            $groups.Add([pscustomobject]@{
                group = $enumName
                count = $icons.Count
                icons = $icons
            })
        }
    }

    if ($groups.Count -eq 0) { continue }

    $moduleTotal = ($groups | Measure-Object -Property count -Sum).Sum
    $data = [pscustomobject]@{
        module = $module.Name
        groups = $groups
        fonts = $fontFaces
        total = $moduleTotal
    }
    $json = $data | ConvertTo-Json -Depth 8 -Compress
    [System.IO.File]::WriteAllText((Join-Path $moduleRoot "icons-data.js"), ("window.ICON_DATA = " + $json + ";"), (New-Object System.Text.UTF8Encoding($false)))

    $indexLines = @()
    $indexLines += "# $($module.Name) icon catalog"
    $indexLines += ""
    $indexLines += "- Groups: $($groups.Count)"
    $indexLines += "- Icons: $moduleTotal"
    $indexLines += "- Fonts: $($fontFaces.Count)"
    $indexLines += ""
    foreach ($g in $groups) {
        $indexLines += "- ``$($g.group)``: $($g.count)"
    }
    [System.IO.File]::WriteAllLines((Join-Path $moduleRoot "ICONS_INDEX.md"), $indexLines, (New-Object System.Text.UTF8Encoding($false)))

    $html = @'
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>__MODULE__ icon browser</title>
  <style>
    body{font-family:Arial,sans-serif;margin:0;background:#f7f7f8;color:#222}
    header{position:sticky;top:0;background:#fff;border-bottom:1px solid #ddd;padding:12px 16px;z-index:1}
    .meta{font-size:13px;color:#555;margin-top:4px}
    .toolbar{display:flex;gap:12px;margin-top:8px;flex-wrap:wrap}
    input,select{padding:8px;font-size:14px}
    main{padding:16px}
    .group{margin-bottom:24px}
    .group h2{margin:0 0 10px 0;font-size:18px}
    .grid{display:grid;grid-template-columns:repeat(auto-fill,minmax(180px,1fr));gap:10px}
    .card{background:#fff;border:1px solid #ddd;border-radius:8px;padding:10px;min-height:96px}
    .icon{font-size:28px;line-height:1}
    .name{font-size:12px;margin-top:8px;word-break:break-all}
    .code{font-family:Consolas,monospace;font-size:11px;color:#666;margin-top:4px}
    .warn{padding:10px;background:#fff4e5;border:1px solid #ffd8a8;border-radius:8px;color:#8a5a00;margin-top:10px}
  </style>
</head>
<body>
  <header>
    <strong>__MODULE__ icon browser</strong>
    <div class="meta">Groups <span id="groupCount"></span> - Icons <span id="iconCount"></span> - Fonts <span id="fontCount"></span></div>
    <div class="toolbar">
      <input id="search" placeholder="Filter icons by name..." />
      <select id="groupFilter"><option value="">All groups</option></select>
      <select id="fontFilter"></select>
    </div>
    <div id="fontWarn" class="warn" style="display:none">No local TTF/OTF file found in this module; preview may use fallback fonts.</div>
  </header>
  <main id="root"></main>
  <script src="./icons-data.js"></script>
  <script>
    const data=window.ICON_DATA||{groups:[],fonts:[],module:'unknown',total:0};
    document.getElementById('groupCount').textContent=data.groups.length;
    document.getElementById('iconCount').textContent=data.total;
    document.getElementById('fontCount').textContent=data.fonts.length;
    const groupFilter=document.getElementById('groupFilter');
    for(const g of data.groups){ const o=document.createElement('option'); o.value=g.group; o.textContent=`${g.group} (${g.count})`; groupFilter.appendChild(o); }
    const fontFilter=document.getElementById('fontFilter');
    if(data.fonts.length===0){
      const o=document.createElement('option'); o.value=''; o.textContent='No local font'; fontFilter.appendChild(o);
      document.getElementById('fontWarn').style.display='block';
    } else {
      for(const f of data.fonts){
        const face=document.createElement('style');
        face.textContent=`@font-face{font-family:"${f.family}";src:url("${f.path}") format("truetype");font-weight:normal;font-style:normal;}`;
        document.head.appendChild(face);
        const o=document.createElement('option'); o.value=f.family; o.textContent=f.family; fontFilter.appendChild(o);
      }
    }
    function glyph(cp){ return String.fromCodePoint(cp); }
    function render(){
      const q=document.getElementById('search').value.trim().toLowerCase();
      const gf=groupFilter.value;
      const ff=fontFilter.value;
      const root=document.getElementById('root'); root.innerHTML='';
      for(const g of data.groups){
        if(gf && g.group!==gf) continue;
        const icons=g.icons.filter(i=>!q||i.name.toLowerCase().includes(q));
        if(!icons.length) continue;
        const sec=document.createElement('section'); sec.className='group';
        const h2=document.createElement('h2'); h2.textContent=`${g.group} (${icons.length}/${g.count})`; sec.appendChild(h2);
        const grid=document.createElement('div'); grid.className='grid';
        for(const i of icons){
          const card=document.createElement('article'); card.className='card';
          const icon=document.createElement('div'); icon.className='icon'; if(ff) icon.style.fontFamily=ff; icon.textContent=glyph(i.codepoint);
          const name=document.createElement('div'); name.className='name'; name.textContent=i.name;
          const code=document.createElement('div'); code.className='code'; code.textContent='U+'+i.hex.toUpperCase();
          card.appendChild(icon); card.appendChild(name); card.appendChild(code); grid.appendChild(card);
        }
        sec.appendChild(grid); root.appendChild(sec);
      }
    }
    document.getElementById('search').addEventListener('input',render);
    groupFilter.addEventListener('change',render);
    fontFilter.addEventListener('change',render);
    render();
  </script>
</body>
</html>
'@

    $html = $html.Replace("__MODULE__", $module.Name)

    [System.IO.File]::WriteAllText((Join-Path $moduleRoot "icons-browser.html"), $html, (New-Object System.Text.UTF8Encoding($false)))

    $catalogEntries.Add([pscustomobject]@{
        module = $module.Name
        groups = $groups.Count
        icons = $moduleTotal
        path = "$($module.Name)/icons-browser.html"
    })
}

$catalogDir = Join-Path $bridgeRoot "catalogs"
if (-not (Test-Path $catalogDir)) { New-Item -ItemType Directory -Path $catalogDir | Out-Null }

$rows = ($catalogEntries | Sort-Object module | ForEach-Object {
    "<tr><td>$($_.module)</td><td>$($_.groups)</td><td>$($_.icons)</td><td><a href=""../$($_.path)"">open</a></td></tr>"
}) -join "`n"

$globalHtml = @"
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width,initial-scale=1"/>
  <title>JRebirth Iconfont Catalogs</title>
  <style>
    body{font-family:Arial,sans-serif;margin:24px;background:#f7f7f8}
    table{width:100%;border-collapse:collapse;background:#fff}
    th,td{border:1px solid #ddd;padding:10px;text-align:left}
    a{color:#0b5fff}
  </style>
</head>
<body>
  <h1>JRebirth Iconfont Catalogs</h1>
  <table>
    <thead><tr><th>Module</th><th>Groups</th><th>Icons</th><th>Catalog</th></tr></thead>
    <tbody>
$rows
    </tbody>
  </table>
</body>
</html>
"@

[System.IO.File]::WriteAllText((Join-Path $catalogDir "index.html"), $globalHtml, (New-Object System.Text.UTF8Encoding($false)))
Write-Output ("Generated catalogs for " + $catalogEntries.Count + " modules")
