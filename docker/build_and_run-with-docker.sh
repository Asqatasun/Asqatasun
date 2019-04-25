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
declare FTESTS=false
declare KTESTS=false
declare YES=false
declare SKIP_BUILD=false
declare SKIP_BUILD_TEST=false
declare SKIP_COPY=false
declare SKIP_DOCKER_BUILD=false
declare SKIP_DOCKER_RUN=false
declare USE_SUDO_DOCKER=false
declare ONLY_LOCALHOST=false
declare BUILD_ONLY_WEBAPP=false
declare BUILD_ONLY_DIR=false
declare LOG_BUILD=false
declare WEBAPP_DIR="web-app/asqatasun-web-app"
declare CONTAINER_EXPOSED_PORT="8085"
declare CONTAINER_NAME="asqa"
declare IMAGE_NAME="asqatasun/asqatasun"
declare TAG_NAME=${TIMESTAMP}

# used by --functional-tests option
declare FIREFOX_BIN="/opt/firefox/firefox"

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
    - execute functional tests    (optional)
    - execute krash test campaign (optional)
  --------------------------------------------------------------
  ${BOLD}${SCRIPT}${NORM} ${BOLD}${GREEN}-s${NORM} <directory> ${BOLD}${GREEN}-d${NORM} <directory> [OPTIONS]
  --------------------------------------------------------------
  ${BOLD}${GREEN}-s${NORM} | --source-dir     <directory> MANDATORY Absolute path to ${APP_NAME} sources directory
  ${BOLD}${GREEN}-d${NORM} | --docker-dir     <directory> MANDATORY Path to directory containing the Dockerfile.
                                    Path must be relative to SOURCE_DIR
  ${BOLD}-p${NORM} | --port           <port>      Default value: ${CONTAINER_EXPOSED_PORT}
  ${BOLD}-n${NORM} | --container-name <name>      Default value: ${CONTAINER_NAME}
  ${BOLD}-i${NORM} | --image-name     <name>      Default value: ${IMAGE_NAME}
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
       --log-build                  Log maven build (see: target/*.log)

  ${BOLD}-h${NORM} | --help                      ${REV} Show this help ${NORM}
  ${BOLD}-y${NORM} | --yes                        Skip confirmation for -k and -f options
  ${BOLD}-k${NORM} | --krash-test                 Also execute krashtest campaign. Please check pre-requisites.
  ${BOLD}-f${NORM} | --functional-tests           Also execute functional tests.   Please check pre-requisites.
       --firefox        <path>      Default value: ${FIREFOX_BIN}  for -k and -f options

                                    pre-requisites for "functional tests" and "krash test campaign"
                                    http://doc.asqatasun.org/en/30_Contributor_doc/Testing/

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o s:d:p:n:i:t:b:lwhfky --long source-dir:,docker-dir:,port:,container-name:,image-name:,tag-name:,build-only-dir:,firefox:,only-localhost,build-only-webapp,help,functional-tests,krash-test,yes,log-build,skip-build-test,skip-build,skip-copy,skip-docker-build,skip-docker-run,use-sudo-docker -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "${TEMP}"

while true; do
  case "$1" in
    -s | --source-dir )         SOURCE_DIR="$2";                shift 2 ;;
    -d | --docker-dir )         DOCKER_DIR="$2";                shift 2 ;;
    -p | --port )               CONTAINER_EXPOSED_PORT="$2";    shift 2 ;;
    -n | --container-name )     CONTAINER_NAME="$2";            shift 2 ;;
    -i | --image-name )         IMAGE_NAME="$2";                shift 2 ;;
    -t | --tag-name  )          TAG_NAME="$2";                  shift 2 ;;
    -b | --build-only-dir )     BUILD_ONLY_DIR="$2";            shift 2 ;;
    -w | --build-only-webapp )  BUILD_ONLY_WEBAPP=true;         shift ;;
    -h | --help )               HELP=true;                      shift ;;
    -f | --functional-tests )   FTESTS=true;                    shift ;;
    -k | --krash-test )         KTESTS=true;                    shift ;;
    -y | --yes )                YES=true;                       shift ;;
    -l | --only-localhost )     ONLY_LOCALHOST=true;            shift ;;
    --firefox )                 FIREFOX_BIN="$2";               shift ;;
    --log-build )               LOG_BUILD=true;                 shift ;;
    --skip-build-test )         SKIP_BUILD_TEST=true;           shift ;;
    --skip-build )              SKIP_BUILD=true;                shift ;;
    --skip-copy )               SKIP_COPY=true;                 shift ;;
    --skip-docker-build )       SKIP_DOCKER_BUILD=true;         shift ;;
    --skip-docker-run )         SKIP_DOCKER_RUN=true;           shift ;;
    --use-sudo-docker )         USE_SUDO_DOCKER=true;           shift ;;

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
ADD_IP=''
URL_TOMCAT="http://localhost:${CONTAINER_EXPOSED_PORT}"
if ${ONLY_LOCALHOST} ; then  
    ADD_IP="127.0.0.1:"; # the ":" at the end is mandatory for docker run
    URL_TOMCAT="http://${ADD_IP}${CONTAINER_EXPOSED_PORT}"
fi
URL_APP="${URL_TOMCAT}/asqatasun"
    # do not add a final slash here (like /asqatasun/)
    # because do_krash_test_campaign() do not support it.

SUDO=''
if ${USE_SUDO_DOCKER} ; then   SUDO='sudo '; fi


#############################################
# Functions
#############################################

# display a step message
function display_step_msg() {
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

# Remove the previous container (stop + rm)
function kill_previous_container() {
    display_step_msg "Remove the previous ${BOLD}${GREEN}Docker${NORM} container"
    set +e
    local RUNNING=$(${SUDO} docker inspect --format="{{ .State.Status }}" ${CONTAINER_NAME} 2>/dev/null)
    set -e
    if [[ "${RUNNING}" == "running" ]]; then
        ${SUDO} docker stop ${CONTAINER_NAME}
        ${SUDO} docker rm ${CONTAINER_NAME}
    elif [[ "${RUNNING}" == "exited" ]]; then
        ${SUDO} docker rm ${CONTAINER_NAME}
    fi
}

# test if URL_TOMCAT responds with an HTTP 200 code
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

# test if URL_APP responds with an HTTP 200 code
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
        (cd  "${BUILD_DIR}"; eval ${BUILD_CMD}) || fail "Error at build ${BUILD_DIR}"
    else
        fail "not valid directory ${BUILD_DIR}"
    fi
}

function do_build() {
    display_step_msg "Maven ${BOLD}${GREEN}build${NORM}"
    if [[ -n "$BUILD_ONLY_DIR" && "$BUILD_ONLY_DIR" != "false" ]]  ; then
         # clean and build $BUILD_ONLY_DIR directory and webapp
        build "${SOURCE_DIR}/${BUILD_ONLY_DIR}"
        build "${SOURCE_DIR}/${WEBAPP_DIR}"
    elif ${BUILD_ONLY_WEBAPP} ; then
        # clean and build only webapp
        build "${SOURCE_DIR}/${WEBAPP_DIR}"
    else
        # clean and build
        build "${SOURCE_DIR}"
    fi
}

# copy TAR.GZ to docker dir
function do_copy_targz() {
    display_step_msg "Copy ${BOLD}${GREEN}tar.gz${NORM} to the docker directory"
    cp "${SOURCE_DIR}/${TGZ_BASENAME}"*"${TGZ_EXT}" "${SOURCE_DIR}/${DOCKER_DIR}/" ||
        fail "Error copying ${SOURCE_DIR}/${TGZ_BASENAME}"
}

# build Docker image
function do_docker_build() {
    display_step_msg "Build ${BOLD}${GREEN}Docker${NORM} image"
    (cd "${SOURCE_DIR}/${DOCKER_DIR}" ; \
        ${SUDO} docker build -t ${IMAGE_NAME}:${TAG_NAME} "${SOURCE_DIR}/${DOCKER_DIR}" ) ||
        fail "Error building container"
}

function do_docker_run() {
    kill_previous_container
    RESULT=$(check_URL ${URL_TOMCAT})
    if [ "${RESULT}" == "000" ]; then
        display_step_msg "Loading ${BOLD}${GREEN}Docker${NORM} container"
        DOCKER_RUN="${SUDO}docker run -p ${ADD_IP}${CONTAINER_EXPOSED_PORT}:8080 --name ${CONTAINER_NAME} -d ${IMAGE_NAME}:${TAG_NAME}"
        eval ${DOCKER_RUN}
    else 
        fail  "${CONTAINER_EXPOSED_PORT} port is already allocated"
    fi

    # wait a bit to let container start
    check_Tomcat_loading
    check_App_loading
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


function check_firefox_binary() {

    # FIREFOX_BIN is file
    if [[ ! -f ${FIREFOX_BIN} ]] ;   then
        fail "Error: ${FIREFOX_BIN} does not exist!"
    fi

    # FIREFOX_BIN  is executable by the user
    if [[ ! -x ${FIREFOX_BIN} ]] ;   then
        fail "Error: ${FIREFOX_BIN} is not an executable!"
    fi

    # @@@TODO check firefox version
    # /opt/firefox/firefox --version
}


function user_confirmation() {
    RUN_TESTS=false
    if ${YES} ;  then
        RUN_TESTS=true
    else
        read -r -p " Are you sure? ... [Y/n] " REPLY
        REPLY=${REPLY,,} # tolower
        if [[ $REPLY =~ ^(yes|y| ) ]] || [[ -z $REPLY ]]; then
            RUN_TESTS=true
        fi
    fi
    echo    # (optional) move to a new line
}



# functional testing
function do_functional_testing() {
    display_step_msg "Functional ${BOLD}${GREEN}testing ${NORM}"
    user_confirmation
    if ${RUN_TESTS} ; then
        check_firefox_binary
        (cd "${SOURCE_DIR}/testing-tools/tgol-test-scenario"; \
            mvn test \
                -Dadmin.user=admin@asqatasun.org \
                -Dadmin.password=myAsqaPassword \
                -Dhost.location=${URL_APP}/ \
                -Dfirefox.path=${FIREFOX_BIN}
        )
    fi
}

# Krash test campaign
function do_krash_test_campaign() {
    # @@@TODO fixing the final slash for URL_APP
    display_step_msg "${BOLD}${GREEN}Krash test${NORM} campaign"
    user_confirmation
    if ${RUN_TESTS} ; then
        check_firefox_binary
        (cd "${SOURCE_DIR}/testing-tools/tgol-test-krash-offline"; \
            mvn test \
                -Duser=admin@asqatasun.org \
                -Dpassword=myAsqaPassword \
                -Dhost.location=${URL_APP} \
                -Dcontract.id=3 \
                -Dxvfb.display=":0" \
                -Dwebdriver.firefox.bin=${FIREFOX_BIN}
        )
    fi
}


#############################################
# Do the actual job
#############################################

if ! ${SKIP_BUILD} ;        then  do_build;                 fi
if ! ${SKIP_COPY} ;         then  do_copy_targz;            fi
if ! ${SKIP_DOCKER_BUILD} ; then  do_docker_build;          fi
if ! ${SKIP_DOCKER_RUN} ;   then  do_docker_run;            fi
if   ${LOG_BUILD};          then  do_maven_log_processing;  fi
if   ${FTESTS} ;            then  do_functional_testing;    fi
if   ${KTESTS} ;            then  do_krash_test_campaign;   fi

echo " -------------------------------------------------------"
echo " Container ... ${CONTAINER_NAME}"
echo " Dockerfile .. ${DOCKER_DIR}"
echo " Image ....... ${IMAGE_NAME}:${TAG_NAME}"
echo " CMD ......... ${DOCKER_RUN}"
echo " -------------------------------------------------------"
echo " Shell ....... ${SUDO}docker exec -ti ${CONTAINER_NAME} /bin/bash"
echo " Log ......... ${SUDO}docker logs -f  ${CONTAINER_NAME}"
echo " URL ......... ${BOLD}${GREEN}${URL_APP}${NORM}"

