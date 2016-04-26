#!/bin/bash

# MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

set -o errexit

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 launches a sequence that:
- builds Asqatasun from sources,
- builds a Docker image
- runs a container based the freshly built image

usage: $0 [OPTIONS]

  -s | --source-dir <directory>     MANDATORY Absolute path to Asqatasun sources directory 
  -d | --docker-dir <directory>     MANDATORY Path to directory containing the Dockerfile. Path must be relative to SOURCE_DIR
  -h | --help                       Show this help

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:h --long source-dir:,socker-dir:help -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare SOURCE_DIR
declare DOCKER_DIR
declare HELP=false

while true; do
  case "$1" in
    -s | --source-dir ) SOURCE_DIR="$2"; shift 2 ;; 
    -d | --docker-dir ) DOCKER_DIR="$2"; shift 2 ;;
    -h | --help )       HELP=true; shift ;;
    * ) break ;;
  esac
done

if [[ -z "$SOURCE_DIR" || -z "$DOCKER_DIR" || "$HELP" == "true" ]]; then
    usage
fi

#############################################
# Functions
#############################################

fail() {
        echo ""
	echo "FAILURE : $*"
        echo ""
	exit -1
}

#############################################
# Variables
#############################################

TIMESTAMP=$(date +%Y-%m-%d) # format 2015-11-25, cf man date
#DOCKER_DIR="docker/single-container"
TGZ_BASENAME="web-app/asqatasun-web-app/target/asqatasun-"
TGZ_EXT=".tar.gz"

#############################################
# Do the actual job
#############################################

# clean and build
(cd "$SOURCE_DIR" ; mvn clean install) ||
    fail "Error at build"
 
# copy TAR.GZ to docker dir
cp "${SOURCE_DIR}/${TGZ_BASENAME}"*"${TGZ_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
    fail "Error copying ${SOURCE_DIR}/${TGZ_BASENAME}"

# build Docker container
(cd "${SOURCE_DIR}/${DOCKER_DIR}" ; \
    docker build -t asqatasun/asqatasun:${TIMESTAMP} "${SOURCE_DIR}/${DOCKER_DIR}" ) ||
    fail "Error building container"

# run container freshly build
# @@@TODO find a way to test whether a container named "asqa" is already running,
# and if so, stop it and delete it
docker run --rm -p 8085:8080 --name asqa asqatasun/asqatasun:${TIMESTAMP}

# functional testing
# @@@TODO 

# destroy container
# @@@TODO

