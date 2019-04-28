# Build Asqatasun

On a Linux Ubuntu 14.04

## Prerequisites

TODO (copy from Dockerfile)

## Actual build

From the top directory of Asqatasun sources
```sh
# before first build
mvn install:install-file -DgroupId=com.saucelabs -DartifactId=sebuilder-interpreter -Dversion=1.0.2 -Dpackaging=jar -Dfile=engine/asqatasun-resources/src/main/resources/lib/sebuilder-interpreter-1.0.2.jar

# build
mvn clean install
```

## Get the artifacts (products from build)

The Asqatasun .tar.gz file is in `web-app/asqatasun-web-app/target/`
