#!/bin/bash

# 2015-11-14 mfaure

set -o errexit

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 creates an A11Y RGAA-3 contract for a given Asqatasun user on a given website URL

usage: $0 [MANDATORY_ARGS] [OPTIONS]

MANDATORY_ARGS:

    -c <contract-name>   MANDATORY Name of contract as displayed in Asqatasun interface
    -w <website-url>     MANDATORY Url of the site to be audited (Fully Qualified Domain Name)
    -u <user-id>         MANDATORY Id of the user the contract is bound to
    
    --database-user <db-user>       MANDATORY Asqatasun database user
    --database-passwd <db-passwd>   MANDATORY Asqatasun database password
    --database-db <db-name>         MANDATORY Asqatasun database db
    --database-host <db-host>       MANDATORY Asqatasun database host

    *At least* one of those parameters must be present:

    --audit-page        Enable page audit
    --audit-site        Enable site audit
    --audit-file        Enable offline file audit
    --audit-scenario    Enable scenario audit

OPTIONS:

    --audit-manual      Enable manual fulfill of an existing page-audit 
    -m <max-pages>      Max number of crawled pages for a site-audit
    -h | --help         Show this message     

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt \
    -o hc:w:u:m: \
    --long help,database-user:,database-passwd:,database-db:,database-host:,audit-page,audit-site,audit-file,audit-scenario,audit-manual \
    -- "$@"`

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

declare AUDIT_PAGE
declare AUDIT_SITE
declare AUDIT_FILE
declare AUDIT_SCENARIO

declare AUDIT_MANUAL
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
    --audit-page )      AUDIT_PAGE=1; shift ;;
    --audit-site )      AUDIT_SITE=1; shift ;;
    --audit-file )      AUDIT_FILE=1; shift ;;
    --audit-scenario )  AUDIT_SCENARIO=1; shift ;;
    --audit-manual )    AUDIT_MANUAL=1; shift ;;
    * ) break ;;
  esac
done

# Mandatory arguments
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

# Mandatory arguments: at least one type of audit (in page / site / file / scenario) must be present
if [[ -z "$AUDIT_PAGE" && \
    -z "$AUDIT_SITE" && \
    -z "$AUDIT_FILE" && \
    -z "$AUDIT_SCENARIO" ]]; then
    usage
fi

#############################################
# Do the actual job: create contract
#############################################

mysql --user="$DB_USER" \
    --password="$DB_PASSWD" \
    --database="$DB_NAME" \
    --host="$DB_HOST" \
    -e "call contract_create( \
        $USER_ID, \
        \"$CONTRACT_NAME\", \
        \"$WEBSITE_URL\", \
        \"RGAA3\", \
        $AUDIT_PAGE, \
        $AUDIT_SITE, \
        $AUDIT_FILE, \
        $AUDIT_SCENARIO, \
        $AUDIT_MANUAL, \
        $MAX_PAGES) \
        ;"