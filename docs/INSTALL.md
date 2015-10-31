# Asqatasun installation

This page describes the steps to follow to install Asqatasun since Version 3.0.0 
(and further) from the binary files or from the sources. The software has been 
tested on 12.04 LTS (Precise Pangolin) and 14.04 LTS (Trusty Tahr).

PLEASE ensure you meet all [prerequisites for Asqatasun on Ubuntu](prerequisites-webapp-doc.md).

/!\ I repeat **DOUBLE CHECK YOU *DO* MEET ALL [requirements](prerequisites-webapp-doc.md)** before installing :)

## Download asqatasun tarball and extract content

Retrieve the [lastest version of Asqatasun](http://download.tanaguru.org/Tanaguru/tanaguru-latest.tar.gz) and extract it on your file system. 

```sh
wget http://download.tanaguru.org/Tanaguru/tanaguru-latest.tar.gz
tar xzf tanaguru-latest.tar.gz
cd tanaguru*
```

## Create the database

Create an empty schema and a asqatasun user. Grant this asqatasun user permissions 
to create, update and delete objects for this schema. The charset of the database 
has to be set to "UTF-8".

```mysql
GRANT USAGE ON * . * TO '$AsqatasunUser'@'localhost' IDENTIFIED BY '$AsqatasunPassword';
CREATE DATABASE IF NOT EXISTS `$AsqatasunDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `$AsqatasunDatabase` . * TO '$AsqatasunUser'@'localhost';
FLUSH PRIVILEGES;
```

where $AsqatasunUser is the asqatasun user, $AsqatasunPassword is the asqatasun user password, and $AsqatasunDatabase is the asqatasun database.

## Execute the installation script

```sh
sudo ./install.sh --database-user <Asqatasunu_dababase_user> \
                  --database-passwd <Asqatasun_dababase_password> \
                  --database-db <Asqatasun_dababase_dbname> \ 
                  --database-host <Asqatasun_dababase_hostname> \ 
                  --asqatasun-url <Asqatasun_webapp_url> \
                  --tomcat-webapps <tomcat_webapps_directory> \
                  --tomcat-user <tomcat_unix_user> \
                  --asqa-admin-email <Asqatasun_admin_email> \
                  --asqa-admin-passwd <Asqatasun_admin_password> \
                  --firefox-esr-binary-path <path_to_Firefox_ESR_binary> \
                  --display-port <Xorg_display_port>
```

### Script options description


* **--database-user** : Mysql user for Asqatasun
* **--database-passwd** : Password of the user specified by --database-user. if this user already exists, please ensure you give its correct password. If not, the user will be automatically created.
* **--database-db** : Database for Asqatasun
* **--asqatasun-url** : URL where asqatasun will be deployed (e.g. http://localhost:8080/asqatasun)
* **--tomcat-webapps** : Tomcat webapps directory (e.g. /var/lib/tomcat7/webapps)
* **--tomcat-user** : Unix user name for the tomcat service (e.g. tomcat7)
* **--asqa-admin-email** : Email of the Asqatasun admin user (by the way, it's you !)
* **--asqa-admin-passwd** : The asqatasun application admin password
* **--firefox-esr-binary-path** : Path to Firefox-ESR binary (e.g. /opt/firefox/firefox) you installed in Asqatasun pre-requesites.
* **--display-port** : Xorg display port (e.g. ":99"). For debug purpose, you may want asqatasun to display firefox instances in the current X session (for desktop). In this case, use ":0" as value and execute the "xhost +" in a terminal to authorize the process (owned by tomcat) to use the X server.

### Example of install-script invocation

```sh
sudo ./install.sh --database-user asqatasun \
                  --database-passwd $MyTGPassWord \
                  --database-db asqatasun \
                  --asqatasun-url http://localhost:8080/asqatasun/ \
                  --tomcat-webapps /var/lib/tomcat7/webapps/ \
                  --tomcat-user tomcat7 \
                  --asqa-admin-email me@email.com \
                  --asqa-admin-passwd toto42 \
                  --firefox-esr-binary-path /opt/firefox/firefox \
                  --display_port :99
```
