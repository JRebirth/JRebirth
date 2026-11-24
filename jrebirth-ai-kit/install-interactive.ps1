#Requires -Version 5.1
<#
.SYNOPSIS
  Interactive installer — prompts for the target project path, then runs install.ps1.
#>
[CmdletBinding()]
param()

$ErrorActionPreference = "Stop"
$KitRoot = $PSScriptRoot

function Write-Info([string]$msg) { Write-Host "[jrebirth-ai-kit] $msg" }

$defaultTarget = (Get-Location).Path
$targetInput = Read-Host "Path to your application project [$defaultTarget]"
$target = if ([string]::IsNullOrWhiteSpace($targetInput)) { $defaultTarget } else { $targetInput.Trim() }

if ($target.StartsWith("~")) {
    $target = Join-Path $env:USERPROFILE $target.Substring(1).TrimStart('\', '/')
}

if (-not (Test-Path -LiteralPath $target)) {
    $create = Read-Host "Directory does not exist. Create it? [y/N]"
    if ($create.ToUpperInvariant() -eq "Y") {
        New-Item -ItemType Directory -Force -Path $target | Out-Null
    } else {
        throw "Aborted."
    }
}

$target = (Resolve-Path -LiteralPath $target).Path

$linkAns = Read-Host "Symlink (junction) skills folders instead of copying? [y/N]"
$forceAns = Read-Host "Replace existing AGENTS.md / CLAUDE.md without prompts? [y/N]"

$params = @{ Target = $target }
if ($linkAns.ToUpperInvariant() -eq "Y") { $params.Link = $true }
if ($forceAns.ToUpperInvariant() -eq "Y") { $params.Force = $true }

Write-Info "Installing into: $target"
& (Join-Path $KitRoot "install.ps1") @params