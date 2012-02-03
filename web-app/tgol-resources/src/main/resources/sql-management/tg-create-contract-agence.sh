#!/bin/bash

# 20111102 mfaure

# Db authentication
DbUser=
DbUserPasswd=
DbName=

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
while getopts ":u:" opt; do
  case $opt in
    u) UserId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$UserId" ]; then
        echo "Usage $0 -u <UserId> "
        exit -1
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
use tanaguru;
insert into TGSI_CONTRACT (Label, Url, Begin_Date, End_Date, USER_Id_User, PRODUCT_Id_Product) values (\"Tanaguru Agency\", \"\", date(now()), date_add(date(now()), interval 1 year), \"$UserId\", \"1\");
"
