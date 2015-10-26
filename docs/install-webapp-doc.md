# Asqatasun web application installation

This page describes the steps to follow to install Asqatasun since Version 3.0.0 (and further) from the binary files or from the sources. The sofware has been tested on 12.04 LTS (Precise Pangolin) and 14.04 LTS (Trusty Tahr).

PLEASE ensure you meet all [prerequisites for Asqatasun 3.x on Ubuntu](#prerequisites).

## Download asqatasun tarball and extract content

Retrieve the [lastest version of Asqatasun](http://download.tanaguru.org/Tanaguru/tanaguru-latest.tar.gz) and extract it on your file system. 

```sh
wget http://download.tanaguru.org/Tanaguru/tanaguru-latest.tar.gz
tar xzf tanaguru-latest.tar.gz
cd tanaguru*
```

## Create the database

Create an empty schema and a asqatasun user. Grant this asqatasun user permissions to create, update and delete objects for this schema. The charset of the database has to be set to "UTF-8".

```mysql
GRANT USAGE ON * . * TO '$tgUser'@'localhost' IDENTIFIED BY '$tgPassword';
CREATE DATABASE IF NOT EXISTS `$tgDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `$tgDatabase` . * TO '$tgUser'@'localhost';
FLUSH PRIVILEGES;
```

where $tgUser is the asqatasun user, $tgPassword is the asqatasun user password, and $tgDatabase is the asqatasun database.

## Execute the installation script

```sh
sudo ./install.sh --mysql-tg-db <Asqatasun_mysql_db> \ 
                  --mysql-tg-user <Asqatasunu_mysql_user> \
                  --mysql-tg-passwd <Asqatasun_mysql_password> \
                  --tanaguru-url <Asqatasun_webapp_url> \
                  --tomcat-webapps <tomcat_webapps_directory> \
                  --tomcat-user <tomcat_unix_user> \
                  --tg-admin-email <Asqatasun_admin_email> \
                  --tg-admin-passwd <Asqatasun_admin_password> \
                  --firefox-esr-path <path_to_Firefox_ESR> \
                  --display-port <Xorg_display_port>
```

### Script options description


* **--mysql-tg-user** : Mysql user for Asqatasun
* **--mysql-tg-passwd** : Password of the user specified by --mysql-tg-user. if this user already exists, please ensure you give its correct password. If not, the user will be automatically created.
* **--mysql-tg-db** : Database for Asqatasun
* **--tanaguru-url** : URL where asqatasun will be deployed (e.g. http://localhost:8080/asqatasun)
* **--tomcat-webapps** : Tomcat webapps directory (e.g. /var/lib/tomcat7/webapps)
* **--tomcat-user** : Unix user name for the tomcat service (e.g. tomcat7)
* **--tg-admin-email** : Email of the Asqatasun admin user (by the way, it's you !)
* **--tg-admin-passwd** : The asqatasun application admin password
* **--firefox-esr-path** : Path to Firefox-ESR binary (e.g. /opt/firefox/firefox) you installed in Asqatasun pre-requesites.
* **--display-port** : Xorg display port (e.g. ":99"). For debug purpose, you may want asqatasun to display firefox instances in the current X session (for desktop). In this case, use ":0" as value and execute the "xhost +" in a terminal to authorize the process (owned by tomcat) to use the X server.

### Example of install-script invocation

```sh
sudo ./install.sh --mysql-tg-user asqatasun \
                  --mysql-tg-passwd $MyTGPassWord \
                  --mysql-tg-db asqatasun \
                  --tanaguru-url http://localhost:8080/asqatasun/ \
                  --tomcat-webapps /var/lib/tomcat7/webapps/ \
                  --tomcat-user tomcat7 \
                  --tg-admin-email me@email.com \
                  --tg-admin-passwd toto42 \
                  --firefox-esr-path /opt/firefox/firefox \
                  --display_port :99
```
