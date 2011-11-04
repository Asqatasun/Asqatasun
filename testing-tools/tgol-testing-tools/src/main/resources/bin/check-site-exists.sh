#!/bin/bash

# check whether a website exists (i.e. gives a response, whatever the response)

# parameter management
# --------------------
if [ ! $1 ] ; then
    echo "$0 : check whether a website exists (i.e. gives a response, whatever the response)";
    echo "Usage: $0 <URL>";
    echo "";
    echo -en "- URL: the fully qualified domain name of the site (must begin with http:// )\n";
    echo "";
    echo "Example: $0 http://www.tanaguru.org/";
    exit -1
fi

if [ ! -e /usr/bin/lynx ]; then
    echo "ERROR: lynx not found"
    exit -1
fi;

# main
# ----

Url=$1
/usr/bin/lynx -source -head "$i"  >/dev/null 2>&1 || echo "Unexistant website: $i"
