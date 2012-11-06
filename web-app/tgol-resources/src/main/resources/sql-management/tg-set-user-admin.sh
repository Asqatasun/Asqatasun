#!/bin/bash

# 20111102 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":e:" opt; do
  case $opt in
    e) Email="$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$Email" ] ; then
	echo "Usage $0 -e <Email> "
        echo "Set given user admin"
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
UPDATE TGSI_USER SET ROLE_Id_Role=3 WHERE Email1 like $Email;
"