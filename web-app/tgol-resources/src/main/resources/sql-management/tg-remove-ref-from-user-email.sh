#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":u:r:" opt; do
  case $opt in
    u) UserEmail="$OPTARG" ;;
    r) Refs="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$UserEmail" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -u <UserEmail> -r \"[...]\" "
        echo "Remove referentials to all the contracts of the given user"
        echo "  The \"r\" option represents the referential and can take several values from :"
        echo "     - r1 -> Accessiweb 2.1"
        echo "     - r2 -> RGAA 2.2"
        echo "     - r3 -> Accessiweb 2.2"
	exit 0
fi

for ref in $Refs;do
   if [ $ref = "r1" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_user_email(\"$UserEmail\", 1);
        "
   fi
   if [ $ref = "r2" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_user_email(\"$UserEmail\", 3);
        "
   fi
   if [ $ref = "r3" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_ref_to_contract_from_user_email(\"$UserEmail\", 4);
        "
   fi
done