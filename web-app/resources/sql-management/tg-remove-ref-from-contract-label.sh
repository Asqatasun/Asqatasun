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
    r) Refs="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$ContractLabel" ] || [ -z "$UserEmail" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -l <ContractLabel> -u <UserEmail> -r \"[...]\" "
        echo "Remove referentials to the given contract"
        echo "  The \"r\" option represents the referential and can take several values from :"
        echo "     - r1 -> Accessiweb 2.1"
	exit 0
fi

for ref in $Refs;do
   if [ $ref = "r1" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_contract_label(\"$ContractLabel\", \"$UserEmail\", 1);
        "
   fi
   if [ $ref = "r2" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_contract_label(\"$ContractLabel\", \"$UserEmail\", 3);
        "
   fi
   if [ $ref = "r3" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_contract_label(\"$ContractLabel\", \"$UserEmail\", 4);
        "
   fi
done