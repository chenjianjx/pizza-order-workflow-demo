#!/usr/bin/env bash
set -euo pipefail

./gradlew clean build
docker-compose down
docker-compose build
docker-compose up