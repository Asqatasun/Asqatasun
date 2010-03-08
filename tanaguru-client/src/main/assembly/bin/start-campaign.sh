#!/bin/bash
# koj 20100223

set -e

LOCAL_PATH=`dirname $0`
LOCAL_PATH=$LOCAL_PATH/..

RESOURCES_DIR=$LOCAL_PATH/resources
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
#find all lists files, extract URLs and launch tanaguru
##################
LIST_FILES="$RESOURCES_DIR/*.txt*"
DATE_SUFFIX=`date '+%Y%m%d-%Hh%Mm%S'`

RESULT_DIR="$RESULT_MAIN_DIR/test-campaign-$DATE_SUFFIX"
if [ ! -d $RESULT_DIR ]; then
  mkdir $RESULT_DIR
fi

for j in ${LIST_FILES}; do
  file=${j##*/}
  base=${file%%.*}
  base=${base#*-}
 
  LIST_ELEMENTS_RESULT_DIR="$RESULT_DIR/$base"
  mkdir $LIST_ELEMENTS_RESULT_DIR
  mkdir $LIST_ELEMENTS_RESULT_DIR/passed 
  mkdir $LIST_ELEMENTS_RESULT_DIR/failed
 
  while IFS=\; read file_name address
  do
    echo -e "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
    echo "Testing $address in ${j} file"
	
    FILE_NAME_RESULT="$LIST_ELEMENTS_RESULT_DIR/$file_name.txt"
    $LAUNCH_TANAGURU $address 1>$LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt 2>$LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt \
	&& echo "OK" \
	|| echo "Boum !"
    var=` tail -1 $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt | grep mark | wc -l ` 
    if [ $var -eq 1 ]
    then 
      rm -f $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt
      echo "see $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt for details"
    else
      rm -f $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt
      echo "see $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt for details"
    fi
 
    echo -e ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
  done < ${j}
done
