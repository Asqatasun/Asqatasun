#!/bin/bash
# koj 20100409

set -e

##################
# set JAVA_OPTIONS
##################
JAVA_HOME=/usr/lib/jvm/java-6-sun

#######################
# set SELENIUM_JAR_PATH
#######################
SELENIUM_JAR_PATH=$HOME/.m2/repository/org/seleniumhq/selenium/server/selenium-server/1.0.3/selenium-server-1.0.3.jar

if [! -f $SELENIUM_JAR_PATH ]; then
  echo $SELENIUM_JAR_PATH
  echo "Selenium Jar 1.0.3 is not install in maven repository"
  exit 0
fi

########################
# launch selenium server
########################
$JAVA_HOME/bin/java -jar $SELENIUM_JAR_PATH