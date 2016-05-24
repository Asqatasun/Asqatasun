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
  -p | --port       <port>          value by default: 8085  
  -t | --functional-tests           also execute functional tests (please check pre-requisites on http://doc.asqatasun.org/en/30_Contributor_doc/Testing/Functional_tests.html)

  --skip-build                      skip Maven build (relies on previous build, that must exists)
  --skip-copy                       skip copying .tar.gz (relies on previous .tar.gz, that must exist)
  --skip-docker-build               skip docker build
  --skip-docker-run                 skip docker run
  --use-sudo-docker		    use "sudo docker" instead of "docker"

  -h | --help                       Show this help

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:p:ht --long source-dir:,docker-dir:,port:,help,functional-tests,skip-build,skip-copy,skip-docker-build,skip-docker-run,use-sudo-docker -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "${TEMP}"

declare SOURCE_DIR
declare DOCKER_DIR
declare HELP=false
declare FTESTS=false
declare SKIP_BUILD=false
declare SKIP_COPY=false
declare SKIP_DOCKER_BUILD=false
declare SKIP_DOCKER_RUN=false
declare USE_SUDO_DOCKER=false
declare CONTAINER_EXPOSED_PORT="8085"

while true; do
  case "$1" in
    -s | --source-dir )         SOURCE_DIR="$2"; shift 2 ;;
    -d | --docker-dir )         DOCKER_DIR="$2"; shift 2 ;;
    -p | --port )               CONTAINER_EXPOSED_PORT="$2"; shift 2 ;;
    -h | --help )               HELP=true; shift ;;
    -t | --functional-tests )   FTESTS=true; shift ;;
    --skip-build )              SKIP_BUILD=true; shift ;;
    --skip-copy )               SKIP_COPY=true; shift ;;
    --skip-docker-build )       SKIP_DOCKER_BUILD=true; shift ;;
    --skip-docker-run )         SKIP_DOCKER_RUN=true; shift ;;
    --use-sudo-docker )         USE_SUDO_DOCKER=true; shift ;;

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

TIMESTAMP=$(date +%Y-%m-%d) # format 2015-11-23, cf man date
TGZ_BASENAME="web-app/asqatasun-web-app/target/asqatasun-"
TGZ_EXT=".tar.gz"
CONTAINER_NAME="asqa"


SUDO=''
if ${USE_SUDO_DOCKER} ; then   SUDO='sudo'; fi

#############################################
# Functions
#############################################

function kill_previous_container() {
    set +e
    RUNNING=$(${SUDO} docker inspect --format="{{ .State.Status }}" ${CONTAINER_NAME} 2>/dev/null)
    set -e

    if [ ${RUNNING} == "running" ]; then
        ${SUDO} docker stop ${CONTAINER_NAME}
        ${SUDO} docker rm ${CONTAINER_NAME}
    elif [ ${RUNNING} == "exited" ]; then
        ${SUDO} docker rm ${CONTAINER_NAME}
    fi
}

function do_build() {
    # clean and build
    (cd "$SOURCE_DIR" ; mvn clean install) ||
        fail "Error at build"
}

function do_copy_targz() {
    # copy TAR.GZ to docker dir
    cp "${SOURCE_DIR}/${TGZ_BASENAME}"*"${TGZ_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
        fail "Error copying ${SOURCE_DIR}/${TGZ_BASENAME}"
}

function do_docker_build() {
    # build Docker container
    (cd "${SOURCE_DIR}/${DOCKER_DIR}" ; \
        ${SUDO} docker build -t asqatasun/asqatasun:${TIMESTAMP} "${SOURCE_DIR}/${DOCKER_DIR}" ) ||
        fail "Error building container"
}

function do_docker_run() {
    kill_previous_container
    ${SUDO} docker run -p ${CONTAINER_EXPOSED_PORT}:8080 --name ${CONTAINER_NAME} -d asqatasun/asqatasun:${TIMESTAMP}

    # wait a bit to let container start
    # @@@TODO find a better way to do this (test if URL responds with 200 or anything else)
    sleep 25
}

function do_functional_testing() {
    # functional testing
    (cd "${SOURCE_DIR}/testing-tools/tgol-test-scenario"; \
        mvn test \
        -Dadmin.user=me@my-email.org \
        -Dadmin.password=myAsqaPassword \
        -Dhost.location=http://localhost:${CONTAINER_EXPOSED_PORT}/asqatasun/ \
        -Dfirefox.path=/opt/firefox/firefox
    )
}

#############################################
# Do the actual job
#############################################

if ! ${SKIP_BUILD} ; then            do_build; fi
if ! ${SKIP_COPY} ; then             do_copy_targz; fi
if ! ${SKIP_DOCKER_BUILD} ; then     do_docker_build; fi
if ! ${SKIP_DOCKER_RUN} ; then       do_docker_run; fi
if ${FTESTS} ; then                  do_functional_testing; fi
