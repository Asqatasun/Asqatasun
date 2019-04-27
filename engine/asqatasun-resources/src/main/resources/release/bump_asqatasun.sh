#!/bin/bash

set -o errexit

TEMP=`getopt -o acpt --long commit,push,tag,automerge,source-dir:,from-version:,to-version:,back-to-snapshot \
             -n 'javawrap' -- "$@"`

if [[ $? != 0 ]] ; then echo "Terminating..." >&2 ; exit 1 ; fi

usage () {
    echo 'usage: ./bump_asqatasun.sh [OPTIONS]...'
    echo ''
    echo '  --from-version <arg>         MANDATORY : The version number to modify.'
    echo '  --to-version <arg>           MANDATORY : The new version number to apply.'
    echo '  --source-dir <arg>           OPTIONAL : Path to the source directory (must be absolute). If not provided, Github repos is used.'
    echo '  -a, --automerge              OPTIONAL : Auto merge from develop to master before bumping.'
    echo '  -c, --commit                 OPTIONAL : Commit automatically modifications.'
    echo '  -p, --push                   OPTIONAL : Push automatically commit on remote.'
    echo '  -t, --tag                    OPTIONAL : Tag automatically version from --to-version arg.'
    echo '  --back-to-snapshot           OPTIONAL : need when switching back to -SNAPSHOT version'
    echo ''
    echo ''
    exit 2
}

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare AUTOMERGE=false
declare COMMIT=false
declare TAG=false
declare PUSH=false
declare SOURCE_DIR=
declare FROM_VERSION=
declare TO_VERSION=
declare RULES_FROM_VERSION=
declare RULES_TO_VERSION=
declare BACK_TO_SNAPSHOT=

while true; do
  case "$1" in
    -a | --automerge ) AUTOMERGE=true; shift ;;
    -c | --commit ) COMMIT=true; shift ;;
    -p | --push ) PUSH=true; shift ;;
    -t | --tag ) TAG=true; shift ;;
    --source-dir ) SOURCE_DIR="$2"; shift 2 ;;
    --from-version ) FROM_VERSION="$2"; shift 2 ;;
    --to-version ) TO_VERSION="$2"; shift 2 ;;
    --back-to-snapshot ) BACK_TO_SNAPSHOT=true; shift ;;
    -- ) shift; break ;;
    * ) break ;;
  esac
done

#############################################
# check mandatory options
#############################################

if [[ "$FROM_VERSION" = "" ]]
then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi
if [[ "$TO_VERSION" = "" ]]
then
    echo ''
    echo 'Mandatory option is missing'
    echo ''
    usage
fi

####################################################################
# change directory to local repos or download it to temporary folder
####################################################################

if [[ "${SOURCE_DIR}" ]]; then
    # Be on develop branch when switching back to -SNAPSHOT release, either way be on master
    if [[ "${BACK_TO_SNAPSHOT}" ]]; then
        git checkout develop
    else
        git checkout master
    fi
else
    cd /tmp
    git clone git@github.com:Asqatasun/Asqatasun.git
    cd Asqatasun
    git checkout origin/develop
    git checkout -b develop
    git checkout master
    # TODO manage case when BACK_TO_SNAPSHOT is true
fi

############
# Auto merge
############

if [[ "$AUTOMERGE" = true ]] ; then
    git merge origin/develop
fi

################
# Effective bump
################

POM_PERL_COMMAND="s!<version>$FROM_VERSION</version>!<version>$TO_VERSION</version>!o"
POM_PERL_COMMAND2="s!<asqatasunVersion>$FROM_VERSION</asqatasunVersion>!<asqatasunVersion>$TO_VERSION</asqatasunVersion>!o"
CONF_PERL_COMMAND="s!asqatasunVersion=$FROM_VERSION!asqatasunVersion=$TO_VERSION!o"
SH_PERL_COMMAND="s!$FROM_VERSION!$TO_VERSION!o"
DOCKER_PERL_COMMAND="s!ASQA_RELEASE=\"$FROM_VERSION\"!ASQA_RELEASE=\"$TO_VERSION\"!o"
ANSIBLE_PERL_COMMAND="s!$FROM_VERSION!$TO_VERSION!o"

echo 'bumping context with version' $TO_VERSION
find . -name "pom.vm"  -exec perl -pi -e $POM_PERL_COMMAND {} \;
find . -name "pom.xml" -exec perl -pi -e $POM_PERL_COMMAND {} \;
find . -name "pom.xml" -exec perl -pi -e $POM_PERL_COMMAND2 {} \;
find . -name "install.sh" -exec perl -pi -e $SH_PERL_COMMAND {} \;
find . -name "asqatasun.conf" -exec perl -pi -e $CONF_PERL_COMMAND {} \;
find . -name "Dockerfile" -exec perl -pi -e $DOCKER_PERL_COMMAND {} \;
find . -name ".travis.yml" -exec perl -pi -e $DOCKER_PERL_COMMAND {} \;
find ansible/asqatasun/defaults/ -name "main.yml" -exec perl -pi -e $ANSIBLE_PERL_COMMAND {} \;
echo 'bumped context with version' $TO_VERSION

#########################################
# Update  contributors.txt 
#########################################

echo "Commits    Contributors"                               > contributors.txt 
echo "--------------------------"                           >> contributors.txt 
git shortlog -s -n                                          >> contributors.txt 
echo ''                                                     >> contributors.txt 
echo ''                                                     >> contributors.txt 
echo "---- Release $TO_VERSION ----------------------"      >> contributors.txt 
echo ''                                                     >> contributors.txt 

#########################################
# Automatic Commit with generated message
#########################################

if [[ "${BACK_TO_SNAPSHOT}" ]]; then
    COMMIT_MESSAGE="Switch back to $TO_VERSION"
else
    COMMIT_MESSAGE="Release $TO_VERSION"
fi

if [[ "$COMMIT" = true ]] ; then
    echo 'committing all files with message : ' ${COMMIT_MESSAGE}
    find . -name "pom.xml" | xargs git add -u
    find . -name "pom.vm"  | xargs git add -u
    find . -name "Dockerfile" | xargs git add -u
    git add **/install.sh 
    git add **/asqatasun.conf
    git add ansible/asqatasun/defaults/main.yml
    git add contributors.txt 
    git commit -m "${COMMIT_MESSAGE}"
    echo 'committed all files with message : ' ${COMMIT_MESSAGE}
fi

###############
# Automatic tag
###############

if [[ "$TAG" = true ]] ; then
    MY_TAG="v$TO_VERSION"
    echo 'tagging new ' $MY_TAG 'tag.'
    git tag -a $MY_TAG -m "$MY_TAG"
    echo 'tagged new ' $MY_TAG 'tag.'
fi

##########################################
# Automatic push to $BRANCH_NAME + $MY_TAG
##########################################

if [[ "$PUSH" = true ]] ; then
    echo 'pushing to master'
    git push origin master
    echo 'pushed to master'
    if [[ "$TAG" = true ]] ; then
        echo 'pushing new ' $MY_TAG 'tag.'
        git push origin $MY_TAG
        echo 'pushed new ' $MY_TAG 'tag.'
    fi
fi

########################
# Clean up temporary dir
########################
#if [[ "${SOURCE_DIR}" = "" ]] ; then
#    cd /tmp
#    rm -fr Asqatasun
#fi

#cd ~

exit 0
