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
declare asqatasun_url=
declare asqatasun_webapp_dir=
declare tomcat_webapps=
declare tomcat_user=
declare asqa_admin_email=
declare asqa_admin_passwd=
declare firefox_esr_binary_path=
declare display_port=

declare omit_cleanup=true

declare dirty_database=false
declare dirty_directories=false
declare dirty_webapp=false

declare TG_CONF_DIR="etc/asqatasun"
declare TG_TMP_DIR="var/tmp/asqatasun"
declare TG_LOG_DIR="var/log/asqatasun"
declare TOMCAT_HOME_DIR="/usr/share"
declare PKG_DIR=$(pwd)

declare ARCH="i386"

declare TG_VERSION="4.0.2"
declare TG_ARCHIVE="asqatasun-$TG_VERSION.$ARCH"
declare TG_WAR_VERSION=$TG_VERSION
declare TG_WAR="asqatasun-web-app-$TG_WAR_VERSION.war"

declare -a OPTIONS=(
	database_user
	database_passwd
	database_db
	database_host
	asqatasun_url
	tomcat_webapps
	tomcat_user
        asqa_admin_email
        asqa_admin_passwd
	firefox_esr_binary_path
	display_port
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


cleanup_directories() {
	rmdir "${prefix}$TG_CONF_DIR" || error "Unable to remove ${prefix}$TG_CONF_DIR"
	rmdir "${prefix}$TG_TMP_DIR" || error "Unable to remove ${prefix}$TG_TMP_DIR"
	rmdir "${prefix}$TG_LOG_DIR" || error "Unable to remove ${prefix}$TG_LOG_DIR"
}

cleanup() {
	$dirty_webapp && cleanup_webapp
	$dirty_directories && cleanup_directories
}

fail_with_usage() {
        echo ""
	echo "FAILURE : $*"
        echo ""
        usage
	if [[ ! $omit_cleanup ]]; then
            cleanup
        fi
	exit -1
}

fail() {
        echo ""
	echo "FAILURE : $*"
        echo ""
	if [[ ! $omit_cleanup ]]; then
            cleanup
        fi
	exit -1
}

prerequesites() {

	echo ""
	echo "Please verify your configuration meets the requirements : http://doc.asqatasun.org/en/10_Install_doc/Asqatasun/pre-requisites.html"
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
        --asqatasun-url <Asqatasun webapp url> 
        --tomcat-webapps <tomcat webapps directory> 
        --tomcat-user <tomcat unix user>
        --asqa-admin-email <Asqatasun admin email>
        --asqa-admin-passwd <Asqatasun admin password>
	--firefox-esr-binary-path <path-to-Firefox-ESR>
	--display-port <Xorg-display-port>
	
Installation options :
 --database-user             Database user for Asqatasun
 --database-passwd           Database password for Asqatasun
 --database-db               Database name for Asqatasun
 --database-host             Database hostname (FQDN or local hostname)
 --asqatasun-url             URL where Asqatasun will be deployed (e.g. http://localhost:8080/asqatasun)
 --tomcat-webapps            Tomcat webapps directory (e.g./var/lib/tomcat/webapps)
 --tomcat-user               Unix username for the tomcat service
 --asqa-admin-email          Email for the Asqatasun admin user
 --asqa-admin-passwd         Pawword for the Asqatasun admin user
 --firefox-esr-binary-path   Path to Firefox-ESR binary (e.g. /opt/firefox-esr/firefox)
 --display-port              Xorg display port (e.g. ":99.1")

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
            $quiet || $script || echo -n "$option : "
            read $option
            value=$(eval echo \${$option})
            echo $value
        done
    done
}

preprocess_options() {
    local protocol='\(https\?://\)\?'
    local domain='[0-9a-z\-]\+\(\.[0-9a-z\-]\+\)*'
    local port='\(:[0-9]\+\)\?'

    asqatasun_webapp_dir=$(
            echo $asqatasun_url | \
            sed "s#${protocol}${domain}${port}/\?##"
    ) || fail "--asqatasun-url argument is not a valid url : \"$asqatasun_url\""

    [[ "$prefix" == /* ]] || fail "Invalid --prefix argument"
    case $prefix in
         */) prefix=$prefix;;
         *) prefix+='/';;
    esac
    if [[ -z "$asqatasun_webapp_dir" ]]; then
        asqatasun_webapp_dir=ROOT
    fi
}

echo_configuration_summary() {
	cat << EOF

Installing Asqatasun with the following configuration :
 - The database user "${database_user}" will be created and used by Asqatasun
 - The database database "${database_db}" will be created and used by Asqatasun
 - The database host is "${database_host}"
 - Path prefix for directories is "${prefix}" 
 - The web application will be installed in "${tomcat_webapps}/${asqatasun_webapp_dir}"
 - The web application will be accessible from "${asqatasun_url}"
 - Asqatasun will write its log file in "${prefix}$TG_LOG_DIR"
 - Asqatasun will use "${prefix}$TG_TMP_DIR" as a temporary directory
 - Asqatasun will read its configuration in "${prefix}$TG_CONF_DIR"
 - The user "${tomcat_user}" will have the write rights on the directories "${prefix}$TG_LOG_DIR" and "${prefix}$TG_TMP_DIR"
 - Tomcat configuration will be updated to with Xms, Xmx, display and webdriver.firefox.bin options"


EOF
}

write_options() {
    MY_UNINSTALL="uninstall.txt"
    touch "${prefix}${TG_CONF_DIR}/${MY_UNINSTALL}"
    chmod 600 "${prefix}${TG_CONF_DIR}/${MY_UNINSTALL}"
    cat >"${prefix}${TG_CONF_DIR}/${MY_UNINSTALL}" <<EOF
#
# DO NOT DELETE
# If you delete this file, you won't be able to uninstall
#
database_user=${database_user}
database_passwd=${database_passwd}
database_db=${database_db}
database_host=${database_host}

prefix=${prefix}
tg_log_dir=${prefix}$TG_LOG_DIR
tg_conf_dir=${prefix}$TG_CONF_DIR
tg_tmp_dir=${prefix}$TG_TMP_DIR

asqatasun_url=${asqatasun_url}
tomcat_webapps=${tomcat_webapps}
asqatasun_webapp_dir=${asqatasun_webapp_dir}
tg_webapp_dir_full=${tomcat_webapps}/${asqatasun_webapp_dir}

firefox_esr_binary_path=${firefox_esr_binary_path}

EOF
}

#############################################
# SQL
#############################################
create_tables() {

    cd "$PKG_DIR/install/engine/sql"
    my_sql_insert asqatasun-20-create-tables.sql
    my_sql_insert asqatasun-30-insert.sql
    
    cd "$PKG_DIR/install/web-app/sql"
    my_sql_insert tgol-20-create-tables.sql
    my_sql_insert tgol-30-insert.sql

    cd "$PKG_DIR/install/rules/sql"
    my_sql_insert 10-rules-resources-insert.sql
    my_sql_insert accessiweb2.2-insert.sql
    my_sql_insert rgaa3.0-insert.sql
    my_sql_insert seo1.0-insert.sql
}

#############################################
# Directories 
#############################################
create_directories() {
    dirty_directories=true
    install -dm 700 -o ${tomcat_user} -g root \
            "${prefix}$TG_CONF_DIR" \
            "${prefix}$TG_LOG_DIR" \
            "${prefix}$TG_TMP_DIR" \
            || fail "Unable to create Asqatasun directories"
    install -dm 755 -o ${tomcat_user} -g root \
            "${prefix}${tomcat_webapps}/${asqatasun_webapp_dir}" \
            || fail "Unable to create Asqatasun webapp directory"
}

install_firefox_profile_files() {
    install -dm 700 -o ${tomcat_user} -g root \
		"${prefix}$TG_TMP_DIR/.gconf" \
		"${prefix}$TG_TMP_DIR/.java" \
		"${prefix}$TG_TMP_DIR/.cache" \
		"${prefix}$TG_TMP_DIR/.dbus" \
		"${prefix}$TG_TMP_DIR/.mozilla" \
		"${prefix}$TG_TMP_DIR/.gnome2" \
		"${prefix}$TG_TMP_DIR/.gnome2_private" \
		|| fail "Unable to create Asqatasun directories"

    ln -s "${prefix}$TG_TMP_DIR/.gconf" "${TOMCAT_HOME_DIR}/${tomcat_user}/.gconf" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.gconf"

    ln -s "${prefix}$TG_TMP_DIR/.java" "${TOMCAT_HOME_DIR}/${tomcat_user}/.java" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.java"

    ln -s "${prefix}$TG_TMP_DIR/.cache" "${TOMCAT_HOME_DIR}/${tomcat_user}/.cache" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.cache"

    ln -s "${prefix}$TG_TMP_DIR/.dbus" "${TOMCAT_HOME_DIR}/${tomcat_user}/.dbus" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.dbus"

    ln -s "${prefix}$TG_TMP_DIR/.mozilla" "${TOMCAT_HOME_DIR}/${tomcat_user}/.mozilla" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.mozilla"

    ln -s "${prefix}$TG_TMP_DIR/.gnome2" "${TOMCAT_HOME_DIR}/${tomcat_user}/.gnome2" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.gnome2"

    ln -s "${prefix}$TG_TMP_DIR/.gnome2_private" "${TOMCAT_HOME_DIR}/${tomcat_user}/.gnome2_private" \
        || fail "Unable to create symlink ${TOMCAT_HOME_DIR}/${tomcat_user}/.gnome2_private"
}

install_configuration() {
    dirty_conf=true
    cp -r "$PKG_DIR"/install/web-app/conf/* \
       "${prefix}$TG_CONF_DIR" || \
            fail "Unable to copy the Asqatasun configuration"
    sed -i -e "s#\$TGOL-DEPLOYMENT-PATH .*#${tomcat_webapps}/${asqatasun_webapp_dir}/WEB-INF/conf#" \
        -e    "s#\$WEB-APP-URL .*#${asqatasun_url}#" \
        -e    "s#\$SQL_SERVER_URL#$database_host#" \
        -e    "s#\$USER#$database_user#" \
        -e    "s#\$PASSWORD#$database_passwd#" \
        -e    "s#\$DATABASE_NAME#$database_db#" \
        "${prefix}$TG_CONF_DIR/asqatasun.conf" || \
            fail "Unable to set up the Asqatasun configuration"

    WITHOUT_DOLLAR=$(echo "${prefix}$TG_CONF_DIR" | tr -d '$')
    if [[ "${prefix}$TG_CONF_DIR" != "$WITHOUT_DOLLAR" ]]; then
            warn "The file ${prefix}$TG_CONF_DIR contains" \
                 "dollar symbols. Check by yourself that the " \
                 "replacement worked fine."
    fi
}

#############################################
# Webapp
#############################################
install_webapp() {
    dirty_webapp=true
    cd "${prefix}${tomcat_webapps}/${asqatasun_webapp_dir}" \
            || fail "Unable to go to the Asqatasun webapp directory"
    unzip -q "$PKG_DIR/install/web-app/$TG_WAR" \
            || fail "Unable to extract the Asqatasun war"
    sed -i -e "s#file:///#file://${prefix}#g" \
        "WEB-INF/conf/tgol-service.xml" || \
            fail "Unable to set up the Asqatasun configuration"
}

edit_esapi_configuration_file() {
    dirty_webapp=true
    cd "$PKG_DIR/install/web-app/token-master-key-encryptor/" \
            || fail "Unable to go to the generate-encryptor-keys directory"
    ./generate-encryptor-keys.sh  > generated_keys.txt \
            || fail "Unable to execute generate-encryptor-keys script"
    encryptorMasterKey=$(grep Encryptor.MasterKey generated_keys.txt)
    encryptorMasterSalt=$(grep Encryptor.MasterSalt generated_keys.txt)
    sed -i -e "s#Encryptor.MasterKey=\$MasterKey#${encryptorMasterKey}#"  \
        -e    "s#Encryptor.MasterSalt=\$MasterSalt#${encryptorMasterSalt}#" \
        "${prefix}$TG_CONF_DIR/ESAPI.properties" || \
            fail "Unable to set up the esapie configuration"
    rm -f generated_keys.txt
}

update_tomcat_configuration() {
    # we assume the filename is the same as tomcat-user specified by user
    MY_DEFAULT_TOMCAT=/etc/default/${tomcat_user}
    local tgOption=` grep "# Asqatasun JVM options" $MY_DEFAULT_TOMCAT `
    if [[ -z "$tgOption" ]]; then
        echo "" >>$MY_DEFAULT_TOMCAT
        echo "# Asqatasun JVM options" >>$MY_DEFAULT_TOMCAT
        echo "JAVA_OPTS=\"\$JAVA_OPTS -Xms512M -Xmx2048M -DconfDir=file://${prefix}${TG_CONF_DIR} -DlogDir=${prefix}${TG_LOG_DIR} -Dwebdriver.firefox.bin=${firefox_esr_binary_path} -Ddisplay=${display_port}\"" >>$MY_DEFAULT_TOMCAT
    else
        fail "Unable to set Tomcat configuration in $MY_DEFAULT_TOMCAT"
    fi
}

#############################################
# User & contract creation
#############################################
create_first_user() {
    # create admin user for Asqatasun
    cd "$PKG_DIR/install/web-app/sql-management"
    sed -i -e "s/^DbUser=.*$/DbUser=$database_user/g" \
        -e "s/^DbUserPasswd=.*$/DbUserPasswd=$database_passwd/g" \
        -e "s/^DbName=.*$/DbName=$database_db/g"  \
        -e "s/^DbHost=.*$/DbHost=$database_host/g" \
        tg-set-user-admin.sh tg-create-user.sh  || \
            fail "Unable to create Asqatasun admin user"
    sh ./tg-create-user.sh -e "${asqa_admin_email}" -p "${asqa_admin_passwd}" -l " " -f " " >/dev/null || \
        fail "Error while creating Asqatasun admin user. User may already exists"
    sh ./tg-set-user-admin.sh -u $asqa_admin_email >/dev/null || \
        fail "Error while setting Asqatasun user as admin"
}

create_first_contracts() {
    # create 3 typical contracts
    cd "$PKG_DIR/install/web-app/sql-management"

    # add SQL procedure "contract_create"
    ./PROCEDURE_contract_create.sh \
        --database-user "$database_user" \
        --database-passwd "$database_passwd" \
        --database-db "$database_db" \
        --database-host "$database_host" || \
            fail "Unable to add SQL Procedure 'contract_create'"

    # Contract Wikipedia A11Y
    ./ASQA_contract_create_A11Y_RGAA3.sh \
        -c "Wikipedia A11Y RGAA-3" \
        -u 1 \
        -w "http://en.wikipedia.org/" \
        --database-user "$database_user" \
        --database-passwd "$database_passwd" \
        --database-db "$database_db" \
        --database-host "$database_host" \
        --audit-page \
        --audit-site \
        --audit-file \
        --audit-scenario \
        --audit-manual \
        -m 1000 || \
            fail "Unable to create contract: Wikipedia A11Y RGAA-3"
    # Contract Wikipedia SEO
    ./ASQA_contract_create_SEO.sh \
        -c "Wikipedia SEO" \
        -u 1 \
        -w "http://en.wikipedia.org/" \
        --database-user "$database_user" \
        --database-passwd "$database_passwd" \
        --database-db "$database_db" \
        --database-host "$database_host" \
        -m 1000 || \
            fail "Unable to create contract: Wikipedia SEO"
    # Contract A11Y Openbar
    ./ASQA_contract_create_A11Y_RGAA3_openbar.sh \
        -c "Openbar A11Y RGAA-3" \
        -u 1 \
        --database-user "$database_user" \
        --database-passwd "$database_passwd" \
        --database-db "$database_db" \
        --database-host "$database_host"  || \
            fail "Unable to create contract: Openbar A11Y RGAA-3"
}

#############################################
# Finish
#############################################
echo_installation_summary() {
	cat << EOF

Well done, your Asqatasun is installed !
Tomcat needs to be restarted (e.g. "sudo service ${tomcat_user} restart", take a coffee while it restarts :) )
Asqatasun is available at: ${asqatasun_url}

EOF
}

#############################################
# main
#############################################
main() {
    # get options
    proceed_cmdline "${@:2}"
    proceed_stdin
    # if the password is in the command line, hide it
    # preprocess options
    preprocess_options
    # print installation summary
    echo_configuration_summary
    # prerequesites
    prerequesites

    # create Asqatasun directories
    create_directories
    echo "Directory creation:                   OK"
    # save options for uninstall
    write_options	
    # filling the SQL database
    create_tables
    echo "SQL inserts:                          OK"
    # install configuration file
    install_configuration
    echo "Asqatasun config files creation:      OK"
    # install webapp
    install_webapp
    echo "Asqatasun webapp creation:            OK"
    # install firefox profile files
    install_firefox_profile_files
    echo "Firefox Profile Files creation:       OK"
    # edit esapi configuration file
    edit_esapi_configuration_file
    echo "Asqatasun webapp configuration:       OK"
    # create first user
    create_first_user
    echo "Asqatasun admin creation:             OK"
    create_first_contracts
    echo "Asqatasun contract creation:          OK"
    # update tomcat configuration
    update_tomcat_configuration
    echo "Tomcat configuration:                 OK"
    # done
    echo_installation_summary
}

main "$0" "$@"
exit $?
