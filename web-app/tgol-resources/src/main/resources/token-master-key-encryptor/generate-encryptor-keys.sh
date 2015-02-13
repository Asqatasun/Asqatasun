#!/bin/bash

touch validation.properties

java -classpath lib/esapi.jar:lib/log4j.jar:lib/servlet-api.jar:lib/commons-fileupload.jar:. -Dorg.owasp.esapi.resources="." org.owasp.esapi.reference.crypto.JavaEncryptor

rm -f validation.properties