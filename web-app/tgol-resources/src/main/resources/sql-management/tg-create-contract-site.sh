#!/bin/bash

# 20120201 mfaure

# Db authentication
DbUser=
DbUserPasswd=
DbName=

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
while getopts ":l:w:u:" opt; do
  case $opt in
    l) SiteLabel="$OPTARG" ;;
    w) URL="$OPTARG" ;;
    u) UserId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$UserId" ]; then
        echo "Usage $0 -l <SiteLabel> -w <FQDN-url> -u <UserId>"
        exit -1
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
use tanaguru;
insert into TGSI_CONTRACT (Label, Url, Begin_Date, End_Date, USER_Id_User, PRODUCT_Id_Product) values (\"$SiteLabel\", \"$URL\", date(now()), date_add(date(now()), interval 1 year), \"$UserId\", \"2\");"
