#!/bin/sh
mvn install:install-file -DgroupId=org.apache -DartifactId=commons-csv -Dversion=1.0-SNAPSHOT -Dpackaging=jar -Dfile=commons-csv-1.0-SNAPSHOT.jar
