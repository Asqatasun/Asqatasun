#!/bin/bash

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument

while getopts ":p:" opt; do
  case $opt in
    p) Passwd="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$Passwd" ]; then
	echo "Usage $0 -p <password>"
	exit -1
fi

/usr/bin/java -jar jacksum.jar -a md5 -q "txt:${1}" 