#Requires -Version 5.1
<#
.SYNOPSIS
  Upload AF Maven site and portable app ZIPs (legacy IONOS SCP).

.NOTES
  Prefer Hosting DMZ publish (publish-jrebirth-to-dmz.ps1) after stage-jrebirth-apps.ps1.
  This script still targets IONOS until DNS cutover.
#>
param(
  [string]$Version = "",
  [string]$AfSiteDir = "",
  [string]$HostName = "home143909784.1and1-data.host",
  [string]$RemoteSitesRoot = "",
  [string]$RemoteAppsRoot = "/homepages/14/d143909784/htdocs/jrebirth/apps",
  [string]$SettingsXml = "$env:USERPROFILE\.m2\settings.xml",
  [string]$StagingRoot = (Join-Path $PSScriptRoot "apps-staging"),
  [string]$JrebirthRoot = (Join-Path $PSScriptRoot "..\org.jrebirth.af"),
  [switch]$SiteOnly,
  [switch]$AppsOnly,
  [switch]$StageOnly
)

$ErrorActionPreference = "Stop"

function Get-AfVersionFromPom {
  param([string]$AfPomPath)
  [xml]$xml = Get-Content -Raw -Path $AfPomPath
  return "$($xml.project.version)".Trim()
}

function Get-JRebirthSitesCredential {
  param([string]$Path)
  [xml]$xml = Get-Content -Raw -Path $Path
  $server = $xml.settings.servers.server | Where-Object { $_.id -eq "JRebirth_Sites" } | Select-Object -First 1
  if (-not $server) { throw "No <server><id>JRebirth_Sites</id> in $Path" }
  return [pscustomobject]@{ User = $server.username; Password = $server.password }
}

function Ensure-Dir([string]$Path) {
  if (-not (Test-Path $Path)) { New-Item -ItemType Directory -Force -Path $Path | Out-Null }
}

$AfRoot = (Resolve-Path $JrebirthRoot).Path
$AfPom = Join-Path $AfRoot "pom.xml"
if (-not $Version) { $Version = Get-AfVersionFromPom -AfPomPath $AfPom }
if (-not $AfSiteDir) { $AfSiteDir = Join-Path $AfRoot "target\site" }
if (-not $RemoteSitesRoot) {
  $RemoteSitesRoot = "/homepages/14/d143909784/htdocs/jrebirth/sites/org.jrebirth-$Version"
}
Write-Host "AF version: $Version"
Write-Host "Site dir:   $AfSiteDir"
Write-Host "Remote site: $RemoteSitesRoot"

# Prefer stage-jrebirth-apps.ps1 layout; fall back to building map inline.
& (Join-Path $PSScriptRoot "stage-jrebirth-apps.ps1") -AfRoot $AfRoot -StagingRoot $StagingRoot -Version $Version

if ($StageOnly) {
  Write-Host "StageOnly: skip remote upload."
  exit 0
}

if (-not $AppsOnly -and -not (Test-Path $AfSiteDir)) {
  throw "Site not generated: $AfSiteDir (run mvn -f org.jrebirth.af/pom.xml site -pl '!distribution')"
}

$cred = Get-JRebirthSitesCredential -Path $SettingsXml
$pscp = Get-Command pscp.exe -ErrorAction SilentlyContinue
if (-not $pscp) {
  $candidate = "C:\Program Files\PuTTY\pscp.exe"
  if (Test-Path $candidate) { $pscp = Get-Item $candidate } else { throw "pscp.exe not found (install PuTTY)" }
}

Write-Host "Testing TCP/22 to $HostName ..."
$tcp = Test-NetConnection $HostName -Port 22 -WarningAction SilentlyContinue
if (-not $tcp.TcpTestSucceeded) {
  throw "TCP/22 to $HostName is closed. Staging is under $StagingRoot — use VPN/WinSCP or DMZ publish."
}

$target = "$($cred.User)@$HostName"
$passArgs = @("-pw", $cred.Password, "-batch")

if (-not $AppsOnly) {
  Write-Host "Uploading AF site -> $RemoteSitesRoot"
  & $pscp.Source @passArgs -r $AfSiteDir "${target}:$RemoteSitesRoot"
}

if (-not $SiteOnly) {
  Get-ChildItem $StagingRoot -Directory | ForEach-Object {
    $rel = $_.Name -replace '_', '/'
    # Prefer nested deployPath folders (demo\12.0.1-SNAPSHOT)
    $remote = "$RemoteAppsRoot/$rel"
    if ($_.Name -notmatch '_') {
      # already path-like via DirectorySeparatorChar staging
      $remote = "$RemoteAppsRoot/$($_.Name)"
    }
  }
  # Upload each staged deployPath tree (supports nested folders from stage-jrebirth-apps.ps1)
  Get-ChildItem $StagingRoot -Recurse -File -Filter *.zip | ForEach-Object {
    $rel = $_.FullName.Substring($StagingRoot.Length).TrimStart('\', '/')
    $remoteDir = Split-Path $rel -Parent
    $remote = "$RemoteAppsRoot/$($remoteDir -replace '\\','/')"
    Write-Host "Uploading $($_.Name) -> $remote/"
    & $pscp.Source @passArgs $_.FullName "${target}:$remote/"
  }
}

Write-Host "Done. Verify:"
Write-Host "  http://sites.jrebirth.org/org.jrebirth-$Version/"
Write-Host "  http://www.jrebirth.org/apps/Demo.html"
Write-Host "  http://apps.jrebirth.org/demo/$Version/"
