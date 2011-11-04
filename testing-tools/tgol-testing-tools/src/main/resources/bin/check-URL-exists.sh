#!/bin/bash

# check whether an URL exists (i.e. doesn't give a 40x HTTP return code)

# parameter management
# --------------------
if [ ! $1 ] ; then
    echo "$0 : check whether an URL exists (i.e. doesn't give a 404)";
    echo "Usage: $0 <URL>";
    echo "";
    echo -en "- URL: the complete URL like http://www.mydomain.com/my/path/to/file.html \n";
    exit -1
fi

if [ ! -e /usr/bin/lynx ]; then
    echo "ERROR: lynx not found"
    exit -1
fi;

# main
# ----

Url=$1
Result=$(/usr/bin/lynx -source -head "${Url}" 2>/dev/null || echo "Unexistant website")

if [ -z "${Result}" ]; then
    # URL OK, nothing to say so exit silently
    exit 0;
fi;

#if [ "${Result}" -eq "Unexistant website" ]; then
#    echo "ERROR: Unexistant website";
#    exit -1;
#fi;

HttpCode=$(echo ${Result} | grep "HTTP/1" | cut -d' ' -f 2)

if [ ! ${HttpCode} ]; then
    echo "ERROR: unable to determine HTTP return code for ${Url}";
    exit -1;
fi

if [ ${HttpCode} -eq 404 ]; then
    echo "Unexistant URL (404): ${Url}"
    exit;
fi;