#!/bin/bash

# 20120824 jkowalczyk

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":u:f:" opt; do
  case $opt in
    u) UserEmail="$OPTARG" ;;
    f) Functs="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$UserEmail" ] || [ -z "$Functs" ]; then
	echo "Usage $0 -u <UserEmail> -f \"[...]\" "
        echo "Remove functionalities to all the contracts of the given user"
        echo "  The \"f\" option represents the functionalities to add and can take several values from :"
        echo "     - f1 -> Up to 10 pages Audit"
        echo "     - f2 -> Site Audit "
        echo "     - f3 -> File upload Audit "
        echo "     - f4 -> Scenario Audit "
	exit 0
fi

for funct in $Functs;do
   if [ $funct = "f1" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_funct_to_contract_from_user_email(\"$UserEmail\", 1);
        "
   fi
   if [ $funct = "f2" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_funct_to_contract_from_user_email(\"$UserEmail\", 2);
        "
   fi
   if [ $funct = "f3" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_funct_to_contract_from_user_email(\"$UserEmail\", 3);
        "
   fi
   if [ $funct = "f4" ];
     then 
        mysql -u $DbUser -p$DbUserPasswd $DbName -e "
        call remove_funct_to_contract_from_user_email(\"$UserEmail\", 4);
        "
   fi
done