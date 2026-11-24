#Requires -Version 5.1
<#
.SYNOPSIS
  Stage portable ZIPs for apps.jrebirth.org from showcase + external app builds.

.NOTES
  Run after -Ppackage-portable builds. Output: tools/apps-staging/<deployPath>/
  This directory is gitignored — never commit application ZIPs to the JRebirth repo.
  Sync to DMZ with Hosting publish-static-jrebirth (AppsStagingDir).
#>
param(
  [string]$AfRoot = (Join-Path $PSScriptRoot "..\org.jrebirth.af"),
  [string]$StagingRoot = (Join-Path $PSScriptRoot "apps-staging"),
  [string]$Version = ""
)

$ErrorActionPreference = "Stop"

function Get-AfVersionFromPom {
  param([string]$AfPomPath)
  [xml]$xml = Get-Content -Raw -Path $AfPomPath
  return "$($xml.project.version)".Trim()
}

$AfRoot = (Resolve-Path $AfRoot).Path
$AfPom = Join-Path $AfRoot "pom.xml"
if (-not $Version) { $Version = Get-AfVersionFromPom -AfPomPath $AfPom }

Write-Host "AF version: $Version"
Write-Host "Staging:    $StagingRoot"

$AppUploads = @(
  @{ Remote = "demo/$Version"; Sources = @(
      "$AfRoot\showcase\demo\target\JRebirthDemo-$Version.zip"
    ) }
  @{ Remote = "analyzer/$Version"; Sources = @(
      "$AfRoot\showcase\analyzer\target\JRebirthAnalyzer-$Version.zip"
    ) }
  @{ Remote = "wave/$Version"; Sources = @(
      "$AfRoot\showcase\wave\target\Wave-$Version.zip"
    ) }
  @{ Remote = "fxml/$Version"; Sources = @(
      "$AfRoot\showcase\fxml\target\FXML-$Version.zip"
    ) }
  @{ Remote = "undoredo/$Version"; Sources = @(
      "$AfRoot\showcase\undoredo\target\UndoRedo-$Version.zip"
    ) }
  @{ Remote = "todos/$Version"; Sources = @(
      "$AfRoot\showcase\todos\target\Todos-$Version.zip"
    ) }
  @{ Remote = "fonticon/$Version"; Sources = @(
      "$AfRoot\showcase\fonticon\target\FontIcon-$Version.zip"
    ) }
  @{ Remote = "workbench/$Version"; Sources = @(
      "$AfRoot\showcase\workbench\target\Workbench-$Version.zip"
    ) }
  @{ Remote = "masteringtables/5.0.0"; Sources = @(
      "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\MasteringTables-5.0.0.zip"
    ) }
  @{ Remote = "comparisontool/3.0.0"; Sources = @(
      "D:\DEV\GIT\ComparisonTool\org.jrebirth.demo.comparisontool\target\ComparisonTool-3.0.0.zip"
    ) }
  @{ Remote = "jaf/5.0.0"; Sources = @(
      "D:\DEV\GIT\JRebirth-Tour\org.jrebirth.af.presentation.jaf\target\JRebirthTour-5.0.0.zip"
    ) }
  @{ Remote = "eclipseday/4.0.0"; Sources = @(
      "D:\DEV\GIT\EclipseDay-Presentation\org.jrebirth.af.presentation.eclipseday\target\EclipseDayToulouse-4.0.0.zip"
    ) }
  @{ Remote = "lightningtalk/4.0.0"; Sources = @(
      "D:\DEV\GIT\LightningTalk\org.jrebirth.af.presentation.lightningtalk\target\LightningTalk-4.0.0.zip"
    ) }
  @{ Remote = "eclipsecon/4.0.0"; Sources = @(
      "D:\DEV\GIT\EclipseCON-France\org.jrebirth.af.presentation.eclipsecon\target\EclipseCON-4.0.0.zip"
    ) }
  # Breizhcamp remains on 3.0.0 until JavaFX Builder migration (legacy jlink zip rename)
  @{ Remote = "javafx2/3.0.0"; Sources = @(
      "D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\target\Breizhcamp-3.0.0.zip"
      "D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\target\Breizhcamp.zip"
    ); Rename = @{ "Breizhcamp.zip" = "Breizhcamp-3.0.0.zip" } }
)

if (-not (Test-Path $StagingRoot)) {
  New-Item -ItemType Directory -Force -Path $StagingRoot | Out-Null
}

$staged = 0
foreach ($app in $AppUploads) {
  $stage = Join-Path $StagingRoot ($app.Remote -replace '/', [IO.Path]::DirectorySeparatorChar)
  if (Test-Path $stage) { Remove-Item $stage -Recurse -Force }
  New-Item -ItemType Directory -Force -Path $stage | Out-Null
  $copied = 0
  foreach ($src in $app.Sources) {
    if (-not (Test-Path $src)) { continue }
    $name = Split-Path $src -Leaf
    if ($app.Rename -and $app.Rename.ContainsKey($name)) { $name = $app.Rename[$name] }
    Copy-Item $src (Join-Path $stage $name) -Force
    $copied++
    break
  }
  if ($copied -eq 0) {
    Write-Warning "No ZIP for $($app.Remote)"
    continue
  }
  Write-Host "Staged $($app.Remote)"
  $staged++
}

Write-Host "Done. Staged $staged app folder(s) under $StagingRoot"
Write-Host "Publish apps with Hosting publish-jrebirth-to-dmz.ps1 -AppsOnly -Apply"
