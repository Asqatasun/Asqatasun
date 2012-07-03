#!/bin/bash

# start-tanaguru -- startup script for the tanaguru engine service
# Written by Jérôme Kowalczyk <jk@open-s.com>.

set -e

###################################
# Definition of LOCAL_PATH variable
###################################
TANAGURU_PATH=`dirname $0`
TANAGURU_PATH=$TANAGURU_PATH/..
export TANAGURU_PATH=$TANAGURU_PATH

####################
# Build-in classpath
####################
TESTS_FILE_DIR=$TANAGURU_PATH/conf
LIB_DIR=$TANAGURU_PATH/lib

CLASSPATH=$TESTS_FILE_DIR:/usr/share/java/mysql-connector-java.jar

JARS=$LIB_DIR/*.jar*

for i in ${JARS}; do
       CLASSPATH=$CLASSPATH:${i}
done

##################
# set JAVA_HOME
##################
# The first existing directory is used for JAVA_HOME (if JAVA_HOME is not
# defined in $DEFAULT)
JDK_DIRS="/usr/lib/jvm/java-6-openjdk /usr/lib/jvm/java-6-sun"

# Look for the right JVM to use
for jdir in $JDK_DIRS; do
    if [ -r "$jdir/bin/java" -a -z "${JAVA_HOME}" ]; then
	JAVA_HOME="$jdir"
    fi
done
export JAVA_HOME

##################
# set JAVA_OPTS
# It also looks like the default heap size of 64M is not enough for most cases
# so the maximum heap size is set to 128M.
# Set java.awt.headless=true if you work without X11 display
##################
JAVA_OPTS="-Dlog4j.configuration=$TANAGURU_PATH/logs/log4j.properties -Djava.util.logging.config.file=$TANAGURU_PATH/logs/logging.properties -Xms64M  -Xmx128M -classpath $CLASSPATH  org.opens.tanaguru.cli.Tanaguru"
LAUNCH_TANAGURU="$JAVA_HOME/bin/java $JAVA_OPTS "

##################
# usage function
# TO DO : add options to configure proxy
##################
usage()
{
cat << EOF

Usage : ./bin/tanaguru_scenario.sh [OPTIONS] SCENARIO_FILE_PATH

OPTIONS:
   -h      Show this message
   -O      Path of the output result file
   -l      Audit Level (Default Silver, can be set to Or(Gold Level), Ar(Silver Level) or Bz (Bronze Level)

ARGUMENTS:
   SCENARIO_FILE_PATH : The path of the file that handles the scenario

EOF
}

#####################
# options extractions
#####################
RESULT_PATH_DIR=

#####################
# authorized level values
#####################
GOLD_LEVEL=Or
SILVER_LEVEL=Ar
BRONZE_LEVEL=Bz

while getopts "O:l:h" OPTION
do
    case $OPTION in
        h)
	  usage
	  exit 1
	  ;;
	O)
          RESULT_PATH_DIR=$OPTARG
	  ;;
        l)
          AUDIT_LEVEL=$OPTARG
          ;;
        *)
          usage
          exit 1
          ;;
    esac
done

if [ ! -z $RESULT_PATH_DIR ] && [ ! -d $RESULT_PATH_DIR  ]; then
    echo ""
    echo "The output directory does not exist. Please create it before launching"
    usage
    exit 1
fi

if [ ! -z $AUDIT_LEVEL ] && [ "$AUDIT_LEVEL" != "$GOLD_LEVEL" ] && [ "$AUDIT_LEVEL" != "$SILVER_LEVEL" ] && [ "$AUDIT_LEVEL" != "$BRONZE_LEVEL" ]; then
    echo "The level $AUDIT_LEVEL is invalid"
    usage
    exit 1
fi

if [ ! -z $RESULT_PATH_DIR ] && [ ! -w $RESULT_PATH_DIR  ]; then
    echo ""
    echo "The output directory is not writable."
    usage
    exit 1
fi

if [ $# -lt 1 ] ; then
    usage
    exit 1
elif [ $# -gt 12 ] ; then
    usage
    exit 1
else
    SCENARIO_FILE_PATH=$1
    index=1
    if [ ! -e $SCENARIO_FILE_PATH ] ; then
	usage
        exit 1
    fi
fi

##################
# SET DEFAULT LEVEL
##################
AUDIT_LEVEL=Ar

##################
# LAUNCH_TANAGURU
##################
if [ ! -z $RESULT_PATH_DIR ] ; then
    DATE_SUFFIX=`date '+%Y%m%d-%Hh%Mm%S'`
    file=${3#http://}
    file=${file#https://}
    file=${file#www.}
    file=${file#file:///}
    file=${file%%/*}

    $LAUNCH_TANAGURU scenario $SCENARIO_FILE_PATH $TANAGURU_PATH $AUDIT_LEVEL > $RESULT_PATH_DIR/$file-$DATE_SUFFIX.txt
    echo "see @ $RESULT_PATH_DIR/$file-$DATE_SUFFIX.txt for results"
    exit 0
else
    $LAUNCH_TANAGURU scenario $SCENARIO_FILE_PATH $TANAGURU_PATH $AUDIT_LEVEL
    exit 0
fi