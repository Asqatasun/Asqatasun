#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":c:r:" opt; do
  case $opt in
    c) ContractId="$OPTARG" ;;
    r) Refs="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$ContractId" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -c <ContractId> -r \"[...]\" "
        echo "Add referentials to the given contract"
        echo "  The \"r\" option represents the referential and can take several values from :"
        echo "     - r1 -> Accessiweb 2.1"
        echo "     - r2 -> Seo "
	exit 0
fi

for ref in $Refs;do
   if [ $ref = "r1" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call add_ref_to_contract_from_contract_id($ContractId, 1);
        "
   fi
   if [ $ref = "r2" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call add_ref_to_contract_from_contract_id($ContractId, 2);
        "
   fi
done