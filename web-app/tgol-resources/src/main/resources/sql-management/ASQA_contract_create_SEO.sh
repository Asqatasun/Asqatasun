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

    -m <maxPages>   Max number of crawled pages for a site-audit
    -h | --help     Show this message     

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o c:hw:u:m: --long database-user:,database-passwd:,database-db:,database-host:,help -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

@@@TODO continue the Work In Progress from here

declare CONF_DIR
declare PURGE=false
declare HELP=false

while true; do
  case "$1" in
    -c | --conf-dir )   CONF_DIR="$2"; shift 2 ;; 
    -p | --purge )      PURGE=true; shift ;;
    -h | --help )       HELP=true; shift ;;
    * ) break ;;
  esac
done


if [[ -z "$CONF_DIR" || "$HELP" == "true" ]]; then
    usage
fi

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