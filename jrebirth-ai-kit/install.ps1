#Requires -Version 5.1
<#
.SYNOPSIS
  Install the JRebirth AI kit into a target application project (Windows).
.PARAMETER Target
  Path to the project root. Defaults to the current directory.
.PARAMETER Force
  Overwrite AGENTS.md / CLAUDE.md without prompting (keeps a .bak first).
.PARAMETER Link
  Prefer directory junctions for skills folders when possible; otherwise copy.
#>
[CmdletBinding()]
param(
    [string]$Target = (Get-Location).Path,
    [switch]$Force,
    [switch]$Link
)

$ErrorActionPreference = "Stop"
$KitRoot = $PSScriptRoot
$Target = (Resolve-Path -LiteralPath $Target).Path

function Write-Info([string]$msg) { Write-Host "[jrebirth-ai-kit] $msg" }

function Backup-Or-Merge([string]$srcFile, [string]$destFile, [string]$label) {
    if (-not (Test-Path -LiteralPath $destFile)) {
        Copy-Item -LiteralPath $srcFile -Destination $destFile
        Write-Info "Installed $label"
        return
    }
    $srcText = Get-Content -LiteralPath $srcFile -Raw -Encoding UTF8
    $destText = Get-Content -LiteralPath $destFile -Raw -Encoding UTF8
    if ($destText -eq $srcText) {
        Write-Info "$label already up to date"
        return
    }
    if ($Force) {
        $bak = "$destFile.bak-$(Get-Date -Format 'yyyyMMdd-HHmmss')"
        Copy-Item -LiteralPath $destFile -Destination $bak
        Copy-Item -LiteralPath $srcFile -Destination $destFile -Force
        Write-Info "Replaced $label (backup: $bak)"
        return
    }
    Write-Host ""
    Write-Host "$label already exists at: $destFile"
    Write-Host "  [B] Backup and replace"
    Write-Host "  [A] Append kit pointer block"
    Write-Host "  [S] Skip"
    $choice = Read-Host "Choice"
    switch ($choice.ToUpperInvariant()) {
        "B" {
            $bak = "$destFile.bak-$(Get-Date -Format 'yyyyMMdd-HHmmss')"
            Copy-Item -LiteralPath $destFile -Destination $bak
            Copy-Item -LiteralPath $srcFile -Destination $destFile -Force
            Write-Info "Replaced $label (backup: $bak)"
        }
        "A" {
            $block = @"

<!-- BEGIN jrebirth-ai-kit -->
Also follow JRebirth rules in the nearest AGENTS.md from jrebirth-ai-kit.
Skills: .cursor/skills/jrebirth-*/SKILL.md
<!-- END jrebirth-ai-kit -->
"@
            if ($destText -notmatch "BEGIN jrebirth-ai-kit") {
                Add-Content -LiteralPath $destFile -Value $block -Encoding UTF8
                Write-Info "Appended kit pointer to $label"
            } else {
                Write-Info "$label already contains kit pointer — skipped"
            }
        }
        default { Write-Info "Skipped $label" }
    }
}

function Install-SkillsTree([string]$destSkills) {
    $srcSkills = Join-Path $KitRoot "skills"
    $parent = Split-Path -Parent $destSkills
    if (-not (Test-Path -LiteralPath $parent)) {
        New-Item -ItemType Directory -Force -Path $parent | Out-Null
    }
    if ($Link) {
        if (Test-Path -LiteralPath $destSkills) {
            $item = Get-Item -LiteralPath $destSkills -Force
            if ($item.Attributes -band [IO.FileAttributes]::ReparsePoint) {
                cmd /c rmdir "$destSkills" | Out-Null
            } else {
                Write-Info "Skills path exists and is not a junction — copying into it: $destSkills"
                Copy-Item -Path (Join-Path $srcSkills "*") -Destination $destSkills -Recurse -Force
                return
            }
        }
        New-Item -ItemType Junction -Path $destSkills -Target $srcSkills | Out-Null
        Write-Info "Linked $destSkills -> $srcSkills"
    } else {
        if (-not (Test-Path -LiteralPath $destSkills)) {
            New-Item -ItemType Directory -Force -Path $destSkills | Out-Null
        }
        Copy-Item -Path (Join-Path $srcSkills "*") -Destination $destSkills -Recurse -Force
        Write-Info "Copied skills -> $destSkills"
    }
}

Write-Info "Target project: $Target"
Backup-Or-Merge (Join-Path $KitRoot "AGENTS.md") (Join-Path $Target "AGENTS.md") "AGENTS.md"
Backup-Or-Merge (Join-Path $KitRoot "CLAUDE.md") (Join-Path $Target "CLAUDE.md") "CLAUDE.md"

$ruleSrc = Join-Path $KitRoot ".cursor\rules\jrebirth-core.mdc"
$ruleDestDir = Join-Path $Target ".cursor\rules"
New-Item -ItemType Directory -Force -Path $ruleDestDir | Out-Null
Copy-Item -LiteralPath $ruleSrc -Destination (Join-Path $ruleDestDir "jrebirth-core.mdc") -Force
Write-Info "Installed .cursor/rules/jrebirth-core.mdc"

$skillTargets = @(
    (Join-Path $Target ".cursor\skills"),
    (Join-Path $Target ".claude\skills"),
    (Join-Path $Target ".codex\skills"),
    (Join-Path $Target ".agents\skills")
)
foreach ($s in $skillTargets) { Install-SkillsTree $s }

Write-Info "Done. Agents should follow AGENTS.md and open jrebirth-* skills before coding."
