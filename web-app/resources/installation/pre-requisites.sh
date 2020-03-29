#!/bin/bash

# MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

set -o errexit

if [[ $UID -ne 0 ]]; then
    echo "$0 must be run as root"
    exit 1
fi

DISTRO_RELEASE=$(/usr/bin/lsb_release -rs)

if [[ "${DISTRO_RELEASE}" == "14.04" ]]; then
    ./pre-requisites-ubuntu-1404.sh
elif  [[ "${DISTRO_RELEASE}" == "18.04" ]]; then
    ./pre-requisites-ubuntu-1804.sh
fi
