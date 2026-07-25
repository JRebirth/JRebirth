# Replace DISABLED_INCLUDE{...} HTML comments with visible ```java markdown blocks
# by reading the referenced source files from the JRebirth project tree.

param(
    [string]$ProjectRoot = (Resolve-Path "$PSScriptRoot\..").Path,
    [string]$SiteRoot = (Resolve-Path "$PSScriptRoot\..\src\site").Path
)

function Strip-LicenseHeader {
    param([string[]]$Lines)
    for ($i = 0; $i -lt $Lines.Count; $i++) {
        if ($Lines[$i] -match '^\s*package\s+') {
            return $Lines[$i..($Lines.Count - 1)]
        }
    }
    return $Lines
}

function Get-BraceBlock {
    param([string[]]$Lines, [int]$StartIndex)
    $depth = 0
    $started = $false
    $result = @()
    for ($i = $StartIndex; $i -lt $Lines.Count; $i++) {
        $line = $Lines[$i]
        $result += $line
        foreach ($ch in $line.ToCharArray()) {
            if ($ch -eq '{') { $depth++; $started = $true }
            elseif ($ch -eq '}') { $depth-- }
        }
        if ($started -and $depth -le 0) { break }
    }
    return $result
}

function Extract-ClassBlock {
    param([string[]]$Lines, [string]$ClassName)
    for ($i = 0; $i -lt $Lines.Count; $i++) {
        if ($Lines[$i] -match "(class|interface|enum)\s+$ClassName\b") {
            # include annotations / modifiers above
            $start = $i
            while ($start -gt 0 -and $Lines[$start - 1] -match '^\s*(@|\w+\s*$|/\*)') { $start-- }
            return Get-BraceBlock -Lines $Lines -StartIndex $start
        }
    }
    return $null
}

function Extract-MethodBlock {
    param([string[]]$Lines, [string]$MethodName)
    for ($i = 0; $i -lt $Lines.Count; $i++) {
        if ($Lines[$i] -match "\b$MethodName\s*\(") {
            $start = $i
            while ($start -gt 0 -and $Lines[$start - 1] -match '^\s*(@|\w+\s*$|/\*)') { $start-- }
            return Get-BraceBlock -Lines $Lines -StartIndex $start
        }
    }
    return $null
}

function Extract-RegexBlock {
    param([string[]]$Lines, [string]$Pattern, [int]$StartOffset = 0, [int]$EndOffset = 0)
    for ($i = 0; $i -lt $Lines.Count; $i++) {
        if ($Lines[$i] -match $Pattern) {
            $from = [Math]::Max(0, $i + $StartOffset)
            $to = [Math]::Min($Lines.Count - 1, $i + $EndOffset)
            return $Lines[$from..$to]
        }
    }
    return $null
}

function Extract-Snippet {
    param([string[]]$Lines, [string]$Snippet, [hashtable]$Params)

    if ([string]::IsNullOrWhiteSpace($Snippet) -or $Snippet -eq '.*') {
        return $Lines
    }

    if ($Snippet -match '^aj:\.\.(\w+)\.(\w+)\(\.\.\)') {
        $block = Extract-MethodBlock -Lines $Lines -MethodName $Matches[2]
        if ($block) { return $block }
    }

    if ($Snippet -match '^aj:\.\.(\w+)$') {
        $block = Extract-ClassBlock -Lines $Lines -ClassName $Matches[1]
        if ($block) { return $block }
    }

    if ($Snippet -match '^aj:\.\.(\w+)\(\.\.\)') {
        $block = Extract-MethodBlock -Lines $Lines -MethodName $Matches[1]
        if ($block) { return $block }
    }

    if ($Snippet -match '^re:(.+)$') {
        $startOffset = 0
        $endOffset = 0
        if ($Params['snippet-start-offset']) { $startOffset = [int]$Params['snippet-start-offset'] }
        if ($Params['snippet-end-offset']) { $endOffset = [int]$Params['snippet-end-offset'] }
        $block = Extract-RegexBlock -Lines $Lines -Pattern $Matches[1] -StartOffset $startOffset -EndOffset $endOffset
        if ($block) { return $block }
    }

    return $Lines
}

function Parse-IncludeParams {
    param([string]$Inner)
    $params = @{}
    foreach ($part in ($Inner -split '\|')) {
        if ($part -match '^([^=]+)=(.*)$') {
            $params[$Matches[1].Trim()] = $Matches[2].Trim()
        }
    }
    return $params
}

function Convert-DisabledIncludes {
    param([string]$Content, [string]$ProjectRoot)

    $pattern = '<!--\s*DISABLED_INCLUDE\{([^}]*)\}\s*-->'
    $regex = [regex]::new($pattern)

    $evaluator = {
        param($match)
        $params = Parse-IncludeParams -Inner $match.Groups[1].Value
        if (-not $params['source']) { return $match.Value }

        $sourcePath = Join-Path $ProjectRoot ($params['source'] -replace '/', '\')
        if (-not (Test-Path $sourcePath)) {
            Write-Warning "Source not found: $sourcePath"
            return $match.Value
        }

        $rawLines = Get-Content $sourcePath -Encoding UTF8
        $lines = Strip-LicenseHeader -Lines $rawLines
        $snippet = $params['snippet']
        $block = Extract-Snippet -Lines $lines -Snippet $snippet -Params $params
        if (-not $block -or $block.Count -eq 0) {
            Write-Warning "Could not extract snippet from $sourcePath ($snippet)"
            return $match.Value
        }

        # Trim trailing blank lines, cap very large blocks
        while ($block.Count -gt 0 -and [string]::IsNullOrWhiteSpace($block[-1])) {
            $block = $block[0..($block.Count - 2)]
        }
        if ($block.Count -gt 80) {
            $block = $block[0..79] + '    // ...'
        }

        $code = ($block -join "`n").TrimEnd()
        return "`n``````java`n$code`n``````"
    }

    return $regex.Replace($Content, $evaluator)
}

$files = Get-ChildItem -Path $SiteRoot -Recurse -Include *.md.vm,*.md,*.adoc -File
$changed = 0
foreach ($file in $files) {
    $content = Get-Content $file.FullName -Raw -Encoding UTF8
    if ($content -notmatch 'DISABLED_INCLUDE\{') { continue }

    $newContent = Convert-DisabledIncludes -Content $content -ProjectRoot $ProjectRoot
    if ($newContent -ne $content) {
        Set-Content -Path $file.FullName -Value $newContent -Encoding UTF8 -NoNewline
        Write-Output "Updated: $($file.FullName)"
        $changed++
    }
}

Write-Output "Done. $changed file(s) updated."
