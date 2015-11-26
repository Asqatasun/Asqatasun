#!/bin/bash

# Uninstall Asqatasun
# @author: mfaure@asqatasun.org
#
# Remove the following:
# - webapp dir
# - asqatasun tmp dir
# - (if purge) conf dir
# - (if purge) drop database
#
# The prerequesites elements are not deleted (Xvfb, Firefox ESR)

set -o errexit

#############################################
# Usage
#############################################
usage () {
    cat <<EOF

$0 uninstalls Asqatasun by removing its files.
Data and configuration are kept unless explicitly specified.

usage: $0 [OPTIONS]

  -c | --conf-dir <directory>   MANDATORY Path to conf directory (containing uninstall.txt)
  -p | --purge                  Delete *also* all data (in database), logs and configuration
  -h | --help                   Show this help

EOF
    exit 2
}

#############################################
# Manage options and usage
#############################################
TEMP=`getopt -o c:ph --long conf-dir:,purge,help -- "$@"`

if [[ $? != 0 ]] ; then
    echo "Terminating..." >&2 ;
    exit 1 ;
fi

# Note the quotes around `$TEMP': they are essential!
eval set -- "$TEMP"

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

#############################################
# Init
#############################################

declare UNINSTALL_FILE=${CONF_DIR}/uninstall.txt

declare database_user
declare database_passwd
declare database_db
declare database_host
declare tg_log_dir
declare tg_conf_dir
declare tg_tmp_dir
declare tg_webapp_dir_full

warn() {
	$quiet || echo "WARNING : $*"
}

error() {
	echo "ERROR : $*"
	return 1
}

my_remove_dir() {
    if [[ ! "$1" ]]; then
        echo "my_remove_dir: missing directory";
        exit;
    fi

    if [[ -e "$1" ]]; then
        echo -en "--> $1 \n"
        rm -rfI "$1" || error "Unable to remove $1"
    fi
}

#############################################
# Get option value
#############################################
get_option_value() {
    database_user=$(grep "database_user" "${UNINSTALL_FILE}" | cut -d"=" -f2)
    database_passwd=$(grep "database_passwd" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    database_db=$(grep "database_db" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    database_host=$(grep "database_host" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    tg_log_dir=$(grep "tg_log_dir" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    tg_conf_dir=$(grep "tg_conf_dir" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    tg_tmp_dir=$(grep "tg_tmp_dir" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
    tg_webapp_dir_full=$(grep "tg_webapp_dir_full" "${UNINSTALL_FILE}" | cut -d"=" -f2) 
}

#############################################
# Do the remove
#############################################
do_the_remove() {
    my_remove_dir "${tg_tmp_dir}"
    my_remove_dir "${tg_webapp_dir_full}"
}


#############################################
# Do the purge
#############################################
purge_database() {
    mysql --user="${database_user}" \
        --password="${database_passwd}" \
        --host="${database_host}" \
        --execute="drop database ${database_db};" \
        || error "Unable to drop ${database_db} database"
}

do_the_purge() {
    purge_database
    my_remove_dir "${tg_log_dir}"
    my_remove_dir "${tg_conf_dir}"
}

#############################################
# Main
#############################################
get_option_value
do_the_remove

if [[ "$PURGE" == "true" ]]; then
    do_the_purge;
fi

cat <<EOF
Asqatasun has been removed.
Don't forget to restart Tomcat.
See you soon
EOF

exit $?