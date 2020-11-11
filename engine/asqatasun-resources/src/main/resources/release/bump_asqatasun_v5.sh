#!/bin/bash

set -o errexit

TEMP=$(getopt -o cpt --long release-version:,dev-version: \
    -n 'javawrap' -- "$@")

if [[ $? != 0 ]]; then
    echo "Terminating..." >&2
    exit 1
fi

usage() {
    echo 'usage: ./bump_asqatasun_v5.sh [OPTIONS]...'
    echo ''
    echo '  --release-version <arg>   MANDATORY: number string of released version.'
    echo '  --dev-version   <arg>     MANDATORY: number string for next development iteration.'
    echo ''
    exit 2
}

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare RELEASE_VERSION=
declare DEV_VERSION=

while true; do
    case "$1" in
    --release-version)
        RELEASE_VERSION="$2"
        shift 2
        ;;
    --dev-version)
        DEV_VERSION="$2"
        shift 2
        ;;
    --)
        shift
        break
        ;;
    *) break ;;
    esac
done

# Check mandatory options
if [[ "${RELEASE_VERSION}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi
if [[ "${DEV_VERSION}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi

function publish_release_version() {
    # The option -DartifactId='*' is important to *also* modify projects (pom.xml) that are not child of parent POM,
    # for instance server/asqatasun-server and web-app/asqatasun-web-app
    mvn versions:set -DnewVersion="${RELEASE_VERSION}" -DartifactId='*'
    git add -u
    git commit -m "RELEASE ${RELEASE_VERSION}"
    git tag -a "${RELEASE_VERSION}" -m "${RELEASE_VERSION}"
    git push origin "${RELEASE_VERSION}"
}

function set_next_dev_version() {
    # The option -DartifactId='*' is important to *also* modify projects (pom.xml) that are not child of parent POM,
    # for instance server/asqatasun-server and web-app/asqatasun-web-app
    mvn versions:set -DnewVersion="${DEV_VERSION}" -DartifactId='*'
    git add -u
    git commit -m "RELEASE Set version for next development iteration: ${DEV_VERSION}"
    git push origin master
}

##########################################
# Main tasks
##########################################

publish_release_version
set_next_dev_version

exit 0
