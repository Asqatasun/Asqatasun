#!/bin/bash

# start-tanaguru -- startup script for the asqatasun engine service
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
#JDK_DIRS="/usr/lib/jvm/java-1.6.0-openjdk /usr/lib/jvm/java-6-openjdk-i386 /usr/lib/jvm/java-6-openjdk-amd64 /usr/lib/jvm/java-6-sun"
JDK_DIRS="/usr/lib/jvm/java-1.7.0-openjdk /usr/lib/jvm/java-7-openjdk-i386 /usr/lib/jvm/java-7-openjdk-amd64 /usr/lib/jvm/java-7-sun"

# Look for the right JVM to use
for jdir in $JDK_DIRS; do
    if [ -r "$jdir/bin/java" -a -z "${JAVA_HOME}" ]; then
	JAVA_HOME="$jdir"
    fi
done
export JAVA_HOME

##########################################################
# default xmx value is 256 but can be overridden by option
##########################################################
XMX_VALUE=256

# store options in variable before shifting
TANAGURU_OPTIONS=$@

while [[ $# > 1 ]]
do
    key="$1"

    case $key in
      -x|--xmx-value)
          XMX_VALUE="$2"
          shift
      ;;
      *)
        # unknown option
      ;;
    esac
    shift
done

if [[ -n ${XMX_VALUE//[0-9]/} ]]; then
    echo "Xmx value is invalid because it contains letters"
else if test $XMX_VALUE -lt 65
  then 
    # restore default
    XMX_VALUE=256 
  fi 
fi 

##################
# set JAVA_OPTS
# It also looks like the default heap size of 128M is not enough for most cases
# so the maximum heap size is set to 256M.
# Set java.awt.headless=true if you work without X11 display
##################
JAVA_OPTS="-Dlog4j.configuration=file:$TANAGURU_PATH/logs/log4j.properties -Djava.util.logging.config.file=file:$TANAGURU_PATH/logs/logging.properties -Xms64M  -Xmx${XMX_VALUE}M -classpath $CLASSPATH  "
JAVA_BIN="$JAVA_HOME/bin/java "


##################
# LAUNCH_TANAGURU
##################

$JAVA_BIN $JAVA_OPTS org.asqatasun.cli.Tanaguru ${TANAGURU_OPTIONS}