#!/bin/bash

# 20131016 mfaure

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":l:w:u:f:r:m:" opt; do
  case $opt in
    l) SiteLabel="$OPTARG" ;;
    w) URL="$OPTARG" ;;
    u) UserId="$OPTARG" ;;
    f) Functs="$OPTARG" ;;
    r) Refs="$OPTARG" ;;
    m) maxDoc="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$SiteLabel" ] || [ -z "$UserId" ] || [ -z "$Functs" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -l <SiteLabel> -w <FQDN-url> -u <UserId> -f \"[...]\" -r \"[...]\" -m MaxDoc"
        echo "  The \"f\" option represents the functionalities and can take several values from :"
        echo "     - f1 -> Up to 10 pages Audit"
        echo "     - f2 -> Site Audit "
        echo "     - f3 -> File upload Audit "
        echo "     - f4 -> Scenario Audit "
        echo "  The \"r\" option represents the referential and can take several values from :"
        echo "     - r1 -> Accessiweb 2.1"
        echo "     - r2 -> RGAA 2.2.1 "
        echo "     - r3 -> Accessiweb 2.2 "
        echo "  The \"m\" option represents max authorized document when the site audit functionality is activated"
	exit 0
fi

if [ -z "$maxDoc" ];then
    maxDoc=NULL
fi

ref1=NULL;
ref2=NULL;
ref3=NULL;
ref4=NULL;
for ref in $Refs;do
   if [ $ref = "r1" ];
     then 
        ref1=1;
   fi
   if [ $ref = "r2" ];
     then 
        ref2=3;
   fi
   if [ $ref = "r3" ];
     then 
        ref3=4;
   fi
done

funct1=NULL;
funct2=NULL;
funct3=NULL;
funct4=NULL;
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
   if [ $funct = "f4" ];
     then 
        funct4=4;
   fi
done   

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
call create_contract($UserId, \"$SiteLabel\", \"$URL\", $ref1, $ref2, $ref3, $ref4, $funct1, $funct2, $funct3, $funct4, $maxDoc);
"