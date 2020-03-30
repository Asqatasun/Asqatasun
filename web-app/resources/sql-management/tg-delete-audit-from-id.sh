#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":a:" opt; do
  case $opt in
    a) AuditId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$AuditId" ]; then
	echo "Usage $0 -a <AuditId> "
        echo "Delete the audit and all its related contents with the given Id"
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
    call delete_audit_from_id($AuditId);
"