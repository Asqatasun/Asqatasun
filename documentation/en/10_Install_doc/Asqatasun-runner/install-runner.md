# Asqatasun-Runner Installation

This page describes the steps to follow to install Asqatasun since Version 3.0.0 (and further) from the binary files or from the sources.

PLEASE ensure you meet all [prerequisites for Asqatasun-Runner 4.x on Ubuntu](prerequisites-runner.md).

## Download asqatasun tarball and extract content

Retrieve the [lastest version of Asqatasun](https://github.com/Asqatasun/Asqatasun/releases/latest). 

Extract it on your file system. The extraction will create a `asqatasun-runner-<version>.<arch>` folder. This folder will become the **execution context** of the installation.

## Create and populate database

**Warning** : If this installation is dedicated to work with the Asqatasun Jenkins plugin, this step is not required. The creation of the database is done when installing the asqatasun Web application. The runner installation just needs to be set with the database access properties (see [next paragraph](#configure-context-with-database-access-properties))

### Create database

Create an empty schema and a asqatasun user. Grant this asqatasun user permissions to create, update and delete objects for this schema. The charset of the database has to be set to "UTF-8".

```mysql
GRANT USAGE ON * . * TO '$tgUser'@'localhost' IDENTIFIED BY '$tgPassword';
CREATE DATABASE IF NOT EXISTS `$tgDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `$tgDatabase` . * TO '$tgUser'@'localhost';
FLUSH PRIVILEGES;
```

where $tgUser is the asqatasun user, $tgPassword is the asqatasun user password, and $tgDatabase is the asqatasun database.

### Populate database

Execute the table creation script and data insertion scripts

```sh
mysql -u ${asqatasun-user} -p < install/engine/sql/asqatasun-20-create-tables.sql
mysql -u ${asqatasun-user} -p < install/engine/sql/asqatasun-30-insert.sql 
mysql -u ${asqatasun-user} -p < install/rules/sql/10-rules-resources-insert.sql
```

Then, modify the 3 following scripts, install/rules/sql/accessiweb2.2-insert.sql, install/rules/sql/rgaa2.2-insert.sql and install/rules/sql/rgaa3.0-insert.sql by removing the lines starting with "INSERT IGNORE INTO `TGSI_REFERENTIAL`" and execute the scripts

```sh
mysql -u ${asqatasun-user} -p < install/rules/sql/accessiweb2.2-insert.sql
mysql -u ${asqatasun-user} -p < install/rules/sql/rgaa2.2-insert.sql
mysql -u ${asqatasun-user} -p < install/rules/sql/rgaa3.0-insert.sql
```

## Configure context with database access properties

Edit asqatasun.conf file and replace all stars '\*\*\*\*\*\*\*\*\*' with the correct user and password for your asqatasun mysql and replace the content of the jdbc.url variable with the right settings.

```sh
vi conf/context/asqatasun.conf
```

## Next

See [Asqatasun-Runner usage](usage-runner.md)

