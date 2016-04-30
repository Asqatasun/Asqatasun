#!/bin/bash

# MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

set -o errexit

if [[ $UID -ne 0 ]]; then
    echo "$0 must be run as root"
    exit 1
fi

#############################################
# Variables
# 
# --> adjust to your own configuration
#
#############################################

# Mysql
MYSQL_ROOT_PASSWD=mysqlRootPassword
MYSQL_CONF_FILE=/etc/mysql/my.cnf
MYSQL_CONF_DIR=/etc/mysql/conf.d
MYSQL_CONF_FILE_FOR_ASQATASUN=asqatasun.cnf

# Mysql for Asqatasun
DATABASE_USER=asqatasun
DATABASE_PASSWD=asqaP4sswd
DATABASE_DBNAME=asqatasun
DATABASE_HOST=localhost

# Tomcat
TOMCAT_LIB_DIR=/usr/share/tomcat7/lib
TOMCAT_USER=tomcat7

# XVFB
XVFB_INITSCRIPT_FILENAME=xvfb
XVFB_INITSCRIPT_DIR=.

# Firefox ESR
FIREFOX_PARENT_DIR=/opt

# Please keep *this* version of Firefox
FIREFOX_BASENAME="firefox-31.4.0esr"
FIREFOX_i686="http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/${FIREFOX_BASENAME}.tar.bz2";
FIREFOX_x86_64="http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/${FIREFOX_BASENAME}.tar.bz2";

#
# That's OK, please do not modify things further,
# you can run this script (as root) and proceed with the install.sh
#
# -*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

#############################################
# init
#############################################

fail() {
    echo ""
	echo "FAILURE : $*"
    echo ""
	exit -1
}

#############################################
# Packages
#############################################

# Packages for unattended installs
export DEBIAN_FRONTEND=noninteractive
apt-get update
apt-get -y --no-install-recommends install \
    debconf \
    apt-utils
    
# Remember: don't do apt-get upgrade|safe-update|dist-upgrade in Docker
# https://docs.docker.com/articles/dockerfile_best-practices/#run

# Pre-define Mysql root passwd
echo "mysql-server mysql-server/root_password password ${MYSQL_ROOT_PASSWD}" | debconf-set-selections
echo "mysql-server mysql-server/root_password_again password ${MYSQL_ROOT_PASSWD}" | debconf-set-selections

# Required packages for Asqatasun
#   Notes:
#     - libdbus-glib-1-2: needed for Firefox Webdriver
apt-get -y --no-install-recommends install \
    wget \
    bzip2 \
    unzip \
    mysql-server \
    libmysql-java \
    tomcat7 \
    libspring-instrument-java \
    xvfb \
    libdbus-glib-1-2 \
    openjdk-7-jre
    
#############################################
# Mail config
#############################################

# @@@TODO (and don't forget to add "postfix" to the list of packages to install (just above)
#          or configure a Mailjet / Mandrill service

#############################################
# Mysql config
#############################################

cat >${MYSQL_CONF_DIR}/${MYSQL_CONF_FILE_FOR_ASQATASUN} <<EOF
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_general_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
max_allowed_packet = 64M
innodb_file_per_table = 1
EOF

service mysql restart

# Create Asqatasun database
mysql -u root --password="${MYSQL_ROOT_PASSWD}" --execute="GRANT USAGE ON * . * TO '${DATABASE_USER}'@'${DATABASE_HOST}' IDENTIFIED BY '${DATABASE_PASSWD}'; \
    CREATE DATABASE IF NOT EXISTS ${DATABASE_DBNAME} CHARACTER SET utf8; \
    GRANT ALL PRIVILEGES ON ${DATABASE_DBNAME} . * TO '${DATABASE_USER}'@'${DATABASE_HOST}'; \
    FLUSH PRIVILEGES;"

#############################################
# Tomcat config
#############################################

ln -s /usr/share/java/spring3-instrument-tomcat.jar ${TOMCAT_LIB_DIR}/spring3-instrument-tomcat.jar
ln -s /usr/share/java/mysql-connector-java.jar ${TOMCAT_LIB_DIR}/mysql-connector-java.jar

#############################################
# XVFB config
#############################################

cp ${XVFB_INITSCRIPT_DIR}/${XVFB_INITSCRIPT_FILENAME} ${XVFB_INITSCRIPT_DIR}/${XVFB_INITSCRIPT_FILENAME}.old

SH_PERL_COMMAND="s!RUN_AS_USER=tomcat7!RUN_AS_USER=${TOMCAT_USER}!o"
perl -pi -e "${SH_PERL_COMMAND}" ${XVFB_INITSCRIPT_DIR}/${XVFB_INITSCRIPT_FILENAME} 

cp ${XVFB_INITSCRIPT_DIR}/${XVFB_INITSCRIPT_FILENAME} /etc/init.d/
update-rc.d ${XVFB_INITSCRIPT_FILENAME} defaults
service ${XVFB_INITSCRIPT_FILENAME} start

#############################################
# Firefox ESR config
#############################################

if [[ $(uname -m) == "x86_64" ]]; then
    FIREFOX_DL=${FIREFOX_x86_64}
elif [[ $(uname -m) == "i686" ]]; then
    FIREFOX_DL=${FIREFOX_i686}
else 
    fail "unknow architecture !?";
fi

cd ${FIREFOX_PARENT_DIR}
wget -q "${FIREFOX_DL}"
tar xvfj ${FIREFOX_BASENAME}.tar.bz2
mv firefox ${FIREFOX_BASENAME}
ln -s ${FIREFOX_BASENAME} firefox
rm ${FIREFOX_BASENAME}.tar.bz2

#############################################
# Clean
#############################################

# For Docker usage
apt-get clean
apt-get autoremove
rm -rf /var/lib/apt/lists/*