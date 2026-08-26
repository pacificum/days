#!/bin/bash
# Archive the project sources into a timestamped zip. Only files tracked by
# git, or untracked but not ignored, are included — anything matched by
# .gitignore is left out.
#
# Usage: ./archive-sources.sh [output-directory]
#   output-directory defaults to the current directory.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "Error: $SCRIPT_DIR is not a git repository" >&2
  exit 1
fi

PROJECT_NAME="$(basename "$SCRIPT_DIR")"
TIMESTAMP="$(date +"%Y%m%d-%H%M%S")"
OUTPUT_DIR="${1:-$PWD}"
ARCHIVE_PATH="${OUTPUT_DIR%/}/${PROJECT_NAME}-sources-${TIMESTAMP}.zip"

# Archives produced by earlier runs of this script are untracked and not
# gitignored, so exclude them explicitly to avoid zipping them into themselves.
# .DS_Store is excluded too, since it isn't gitignored in this repo either.
git ls-files -z --cached --others --exclude-standard -- ':!:*.zip' ':!:.DS_Store' ':!:**/.DS_Store' \
  | xargs -0 zip -r "$ARCHIVE_PATH"

echo "Created archive: $ARCHIVE_PATH"
