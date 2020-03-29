#!/bin/bash

# 2017-02-03 mfaure

set -o errexit

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 deletes an audit based on its technical id

usage: $0 [MANDATORY_ARGS] [OPTIONS]

MANDATORY_ARGS:

    -i <audit-id>                   MANDATORY id of the audit to delete
    
    --database-user <db-user>       MANDATORY Asqatasun database user
    --database-passwd <db-passwd>   MANDATORY Asqatasun database password
    --database-db <db-name>         MANDATORY Asqatasun database db
    --database-host <db-host>       MANDATORY Asqatasun database host

OPTIONS:

    -h | --help     Show this message

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o hi: --long database-user:,database-passwd:,database-db:,database-host:,help -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare HELP=false
declare AUDIT_ID
declare DB_USER
declare DB_PASSWD
declare DB_NAME
declare DB_HOST

while true; do
  case "$1" in
    -h | --help )       HELP=true; shift ;;
    -i )                AUDIT_ID="$2"; shift 2 ;;
    --database-user )   DB_USER="$2"; shift 2 ;;
    --database-passwd ) DB_PASSWD="$2"; shift 2 ;;
    --database-db )     DB_NAME="$2"; shift 2 ;;
    --database-host )   DB_HOST="$2"; shift 2 ;;
    * ) break ;;
  esac
done

if [[ -z "$AUDIT_ID" || \
    -z "$DB_USER" || \
    -z "$DB_PASSWD" || \
    -z "$DB_NAME" || \
    -z "$DB_HOST" || \
    "$HELP" == "true" ]]; then
    usage
fi

#############################################
# Do the actual job
#############################################

mysql --user="$DB_USER" \
    --password="$DB_PASSWD" \
    --database="$DB_NAME" \
    --host="$DB_HOST" \
    -e "call delete_audit_from_id($AUDIT_ID)"