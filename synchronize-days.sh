#!/bin/bash
# Synchronize the light.days package and its views into the sibling
# peaceplatform project, so both projects share the same "days" feature code.
#
# Usage: ./synchronize-days.sh [peaceplatform-dir]
#   peaceplatform-dir defaults to ../peaceplatform relative to this project.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

DEST_ROOT="${1:-../peaceplatform}"
DEST_ROOT="$(cd "$DEST_ROOT" && pwd)"

SRC_LIGHT_DAYS="$SCRIPT_DIR/app/light/days"
SRC_VIEWS_DAYS="$SCRIPT_DIR/app/views/days"
DEST_LIGHT_DAYS="$DEST_ROOT/app/light/days"
DEST_VIEWS_DAYS="$DEST_ROOT/app/views/days"

for dir in "$SRC_LIGHT_DAYS" "$SRC_VIEWS_DAYS"; do
  if [ ! -d "$dir" ]; then
    echo "Error: source directory not found: $dir" >&2
    exit 1
  fi
done

echo "Synchronizing app/light/days -> $DEST_LIGHT_DAYS"
rsync -av --delete "$SRC_LIGHT_DAYS/" "$DEST_LIGHT_DAYS/"

echo "Synchronizing app/views/days -> $DEST_VIEWS_DAYS"
rsync -av --delete "$SRC_VIEWS_DAYS/" "$DEST_VIEWS_DAYS/"

echo "Done."
