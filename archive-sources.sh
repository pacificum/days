#!/bin/bash
# Archive the project sources into a timestamped zip, excluding build
# artifacts, IDE metadata, and other generated files.
#
# Usage: ./archive-sources.sh [output-directory]
#   output-directory defaults to the current directory.

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

PROJECT_NAME="$(basename "$SCRIPT_DIR")"
TIMESTAMP="$(date +"%Y%m%d-%H%M%S")"
OUTPUT_DIR="${1:-$PWD}"
ARCHIVE_PATH="${OUTPUT_DIR%/}/${PROJECT_NAME}-sources-${TIMESTAMP}.zip"

EXCLUDES=(
  ".git/*"
  ".DS_Store"
  "*/.DS_Store"
  ".idea/*"
  ".bloop/*"
  ".bsp/*"
  ".metals/*"
  ".vscode/*"
  "target/*"
  "*/target/*"
  "project/project/*"
  "project/target/*"
  "logs/*"
  "*/logs/*"
  "tmp/*"
  "*/tmp/*"
  "dist/*"
  "out/*"
  ".history/*"
  "*.class"
  "*.jar"
  "node_modules/*"
  "*/node_modules/*"
  "*.zip"
)

zip -r "$ARCHIVE_PATH" . -x "${EXCLUDES[@]}"

echo "Created archive: $ARCHIVE_PATH"
