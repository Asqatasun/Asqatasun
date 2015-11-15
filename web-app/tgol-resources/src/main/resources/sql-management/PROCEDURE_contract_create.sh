#!/bin/bash

# 2015-11-14 mfaure

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 inserts the sql stored procedure "contract_create"

usage: $0 [MANDATORY_ARGS] [OPTIONS]

MANDATORY_ARGS:

    --database-user <db-user>       MANDATORY Asqatasun database user
    --database-passwd <db-passwd>   MANDATORY Asqatasun database password
    --database-db <db-name>         MANDATORY Asqatasun database db
    --database-host <db-host>       MANDATORY Asqatasun database host

OPTIONS:

    -h | --help         Show this message     

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt \
    -o h \
    --long help,database-user:,database-passwd:,database-db:,database-host: \
    -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

declare HELP=false

declare DB_USER
declare DB_PASSWD
declare DB_NAME
declare DB_HOST

declare MY_PROCEDURE_FILE="PROCEDURE_contract_create.sql"

while true; do
  case "$1" in
    -h | --help )       HELP=true; shift ;;
    --database-user )   DB_USER="$2"; shift 2 ;;
    --database-passwd ) DB_PASSWD="$2"; shift 2 ;;
    --database-db )     DB_NAME="$2"; shift 2 ;;
    --database-host )   DB_HOST="$2"; shift 2 ;;
    * ) break ;;
  esac
done

# Mandatory arguments
if [[ 
    -z "$DB_USER" || \
    -z "$DB_PASSWD" || \
    -z "$DB_NAME" || \
    -z "$DB_HOST" || \
    "$HELP" == "true" 
    ]]; then
    usage
fi

#############################################
# Do the actual job: create contract
#############################################
sed -i \
    -e "s#\$myDatabaseName#$DB_NAME#" \
    -e "s#\$myDatabaseUser#$DB_USER#" \
    $MY_PROCEDURE_FILE

cat $MY_PROCEDURE_FILE | \
    mysql --user="$DB_USER" \
          --password="$DB_PASSWD"\
          --host="$DB_NAME" \
          --database="$DB_HOST"