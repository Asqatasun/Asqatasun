#!/bin/bash

# 2015-11-12 mfaure

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 creates an SEO contract for a given Asqatasun user

usage: $0 [MANDATORY_ARGS] [OPTIONS]

MANDATORY_ARGS:

    -c <contract-name>   MANDATORY Contract name
    -w <website-url>     MANDATORY Url of the site (Fully Qualified Domain Name)
    -u <user-id>         MANDATORY Id of the user the contract is bound to
    
    --database-user <db-user>       MANDATORY Asqatasun database user
    --database-passwd <db-passwd>   MANDATORY Asqatasun database password
    --database-db <db-name>         MANDATORY Asqatasun database db
    --database-host <db-host>       MANDATORY Asqatasun database host

OPTIONS:

    -m <maxPages>       Max number of crawled pages for a site-audit

EOF
    exit 2
}

# For Getopts: very first ":" for error managing, then a ":" after each option requiring an argument
DbUser=
DbUserPasswd=
DbName=

while getopts ":l:w:u:m:" opt; do
  case $opt in
    l) SiteLabel="$OPTARG" ;;
    w) URL="$OPTARG" ;;
    u) UserId="$OPTARG" ;;
    m) maxDoc="$OPTARG" ;;
    :) echo "Missing argument for option -$OPTARG" ;;
    ?) echo "Unkown option $OPTARG" ;;
  esac
done

if [ -z "$SiteLabel" ] || [ -z "$UserId" ] || [ -z "$Functs" ] || [ -z "$Refs" ]; then
	echo "Usage $0 -l <SiteLabel> -w <FQDN-url> -u <UserId>  -m MaxDoc"
        echo "  The \"m\" option represents max authorized document when the site audit functionality is activated"
	exit 0
fi

if [ -z "$maxDoc" ];then
    maxDoc=NULL
fi

mysql -u $DbUser -p$DbUserPasswd $DbName -e "
call contract_create($UserId, \"$SiteLabel\", \"$URL\", "SEO", 0, 1, 0, 0, 0, $maxDoc);
"