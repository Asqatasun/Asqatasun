#!/bin/bash
# koj 20100223

set -e

LOCAL_PATH=`dirname $0`
LOCAL_PATH=$LOCAL_PATH/..

RESULT_MAIN_DIR=$LOCAL_PATH/results

if [ ! -d $RESULT_MAIN_DIR ]; then
  mkdir $RESULT_MAIN_DIR
fi

##################
#Build-in classpath
##################
TESTS_FILE_DIR=$LOCAL_PATH/conf
LIB_DIR=$LOCAL_PATH/lib

CLASSPATH=$TESTS_FILE_DIR:/usr/share/java/mysql-connector-java.jar

JARS=$LIB_DIR/*.jar*

for i in ${JARS}; do
       CLASSPATH=$CLASSPATH:${i}
done

TEST_SET_FILE_NAME=tests

##################
# set JAVA_OPTIONS
##################
JAVA_HOME=/usr/lib/jvm/java-6-sun
JAVA_OPTS="-Dlog4j.configuration=mylog4j.properties -classpath $CLASSPATH  org.opens.tanaguru.client.FullClientImpl"
LAUNCH_TANAGURU="$JAVA_HOME/bin/java $JAVA_OPTS $TEST_SET_FILE_NAME"

##################
# launch tanaguru
##################
DATE_SUFFIX=`date '+%Y%m%d-%Hh%Mm%S'`
file=${1#http://}
file=${file#https://}
file=${file#www.}
file=${file#file:///}
file=${file%%/*}


echo -e "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
echo "Testing $1 page"
$LAUNCH_TANAGURU "$1" 1>$RESULT_MAIN_DIR/$file-$DATE_SUFFIX-passed.txt 2>$RESULT_MAIN_DIR/$file-$DATE_SUFFIX-failed.txt \
	&& echo "OK" \
	|| echo "Boum !"

var=` tail -1 $RESULT_MAIN_DIR/$file-$DATE_SUFFIX-passed.txt | grep mark | wc -l `

if [ $var -eq 1 ]
then
  rm -f $RESULT_MAIN_DIR/$file-$DATE_SUFFIX-failed.txt
  echo "see @ $RESULT_MAIN_DIR/$file-$DATE_SUFFIX-passed.txt for details"
else
  rm -f $RESULT_MAIN_DIR/$file-$DATE_SUFFIX-passed.txt
echo "see @ $RESULT_MAIN_DIR/$file-$DATE_SUFFIX-failed.txt for details"
fi

echo -e ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"