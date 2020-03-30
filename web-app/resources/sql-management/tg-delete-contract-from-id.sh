#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":c:" opt; do
  case $opt in
    c) ContractId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$ContractId" ]; then
	echo "Usage $0 -c <ContractId> "
        echo "Delete the contract with the given Id"
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
    call delete_contract_from_id($ContractId);
"