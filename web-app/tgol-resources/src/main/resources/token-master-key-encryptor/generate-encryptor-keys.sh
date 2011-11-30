#!/bin/bash

java -classpath lib/esapi.jar:lib/log4j.jar:lib/servlet-api.jar:lib/commons-fileupload.jar -Dorg.owasp.esapi.resources="$PATH" org.owasp.esapi.reference.crypto.JavaEncryptor