#!/bin/bash

# 20111102 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":e:p:l:f:" opt; do
  case $opt in
    e) Email="$OPTARG" ;;
    p) Passwd="$OPTARG" ;;
    l) LastName="$OPTARG" ;;
    f) FirstName="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$Email" ] || [ -z "$Passwd" ] || [ -z "$LastName" ] || [ -z "$FirstName" ]; then
	echo "Usage $0 -e <Email> -p <password> -l <LastName> -f <FirstName> "
	exit -1
fi

Md5pwd=$(/usr/bin/java -jar ./Passwdmd5/jacksum.jar -a md5 -q "txt:${Passwd}")
Md5pwd=$(echo $Md5pwd | cut -c1-32)

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
insert into TGSI_USER (Email1, Password, Name, First_Name, ROLE_Id_Role) values (\"$Email\", \"$Md5pwd\", \"$LastName\", \"$FirstName\", \"2\");
select Id_User, Email1, Password, Name, First_Name from TGSI_USER where Email1=\"$Email\" and Password=\"$Md5pwd\";
"