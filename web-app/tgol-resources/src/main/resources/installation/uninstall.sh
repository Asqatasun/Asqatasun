#!/bin/bash

declare prefix="/"

declare TG_CONF_DIR="etc/tanaguru/"
declare TG_TMP_DIR="var/tmp/tanaguru"
declare TG_LOG_DIR="var/log/tanaguru"
declare TOMCAT_HOME_DIR="/usr/share"
declare PKG_DIR=$(pwd)

warn() {
	$quiet || echo "WARNING : $*"
}

error() {
	echo "ERROR : $*"
	return 1
}


cleanup_directories() {
	rmdir "$TG_CONF_DIR" || error "Unable to remove $TG_CONF_DIR"
	rmdir "$TG_TMP_DIR" || error "Unable to remove $TG_TMP_DIR"
	rmdir "$TG_LOG_DIR" || error "Unable to remove $TG_LOG_DIR"
}
