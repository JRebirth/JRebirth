#Requires -Version 5.1
<#
.SYNOPSIS
  Capture window screenshots for JRebirth site images/apps/*.png
#>
param(
  [string]$OutDir = (Join-Path $PSScriptRoot "..\org.jrebirth.af\src\site\resources\images\apps"),
  [int]$WaitSeconds = 12,
  [int]$SettleMs = 2000,
  [string[]]$OnlyPng = @()
)

$ErrorActionPreference = "Stop"
Add-Type -AssemblyName System.Drawing

Add-Type @"
using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;
public static class Win32Cap {
  [DllImport("user32.dll")] public static extern bool SetForegroundWindow(IntPtr hWnd);
  [DllImport("user32.dll")] public static extern bool ShowWindow(IntPtr hWnd, int nCmdShow);
  [DllImport("user32.dll")] public static extern bool GetWindowRect(IntPtr hWnd, out RECT lpRect);
  [DllImport("user32.dll")] public static extern bool PrintWindow(IntPtr hwnd, IntPtr hDC, uint nFlags);
  [DllImport("user32.dll")] public static extern bool IsWindowVisible(IntPtr hWnd);
  [DllImport("user32.dll")] public static extern int GetWindowTextLength(IntPtr hWnd);
  [DllImport("user32.dll", CharSet = CharSet.Unicode)] public static extern int GetWindowText(IntPtr hWnd, StringBuilder lpString, int nMaxCount);
  [DllImport("user32.dll")] public static extern uint GetWindowThreadProcessId(IntPtr hWnd, out uint lpdwProcessId);
  [DllImport("user32.dll")] public static extern bool EnumWindows(EnumWindowsProc lpEnumFunc, IntPtr lParam);
  [DllImport("user32.dll")] public static extern IntPtr GetForegroundWindow();
  public delegate bool EnumWindowsProc(IntPtr hWnd, IntPtr lParam);
  public struct RECT { public int Left, Top, Right, Bottom; }
  public static List<IntPtr> FindWindowsForPid(uint pid) {
    var list = new List<IntPtr>();
    EnumWindows((hWnd, _) => {
      uint wpid;
      GetWindowThreadProcessId(hWnd, out wpid);
      if (wpid != pid || !IsWindowVisible(hWnd)) return true;
      var r = new RECT();
      GetWindowRect(hWnd, out r);
      if ((r.Right - r.Left) < 120 || (r.Bottom - r.Top) < 80) return true;
      list.Add(hWnd);
      return true;
    }, IntPtr.Zero);
    return list;
  }
}
"@

function Get-ProcessTreeIds {
  param([int]$RootPid)
  $ids = New-Object 'System.Collections.Generic.HashSet[int]'
  [void]$ids.Add($RootPid)
  $queue = [System.Collections.Generic.Queue[int]]::new()
  $queue.Enqueue($RootPid)
  while ($queue.Count -gt 0) {
    $parent = $queue.Dequeue()
    $children = Get-CimInstance Win32_Process -Filter "ParentProcessId=$parent" -ErrorAction SilentlyContinue
    foreach ($c in $children) {
      $cid = [int]$c.ProcessId
      if ($ids.Add($cid)) { $queue.Enqueue($cid) }
    }
  }
  return @($ids)
}

function Find-LargestWindow {
  param([int[]]$Pids)
  $best = [IntPtr]::Zero
  $bestArea = 0
  foreach ($procId in $Pids) {
    foreach ($h in [Win32Cap]::FindWindowsForPid([uint32]$procId)) {
      $r = New-Object Win32Cap+RECT
      [void][Win32Cap]::GetWindowRect($h, [ref]$r)
      $area = ($r.Right - $r.Left) * ($r.Bottom - $r.Top)
      if ($area -gt $bestArea) { $bestArea = $area; $best = $h }
    }
  }
  if ($best -ne [IntPtr]::Zero) { return $best }
  foreach ($procId in $Pids) {
    try {
      $p = Get-Process -Id $procId -ErrorAction Stop
      $p.Refresh()
      if ($p.MainWindowHandle -ne [IntPtr]::Zero) { return $p.MainWindowHandle }
    } catch { }
  }
  return $null
}

function Wait-MainWindow {
  param([System.Diagnostics.Process]$Proc, [int]$TimeoutSec = 120)
  $deadline = (Get-Date).AddSeconds($TimeoutSec)
  while ((Get-Date) -lt $deadline) {
  $Proc.Refresh()
    $pids = Get-ProcessTreeIds -RootPid $Proc.Id
    $alive = $false
    foreach ($procId in $pids) {
      if (Get-Process -Id $procId -ErrorAction SilentlyContinue) { $alive = $true; break }
    }
    if (-not $alive) { return $null }
    $hwnd = Find-LargestWindow -Pids $pids
    if ($hwnd) { return $hwnd }
    Start-Sleep -Milliseconds 300
  }
  return $null
}

function Get-WindowTitle {
  param([IntPtr]$Hwnd)
  $len = [Win32Cap]::GetWindowTextLength($Hwnd)
  if ($len -le 0) { return "" }
  $sb = New-Object System.Text.StringBuilder ($len + 2)
  [void][Win32Cap]::GetWindowText($Hwnd, $sb, $sb.Capacity)
  return $sb.ToString()
}

function Assert-ForegroundWindow {
  param([IntPtr]$Hwnd, [int]$Attempts = 12)
  for ($i = 0; $i -lt $Attempts; $i++) {
    [void][Win32Cap]::ShowWindow($Hwnd, 9)
    [void][Win32Cap]::SetForegroundWindow($Hwnd)
    Start-Sleep -Milliseconds 350
    if ([Win32Cap]::GetForegroundWindow() -eq $Hwnd) { return $true }
  }
  return $false
}

function Save-WindowShot {
  param([IntPtr]$Hwnd, [string]$Path)
  if (-not (Assert-ForegroundWindow -Hwnd $Hwnd)) {
    throw "Could not bring target window to foreground"
  }
  $title = Get-WindowTitle -Hwnd $Hwnd
  Write-Host "  window: '$title'"

  $r = New-Object Win32Cap+RECT
  [void][Win32Cap]::GetWindowRect($Hwnd, [ref]$r)
  $w = $r.Right - $r.Left
  $h = $r.Bottom - $r.Top
  if ($w -lt 200 -or $h -lt 200) {
    throw "Window too small to capture (${w}x${h})"
  }

  $dir = Split-Path $Path -Parent
  if (-not (Test-Path $dir)) { New-Item -ItemType Directory -Force -Path $dir | Out-Null }

  function Save-Bmp([System.Drawing.Bitmap]$Bitmap) {
    $Bitmap.Save($Path, [System.Drawing.Imaging.ImageFormat]::Png)
    $Bitmap.Dispose()
    $len = (Get-Item -LiteralPath $Path).Length
    if ($len -lt 5000) { throw "Output too small ($len bytes)" }
  }

  # JavaFX / GPU windows: PrintWindow often returns a blank 1x1 PNG — prefer screen copy.
  $screen = New-Object System.Drawing.Bitmap $w, $h
  $sg = [System.Drawing.Graphics]::FromImage($screen)
  $sg.CopyFromScreen($r.Left, $r.Top, 0, 0, [System.Drawing.Size]::new($w, $h), [System.Drawing.CopyPixelOperation]::SourceCopy)
  $sg.Dispose()
  try {
    Save-Bmp $screen
    return
  } catch {
    $screen.Dispose()
  }

  $bmp = New-Object System.Drawing.Bitmap $w, $h
  $g = [System.Drawing.Graphics]::FromImage($bmp)
  $hdc = $g.GetHdc()
  [void][Win32Cap]::PrintWindow($Hwnd, $hdc, 2)
  $g.ReleaseHdc($hdc)
  $g.Dispose()
  Save-Bmp $bmp
}

$apps = @(
  @{ Png = "wave.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\wave\target\jpackage-app-image\Wave\Wave.exe" }
  @{ Png = "todos.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\todos\target\jpackage-app-image\Todos\Todos.exe" }
  @{ Png = "workbench.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\workbench\target\jpackage-app-image\Workbench\Workbench.exe" }
  @{ Png = "fxml.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\fxml\target\jpackage-app-image\FXML\FXML.exe" }
  @{ Png = "undoredo.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\undoredo\target\jpackage-app-image\UndoRedo\UndoRedo.exe" }
  @{ Png = "fonticon.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\fonticon\target\jpackage-app-image\FontIcon\FontIcon.exe" }
  @{ Png = "demo.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\demo\target\jpackage-app-image\JRebirthDemo\JRebirthDemo.exe" }
  @{ Png = "analyzer.png"; Exe = "D:\DEV\GIT\JRebirth\org.jrebirth.af\showcase\analyzer\target\jpackage-app-image\JRebirthAnalyzer\JRebirthAnalyzer.exe" }
  @{ Png = "masteringtables.png"; Exe = "D:\DEV\GIT\MasteringTables\org.jrebirth.demo.masteringtables\target\jpackage-app-image\MasteringTables\MasteringTables.exe" }
  @{ Png = "comparisontool.png"; Exe = "D:\DEV\GIT\ComparisonTool\org.jrebirth.demo.comparisontool\target\jpackage-app-image\ComparisonTool\ComparisonTool.exe"; ExeAlt = "$env:TEMP\ct-cap\ComparisonTool\ComparisonTool.exe" }
  @{ Png = "jrebirthtour.png"; Exe = "D:\DEV\GIT\JRebirth-Tour\org.jrebirth.af.presentation.jaf\target\jpackage-app-image\JRebirthTour\JRebirthTour.exe"; WaitSeconds = 22; TitleMatch = "Tour|JRebirth" }
  @{ Png = "lightningtalk.png"; Exe = "D:\DEV\GIT\LightningTalk\org.jrebirth.af.presentation.lightningtalk\target\jpackage-app-image\LightningTalk\LightningTalk.exe"; WaitSeconds = 22 }
  @{ Png = "breizhcamp.png"; Exe = "D:\DEV\GIT\JFX-Presentation\org.jrebirth.af.presentation.javafx2\target\jpackage-app-image\Breizhcamp\Breizhcamp.exe"; WaitSeconds = 22; TitleMatch = "Breizh|JavaFX|Presentation" }
  @{ Png = "eclipsecon.png"; Exe = "D:\DEV\GIT\EclipseCON-France\org.jrebirth.af.presentation.eclipsecon\target\jpackage-app-image\EclipseCON\EclipseCON.exe"; WaitSeconds = 22 }
  @{ Png = "eclipseday.png"; Exe = "D:\DEV\GIT\EclipseDay-Presentation\org.jrebirth.af.presentation.eclipseday\target\jpackage-app-image\EclipseDayToulouse\EclipseDayToulouse.exe"; WaitSeconds = 22 }
  @{ Png = "ezdojo.png"; Exe = "D:\DEV\GIT\ezDojo\client\app\target\jpackage-app-image\EzDojo\EzDojo.exe" }
)

$OutDir = (Resolve-Path -LiteralPath $OutDir -ErrorAction SilentlyContinue)
if (-not $OutDir) {
  $OutDir = (Join-Path $PSScriptRoot "..\org.jrebirth.af\src\site\resources\images\apps")
  New-Item -ItemType Directory -Force -Path $OutDir | Out-Null
  $OutDir = (Resolve-Path $OutDir).Path
}

$results = @()
foreach ($app in $apps) {
  $name = $app.Png
  if ($OnlyPng.Count -gt 0 -and ($OnlyPng -notcontains $name)) { continue }
  $exePath = $app.Exe
  if (-not (Test-Path $exePath) -and $app.ExeAlt -and (Test-Path $app.ExeAlt)) {
    $exePath = $app.ExeAlt
  }
  if (-not (Test-Path $exePath)) {
    Write-Warning "SKIP $name — missing $($app.Exe)"
    $results += [pscustomobject]@{ App = $name; Status = "SKIP"; Detail = "exe missing" }
    continue
  }
  $procName = [IO.Path]::GetFileNameWithoutExtension($exePath)
  Get-Process -Name $procName -ErrorAction SilentlyContinue | Stop-Process -Force -ErrorAction SilentlyContinue
  Start-Sleep -Milliseconds 400
  $wd = Split-Path $exePath -Parent
  Write-Host "Capture $name ..."
  $p = Start-Process -FilePath $exePath -WorkingDirectory $wd -PassThru
  $timeoutSec = if ($null -ne $app.WaitSeconds) { [Math]::Max(90, [int]$app.WaitSeconds + 40) } else { 120 }
  $hwnd = Wait-MainWindow -Proc $p -TimeoutSec $timeoutSec
  if (-not $hwnd) {
    Write-Warning "No window for $name"
    if (-not $p.HasExited) { $p | Stop-Process -Force -ErrorAction SilentlyContinue }
    $results += [pscustomobject]@{ App = $name; Status = "FAIL"; Detail = "no window" }
    continue
  }
  $appWait = if ($null -ne $app.WaitSeconds) { [int]$app.WaitSeconds } else { $WaitSeconds }
  Start-Sleep -Seconds $appWait
  Start-Sleep -Milliseconds $SettleMs
  $treeIds = Get-ProcessTreeIds -RootPid $p.Id
  $hwnd = Find-LargestWindow -Pids $treeIds
  if (-not $hwnd) {
    Write-Warning "Window lost before capture for $name"
    if (-not $p.HasExited) { $p | Stop-Process -Force -ErrorAction SilentlyContinue }
    $results += [pscustomobject]@{ App = $name; Status = "FAIL"; Detail = "window lost" }
    continue
  }
  $out = Join-Path $OutDir $name
  try {
    if ($app.TitleMatch) {
      $t = Get-WindowTitle -Hwnd $hwnd
      if ($t -notmatch $app.TitleMatch) {
        throw "Unexpected window title '$t' (expected pattern $($app.TitleMatch))"
      }
    }
    Save-WindowShot -Hwnd $hwnd -Path $out
    $len = (Get-Item $out).Length
    if ($len -lt 5000) {
      throw "Output too small ($len bytes) — likely blank window"
    }
    $results += [pscustomobject]@{ App = $name; Status = "OK"; Detail = "$out ($len bytes)" }
    Write-Host "  -> $out"
  } catch {
    Write-Warning "Capture failed $name : $_"
    $results += [pscustomobject]@{ App = $name; Status = "FAIL"; Detail = "$_" }
  }
  if (-not $p.HasExited) { $p | Stop-Process -Force -ErrorAction SilentlyContinue }
  Start-Sleep -Milliseconds 800
}

$results | Format-Table -AutoSize
$fail = @($results | Where-Object Status -ne "OK").Count
if ($fail -gt 0) { exit 1 }