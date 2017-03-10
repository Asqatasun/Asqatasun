# Krash test campaign.md

**Remark**: `/!\` 2017-02-27 this document is being written

## Kind of krash test campaigns

* offline campaign: based on archived webpages
* online campaign: based on a selection of live websites

## Pre-requisites

* a Linux environnement with:
    * graphical environment (i.e. an XServer running),
    * Asqatasun source code (your current working directory),
* an Asqatasun instance running at, say,  http://localhost:8080/asqatasun/
* your Firefox ESR 31.4 installed in `/opt/firefox/`,

## Offline krash test campaign

First, allow WebDriver to connect to your own Xserver:

```shell
xhost +si:hostname:localhost
```

Then do

```shell
cd testing-tools/tgol-test-krash-offline
mvn test \
    -Duser=me@my-email.org \
    -Dpassword=myAsqaPassword \
    -Dhost.location=http://localhost:8080/asqatasun/ \
    -Dcontract.id=3 \
    -Dxvfb.display=":0"
```

### Notes (to be ordered)

* Set the property file, find where and with what content
* Distinguish the Asqatasun instance being tested and the tools required to make the test (XVFB...)
* Explain how to get the value "3" for contract.id
