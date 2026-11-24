#!/usr/bin/env bash
# Install the JRebirth AI kit into a target application project (Linux/macOS).
# Usage: ./install.sh [target-dir] [--force] [--link]
set -euo pipefail

KIT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
TARGET="${1:-.}"
FORCE=0
LINK=0
shift || true
while [[ $# -gt 0 ]]; do
  case "$1" in
    --force) FORCE=1 ;;
    --link) LINK=1 ;;
    *) TARGET="$1" ;;
  esac
  shift || true
done

TARGET="$(cd "$TARGET" && pwd)"
info() { echo "[jrebirth-ai-kit] $*"; }

backup_or_merge() {
  local src="$1" dest="$2" label="$3"
  if [[ ! -f "$dest" ]]; then
    cp "$src" "$dest"
    info "Installed $label"
    return
  fi
  if cmp -s "$src" "$dest"; then
    info "$label already up to date"
    return
  fi
  if [[ "$FORCE" -eq 1 ]]; then
    local bak="${dest}.bak-$(date +%Y%m%d-%H%M%S)"
    cp "$dest" "$bak"
    cp "$src" "$dest"
    info "Replaced $label (backup: $bak)"
    return
  fi
  echo
  echo "$label already exists at: $dest"
  echo "  [B] Backup and replace"
  echo "  [A] Append kit pointer block"
  echo "  [S] Skip"
  read -r -p "Choice: " choice
  case "$(echo "$choice" | tr '[:lower:]' '[:upper:]')" in
    B)
      local bak="${dest}.bak-$(date +%Y%m%d-%H%M%S)"
      cp "$dest" "$bak"
      cp "$src" "$dest"
      info "Replaced $label (backup: $bak)"
      ;;
    A)
      if ! grep -q "BEGIN jrebirth-ai-kit" "$dest"; then
        cat >> "$dest" <<'EOF'

<!-- BEGIN jrebirth-ai-kit -->
Also follow JRebirth rules in the nearest AGENTS.md from jrebirth-ai-kit.
Skills: .cursor/skills/jrebirth-*/SKILL.md
<!-- END jrebirth-ai-kit -->
EOF
        info "Appended kit pointer to $label"
      else
        info "$label already contains kit pointer — skipped"
      fi
      ;;
    *) info "Skipped $label" ;;
  esac
}

install_skills() {
  local dest="$1"
  local src="$KIT_ROOT/skills"
  mkdir -p "$(dirname "$dest")"
  if [[ "$LINK" -eq 1 ]]; then
    if [[ -L "$dest" ]]; then
      rm "$dest"
    elif [[ -d "$dest" ]]; then
      info "Skills path exists — copying into it: $dest"
      cp -R "$src"/. "$dest"/
      return
    fi
    ln -s "$src" "$dest"
    info "Linked $dest -> $src"
  else
    mkdir -p "$dest"
    cp -R "$src"/. "$dest"/
    info "Copied skills -> $dest"
  fi
}

info "Target project: $TARGET"
backup_or_merge "$KIT_ROOT/AGENTS.md" "$TARGET/AGENTS.md" "AGENTS.md"
backup_or_merge "$KIT_ROOT/CLAUDE.md" "$TARGET/CLAUDE.md" "CLAUDE.md"

mkdir -p "$TARGET/.cursor/rules"
cp "$KIT_ROOT/.cursor/rules/jrebirth-core.mdc" "$TARGET/.cursor/rules/jrebirth-core.mdc"
info "Installed .cursor/rules/jrebirth-core.mdc"

for d in .cursor/skills .claude/skills .codex/skills .agents/skills; do
  install_skills "$TARGET/$d"
done

info "Done. Agents should follow AGENTS.md and open jrebirth-* skills before coding."
