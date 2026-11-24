#Requires -Version 5.1
<#
.SYNOPSIS
  Fast generate + local preview of the JRebirth AF Maven site.
#>
[CmdletBinding()]
param(
    [switch]$SkipBuild,
    [switch]$BuildOnly,
    [switch]$FullReactor,
    [switch]$NoBrowser,
    [int]$Port = 4173
)

$ErrorActionPreference = "Stop"

# scripts/ → preview-site/ → skills/ → .cursor/ → org.jrebirth.af/
$AfRoot = (Resolve-Path (Join-Path $PSScriptRoot "..\..\..\..")).Path
$RepoRoot = (Resolve-Path (Join-Path $AfRoot "..")).Path
$SiteDir = Join-Path $AfRoot "target\site"
$SkinPom = "D:\DEV\GIT\JRebirth-Skin\org.jrebirth.site.jrebirth-skin\pom.xml"

function Write-Info([string]$Message) {
    Write-Host "[preview-site] $Message"
}

function Test-SkinInstalled {
    $candidates = @(
        (Join-Path $env:USERPROFILE ".m2\repository\org\jrebirth\site\jrebirth-skin"),
        "D:\DEV\CACHE\repository\org\jrebirth\site\jrebirth-skin"
    )
    foreach ($c in $candidates) {
        if (Test-Path $c) { return $true }
    }
    return $false
}

function Ensure-Skin {
    param([switch]$Force)
    if ((Test-SkinInstalled) -and -not $Force) {
        Write-Info "Site skin already present in local .m2 (pass -ForceSkin to reinstall)"
        return
    }
    if (-not (Test-Path -LiteralPath $SkinPom)) {
        throw "JRebirth skin POM not found: $SkinPom (install skin manually once)"
    }
    Write-Info "Installing jrebirth-skin into local .m2..."
    & mvn -f $SkinPom install "-DskipTests"
    if ($LASTEXITCODE -ne 0) { throw "Skin install failed (exit $LASTEXITCODE)" }
}

function Build-Site {
    Ensure-Skin
    Push-Location $AfRoot
    try {
        if ($FullReactor) {
            Write-Info "Full reactor site (slow): mvn site -pl '!distribution'"
            & mvn site "-pl" "!distribution"
        } else {
            Write-Info "Fast site: mvn site -N (aggregator only)"
            & mvn site -N
        }
        if ($LASTEXITCODE -ne 0) { throw "Maven site failed (exit $LASTEXITCODE)" }
    } finally {
        Pop-Location
    }
    if (-not (Test-Path (Join-Path $SiteDir "index.html"))) {
        throw "Site output missing index.html under $SiteDir"
    }
    Write-Info "Site ready: $SiteDir"
}

function Start-PreviewServer {
    if (-not (Test-Path (Join-Path $SiteDir "index.html"))) {
        throw "No site at $SiteDir — run without -SkipBuild first"
    }

    $listenerBusy = Get-NetTCPConnection -LocalPort $Port -State Listen -ErrorAction SilentlyContinue
    if ($listenerBusy) {
        Write-Info "Port $Port already in use — reusing existing listener"
    } else {
        Write-Info "Starting static server on http://127.0.0.1:$Port/"
        # Prefer `py -3` on Windows (Microsoft Store `python` stub often fails with exit 9009).
        $launcher = $null
        $argList = @()
        $pyLauncher = Get-Command py -ErrorAction SilentlyContinue
        if ($pyLauncher) {
            $launcher = $pyLauncher.Source
            $argList = @("-3", "-m", "http.server", "$Port", "--bind", "127.0.0.1")
        } else {
            foreach ($name in @("python3", "python")) {
                $cmd = Get-Command $name -ErrorAction SilentlyContinue
                if ($cmd -and $cmd.Source -notmatch "WindowsApps") {
                    $launcher = $cmd.Source
                    $argList = @("-m", "http.server", "$Port", "--bind", "127.0.0.1")
                    break
                }
            }
        }
        if (-not $launcher) {
            throw "Python not found (need `py -3` or a real python.exe). Open $SiteDir in a browser via file:// as fallback."
        }

        Start-Process -FilePath $launcher -ArgumentList $argList -WorkingDirectory $SiteDir -WindowStyle Hidden | Out-Null
        Start-Sleep -Seconds 1
    }

    $homeUrl = "http://127.0.0.1:$Port/index.html"
    $aiUrl = "http://127.0.0.1:$Port/doc/AI-Kit.html"
    Write-Info "Home:   $homeUrl"
    Write-Info "AI kit: $aiUrl"
    Write-Info "Anchor: http://127.0.0.1:$Port/index.html#ai-kit"

    if (-not $NoBrowser) {
        Start-Process $homeUrl | Out-Null
    }
}

if (-not $SkipBuild) {
    Build-Site
} else {
    Write-Info "SkipBuild: using existing $SiteDir"
}

if (-not $BuildOnly) {
    Start-PreviewServer
} else {
    Write-Info "BuildOnly: server not started"
}
