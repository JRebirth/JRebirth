#Requires -Version 5.1
param(
    [Parameter(Mandatory = $true)]
    [string]$NewVersion,
    [string]$AfPom = (Join-Path $PSScriptRoot "..\..\..\pom.xml")
)

$ErrorActionPreference = "Stop"
$repoRoot = (Resolve-Path (Join-Path $PSScriptRoot "..\..\..\..")).Path
$pom = $AfPom
if (-not (Test-Path $pom)) {
    $pom = Join-Path $repoRoot "org.jrebirth.af\pom.xml"
}
if (-not (Test-Path $pom)) { throw "AF pom not found: $pom" }

Write-Host "Setting AF reactor version to $NewVersion"
Push-Location $repoRoot
try {
    mvn -f org.jrebirth.af/pom.xml -B "org.codehaus.mojo:versions-maven-plugin:2.18.0:set" "-DnewVersion=$NewVersion" -DprocessAllModules=true -DgenerateBackupPoms=false
    if ($LASTEXITCODE -ne 0) { throw "versions:set failed" }
}
finally {
    Pop-Location
}
Write-Host "Done. Review git diff; fix orphan modules outside the reactor if needed."