#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":u:" opt; do
  case $opt in
    u) UserId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$UserId" ]; then
	echo "Usage $0 -u <UserId> "
        echo "Delete all the audits and all their related contents for the given user identified by its ID"
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
    call clean_up_audit_from_user_email($UserId);
"