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


declare SOURCE_DIR
declare DOCKER_DIR
declare HELP=false
declare SKIP_BUILD=false
declare SKIP_BUILD_TEST=false
declare SKIP_COPY=false
declare SKIP_DOCKER_BUILD=false
declare SKIP_DOCKER_RUN=false
declare USE_NEW_DATABASE=false
declare RUN_CONTAINERS_IN_BACKGROUND=false
declare BUILD_ONLY_API_SERVER=false
declare BUILD_ONLY_WEBAPP=false
declare BUILD_ONLY_DIR=false
declare LOG_BUILD=false
declare WEBAPP_DIR="web-app/asqatasun-web-app"
declare API_SERVER_DIR="server/asqatasun-server"
declare CONTAINER_EXPOSED_PORT="8080"


#############################################
# Usage
#############################################
usage () {
    cat <<EOF

  Help documentation for ${BOLD}${SCRIPT}${NORM}
  --------------------------------------------------------------
  This script launches a sequence that:
    - builds ${BOLD}${APP_NAME}${NORM} from sources
    - copy .war and .jar file to docker directory
    - builds new Docker images
    - runs   new Docker containers
  --------------------------------------------------------------
  ${BOLD}${SCRIPT}${NORM} ${BOLD}${GREEN}-s${NORM} <directory> ${BOLD}${GREEN}-d${NORM} <directory> [OPTIONS]
  --------------------------------------------------------------
  ${BOLD}${GREEN}-s${NORM} | --source-dir     <directory> MANDATORY Absolute path to ${APP_NAME} sources directory
  ${BOLD}${GREEN}-d${NORM} | --docker-dir     <directory> MANDATORY Path to directory containing the Dockerfile.
                                    Path must be relative to SOURCE_DIR

  Build options
  --------------------------------------------------------------
  ${BOLD}-r${NORM} | --rebuild-only   <directory> Rebuild only <directory> (relative to SOURCE_DIR), webapp and API server
  ${BOLD}-a${NORM} | --rebuild-only-api           Rebuild only API server (relies on previous build)
  ${BOLD}-w${NORM} | --rebuild-only-webapp        Rebuild only webapp (relies on previous build)
       --skip-build-test            Skip unit tests on Maven build
       --skip-build                 Skip Maven build (relies on previous build, that must exists)
       --skip-copy                  Skip copying .war/.jar (relies on previous .war/.jar, that must exist)
       --log-build                  Log maven build (see: target/*.log)

  Docker options
  --------------------------------------------------------------
  ${BOLD}-n${NORM} | --use-new-database           Use a new empty database
  ${BOLD}-b${NORM} | --background-containers      Run containers in background
       --skip-docker-build          Skip docker build
       --skip-docker-run            Skip docker run

  --------------------------------------------------------------
  ${BOLD}-h${NORM} | --help                      ${REV} Show this help ${NORM}

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:r:awnbh --long source-dir:,docker-dir:,rebuild-only:,rebuild-only-webapp,rebuild-only-api,use-new-database,background-containers,help,log-build,skip-build-test,skip-build,skip-copy,skip-docker-build,skip-docker-run -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "${TEMP}"

while true; do
  case "$1" in
    -s | --source-dir )             SOURCE_DIR="$2";                    shift 2 ;;
    -d | --docker-dir )             DOCKER_DIR="$2";                    shift 2 ;;
    -b | --rebuild-only )           BUILD_ONLY_DIR="$2";                shift 2 ;;
    -w | --rebuild-only-webapp )    BUILD_ONLY_WEBAPP=true;             shift ;;
    -a | --rebuild-only-api )       BUILD_ONLY_API_SERVER=true;         shift ;;
    -h | --help )                   HELP=true;                          shift ;;
    -n | --use-new-database )       USE_NEW_DATABASE=true;              shift ;;
    -b | --background-containers )  RUN_CONTAINERS_IN_BACKGROUND=true;  shift ;;
    --log-build )                   LOG_BUILD=true;                     shift ;;
    --skip-build-test )             SKIP_BUILD_TEST=true;               shift ;;
    --skip-build )                  SKIP_BUILD=true;                    shift ;;
    --skip-copy )                   SKIP_COPY=true;                     shift ;;
    --skip-docker-build )           SKIP_DOCKER_BUILD=true;             shift ;;
    --skip-docker-run )             SKIP_DOCKER_RUN=true;               shift ;;

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
    exit 255
}


#############################################
# Variables
#############################################

API_FILE_BASENAME="server/asqatasun-server/target/asqatasun-server-"
APP_FILE_BASENAME="web-app/asqatasun-web-app/target/asqatasun-web-app-"
API_FILE_EXT=".jar"
APP_FILE_EXT=".war"
ADD_IP=''
URL_TOMCAT="http://localhost:${CONTAINER_EXPOSED_PORT}"
if ${ONLY_LOCALHOST} ; then  
    ADD_IP="127.0.0.1:"; # the ":" at the end is mandatory for docker run
    URL_TOMCAT="http://${ADD_IP}${CONTAINER_EXPOSED_PORT}"
fi
URL_APP="${URL_TOMCAT}/asqatasun"
    # do not add a final slash here (like /asqatasun/)
    # because do_krash_test_campaign() do not support it.


#############################################
# Functions
#############################################

# display a step message
function display_step_msg() {
    echo " -------------------------------------------------------"
    echo " ${1}"
    echo ""
}

# display a step message
function display_small_step_msg() {
    echo " -------------------------------------------------------"
    echo " ${1}"
}

# Check URL (return HTTP code)
function check_URL() {
    local URL="$1"
    set +e
    local RESULT=$(curl -o /dev/null --silent --write-out '%{http_code}\n' "${URL}")
    set -e
    echo "${RESULT}"
}

# test if URL_TOMCAT responds with an HTTP 200 code
function check_Tomcat_loading() {
    local  time=0
    local  RESULT=$(check_URL ${URL_TOMCAT})
    while ((${RESULT}!=200))
    do
        RESULT=$(check_URL ${URL_TOMCAT})
        if [[ "${RESULT}" == "200" ]]; then
            display_step_msg "Tomcat server is now running .......... HTTP code = ${BOLD}${GREEN}${RESULT}${NORM}"
        else
            ((time+=1))
            echo " ... ${REV}${time}${NORM} ... Tomcat server loading ..."
            sleep 1
        fi
    done
}

# test if URL_APP responds with an HTTP 200 code
function check_App_loading() {
    local  time=0
    local  RESULT=$(check_URL ${URL_APP}/)
    if [[ "${RESULT}" == "200" ]]; then
            display_step_msg "Asqatasun     is now running .......... HTTP code = ${BOLD}${GREEN}${RESULT}${NORM}"
    else
        while ((${RESULT}!=200))
        do
            RESULT=$(check_URL ${URL_APP}/)
            if [[ "${RESULT}" == "200" ]]; then
                display_step_msg "${APP_NAME} is now running ....... HTTP code = ${BOLD}${GREEN}${RESULT}${NORM}"
            else
                ((time+=1))
                echo " ... ${REV}${time}${NORM} ... loading ${APP_NAME} ..."
                sleep 1
            fi
        done
     fi
}

function build() {
     MAVEN_OPTION=''
     if ${SKIP_BUILD_TEST} ; then
         MAVEN_OPTION=' -Dmaven.test.skip=true '; # skip unit tests
     fi

    MAVEN_LOG=''
    if ${LOG_BUILD}; then
        MAVEN_LOG=" | tee log_maven.log";
    fi
    BUILD_CMD="mvn clean install ${MAVEN_OPTION} ${MAVEN_LOG}"
    BUILD_DIR=$*
    if [[ -d "${BUILD_DIR}" ]] ; then
        echo "${BUILD_CMD}"
        (cd  "${BUILD_DIR}"; eval ${BUILD_CMD}) || fail "Error at build ${BUILD_DIR}"
    else
        fail "not valid directory ${BUILD_DIR}"
    fi
}

function do_build() {
    display_step_msg "Maven ${BOLD}${GREEN}build${NORM}"
    if [[ -n "$BUILD_ONLY_DIR" && "$BUILD_ONLY_DIR" != "false" ]]  ; then
        # clean and build $BUILD_ONLY_DIR directory, webapp and api server
        build "${SOURCE_DIR}/${BUILD_ONLY_DIR}"
        build "${SOURCE_DIR}/${WEBAPP_DIR}"
        bbuild "${SOURCE_DIR}/${API_SERVER_DIR}"
    elif ${BUILD_ONLY_WEBAPP} ; then
        # clean and build only webapp
        build "${SOURCE_DIR}/${WEBAPP_DIR}"
    elif ${BUILD_ONLY_API_SERVER} ; then
        # clean and build only api server
        build "${SOURCE_DIR}/${API_SERVER_DIR}"
    else
        # clean and build
        build "${SOURCE_DIR}"
    fi
}

# copy webapp file (.war) to docker dir
function do_copy_webappFile() {
    display_small_step_msg "Copy webapp ${BOLD}${GREEN}.war${NORM} to docker directory"
    cp "${SOURCE_DIR}/${APP_FILE_BASENAME}"*"${APP_FILE_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
        fail "Error copying ${SOURCE_DIR}/${APP_FILE_BASENAME}"
}

# copy API file (.jar) to docker dir
function do_copy_apiFile() {
    display_small_step_msg "Copy API ${BOLD}${GREEN}.jar${NORM} to docker directory"
    cp "${SOURCE_DIR}/${API_FILE_BASENAME}"*"${API_FILE_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
        fail "Error copying ${SOURCE_DIR}/${API_FILE_BASENAME}"
}


# build Docker image
function do_docker_build() {
    display_step_msg "Build ${BOLD}${GREEN}Docker${NORM} image"
    echo " ${DOCKER_DIR}";
    (cd "${SOURCE_DIR}/${DOCKER_DIR}" ; \
        docker-compose build) ||
#       docker-compose build --no-cache) ||
        fail "Error building container"
}

# Remove the previous container (stop + rm)
function delete_previous_container() {
    display_step_msg "Remove previous ${BOLD}${GREEN}Docker${NORM} containers"
    echo " ${DOCKER_DIR}";
    (
        cd "${SOURCE_DIR}/${DOCKER_DIR}";
        echo "---> docker-compose rm --stop --force -v"
        set +e;
        docker-compose rm --stop --force -v;
        set -e;
    ) ||  fail "Error removing previous containers"
}


function do_docker_run() {
    if ${USE_NEW_DATABASE} ; then
        delete_previous_container
    fi

    DOCKER_COMPOSE_OPTIONS=""
    if ${RUN_CONTAINERS_IN_BACKGROUND} ; then
        DOCKER_COMPOSE_OPTIONS=" --detach "
    fi

    display_step_msg "Loading ${BOLD}${GREEN}Docker${NORM} container"
    cd "${SOURCE_DIR}/${DOCKER_DIR}"
    echo " ${DOCKER_DIR}"
    echo "---> docker-compose stop"
    docker-compose stop
    DOCKER_RUN="docker-compose up ${DOCKER_COMPOSE_OPTIONS}"
    echo "---> ${DOCKER_RUN}"
    eval ${DOCKER_RUN}

    if ${RUN_CONTAINERS_IN_BACKGROUND} ; then
        DOCKER_COMPOSE_OPTIONS=" --detach "
    fi

#    RESULT=$(check_URL ${URL_TOMCAT})
#    if [ "${RESULT}" == "000" ]; then
#        display_step_msg "Loading ${BOLD}${GREEN}Docker${NORM} container"
#        cd "${SOURCE_DIR}/${DOCKER_DIR}"
##       DOCKER_RUN="docker-compose up  --build"
##        DOCKER_RUN="docker-compose up"
#        DOCKER_RUN="docker-compose up --detach"
#        eval ${DOCKER_RUN}
#    else
#        fail  "${CONTAINER_EXPOSED_PORT} port is already allocated"
#    fi

    # wait a bit to let container start  --->
#    check_Tomcat_loading
#    check_App_loading
}

function do_maven_log_processing() {
    if [[ ${LOG_BUILD} && "${SKIP_BUILD}" == "false" ]] ; then
        LOG_DIR_SRC="${SOURCE_DIR}"
        if ${BUILD_ONLY_WEBAPP} ; then
            LOG_DIR_SRC="${SOURCE_DIR}/${WEBAPP_DIR}";
        fi
        LOG_DIR_SRC="${LOG_DIR_SRC}";
        LOG_DIR="${LOG_DIR_SRC}/target";
        mkdir -p "${LOG_DIR}"
        mv    "${LOG_DIR_SRC}/log_maven.log" "${LOG_DIR}/"
        echo " -------------------------------------------------------"
        cat "${LOG_DIR}/log_maven.log" | grep "<<< FAILURE! -" | tee "${LOG_DIR}/log_maven-FAIL.log" ;
        cat "${LOG_DIR}/log_maven.log" | grep "WARN"               > "${LOG_DIR}/log_maven-WARM.log" ;
        FAIL=`cat "${LOG_DIR}/log_maven-FAIL.log" | wc -l` ;
        WARM=`cat "${LOG_DIR}/log_maven-WARM.log" | wc -l` ;
        echo " maven FAILURE ... ${FAIL} ";
        echo " maven WARN ...... ${WARM} ";
    fi
}

function user_confirmation() {
    USER_CONFIRMATION=false
    FORCE_YES=false
    if ${FORCE_YES} ;  then
        USER_CONFIRMATION=true
    else
        echo " -------------------------------------------------------"
        read -r -p " ${1} ... [Y/n] " REPLY
        REPLY=${REPLY,,} # tolowern

        if [[ $REPLY =~ ^(yes|y| ) ]] || [[ -z $REPLY ]]; then
            USER_CONFIRMATION=true
        fi
    fi
    echo    # (optional) move to a new line
}



function display_final_msg() {
    if ${RUN_CONTAINERS_IN_BACKGROUND} ; then
        cd "${SOURCE_DIR}/${DOCKER_DIR}"
        display_step_msg "${BOLD}${GREEN}INFO${NORM} ---> ${BOLD}${GREEN}docker-compose config${NORM}"
        docker-compose config
        display_step_msg "${BOLD}${GREEN}INFO${NORM} ---> ${BOLD}${GREEN}docker-compose top${NORM}"
        docker-compose top
        display_step_msg "${BOLD}${GREEN}INFO${NORM} ---> ${BOLD}${GREEN}docker-compose ps${NORM}"
        docker-compose ps

        display_step_msg "${BOLD}${GREEN}INFO${NORM}"
        if [[ ${LOG_BUILD} && "${SKIP_BUILD}" == "false" ]] ; then
            echo " -------------------------------------------------------"
            echo " maven FAILURE ... ${FAIL} ";
            echo " maven WARN ...... ${WARM} ";
        fi
        echo " -------------------------------------------------------"
        echo " Dockerfile .. ${DOCKER_DIR}"
        echo " CMD ......... ${DOCKER_RUN}"

        echo " -------------------------------------------------------"
    #    echo " URL ......... ${BOLD}${GREEN}${URL_APP}${NORM}"

        user_confirmation " Display Docker logs ?"
        if ${USER_CONFIRMATION} ; then
             display_step_msg "${BOLD}${GREEN}INFO${NORM} ---> ${BOLD}${GREEN}docker-compose logs --follow --tail=100 ${NORM}"
             docker-compose logs --follow --tail=100
        fi
    fi
}


#############################################
# Do the actual job
#############################################
if ! ${SKIP_BUILD} ;                    then  do_build;                 fi
if   ${LOG_BUILD};                      then  do_maven_log_processing;  fi
if ! ${SKIP_COPY} ;                     then  do_copy_webappFile;       fi
if ! ${SKIP_COPY} ;                     then  do_copy_apiFile;          fi
if ! ${SKIP_DOCKER_BUILD} ;             then  do_docker_build;          fi
if ! ${SKIP_DOCKER_RUN} ;               then  do_docker_run;            fi
if ! ${SKIP_DOCKER_RUN} && ${RUN_CONTAINERS_IN_BACKGROUND} ;
then
    display_final_msg
fi
cd "${SOURCE_DIR}/${DOCKER_DIR}"
URL_APP=`docker-compose config | grep ":8080/tcp" | sed 's/-//g' | sed 's/ //g' | sed 's/:8080\/tcp//g'`
echo "http://${URL_APP}/"
