#Asqatasun Ansible Role
This Ansible role will install the` Asqatasun Opensource Accessibility` on Ubuntu 12.04/14.04 LTS.
Before using this role, you can modify the variables or if you wish, you can continue with the defaults that has been provided with the role.
``` yaml
---
asqatasun_debian_pkgs:
  - wget
  - bzip2
  - openjdk-7-jre
  - unzip
  - mysql-server
  - libmysql-java
  - python-mysqldb
  - libtomcat7-java
  - tomcat7 
  - libspring-instrument-java
  - xvfb
  - libdbus-glib-1-2
  - mailutils
  - postfix

locale: 'en_US.UTF-8'

asqatasun_download_link: "http://download.asqatasun.org/asqatasun-latest.tar.gz"
# Asqatasun version that you want to install, get the full list of releases 
#by clicking in the release tab of the github main interface.
asqatasun_version: "asqatasun-4.0.0-beta2"

# Go this link to find your desired ESR Firefox
# For 32-bit architecture
# http://download-origin.cdn.mozilla.net/pub/firefox/releases/31.4.0esr/linux-i686/
# For 64-bit architecture
# http://download-origin.cdn.mozilla.net/pub/firefox/releases/31.4.0esr/linux-x86_64/
# Default is en-US in our example
fixfox_esr_link: "http://download-origin.cdn.mozilla.net/pub/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2"

asqatasun_parameters:
  db_name: 'asqatasundb'
  db_user: 'asqatasunuser'
  db_password: 'AsqatasunPassword'
  db_host: 'localhost'
  url: 'http://localhost:8080/asqatasun/'
  admin_email: 'admin@example.com'
  admin_passwd: 'asqatasun15'
```
- You may want to read about the [pre-requisites of Asqatasun on Ubuntu](https://github.com/Asqatasun/Asqatasun/blob/master/docs/prerequisites-webapp-doc.md)
- You may also read the [official installation procedure on Asqatasun github repo](https://github.com/Asqatasun/Asqatasun/blob/master/docs/INSTALL.md).

Also provided the `Vagrantfile` with the role, just run this command:
```shell
vagrant up
```
It will boot up the Ubuntu 14.04 LTS machine and install the Asqatasun on it:
```shell
PLAY [Installing the Asqatasun] ***********************************************

GATHERING FACTS ***************************************************************
ok: [asqatasun]

TASK: [asqatasun | Set Postfix options] ***************************************
changed: [asqatasun] => (item={'question': 'postfix/mailname', 'value': ' '})
changed: [asqatasun] => (item={'question': 'postfix/main_mailer_type', 'value': 'Satellite system'})

TASK: [asqatasun | Update locale Setting] *************************************
ok: [asqatasun]

TASK: [asqatasun | Reconfigure locale] ****************************************
skipping: [asqatasun]

TASK: [asqatasun | Update the Ubuntu repos] ***********************************
ok: [asqatasun]
```
Once the installation will be finished, just enter the following url to your browser to get the default Asqatasun page:
```http://192.168.33.33:8080/asqatasun/
```
Hope this role will help you. Thanks!

