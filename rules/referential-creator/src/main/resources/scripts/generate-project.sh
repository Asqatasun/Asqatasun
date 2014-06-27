#! /bin/sh

unset M2_HOME && mvn archetype:generate -DarchetypeGroupId=org.codehaus.mojo.archetypes -DarchetypeArtifactId=pom-root -DgroupId=$1 -DartifactId=tanaguru-$3 -Dversion=1.0-SNAPSHOT -DinteractiveMode=false

cd $2/tanaguru-$3 && mvn archetype:generate -DgroupId=$1 -DartifactId=$3 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd $2/tanaguru-$3 && mvn archetype:generate -DgroupId=$1 -DartifactId=$4 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd $2/tanaguru-$3 && mvn archetype:generate -DgroupId=$1 -DartifactId=$5 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd $2/tanaguru-$3 && mvn archetype:generate -DgroupId=$1 -DartifactId=$6 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

cd $2/tanaguru-$3 && mvn archetype:generate -DgroupId=$1 -DartifactId=$7 -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false