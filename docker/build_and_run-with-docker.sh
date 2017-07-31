#!/bin/bash

# MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

set -o errexit

#############################################
# Variables
#############################################
APP_NAME="Asqatasun"
TIMESTAMP=$(date +%Y-%m-%d) # format 2015-11-23, cf man date
SCRIPT=`basename ${BASH_SOURCE[0]}`

    #Set fonts for Help
        BOLD=$(tput bold)
      # STOT=$(tput smso)
        UNDR=$(tput smul)
        REV=$(tput rev)
        RED=$(tput setaf 1)
        GREEN=$(tput setaf 2)
      # YELLOW=$(tput setaf 3)
      # MAGENTA=$(tput setaf 5)
      # WHITE=$(tput setaf 7)
        NORM=$(tput sgr0)
        NORMAL=$(tput sgr0)

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

  Help documentation for ${BOLD}${SCRIPT}${NORM}
  --------------------------------------------------------------
  This script launches a sequence that:
    - builds ${BOLD}${APP_NAME}${NORM} from sources
    - builds a new Docker image
    - runs   a new Docker container
    - execute functional tests
  --------------------------------------------------------------
  ${BOLD}${SCRIPT}${NORM} ${BOLD}${GREEN}-s${NORM} <directory> ${BOLD}${GREEN}-d${NORM} <directory> [OPTIONS]
  --------------------------------------------------------------
  ${BOLD}${GREEN}-s${NORM} | --source-dir     <directory> MANDATORY Absolute path to ${APP_NAME} sources directory
  ${BOLD}${GREEN}-d${NORM} | --docker-dir     <directory> MANDATORY Path to directory containing the Dockerfile.
                                    Path must be relative to SOURCE_DIR
  ${BOLD}-p${NORM} | --port           <port>      Default value: 8085
  ${BOLD}-n${NORM} | --container-name <name>      Default value: asqa
  ${BOLD}-i${NORM} | --image-name     <name>      Default value: asqatasun/asqatasun
  ${BOLD}-t${NORM} | --tag-name       <name>      Default value: ${TIMESTAMP}

  ${BOLD}-b${NORM} | --build-only-dir <directory> Build only webapp and <directory> (relative to SOURCE_DIR)
  ${BOLD}-w${NORM} | --build-only-webapp          Build only webapp (relies on previous build)
  ${BOLD}-l${NORM} | --only-localhost             Container available only on localhost
       --use-sudo-docker            Use "sudo docker" instead of "docker"
       --skip-build-test            Skip unit tests on Maven build
       --skip-build                 Skip Maven build (relies on previous build, that must exists)
       --skip-copy                  Skip copying .tar.gz (relies on previous .tar.gz, that must exist)
       --skip-docker-build          Skip docker build
       --skip-docker-run            Skip docker run

  ${BOLD}-h${NORM} | --help                      ${REV} Show this help ${NORM}
  ${BOLD}-t${NORM} | --functional-tests           Also execute functional tests. Please check pre-requisites
                                    on http://doc.asqatasun.org/en/30_Contributor_doc/Testing/Functional_tests.html

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:p:n:i:t:b:lwht --long source-dir:,docker-dir:,port:,container-name:,image-name:,tag-name:,build-only-dir:,only-localhost,build-only-webapp,help,functional-tests,skip-build-test,skip-build,skip-copy,skip-docker-build,skip-docker-run,use-sudo-docker -- "$@"`

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
declare SKIP_BUILD_TEST=false
declare SKIP_COPY=false
declare SKIP_DOCKER_BUILD=false
declare SKIP_DOCKER_RUN=false
declare USE_SUDO_DOCKER=false
declare ONLY_LOCALHOST=false
declare BUILD_ONLY_WEBAPP=false
declare BUILD_ONLY_DIR=false
declare WEBAPP_DIR="web-app/asqatasun-web-app"
declare CONTAINER_EXPOSED_PORT="8085"
declare CONTAINER_NAME="asqa"
declare IMAGE_NAME="asqatasun/asqatasun"
declare TAG_NAME=${TIMESTAMP}

while true; do
  case "$1" in
    -s | --source-dir )         SOURCE_DIR="$2"; shift 2 ;;
    -d | --docker-dir )         DOCKER_DIR="$2"; shift 2 ;;
    -p | --port )               CONTAINER_EXPOSED_PORT="$2"; shift 2 ;;
    -n | --container-name )     CONTAINER_NAME="$2"; shift 2 ;;
    -i | --image-name )         IMAGE_NAME="$2"; shift 2 ;;
    -t | --tag-name  )          TAG_NAME="$2"; shift 2 ;;
    -b | --build-only-dir )     BUILD_ONLY_DIR="$2"; shift 2 ;;
    -w | --build-only-webapp )  BUILD_ONLY_WEBAPP=true; shift ;;
    -h | --help )               HELP=true; shift ;;
    -t | --functional-tests )   FTESTS=true; shift ;;
    -l | --only-localhost )     ONLY_LOCALHOST=true; shift ;;

    --skip-build-test )         SKIP_BUILD_TEST=true; shift ;;
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
    ERROR_MSG=$*
    echo ""
    echo " ${RED}-----------------------------------------------------------${NORM}"
    echo " ${BOLD}FAILURE${NORM}: loading ${APP_NAME} is not possible."
    echo " ${RED}${ERROR_MSG}${NORM}"
    echo " ${RED}-----------------------------------------------------------${NORM}"
    exit -1
}


#############################################
# Variables
#############################################

TGZ_BASENAME="web-app/asqatasun-web-app/target/asqatasun-"
TGZ_EXT=".tar.gz"
URL="http://localhost:${CONTAINER_EXPOSED_PORT}/asqatasun/"
ADD_IP=''
URL="http://localhost:${CONTAINER_EXPOSED_PORT}/asqatasun/"
if ${ONLY_LOCALHOST} ; then  
    ADD_IP="127.0.0.1:";
    URL="http://127.0.0.1:${CONTAINER_EXPOSED_PORT}/asqatasun/"
fi

SUDO=''
if ${USE_SUDO_DOCKER} ; then   SUDO='sudo '; fi


#############################################
# Functions
#############################################

function kill_previous_container() {
    set +e
    RUNNING=$(${SUDO} docker inspect --format="{{ .State.Status }}" ${CONTAINER_NAME} 2>/dev/null)
    set -e

    if [ "${RUNNING}" == "running" ]; then
        ${SUDO} docker stop ${CONTAINER_NAME}
        ${SUDO} docker rm ${CONTAINER_NAME}
    elif [ "${RUNNING}" == "exited" ]; then
        ${SUDO} docker rm ${CONTAINER_NAME}
    fi
}

function do_build() {
    MAVEN_OPTION=''
    if ${SKIP_BUILD_TEST} ; then
        MAVEN_OPTION=' -Dmaven.test.skip=true '; # skip unit tests
    fi

    # clean and build
    if [[ -n "$BUILD_ONLY_DIR" && "$BUILD_ONLY_DIR" != "false" ]]  ; then
        if [[ -d "${SOURCE_DIR}/${BUILD_ONLY_DIR}" ]] ; then
            # clean and build only $BUILD_ONLY_DIR directory and webapp
            (   cd "${SOURCE_DIR}/${BUILD_ONLY_DIR}"; mvn clean install ${MAVEN_OPTION}; \
                cd "${SOURCE_DIR}/${WEBAPP_DIR}";     mvn clean install ${MAVEN_OPTION}) ||
                   fail "Error at build"
        else
            fail "not valid directory ${SOURCE_DIR}/${BUILD_ONLY_DIR}"
        fi
    elif ${BUILD_ONLY_WEBAPP} ; then
        # clean and build only webapp
        (cd "${SOURCE_DIR}/${WEBAPP_DIR}"; mvn clean install ${MAVEN_OPTION}) ||
            fail "Error at build"
    else
        # clean and build
        (cd "$SOURCE_DIR"; mvn clean install ${MAVEN_OPTION}) ||
            fail "Error at build"
    fi
}

function do_copy_targz() {
    # copy TAR.GZ to docker dir
    cp "${SOURCE_DIR}/${TGZ_BASENAME}"*"${TGZ_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
        fail "Error copying ${SOURCE_DIR}/${TGZ_BASENAME}"
}

function do_docker_build() {
    # build Docker container
    (cd "${SOURCE_DIR}/${DOCKER_DIR}" ; \
        ${SUDO} docker build -t ${IMAGE_NAME}:${TAG_NAME} "${SOURCE_DIR}/${DOCKER_DIR}" ) ||
        fail "Error building container"
}



function do_docker_run() {
    kill_previous_container

    set +e
    RESULT=$(curl -o /dev/null --silent --write-out '%{http_code}\n' ${URL})
    set -e
    if [ "${RESULT}" == "000" ]; then
        DOCKER_RUN="${SUDO}docker run -p ${ADD_IP}${CONTAINER_EXPOSED_PORT}:8080 --name ${CONTAINER_NAME} -d ${IMAGE_NAME}:${TAG_NAME}"
        eval ${DOCKER_RUN}
    else 
        fail  "${CONTAINER_EXPOSED_PORT} port is already allocated"
    fi


    # wait a bit to let container start
    # test if URL responds with 200
    time=0
    while ((RESULT!=200))
    do
        set +e
        RESULT=$(curl -o /dev/null --silent --write-out '%{http_code}\n' ${URL})
        set -e
        if [ "${RESULT}" == "200" ]; then
            echo " -------------------------------------------------------"
            echo " ${APP_NAME} is now running ........ HTTP code = ${BOLD}${GREEN}${RESULT}${NORM}"
        else 
            ((time+=1))
            echo " ... ${REV}${time}${NORM} ... loading ${APP_NAME} ..."
            sleep 1
        fi
    done
}

function do_functional_testing() {
    # functional testing
    (cd "${SOURCE_DIR}/testing-tools/tgol-test-scenario"; \
        mvn test \
        -Dadmin.user=me@my-email.org \
        -Dadmin.password=myAsqaPassword \
        -Dhost.location=${URL} \
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

echo " -------------------------------------------------------"
echo " Container .. ${CONTAINER_NAME}"
echo " Image ...... ${IMAGE_NAME}:${TAG_NAME}"
echo " CMD ........ ${DOCKER_RUN}"
echo " -------------------------------------------------------"
echo " Shell ...... ${SUDO}docker exec -ti ${CONTAINER_NAME} /bin/bash"
echo " Log ........ ${SUDO}docker logs -f  ${CONTAINER_NAME}"
echo " URL ........ ${BOLD}${GREEN}${URL}${NORM}"

