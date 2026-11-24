# Windows jpackage portable target for JRebirth RIA apps (called from Maven -Ppackage-portable).
# Produces an app-image ZIP with AppName.exe + app/ + runtime/ (same layout as EzDojo portable).
param(
    [Parameter(Mandatory = $true)]
    [ValidateSet('portable')]
    [string] $Target,

    [string] $AppName = 'JRebirthApp',
    [string] $AppVersion = '1.0.0',
    [string] $Vendor = 'JRebirth',
    [string] $ModuleName = '',
    [string] $MainModule = '',
    [string] $ModulePathDir = '',
    [string] $StagingDir = '',
    [string] $TempDir = '',
    [string] $PortableZipName = 'App-portable.zip',
    [string] $BuildDirectory = '',
    [string] $IconPath = '',
    [string] $JavaHome = $env:JAVA_HOME
)

$ErrorActionPreference = 'Stop'

# jpackage --app-version must be numeric (strip Maven -SNAPSHOT / qualifiers).
if ($AppVersion -match '^(\d+(?:\.\d+){0,3})') {
    $AppVersion = $Matches[1]
}

if ([string]::IsNullOrWhiteSpace($BuildDirectory)) {
    throw 'BuildDirectory is required'
}
if ([string]::IsNullOrWhiteSpace($ModulePathDir)) {
    throw 'ModulePathDir is required'
}
if ([string]::IsNullOrWhiteSpace($MainModule) -and [string]::IsNullOrWhiteSpace($ModuleName)) {
    throw 'MainModule or ModuleName is required'
}
if ([string]::IsNullOrWhiteSpace($StagingDir)) {
    $StagingDir = Join-Path $BuildDirectory 'jpackage-app-image'
}

function Get-JPackageBaseTempDir {
    # jpackage fails when intermediate paths contain non-ASCII chars (e.g. C:\Users\Sébastien\...).
    if (-not [string]::IsNullOrWhiteSpace($TempDir)) {
        return $TempDir
    }
    return Join-Path $BuildDirectory 'jpackage-temp'
}

function New-JPackageTempPath {
    param([string] $SubDir)
    $baseTempDir = Get-JPackageBaseTempDir
    $runId = [guid]::NewGuid().ToString('N')
    New-Item -ItemType Directory -Force -Path $baseTempDir | Out-Null
    Join-Path $baseTempDir "$SubDir-$runId"
}

function Clear-JPackageAppImage {
    $path = Join-Path $StagingDir $AppName
    if (Test-Path -LiteralPath $path) {
        Remove-Item -LiteralPath $path -Recurse -Force
    }
}

function Invoke-JPackage {
    param([string[]] $ExtraArgs)
    $moduleMain = if ($MainModule) { $MainModule } else { "$ModuleName" }
    $base = @(
        '--name', $AppName,
        '--app-version', $AppVersion,
        '--vendor', $Vendor,
        '--verbose',
        '--runtime-image', $JavaHome,
        '--module-path', $ModulePathDir,
        '--module', $moduleMain,
        '--java-options', '--enable-native-access=javafx.graphics',
        '--java-options', '--add-exports=javafx.graphics/com.sun.javafx.application=ALL-UNNAMED'
    )
    if (-not [string]::IsNullOrWhiteSpace($IconPath) -and (Test-Path -LiteralPath $IconPath)) {
        $base += @('--icon', $IconPath)
    }
    & jpackage @base @ExtraArgs
    if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
}

switch ($Target) {
    'portable' {
        Clear-JPackageAppImage
        $tempPath = New-JPackageTempPath -SubDir 'app-image'
        Invoke-JPackage @(
            '--type', 'app-image',
            '--dest', $StagingDir,
            '--temp', $tempPath
        )
        $src = Join-Path $StagingDir $AppName
        if (-not (Test-Path -LiteralPath $src)) {
            throw "Missing jpackage app image: $src"
        }
        $out = Join-Path $BuildDirectory $PortableZipName
        if (Test-Path -LiteralPath $out) { Remove-Item -LiteralPath $out -Force }
        Compress-Archive -LiteralPath $src -DestinationPath $out -CompressionLevel Optimal -Force
        Write-Host "Portable ZIP: $out"
    }
}
