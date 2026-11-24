#!/usr/bin/env bash
# Interactive installer for the JRebirth AI kit — asks where to install, then runs install.sh.
# Usage: ./install-interactive.sh
set -euo pipefail

KIT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$KIT_ROOT"

info() { echo "[jrebirth-ai-kit] $*"; }

default_target="$(pwd)"
read -r -p "Path to your application project [$default_target]: " target
target="${target:-$default_target}"
target="${target/#\~/$HOME}"

if [[ ! -d "$target" ]]; then
  read -r -p "Directory does not exist. Create it? [y/N] " create
  if [[ "$(echo "$create" | tr '[:upper:]' '[:lower:]')" == "y" ]]; then
    mkdir -p "$target"
  else
    echo "Aborted." >&2
    exit 1
  fi
fi

target="$(cd "$target" && pwd)"

use_link=0
read -r -p "Symlink skills folders instead of copying? [y/N] " link_ans
if [[ "$(echo "$link_ans" | tr '[:upper:]' '[:lower:]')" == "y" ]]; then
  use_link=1
fi

use_force=0
read -r -p "Replace existing AGENTS.md / CLAUDE.md without prompts? [y/N] " force_ans
if [[ "$(echo "$force_ans" | tr '[:upper:]' '[:lower:]')" == "y" ]]; then
  use_force=1
fi

extra=()
[[ "$use_link" -eq 1 ]] && extra+=(--link)
[[ "$use_force" -eq 1 ]] && extra+=(--force)

info "Installing into: $target"
chmod +x "$KIT_ROOT/install.sh"
exec "$KIT_ROOT/install.sh" "$target" "${extra[@]}"