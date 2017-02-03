#!/bin/bash

set -o errexit

#############################################
# Init
#############################################

declare prefix="/"

declare database_user=
declare database_passwd=
declare database_db=
declare database_host=

declare omit_cleanup=true
declare quiet=false

declare dirty_database=false
declare dirty_directories=false
declare dirty_webapp=false

declare PKG_DIR=$(pwd)

declare ARCH="i386"

declare -a OPTIONS=(
	database_user
	database_passwd
	database_db
	database_host
)

warn() {
	if [[ ! $quiet ]]; then
            echo "WARNING : $*"
        fi
}

error() {
	echo "ERROR : $*"
	return 1
}

fail_with_usage() {
    echo ""
    echo "FAILURE : $*"
    echo ""
    usage
	exit -1
}

fail() {
    echo ""
    echo "FAILURE : $*"
    echo ""
    exit -1
}

prerequisites() {

	echo ""
	echo "Please verify your configuration meets the *SQL* requirements:"
	echo "http://doc.asqatasun.org/en/10_Install_doc/Asqatasun/Pre-requisites.html"
	echo ""	
	read -p "Are you sure you want to continue installation (yes/no)? " response
	if [[ ! "${response}" == "yes" ]]; then
            fail "Installation is stopped"
        fi
	echo ""	
}

my_sql_insert() {
    # Option management
    if [ ! "$1" ]; then
        fail "my_sql_insert: missing sql file";
    fi
    SQL_FILE="$1"

    if [ ! -f "$SQL_FILE" ]; then
        fail "$SQL_FILE does not exists";
    fi

    # Effective SQL command
    cat ${SQL_FILE} | \
            mysql --user="${database_user}" \
                  --password="${database_passwd}"\
                  --host="${database_host}" \
                  --database="${database_db}" || \
            fail "Unable to run SQL file: ${SQL_FILE}"
}

#############################################
# Usage
#############################################

usage() {
	cat << EOF

Usage : $0 [-h] 
        --database-user <Asqatasun database user> 
        --database-passwd <Asqatasun database password> 
        --database-db <Asqatasun database db> 
        --database-host <database host> 

Installation options :
 --database-user             Database user for Asqatasun
 --database-passwd           Database password for Asqatasun
 --database-db               Database name for Asqatasun
 --database-host             Database hostname (FQDN or local hostname)

Script options :
 -h --help           Display this help message

EOF
}

#############################################
# Option management
#############################################
proceed_cmdline() {
    while [[ "$#" -gt 0 ]]; do
        local var=$(echo ${1#--} | tr '-' '_')
        local isVar=false

        for option in ${OPTIONS[@]}; do
            if [[ "$option" = "$var" ]]; then
                eval $var=$2
                shift 2 || fail_with_usage "Missing argument after $1"
                isVar=true
                break
            fi
        done
        if [[ ! $isVar ]]; then
            case "$1" in
                -h|--help)        usage; exit 0;;
                *) usage; exit 1;;
            esac
        fi
    done
        
}

getvar() {
    echo $1 | sed 's/^-\+//' | tr '-' '_'
}

echo_missing_options() {
    for option in ${OPTIONS[@]}; do
            [[ -z "$(eval echo \${$option})" ]] && \
                    echo -n "--${option/-/_} "
    done
}

proceed_stdin() {
    local missing_options=$(echo_missing_options)
    [[ ! -z $missing_options ]] && fail_with_usage "Missing options : " $missing_options
    for option in ${OPTIONS[@]}; do
        local value=$(eval echo \${$option})
        while [[ -z "$value" ]]; do
            ${quiet} || echo -n "$option : "
            read $option
            value=$(eval echo \${$option})
            echo $value
        done
    done
}

echo_configuration_summary() {
	cat << EOF

Installing Asqatasun with the following configuration :
 - The database user "${database_user}" will be used by Asqatasun
 - The database database "${database_db}" will be used by Asqatasun
 - The database host is "${database_host}"

EOF
}

#############################################
# SQL
#############################################
create_tables() {
    cd "$PKG_DIR/engine/sql"
    my_sql_insert asqatasun-20-create-tables.sql
    my_sql_insert asqatasun-30-insert.sql
    
    cd "$PKG_DIR/web-app/sql"
    my_sql_insert tgol-20-create-tables.sql
    my_sql_insert tgol-30-insert.sql

    cd "$PKG_DIR/rules/sql"
    my_sql_insert 10-rules-resources-insert.sql
    my_sql_insert accessiweb2.2-insert.sql
    my_sql_insert rgaa3.0-insert.sql
    my_sql_insert seo1.0-insert.sql
    my_sql_insert rgaa3.2016-insert.sql
}

#############################################
# SQL Scripts & procedure
#############################################
add_procedures() {
    cd "$PKG_DIR/web-app/sql-management"
    my_sql_insert PROCEDURE_ACT_list_running_acts.sql
    my_sql_insert PROCEDURE_AUDIT_last_audits.sql
    my_sql_insert PROCEDURE_AUDIT_delete_from_id.sql
    my_sql_insert PROCEDURE_CONTRACT_creation.sql
}

# TODO copy associated shell scripts (but where ? /usr/local/bin ?)

#############################################
# Finish
#############################################
echo_installation_summary() {
	cat << EOF

Well done, your SQL for Asqatasun is installed !
Please proceed with pre-requisites.sh and install.sh
EOF
}

#############################################
# main
#############################################
main() {
    # get options
    proceed_cmdline "${@:2}"
    proceed_stdin
    # print installation summary
    echo_configuration_summary
    # prerequisites
    prerequisites

    # filling the SQL database
    create_tables
    echo "SQL inserts:                          OK"
    add_procedures
    echo "SQL procedures:                       OK"
    # done
    echo_installation_summary
}

main "$0" "$@"
exit $?
