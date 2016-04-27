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
- execute functional tests

usage: $0 [OPTIONS]

  -s | --source-dir <directory>     MANDATORY Absolute path to Asqatasun sources directory 
  -d | --docker-dir <directory>     MANDATORY Path to directory containing the Dockerfile. Path must be relative to SOURCE_DIR
  -t | --functional-tests           also execute functional tests (please check pre-requisites on http://doc.asqatasun.org/en/30_Contributor_doc/Testing/Functional_tests.html)
  -h | --help                       Show this help

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:ht --long source-dir:,docker-dir:,help,functional-tests -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare SOURCE_DIR
declare DOCKER_DIR
declare HELP=false
declare FTESTS=false

while true; do
  case "$1" in
    -s | --source-dir ) SOURCE_DIR="$2"; shift 2 ;; 
    -d | --docker-dir ) DOCKER_DIR="$2"; shift 2 ;;
    -h | --help )       HELP=true; shift ;;
    -t | --functional-tests )   FTESTS=true; shift ;;
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

# Run container freshly build
# @@@TODO find a way to test whether a container named "asqa" is already running, and if so, stop it and delete it
# Container is destroyed if exiting gracefully (--rm option)
docker run --rm -p 8085:8080 --name asqa asqatasun/asqatasun:${TIMESTAMP}

# functional testing
if [ $FTESTS ]; then
    (cd "${SOURCE_DIR}/testing-tools/tgol-test-scenario"; \
        mvn test \
        -Dadmin.user=me@my-email.org \
        -Dadmin.password=myAsqaPassword \
        -Dhost.location=http://localhost:8085/asqatasun/ \
        -Dfirefox.path=/opt/firefox/firefox
    )
fi
