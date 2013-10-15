#!/bin/bash

# 20111102 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":u:" opt; do
  case $opt in
    u) Email="$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$Email" ] ; then
	echo "Usage $0 -u <Email> "
        echo "Set given user administrator privileges"
	exit -1
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
UPDATE TGSI_USER SET ROLE_Id_Role=3 WHERE Email1 like \"$Email\";
"
