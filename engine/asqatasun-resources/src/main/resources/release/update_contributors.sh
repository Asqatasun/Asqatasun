#!/bin/bash

set -o errexit

TEMP=$(getopt -o cpt --long to-version: \
    -n 'javawrap' -- "$@")

if [[ $? != 0 ]]; then
    echo "Terminating..." >&2
    exit 1
fi

usage() {
    echo 'usage: ./update_contributors.sh [OPTIONS]...'
    echo ''
    echo '  --to-version   <arg>   MANDATORY: New version number to apply.'
    echo ''
    exit 2
}

# Note the quotes around $TEMP: they are essential!
eval set -- "$TEMP"

declare TO_VERSION=

while true; do
    case "$1" in
    --to-version)
        TO_VERSION="$2"
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
if [[ "${TO_VERSION}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi

#########################################
# Update contributors.txt
#########################################
function update_contributors() {
    {
        echo "===== Release ${TO_VERSION} ====="
        echo ''
        echo "Commits    Contributors"
        echo "--------------------------"
        git shortlog -s -n
        echo ''
    } >contributors.txt
}

##########################################
# Main tasks
##########################################
update_contributors

exit 0
