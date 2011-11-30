#!/bin/bash

# 20111102 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":l:w:u:p:" opt; do
  case $opt in
    l) SiteLabel="$OPTARG" ;;
    w) URL="$OPTARG" ;;
    u) UserId="$OPTARG" ;;
    p) ProductId="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$SiteLabel" ] || [ -z "$URL" ] || [ -z "$UserId" ] || [ -z "$ProductId" ]; then
	echo "Usage $0 -l <SiteLabel> -w <FQDN-url> -u <UserId> -p <ProductId> "
        echo "ProductId=1 -> Up to 10 pages Audit"
        echo "ProductId=2 -> Site Audit "
        echo "ProductId=7 -> File upload Audit "
	exit 0
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
insert into TGSI_CONTRACT (Label, Url, Begin_Date, End_Date, USER_Id_User, PRODUCT_Id_Product) values (\"$SiteLabel\", \"$URL\", date(now()),
date_add(date(now()), interval 1 year), \"$UserId\", \"$ProductId\");
"