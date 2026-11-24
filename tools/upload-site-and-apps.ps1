#Requires -Version 5.1
<#
.SYNOPSIS
  Upload AF Maven site (org.jrebirth-VERSION) and Demo/Slides/showcase binaries to IONOS.

.NOTES
  Needs outbound TCP/22 (SCP) to s143909802.onlinehome.fr.
  Credentials: Maven settings server id JRebirth_Sites (do not commit passwords).
  Prefers PuTTY pscp.exe when available.
#>
param(
  [string]$Version = "",
  [string]$AfSiteDir = "",
  [string]$HostName = "home143909784.1and1-data.host",
  [string]$RemoteSitesRoot = "",
  [string]$RemoteAppsRoot = "/homepages/14/d143909784/htdocs/jrebirth/apps",
  [string]$WinScpSession = "u39172299@home143909784.1and1-data.host",
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
  $v = $xml.project.version
  if (-not $v) { throw "No <version> in $AfPomPath" }
  return "$v".Trim()
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

# path under apps.jrebirth.org => local files to upload (zip/exe preferred names)
$AppUploads = @(
  @{ Remote = "demo/$Version"; Sources = @(
      "$AfRoot\showcase\demo\target\JRebirthDemo.zip"
      "$AfRoot\showcase\demo\target\JRebirthDemo-$Version.exe"
    ); Rename = @{ "JRebirthDemo.zip" = "JRebirthDemo-$Version.zip" } }
  @{ Remote = "analyzer/$Version"; Sources = @(
      "$AfRoot\showcase\analyzer\target\JRebirthAnalyzer.zip"
      "$AfRoot\showcase\analyzer\target\JRebirthAnalyzer-$Version.exe"
    ); Rename = @{ "JRebirthAnalyzer.zip" = "JRebirthAnalyzer-$Version.zip" } }
  @{ Remote = "wave/$Version"; Sources = @(
      "$AfRoot\showcase\wave\target\Wave.zip"
      "$AfRoot\showcase\wave\target\Wave-$Version.exe"
    ); Rename = @{ "Wave.zip" = "Wave-$Version.zip" } }
  @{ Remote = "fxml/$Version"; Sources = @(
      "$AfRoot\showcase\fxml\target\FXML.zip"
      "$AfRoot\showcase\fxml\target\FXML-$Version.exe"
    ); Rename = @{ "FXML.zip" = "FXML-$Version.zip" } }
  @{ Remote = "undoredo/$Version"; Sources = @(
      "$AfRoot\showcase\undoredo\target\UndoRedo.zip"
      "$AfRoot\showcase\undoredo\target\UndoRedo-$Version.exe"
    ); Rename = @{ "UndoRedo.zip" = "UndoRedo-$Version.zip" } }
  @{ Remote = "todos/$Version"; Sources = @(
      "$AfRoot\showcase\todos\target\Todos.zip"
      "$AfRoot\showcase\todos\target\Todos-$Version.exe"
    ); Rename = @{ "Todos.zip" = "Todos-$Version.zip" } }
  @{ Remote = "fonticon/$Version"; Sources = @(
      "$AfRoot\showcase\fonticon\target\FontIcon.zip"
      "$AfRoot\showcase\fonticon\target\FontIcon-$Version.exe"
    ); Rename = @{ "FontIcon.zip" = "FontIcon-$Version.zip" } }
  @{ Remote = "workbench/$Version"; Sources = @(
      "$AfRoot\showcase\workbench\target\Workbench.zip"
      "$AfRoot\showcase\workbench\target\Workbench-$Version.exe"
    ); Rename = @{ "Workbench.zip" = "Workbench-$Version.zip" } }
  @{ Remote = "masteringtables/4.0.0"; Sources = @(
      "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\MasteringTables.zip"
      "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\apps-upload\MasteringTables-4.0.0.zip"
      "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\installer\Mastering Tables-Installer.exe"
      "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\apps-upload\MasteringTables-4.0.0.exe"
    ); Rename = @{
      "MasteringTables.zip" = "MasteringTables-4.0.0.zip"
      "Mastering Tables-Installer.exe" = "MasteringTables-4.0.0.exe"
    } }
  @{ Remote = "comparisontool/2.0.0"; Sources = @(
      "D:\DEV\GIT\ComparisonTool\org.jrebirth.demo.comparisontool\target\ComparisonTool.zip"
      "D:\DEV\GIT\ComparisonTool\org.jrebirth.demo.comparisontool\target\installer\Comparison Tool-2.0.0.exe"
    ); Rename = @{
      "ComparisonTool.zip" = "ComparisonTool-2.0.0.zip"
      "Comparison Tool-2.0.0.exe" = "ComparisonTool-2.0.0.exe"
    } }
  @{ Remote = "jaf/4.0.0"; Sources = @(
      "D:\DEV\GIT\JRebirth-Tour\org.jrebirth.af.presentation.jaf\target\JRebirthTour.zip"
      "D:\DEV\GIT\JRebirth-Tour\org.jrebirth.af.presentation.jaf\target\installer\JRebirth Tour-Installer.exe"
    ); Rename = @{
      "JRebirthTour.zip" = "JRebirthTour-4.0.0.zip"
      "JRebirth Tour-Installer.exe" = "JRebirthTour-4.0.0.exe"
    } }
  @{ Remote = "eclipseday/3.0.0"; Sources = @(
      "D:\DEV\GIT\EclipseDay-Presentation\org.jrebirth.af.presentation.eclipseday\target\EclipseDayToulouse.zip"
      "D:\DEV\GIT\EclipseDay-Presentation\org.jrebirth.af.presentation.eclipseday\target\installer\EclipseDay Presentation-Installer.exe"
    ); Rename = @{
      "EclipseDayToulouse.zip" = "EclipseDayToulouse-3.0.0.zip"
      "EclipseDay Presentation-Installer.exe" = "EclipseDayToulouse-3.0.0.exe"
    } }
  @{ Remote = "javafx2/3.0.0"; Sources = @(
      "D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\target\Breizhcamp.zip"
      "D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\target\installer\Breizhcamp Presentation-Installer.exe"
    ); Rename = @{
      "Breizhcamp.zip" = "Breizhcamp-3.0.0.zip"
      "Breizhcamp Presentation-Installer.exe" = "Breizhcamp-3.0.0.exe"
    } }
  @{ Remote = "lightningtalk/3.0.0"; Sources = @(
      "D:\DEV\GIT\LightningTalk\org.jrebirth.af.presentation.lightningtalk\target\LightningTalk.zip"
      "D:\DEV\GIT\LightningTalk\org.jrebirth.af.presentation.lightningtalk\target\installer\Lightning Talk-Installer.exe"
    ); Rename = @{
      "LightningTalk.zip" = "LightningTalk-3.0.0.zip"
      "Lightning Talk-Installer.exe" = "LightningTalk-3.0.0.exe"
    } }
  @{ Remote = "eclipsecon/3.0.0"; Sources = @(
      "D:\DEV\GIT\EclipseCON-France\org.jrebirth.af.presentation.eclipsecon\target\EclipseCON.zip"
      "D:\DEV\GIT\EclipseCON-France\org.jrebirth.af.presentation.eclipsecon\target\installer\EclipseCON Presentation-Installer.exe"
    ); Rename = @{
      "EclipseCON.zip" = "EclipseCON-3.0.0.zip"
      "EclipseCON Presentation-Installer.exe" = "EclipseCON-3.0.0.exe"
    } }
)

if (-not $AppsOnly -and -not (Test-Path $AfSiteDir)) {
  throw "Site not generated: $AfSiteDir (run mvn -f org.jrebirth.af/pom.xml site -pl '!distribution')"
}

$cred = Get-JRebirthSitesCredential -Path $SettingsXml
$pscp = Get-Command pscp.exe -ErrorAction SilentlyContinue
if (-not $pscp) {
  $candidate = "C:\Program Files\PuTTY\pscp.exe"
  if (Test-Path $candidate) { $pscp = Get-Item $candidate } else { throw "pscp.exe not found (install PuTTY)" }
}

# Always refresh local staging tree under tools/apps-staging (site + app binaries).
$stageRoot = Join-Path $PSScriptRoot "apps-staging"
if (-not (Test-Path $stageRoot)) { New-Item -ItemType Directory -Force -Path $stageRoot | Out-Null }
Write-Host "Local staging root: $stageRoot"

if ($StageOnly) {
  Write-Host "StageOnly: skip remote upload. Upload later with WinSCP/pscp when TCP/22 is reachable."
  Write-Host "  Site:  $AfSiteDir  ->  $RemoteSitesRoot"
  Write-Host "  Apps:  staged folders under $stageRoot"
  exit 0
}

Write-Host "Testing TCP/22 to $HostName ..."
$tcp = Test-NetConnection $HostName -Port 22 -WarningAction SilentlyContinue
if (-not $tcp.TcpTestSucceeded) {
  throw "TCP/22 to $HostName is closed from this network. Staging is under $stageRoot — use a VPN or WinSCP, then re-run."
}

$target = "$($cred.User)@$HostName"
$passArgs = @("-pw", $cred.Password, "-batch")

if (-not $AppsOnly) {
  Write-Host "Uploading AF site -> $RemoteSitesRoot"
  & $pscp.Source @passArgs -r $AfSiteDir "${target}:$RemoteSitesRoot"
}

if (-not $SiteOnly) {
  Ensure-Dir $StagingRoot
  foreach ($app in $AppUploads) {
    $stage = Join-Path $StagingRoot ($app.Remote -replace '/', '_')
    if (Test-Path $stage) { Remove-Item $stage -Recurse -Force }
    Ensure-Dir $stage
    $copied = 0
    foreach ($src in $app.Sources) {
      if (-not (Test-Path $src)) { continue }
      $name = Split-Path $src -Leaf
      if ($app.Rename -and $app.Rename.ContainsKey($name)) { $name = $app.Rename[$name] }
      Copy-Item $src (Join-Path $stage $name) -Force
      $copied++
    }
    if ($copied -eq 0) {
      Write-Warning "No binaries found for $($app.Remote) — skip upload"
      continue
    }
    $remote = "$RemoteAppsRoot/$($app.Remote)"
    Write-Host "Uploading $($app.Remote) ($copied file(s)) -> $remote"
    & $pscp.Source @passArgs -r (Join-Path $stage "*") "${target}:$remote/"
  }
}

Write-Host "Done. Verify:"
Write-Host "  http://sites.jrebirth.org/org.jrebirth-$Version/"
Write-Host "  http://www.jrebirth.org/apps/Demo.html"
Write-Host "  http://apps.jrebirth.org/demo/$Version/"
