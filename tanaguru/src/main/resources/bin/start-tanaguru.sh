#!/bin/bash

# start-tanaguru -- startup script for the tanaguru engine service
# Written by Jérôme Kowalczyk <jk@open-s.com>.

set -e

##################
# Definition of LOCAL_PATH variable
##################
LOCAL_PATH=`dirname $0`
LOCAL_PATH=$LOCAL_PATH/..

##################
# Creation of results folder
##################
RESULT_MAIN_DIR=$LOCAL_PATH/results
if [ ! -d $RESULT_MAIN_DIR ]; then
  mkdir $RESULT_MAIN_DIR
fi

##################
# Build-in classpath
##################
TESTS_FILE_DIR=$LOCAL_PATH/conf
LIB_DIR=$LOCAL_PATH/lib

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
JAVA_OPTS="-Dlog4j.configuration=logs/log4j.properties -Djava.util.logging.config.file=logs/logging.properties -Xms64M  -Xmx128M -classpath $CLASSPATH  org.opens.tanaguru.cli.Tanaguru"
LAUNCH_TANAGURU="$JAVA_HOME/bin/java $JAVA_OPTS "

##################
# LAUNCH_TANAGURU
##################
DATE_SUFFIX=`date '+%Y%m%d-%Hh%Mm%S'`
file=${1#http://}
file=${file#https://}
file=${file#www.}
file=${file#file:///}
file=${file%%/*}

if [ $# -lt 1 ] ; then
      URL_ARGS='--help'
elif [ $# -gt 10 ] ; then
      URL_ARGS='-tmu'
else
    for i in $*; do
        URL_ARGS=$i';'$URL_ARGS
    done;
fi

$LAUNCH_TANAGURU $URL_ARGS $RESULT_MAIN_DIR/$file-$DATE_SUFFIX.txt