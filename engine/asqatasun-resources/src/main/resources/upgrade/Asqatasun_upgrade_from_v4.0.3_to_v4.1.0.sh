#!/usr/bin/env bash

set -o errexit

#############################################
# Variables
#############################################

MYSQL_ASQATASUN_CNF="/etc/mysql/conf.d/asqatasun.cnf"

#############################################
# Functions
#############################################

function set_utf8mb4_to_mysqlclient_conf() {
    cp "${MYSQL_ASQATASUN_CNF}" "${MYSQL_ASQATASUN_CNF}.bak"
    sed -i 's/default-character-set=utf8/default-character-set=utf8mb4/g' "${MYSQL_ASQATASUN_CNF}"
    sed -i 's/collation-server = utf8_general_ci/collation-server = utf8mb4_general_ci/' "${MYSQL_ASQATASUN_CNF}"
    sed -i 's/SET NAMES utf8/SET NAMES utf8mb4/' "${MYSQL_ASQATASUN_CNF}"
    sed -i 's/character-set-server = utf8/character-set-server = utf8mb4/' "${MYSQL_ASQATASUN_CNF}"
}

#############################################
# Main
#############################################

set_utf8mb4_to_mysqlclient_conf
