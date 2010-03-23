#!/bin/bash
# koj 20100223

set -e

RESOURCES_DIR=../resources
DOWNLOAD_DIR=../downloaded-pages

if [ ! -d $DOWNLOAD_DIR ]; then
  mkdir -p $DOWNLOAD_DIR
fi

LIST_FILES="$RESOURCES_DIR/*.txt"
DATE_SUFFIX=`date '+%Y%m%d'`

for j in ${LIST_FILES}; do
  #echo $j
  file=${j##*/}
  base=${file%%.*}
  base=${base#*-}
 
  LIST_ELEMENTS_DOWNLOAD_DIR="$DOWNLOAD_DIR/$base"
  mkdir -p $LIST_ELEMENTS_DOWNLOAD_DIR
  
while IFS=\; read file_name address
  do
    echo -e "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
    echo -e "$j \t $address \t $file_name"

    wget -o /dev/null -P /tmp/$file_name-$DATE_SUFFIX $address  
    MAIN_FILE_NAME=`ls /tmp/$file_name-$DATE_SUFFIX/`
    rm -fr /tmp/$file_name-$DATE_SUFFIX
    sleep 1

    wget -T 30 -p --convert-links -nH -nd -o /dev/null -P $LIST_ELEMENTS_DOWNLOAD_DIR/$file_name-$DATE_SUFFIX $address  || echo ""
    if [ $MAIN_FILE_NAME != "index.html" ] 
      then mv -f $LIST_ELEMENTS_DOWNLOAD_DIR/$file_name-$DATE_SUFFIX/$MAIN_FILE_NAME $LIST_ELEMENTS_DOWNLOAD_DIR/$file_name-$DATE_SUFFIX/index.html
    fi
  done < ${j}
done
