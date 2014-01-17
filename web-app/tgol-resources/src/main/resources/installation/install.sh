#!/bin/bash

declare prefix="/"

declare mysql_tg_user=
declare mysql_tg_passwd=
declare mysql_root_user=
declare mysql_root_passwd=
declare mysql_tg_db=
declare tanaguru_url=
declare tanaguru_webapp_dir=
declare tomcat_webapps=
declare tomcat_user=
declare tg_admin_email=
declare tg_admin_passwd=
declare firefox_esr_path=
declare display_port=

declare omit_cleanup=true

declare dirty_database=false
declare dirty_directories=false
declare dirty_webapp=false

declare TG_CONF_DIR="etc/tanaguru/"
declare TG_TMP_DIR="var/tmp/tanaguru"
declare TG_LOG_DIR="var/log/tanaguru"
declare PKG_DIR=$(pwd)

declare ARCH="i386"

declare TG_VERSION="3.0.0-beta9"
declare TG_ARCHIVE="tanaguru-$TG_VERSION.$ARCH"
declare TG_WAR_VERSION=$TG_VERSION
declare TG_WAR="tgol-web-app-$TG_WAR_VERSION.war"

declare -a OPTIONS=(
	mysql_root_user
	mysql_root_passwd
	mysql_tg_user
	mysql_tg_passwd
	mysql_tg_db
	tanaguru_url
	tomcat_webapps
	tomcat_user
        tg_admin_email
        tg_admin_passwd
	firefox_esr_path
	display_port
)

warn() {
	$quiet || echo "WARNING : $*"
}

error() {
	echo "ERROR : $*"
	return 1
}

cleanup_database() {
	mysql --user="$mysql_root_user"
	      --password="$mysql_root_passwd"
		<< EOF
DROP DATABASE `$mysql_tg_db`;
DROP USER '$mysql_tg_user'@'localhost';
EOF
}

cleanup_directories() {
	rmdir "$TG_CONF_DIR" || error "Unable to remove $TG_CONF_DIR"
	rmdir "$TG_TMP_DIR" || error "Unable to remove $TG_TMP_DIR"
	rmdir "$TG_LOG_DIR" || error "Unable to remove $TG_LOG_DIR"
}

cleanup() {
	$dirty_webapp && cleanup_webapp
	$dirty_database && cleanup_database
	$dirty_directories && cleanup_directories
}

fail_with_usage() {
        echo ""
	echo "FAILURE : $*"
        echo ""
        usage
	$omit_cleanup || cleanup
	exit -1
}

fail() {
        echo ""
	echo "FAILURE : $*"
        echo ""
	$omit_cleanup || cleanup
	exit -1
}

prerequesites() {

	echo ""
	echo "Please verify your configuration meets the requirements : http://www.tanaguru.org/en/content/ubuntu-prerequisites-tanaguru-3x"
	echo ""	
	read -p "Are you sure you want to continue installation (yes/no)? " response
	[[ "${response}" == "yes" ]] || fail "Installation is stopped"
	echo ""	
}

usage() {
	cat << EOF

Usage : $0 [-h] 
        --mysql-tg-db <Tanaguru mysql db> 
        --mysql-tg-user <Tanaguru mysql user> 
        --mysql-tg-psswd <Tanaguru mysql password> 
        --mysql-root-user <mysql root user> 
        --mysql-root-passwd <mysql root password> 
        --tanaguru-url <Tanaguru webapp url> 
        --tomcat-webapps <tomcat webapps directory> 
        --tomcat-user <tomcat unix user>
        --tg-admin-email <Tanaguru admin email>
        --tg-admin-passwd <tanaguru admin password>
	--firefox-esr-path <path-to-Firefox-ESR>
	--display-port <Xorg-display-port>
	
Installation options :
 --mysql-root-user           Mysql user with privileges of database creation and user creation (e.g. mysql root user)
 --mysql-root-passwd         Password of the user specified by --mysql-root-user
 --mysql-tg-user             Mysql user for Tanaguru
 --mysql-tg-passwd           Password of the user specified by --mysql-tg-user
 --mysql-tg-db               Database for Tanaguru
 --tanaguru-url              URL where tanaguru will be deployed (e.g. http://localhost:8080/)
 --tomcat-webapps            Tomcat webapps directory (e.g./var/lib/tomcat/webapps)
 --tomcat-user               Unix user name for the tomcat service
 --tg-admin-email            Email of the Tanaguru admin user
 --tg-admin-passwd           The tanaguru application admin password
 --firefox-esr-path	     Path to Firefox-ESR binary (e.g. /opt/firefox-esr/firefox)
 --display-port              Xorg display port (e.g. ":99.1")

Script options :
 -h --help           Display this help message

EOF
}

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
		$isVar || case "$1" in
			-h|--help)        usage; exit 0;;
			*) usage; exit 1;;
		esac
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

preprocess_options()
{
	local protocol='\(https\?://\)\?'
	local domain='[0-9a-z\-]\+\(\.[0-9a-z\-]\+\)*'
	local port='\(:[0-9]\+\)\?'

	tanaguru_webapp_dir=$(
		echo $tanaguru_url | \
		sed "s#${protocol}${domain}${port}/\?##"
	) || fail "--tanaguru-url argument is not a valid url : \"$tanaguru_url\""

	[[ "$prefix" == /* ]] || fail "Invalid --prefix argument"
        case $prefix in
	     */) prefix=$prefix;;
	     *) prefix+='/';;
	esac
	[[ -z "$tanaguru_webapp_dir" ]] && tanaguru_webapp_dir=ROOT
}

echo_configuration_summary() {
	cat << EOF

Installing Tanaguru with the following configuration :
 - All path are relative to "${prefix}"
 - The mysql user "${mysql_root_user}" will be used to create the Tanaguru database and user
 - The mysql user "${mysql_tg_user}" will be created and used by Tanaguru
 - The mysql database "${mysql_tg_db}" will be created and used by Tanaguru
 - The web application will be installed in "${tomcat_webapps}/${tanaguru_webapp_dir}"
 - The web application will be accessible from "${tanaguru_url}"
 - Tanaguru will write its log file in "${prefix}$TG_LOG_DIR"
 - Tanaguru will use "${prefix}$TG_TMP_DIR" as a temporary directory
 - Tanaguru will read its configuration in "${prefix}$TG_CONF_DIR"
 - The user "${tomcat_user}" will have the write rights on the directories "${prefix}$TG_LOG_DIR" and "${prefix}$TG_TMP_DIR"
 - Tomcat configuration will be updated to with Xms, Xmx, display and webdriver.firefox.bin options"


EOF
}

create_user_and_database() {
	cd "$PKG_DIR/install/engine/sql"
	cat tanaguru-10-create-user-and-base.sql |         \
		sed -e "s/\$tgUser/$mysql_tg_user/g"       \
		    -e "s/\$tgPassword/$mysql_tg_passwd/g" \
		    -e "s/\$tgDatabase/$mysql_tg_db/g" |   \
		mysql --user="$mysql_root_user"            \
		      --password="$mysql_root_passwd" ||   \
		fail "Unable to create the user or the database."
	dirty_database=true
}

create_tables() {
	
	cd "$PKG_DIR/install/engine/sql"
	cat tanaguru-20-create-tables.sql                \
	    tanaguru-30-insert.sql |                     \
		mysql --user=${mysql_tg_user}            \
		      --password=${mysql_tg_passwd}      \
                      ${mysql_tg_db} ||                  \
		fail "Unable to create the rules tables."\
                     "The mysql user ${mysql_tg_user}" \
                     "may already exists with a different" \
                     "password in the database."

	cd "$PKG_DIR/install/rules/sql"
	cat 10-rules-resources-insert.sql                \
            accessiweb2.1-insert.sql               \
            accessiweb2.2-insert.sql               \
            rgaa2.2-insert.sql               \
            seo-10-insert.sql |              \
		mysql --user=${mysql_tg_user}            \
		      --password=${mysql_tg_passwd}      \
                      ${mysql_tg_db} ||                  \
		fail "Unable to create the rules tables" \
                     "The mysql user ${mysql_tg_user}" \
                     "may already exists with a different" \
                     "password in the database."
	
	cd "$PKG_DIR/install/web-app/sql"
	cat tgol-20-create-tables.sql tgol-30-insert.sql | \
		sed -e "s/\$tgDatabase/$mysql_tg_db/" |    \
		mysql --user="$mysql_tg_user"              \
		      --password="$mysql_tg_passwd" ||     \
		fail "Unable to create and fill the TGSI tables" \
                     "The mysql user ${mysql_tg_user}" \
                     "may already exists with a different" \
                     "password in the database."
}

create_directories() {
	dirty_directories=true
	install -dm 700 -o ${tomcat_user} -g root \
		"${prefix}/$TG_CONF_DIR"         \
		"${prefix}/$TG_LOG_DIR"          \
		"${prefix}/$TG_TMP_DIR"          \
		|| fail "Unable to create Tanaguru directories"
	install -dm 755 -o ${tomcat_user} -g root                          \
		"${prefix}/${tomcat_webapps}/${tanaguru_webapp_dir}" \
		|| fail "Unable to create Tanaguru webapp directory"
}

install_configuration() {
	dirty_conf=true
	cp -r "$PKG_DIR"/install/web-app/conf/* \
	   "${prefix}/$TG_CONF_DIR" ||                  \
		fail "Unable to copy the tanaguru configuration"
	sed -i -e "s#\$TGOL-DEPLOYMENT-PATH .*#${tomcat_webapps}/${tanaguru_webapp_dir}/WEB-INF/conf#" \
	    -e    "s#\$WEB-APP-URL .*#${tanaguru_url}#"               \
	    -e    "s#\$SQL_SERVER_URL#localhost#"                     \
	    -e    "s#\$USER#$mysql_tg_user#"                          \
	    -e    "s#\$PASSWORD#$mysql_tg_passwd#"                    \
	    -e    "s#\$DATABASE_NAME#$mysql_tg_db#"                   \
	    "${prefix}/$TG_CONF_DIR/tanaguru.conf" ||                    \
		fail "Unable to set up the tanaguru configuration"
	grep '$' "${prefix}/$TG_CONF_DIR" >/dev/null &&            \
		warn "The file ${prefix}/$TG_CONF_DIR contains"    \
		     "dollar symboles. Check by yourself that the " \
		     "replacement worked fine."
}

install_webapp() {
	dirty_webapp=true
	cd "${prefix}/${tomcat_webapps}/${tanaguru_webapp_dir}"        \
		|| fail "Unable to go to the tanaguru webapp directory"
	unzip -q "$PKG_DIR/install/web-app/$TG_WAR" \
		|| fail "Unable to extract the tanaguru war"
        sed -i -e "s#file:///#file://${prefix}/#g"              \
	    "WEB-INF/conf/tgol-service.xml" ||                    \
		fail "Unable to set up the tanaguru configuration"
}

edit_esapi_configuration_file() {
	dirty_webapp=true
	cd "$PKG_DIR/"/install/web-app/token-master-key-encryptor/ \
		|| fail "Unable to go to the generate-encryptor-keys directory"
	./generate-encryptor-keys.sh  > generated_keys.txt \
		|| fail "Unable to execute generate-encryptor-keys script"
	encryptorMasterKey=$(grep Encryptor.MasterKey generated_keys.txt)
	encryptorMasterSalt=$(grep Encryptor.MasterSalt generated_keys.txt)
	sed -i -e "s#Encryptor.MasterKey=\$MasterKey#${encryptorMasterKey}#"  \
	    -e    "s#Encryptor.MasterSalt=\$MasterSalt#${encryptorMasterSalt}#" \
	    "${prefix}/$TG_CONF_DIR/ESAPI.properties" ||                    \
		fail "Unable to set up the esapie configuration"
	rm -f generated_keys.txt
}

create_first_user() {

	cd "$PKG_DIR/install/web-app/sql-management"
        sed -i -e "s/^DbUser=.*$/DbUser=$mysql_tg_user/g" 	\
	    -e "s/^DbUserPasswd=.*$/DbUserPasswd=$mysql_tg_passwd/g" \
	    -e "s/^DbName=.*$/DbName=$mysql_tg_db/g"  \
            tg-set-user-admin.sh tg-create-user.sh  ||   \
		fail "Unable to create tanaguru admin user"
        sh ./tg-create-user.sh -e $tg_admin_email -p $tg_admin_passwd -l " " -f " " >/dev/null || fail "Error while creating Tanaguru user. User may already exists"
        sh ./tg-set-user-admin.sh -u $tg_admin_email >/dev/null || fail "Error while setting Tanaguru user as admin"
}

update_tomcat_configuration() {
	# we assume the filename is the same as tomcat-user specified by user
	MY_DEFAULT_TOMCAT=/etc/default/${tomcat_user}
	local tgOption=` grep "# Tanaguru JVM options" $MY_DEFAULT_TOMCAT `
	if [[ -z "$tgOption" ]]; then
	    echo "Adding JAVA_OPTS in tomcat configuration file"
	    echo "" >>$MY_DEFAULT_TOMCAT
	    echo "# Tanaguru JVM options" >>$MY_DEFAULT_TOMCAT
	    echo "JAVA_OPTS=\"\$JAVA_OPTS -Xms512M -Xmx2048M -Dwebdriver.firefox.bin=${firefox_esr_path} -Ddisplay=${display_port}\"" >>$MY_DEFAULT_TOMCAT
	fi
}

echo_installation_summary() {
	cat << EOF

Well done, your Tanaguru is installed !

Tomcat needs to be restarted (e.g. "sudo service ${tomcat_user} restart", take a coffee while it restarts :) )

Tanaguru is available at:

	${tanaguru_url}

EOF
}

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
	# create tanaguru directories
	create_directories
	echo "Directory creation:	.	.	OK"
	# create SQL user and database
	create_user_and_database
	echo "SQL username and database creation:	OK"
	# filling the SQL database
	create_tables
	echo "SQL inserts: 		.	.	OK"
	# install configuration file
	install_configuration
	echo "Tanaguru config files creation: .       OK"
	# install webapp
	install_webapp
	echo "Tanaguru webapp creation: 	.	OK"
	# edit esapi configuration file
	edit_esapi_configuration_file
	echo "Tanaguru webapp configuration: 	.	OK"
	# create first user
	create_first_user
	echo "Tanaguru admin creation:        .	OK"
	# update tomcat configuration
	update_tomcat_configuration
	echo "Tomcat configuration: 	.	.	OK"
	# done
	echo_installation_summary
}

main "$0" "$@"
exit $?
