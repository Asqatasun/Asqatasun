# Tanaguru Command Line Interface Installation

This page describes the steps to follow to install Tanaguru since Version 3.0.0 (and further) from the binary files or from the sources. The sofware has been tested on 12.04 LTS (Precise Pangolin) and 14.04 LTS (Trusty Tahr).

PLEASE ensure you meet all [prerequisites for Tanaguru 3.x on Ubuntu](#prerequisites).

## Download tanaguru tarball and extract content

Retrieve the [lastest version of Tanaguru](http://download.tanaguru.org/Tanaguru/tanaguru-latest.tar.gz). 

Extract it on your file system. The extraction will create a "tanaguru" folder. This folder will become the execution context of the installation.

## Create and populate database

Warning : If this installation is dedicated to work with the Tanaguru Jenkins plugin, this step is not required. The creation of the database is done when installing the tanaguru Web application. The cli installation just needs to be set with the database access properties (see [next paragraph](#configure-context-with-database-access-properties))

### Create database

Create an empty schema and a tanaguru user. Grant this tanaguru user permissions to create, update and delete objects for this schema. The charset of the database has to be set to "UTF-8".

```mysql
GRANT USAGE ON * . * TO '$tgUser'@'localhost' IDENTIFIED BY '$tgPassword';
CREATE DATABASE IF NOT EXISTS `$tgDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `$tgDatabase` . * TO '$tgUser'@'localhost';
FLUSH PRIVILEGES;
```

where $tgUser is the tanaguru user, $tgPassword is the tanaguru user password, and $tgDatabase is the tanaguru database.

### Populate database

Execute the table creation script and data insertion scripts

```sh
mysql -u ${tanaguru-user} -p < install/engine/sql/tanaguru-20-create-tables.sql
mysql -u ${tanaguru-user} -p < install/engine/sql/tanaguru-30-insert.sql 
mysql -u ${tanaguru-user} -p < install/rules/sql/10-rules-resources-insert.sql
```

Then, modify the 3 following scripts, install/rules/sql/accessiweb2.2-insert.sql, install/rules/sql/rgaa2.2-insert.sql and install/rules/sql/rgaa3.0-insert.sql by removing the lines starting with "INSERT IGNORE INTO `TGSI_REFERENTIAL`" and execute the scripts

```sh
mysql -u ${tanaguru-user} -p < install/rules/sql/accessiweb2.2-insert.sql
mysql -u ${tanaguru-user} -p < install/rules/sql/rgaa2.2-insert.sql
mysql -u ${tanaguru-user} -p < install/rules/sql/rgaa3.0-insert.sql
```

## Configure context with database access properties

Edit tanaguru.conf file and replace all stars '\*\*\*\*\*\*\*\*\*' with the correct user and password for your tanaguru mysql and replace the content of the jdbc.url variable with the right settings.

```sh
vi conf/context/tanaguru.conf
```
