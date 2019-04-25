# Krash test campaign

## Kind of krash test campaigns

* offline campaign: based on archived webpages hosted at http://nonreg.asqatasun.ovh/
* online campaign: based on a selection of live websites

## Pre-requisites

* a Linux environment with:
    * graphical environment (i.e. an XServer running),
    * Asqatasun source code (your current working directory),
* an Asqatasun docker instance running at `http://localhost:8085/asqatasun/`
* a Firefox ESR 31.4 installed in `/opt/firefox/`,

## Offline krash test campaign

First, allow WebDriver to connect to your own Xserver:

```shell
xhost +si:hostname:localhost
```

Then do

```shell
cd testing-tools/tgol-test-krash-offline
mvn test \
    -Duser=admin@asqatasun.org \
    -Dpassword=myAsqaPassword \
    -Dhost.location=http://localhost:8085/asqatasun \
    -Dcontract.id=3 \
    -Dxvfb.display=":0" \
    -Dwebdriver.firefox.bin=/opt/firefox/firefox
```

**Warning:** do _not_ add a trailing slash to your `host.location` otherwise the tests can't be run.

**Note:** the value "3" for `contract.id` is the id of the "openbar" contract created by the installer
