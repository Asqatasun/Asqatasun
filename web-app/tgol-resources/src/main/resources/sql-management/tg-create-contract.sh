#!/bin/bash

# 20111102 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=tanaguru
DbUserPasswd=t4n4guru
DbName=tanaguruProd

while getopts ":l:w:u:f:r:" opt; do
  case $opt in
    l) SiteLabel="$OPTARG" ;;
    w) URL="$OPTARG" ;;
    u) UserId="$OPTARG" ;;
    f) Functs="$OPTARG" ;;
    r) Refs="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

echo $SiteLabel 
echo $URL
echo $Functs 
echo $Refs
echo "stop"
if [ -z "$SiteLabel" ] || [ -z "$URL" ] || [ -z "$UserId" ] || [ -z "$Functs" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -l <SiteLabel> -w <FQDN-url> -u <UserId> -f \"[...]\" -r \"[...]\" "
        echo "  The \"f\" option represents the functionalities and can take several values from :"
        echo "     - f1 -> Up to 10 pages Audit"
        echo "     - f2 -> Site Audit "
        echo "     - f3 -> File upload Audit "
        echo "  The \"r\" option represents the referential and can take several values from :"
        echo "     - r1 -> Accessiweb 2.1"
        echo "     - r2 -> Seo "
	exit 0
fi

ref1=NULL;
ref2=NULL;
for ref in $Refs;do
   if [ $ref = "r1" ];
     then 
        ref1=1;
   fi
   if [ $ref = "r2" ];
     then 
        ref2=2;
   fi
done

funct1=NULL;
funct2=NULL;
funct3=NULL;
for funct in $Functs;do
   if [ $funct = "f1" ];
     then 
        funct1=1;
   fi
   if [ $funct = "f2" ];
     then 
        funct2=2;
   fi
   if [ $funct = "f3" ];
     then 
        funct3=3;
   fi
done   

echo $ref1;
echo $ref2;
echo $funct1;
echo $funct2;
echo $funct3;
echo "create_contract($UserId, "$SiteLabel", "$URL", $funct1, $funct2, $funct3, $ref1, $ref2);"
mysql -u $DbUser -p$DbUserPasswd $DbName -e "
call create_contract($UserId, \"$SiteLabel\", \"$URL\", $ref1, $ref2, $funct1, $funct2, $funct3);
"