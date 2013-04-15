#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":l:u:r:" opt; do
  case $opt in
    l) ContractLabel="$OPTARG" ;;
    u) UserEmail="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$ContractLabel" ] || [ -z "$UserEmail" ] ; then
	echo "Usage $0 -l <ContractLabel> -u <UserEmail> "
        echo "Delete contract with the label for the given user"
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
    call delete_contract_from_label(\"$ContractLabel\", \"$UserEmail\");
"