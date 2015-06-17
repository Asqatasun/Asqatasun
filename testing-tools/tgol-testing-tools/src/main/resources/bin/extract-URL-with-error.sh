#!/bin/bash

# extract URLs on error from a test campaign

# parameter management
# --------------------
if [ ! $1 ] || [ ! $2 ] || [ ! $3 ]; then
    echo "$0: extract URLs on error from a test campaign";
    echo "Usage: $0 <ErrorText> <BuildLog> <UrlFile>";
    echo "";
    echo -en "- ErrorText: \t text given by Tanaguru Engine\n";
    echo -en "- BuildLog: \t path to raw log from maven / hudson\n";
    echo -en "- UlrFile: \t path to file URL file (formated this way: key;URL )\n";
    echo "";
    echo "Example: $0 ERROR_WHILE_LOADING tgol-krashtest-online.log LIST-online-all-sites.txt";
    exit -1
fi

ErrorText=$1
BuildLog=$2
UrlList=$3

# Algorithm
# ---------
# First: extract URL keys
# Second: extract URL from its key
for i in $( /bin/grep --text "${ErrorText}" -B 1 "${BuildLog}" | grep "Running org.tanaguru.webapp.test" | perl -pi -e 's!Running org.tanaguru.webapp.test.(.*)Test!$1!g' | perl -pi -e 's!\_!\-!g' );
do
    grep "${i};" ${UrlList} | perl -pi -e 's!.*?;(.*)!$1!' ;
done

