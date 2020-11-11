#!/bin/bash

set -o errexit

TEMP=$(getopt -o cpt --long branch:,commit,push,tag,source-dir:,from-version:,to-version:,back-to-snapshot \
    -n 'javawrap' -- "$@")

if [[ $? != 0 ]]; then
    echo "Terminating..." >&2
    exit 1
fi

usage() {
    echo 'usage: ./bump_asqatasun_v4.sh [OPTIONS]...'
    echo ''
    echo '  --from-version <arg>   MANDATORY: Version number to modify.'
    echo '  --to-version   <arg>   MANDATORY: New version number to apply.'
    echo '  --branch       <arg>   MANDATORY: Name of the release branch to checkout.'
    echo ''
    echo '  --source-dir   <arg>   OPTIONAL: Absolute path to the source directory. If not provided, Github repos is used.'
    echo '  -c, --commit           OPTIONAL: Commit automatically modifications.'
    echo '  -p, --push             OPTIONAL: Push automatically commit on remote.'
    echo '  -t, --tag              OPTIONAL: Tag automatically version from --to-version arg.'
    echo '  --back-to-snapshot     OPTIONAL: Triggers the switch back to "-SNAPSHOT" version'
    echo ''
    exit 2
}

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare COMMIT=false
declare TAG=false
declare PUSH=false
declare SOURCE_DIR=
declare BRANCH=
declare FROM_VERSION=
declare TO_VERSION=
declare BACK_TO_SNAPSHOT=

while true; do
    case "$1" in
    -c | --commit)
        COMMIT=true
        shift
        ;;
    -p | --push)
        PUSH=true
        shift
        ;;
    -t | --tag)
        TAG=true
        shift
        ;;
    --source-dir)
        SOURCE_DIR="$2"
        shift 2
        ;;
    --from-version)
        FROM_VERSION="$2"
        shift 2
        ;;
    --to-version)
        TO_VERSION="$2"
        shift 2
        ;;
    --branch)
        BRANCH="$2"
        shift 2
        ;;
    --back-to-snapshot)
        BACK_TO_SNAPSHOT=true
        shift
        ;;
    --)
        shift
        break
        ;;
    *) break ;;
    esac
done

# Check mandatory options
if [[ "${FROM_VERSION}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi
if [[ "${TO_VERSION}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi
if [[ "${BRANCH}" == "" ]]; then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi

####################################################################
# Checkout on correct branch
####################################################################
function checkout_source_code() {
    # We release so we have to be on a release branch
    if [[ "${SOURCE_DIR}" ]]; then
        git checkout "${BRANCH}"
    else
        cd /tmp
        git clone git@github.com:Asqatasun/Asqatasun.git
        cd Asqatasun
        git checkout "${BRANCH}"
    fi
}

########################################
# Change version number (effective bump)
########################################
function bump_release() {
    POM_PERL_COMMAND="s!<version>${FROM_VERSION}</version>!<version>${TO_VERSION}</version>!o"
    POM_PERL_COMMAND2="s!<asqatasunVersion>${FROM_VERSION}</asqatasunVersion>!<asqatasunVersion>${TO_VERSION}</asqatasunVersion>!o"
    CONF_PERL_COMMAND="s!asqatasunVersion=${FROM_VERSION}!asqatasunVersion=${TO_VERSION}!o"
    SH_PERL_COMMAND="s!${FROM_VERSION}!${TO_VERSION}!o"
    DOCKER_PERL_COMMAND="s!ASQA_RELEASE=\"${FROM_VERSION}\"!ASQA_RELEASE=\"${TO_VERSION}\"!o"
    ANSIBLE_PERL_COMMAND="s!${FROM_VERSION}!${TO_VERSION}!o"

    echo "Bumping context with version: ${TO_VERSION}"
    find . -name "pom.vm" -exec perl -pi -e "${POM_PERL_COMMAND}" {} \;
    find . -name "pom.xml" -exec perl -pi -e "${POM_PERL_COMMAND}" {} \;
    find . -name "pom.xml" -exec perl -pi -e "${POM_PERL_COMMAND2}" {} \;
    find . -name "install.sh" -exec perl -pi -e "${SH_PERL_COMMAND}" {} \;
    find . -name "asqatasun.conf" -exec perl -pi -e "${CONF_PERL_COMMAND}" {} \;
    find . -name "Dockerfile" -exec perl -pi -e "${DOCKER_PERL_COMMAND}" {} \;
    find . -name ".travis.yml" -exec perl -pi -e "${DOCKER_PERL_COMMAND}" {} \;
    find ansible/asqatasun/defaults/ -name "main.yml" -exec perl -pi -e "${ANSIBLE_PERL_COMMAND}" {} \;
    echo "Bumped context with version: ${TO_VERSION}"
    echo ''
}

#########################################
# Update contributors.txt
#########################################
function update_contributors() {
    {
        echo "Commits    Contributors"
        echo "--------------------------"
        git shortlog -s -n
        echo ''
        echo ''
        echo "---- Release ${TO_VERSION} ----------------------"
        echo ''
    } >contributors.txt
}

#########################################
# Automatic Commit with generated message
#########################################
function commit_files() {
    if [[ "${BACK_TO_SNAPSHOT}" ]]; then
        COMMIT_MESSAGE="Switch back to ${TO_VERSION}"
    else
        COMMIT_MESSAGE="Release ${TO_VERSION}"
    fi

    if [[ "${COMMIT}" == true ]]; then
        echo "Committing all files with message :  ${COMMIT_MESSAGE}"
        find . -name "pom.xml" -exec git add -u {} +
        find . -name "pom.vm" -exec git add -u {} +
        find . -name "Dockerfile" -exec git add -u {} +
        git add web-app/tgol-resources/src/main/resources/installation/install.sh
        git add web-app/tgol-resources/src/main/resources/conf/asqatasun.conf
        git add ansible/asqatasun/defaults/main.yml
        git add contributors.txt
        git commit -m "${COMMIT_MESSAGE}"
        echo "Committed all files with message :  ${COMMIT_MESSAGE}"
        echo ''
    fi
}

###############
# Automatic tag
###############
function add_tag() {
    if [[ "${TAG}" == true ]]; then
        MY_TAG="v${TO_VERSION}"
        git tag -a "${MY_TAG}" -m "${MY_TAG}"
        echo "Added new tag: ${MY_TAG}"
    fi
}

##########################################
# Push branch and tag
##########################################
function push_branch_and_tag() {
    if [[ "${PUSH}" == true ]]; then
        git push origin "${BRANCH}"
        echo "Pushed to branch: ${BRANCH}"
        if [[ "${TAG}" == true ]]; then
            git push origin "${MY_TAG}"
            echo "Pushed new tag: ${MY_TAG}"
        fi
    fi
}

##########################################
# Main tasks
##########################################

checkout_source_code
bump_release
update_contributors
commit_files
add_tag
push_branch_and_tag

exit 0
