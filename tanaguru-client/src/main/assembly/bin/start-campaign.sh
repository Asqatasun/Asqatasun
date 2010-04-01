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

while getopts o: option
do
 case $option in
  o)
   echo "option offline with the following path : $OPTARG"
   if [ -d $OPTAGR ] 
     then 
       OFFLINE_PATH=$OPTARG 
     else
       echo "The path given in argument does not exit" 
       exit 1 
   fi 
   ;;
  \?) 
   exit 1
   ;;
 esac
done

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
DATE_SUFFIX=`date '+%Y%m%d-%Hh%Mm%S'`

RESULT_DIR="$RESULT_MAIN_DIR/test-campaign-$DATE_SUFFIX"
if [ ! -d $RESULT_DIR ]; then
  mkdir $RESULT_DIR
  mkdir $RESULT_DIR/failed
fi

if [ $OFFLINE_PATH ] 
  then 
    LIST_SITES=`find $OFFLINE_PATH -name "index.html"`

    for j in ${LIST_SITES}; do
      base=${j%/*}
      base=${base%/*}
      base=${base##*/}
      LIST_ELEMENTS_RESULT_DIR="$RESULT_DIR/$base"
      
      if [ ! -d $LIST_ELEMENTS_RESULT_DIR ]; then
         mkdir $LIST_ELEMENTS_RESULT_DIR
         mkdir $LIST_ELEMENTS_RESULT_DIR/passed
         mkdir $LIST_ELEMENTS_RESULT_DIR/failed
      fi

      file_name=${j%/*}
      file_name=${file_name##*/}
      
      echo -e "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
      echo "Testing ${j} "
	
      FILE_NAME_RESULT="$LIST_ELEMENTS_RESULT_DIR/$file_name.txt"
      $LAUNCH_TANAGURU $j 1>$LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt 2>$LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt \
        	&& echo "OK" \
        	|| echo "Boum !"
        var=` tail -1 $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt | grep mark | wc -l ` 
        if [ $var -eq 1 ]
          then 
            rm -f $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt
            echo "see $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt for details"
          else
            rm -f $LIST_ELEMENTS_RESULT_DIR/passed/$file_name.txt
            cp $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt $RESULT_DIR/failed
            echo "see $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt for details"
        fi
 
        echo -e ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
    done
  else
    LIST_FILES="$RESOURCES_DIR/*.txt*" 
    echo $LIST_FILES
    for j in ${LIST_FILES}; do
      file=${j##*/}

      # toggle comments to have topic directories created (here *AND* in the while loop)
      #base=${file%%.*}
      #base=${base#*-}
      #LIST_ELEMENTS_RESULT_DIR="$RESULT_DIR/$base"
      #mkdir -p $LIST_ELEMENTS_RESULT_DIR
      #mkdir -p $LIST_ELEMENTS_RESULT_DIR/passed
      #mkdir -p $LIST_ELEMENTS_RESULT_DIR/failed
 
      while IFS=\; read file_name address
      do
        LIST_ELEMENTS_RESULT_DIR="$RESULT_DIR/$file_name"
        mkdir -p $LIST_ELEMENTS_RESULT_DIR/passed
        mkdir -p $LIST_ELEMENTS_RESULT_DIR/failed

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
            cp $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt $RESULT_DIR/failed
            echo "see $LIST_ELEMENTS_RESULT_DIR/failed/$file_name.txt for details"
        fi
 
        echo -e ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
      done < ${j}
    done
fi
