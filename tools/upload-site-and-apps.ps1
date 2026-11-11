#Requires -Version 5.1
<#
.SYNOPSIS
  Upload AF Maven site (org.jrebirth-12.0.0) and MasteringTables 4.0.0 binaries to IONOS.

.NOTES
  Needs outbound TCP/22 (SCP) to s143909802.onlinehome.fr.
  Credentials: Maven settings server id JRebirth_Sites (do not commit passwords).
  Prefers PuTTY pscp.exe when available.
#>
param(
  [string]$AfSiteDir = (Join-Path $PSScriptRoot "..\org.jrebirth.af\target\site"),
  [string]$MtUploadDir = "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\apps-upload",
  [string]$HostName = "s143909802.onlinehome.fr",
  [string]$RemoteSitesRoot = "/kunden/homepages/14/d143909784/htdocs/jrebirth/sites/org.jrebirth-12.0.0",
  [string]$RemoteAppsRoot = "/kunden/homepages/14/d143909784/htdocs/jrebirth/apps/masteringtables/4.0.0",
  [string]$SettingsXml = "$env:USERPROFILE\.m2\settings.xml"
)

$ErrorActionPreference = "Stop"

function Get-JRebirthSitesCredential {
  param([string]$Path)
  [xml]$xml = Get-Content -Raw -Path $Path
  $server = $xml.settings.servers.server | Where-Object { $_.id -eq "JRebirth_Sites" } | Select-Object -First 1
  if (-not $server) { throw "No <server><id>JRebirth_Sites</id> in $Path" }
  return [pscustomobject]@{ User = $server.username; Password = $server.password }
}

if (-not (Test-Path $AfSiteDir)) { throw "Site not generated: $AfSiteDir (run mvn -f org.jrebirth.af/pom.xml site -pl '!distribution')" }
if (-not (Test-Path $MtUploadDir)) { throw "MT binaries missing: $MtUploadDir" }

$cred = Get-JRebirthSitesCredential -Path $SettingsXml
$pscp = Get-Command pscp.exe -ErrorAction SilentlyContinue
if (-not $pscp) {
  $candidate = "C:\Program Files\PuTTY\pscp.exe"
  if (Test-Path $candidate) { $pscp = Get-Item $candidate } else { throw "pscp.exe not found (install PuTTY)" }
}

Write-Host "Testing TCP/22 to $HostName ..."
$tcp = Test-NetConnection $HostName -Port 22 -WarningAction SilentlyContinue
if (-not $tcp.TcpTestSucceeded) {
  throw "TCP/22 to $HostName is closed from this network. Use a network/VPN that can reach IONOS SCP, then re-run."
}

$target = "$($cred.User)@$HostName"
$passArgs = @("-pw", $cred.Password, "-batch")

Write-Host "Uploading AF site -> $RemoteSitesRoot"
& $pscp.Source @passArgs -r $AfSiteDir "${target}:$RemoteSitesRoot"

Write-Host "Uploading MasteringTables binaries -> $RemoteAppsRoot"
& $pscp.Source @passArgs -r (Join-Path $MtUploadDir "*") "${target}:$RemoteAppsRoot/"

Write-Host "Done. Verify:"
Write-Host "  http://sites.jrebirth.org/org.jrebirth-12.0.0/"
Write-Host "  http://apps.jrebirth.org/masteringtables/4.0.0/"
