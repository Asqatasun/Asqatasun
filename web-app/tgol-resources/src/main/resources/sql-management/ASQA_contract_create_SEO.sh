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

    -c <contract-name>   MANDATORY Name of contract as displayed in Asqatasun interface
    -w <website-url>     MANDATORY Url of the site to be audited (Fully Qualified Domain Name)
    -u <user-id>         MANDATORY Id of the user the contract is bound to
    
    --database-user <db-user>       MANDATORY Asqatasun database user
    --database-passwd <db-passwd>   MANDATORY Asqatasun database password
    --database-db <db-name>         MANDATORY Asqatasun database db
    --database-host <db-host>       MANDATORY Asqatasun database host

OPTIONS:

    -m <max-pages>   Max number of crawled pages for a site-audit
    -h | --help     Show this message     

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o hc:w:u:m: --long database-user:,database-passwd:,database-db:,database-host:,help -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare HELP=false
declare CONTRACT_NAME
declare WEBSITE_URL
declare USER_ID
declare DB_USER
declare DB_PASSWD
declare DB_NAME
declare DB_HOST
declare MAX_PAGES=NULL

while true; do
  case "$1" in
    -h | --help )       HELP=true; shift ;;
    -c )                CONTRACT_NAME="$2"; shift 2 ;; 
    -w )                WEBSITE_URL="$2"; shift 2 ;; 
    -u )                USER_ID="$2"; shift 2 ;; 
    -m )                MAX_PAGES="$2"; shift 2 ;; 
    --database-user )   DB_USER="$2"; shift 2 ;;
    --database-passwd ) DB_PASSWD="$2"; shift 2 ;;
    --database-db )     DB_NAME="$2"; shift 2 ;;
    --database-host )   DB_HOST="$2"; shift 2 ;;
    * ) break ;;
  esac
done


if [[ -z "$CONTRACT_NAME" || \
    -z "$WEBSITE_URL" || \
    -z "$USER_ID" || \
    -z "$DB_USER" || \
    -z "$DB_PASSWD" || \
    -z "$DB_NAME" || \
    -z "$DB_HOST" || \
    "$HELP" == "true" ]]; then
    usage
fi

#############################################
# Do the actual job: create contract
#############################################

mysql --user="$DB_USER" \
    --password="$DB_PASSWD" \
    --database="$DB_NAME" \
    --host="$DB_HOST" \
    -e "call contract_create($USER_ID, \"$CONTRACT_NAME\", \"$WEBSITE_URL\", \"SEO\", 0, 1, 0, 0, 0, $MAX_PAGES);"