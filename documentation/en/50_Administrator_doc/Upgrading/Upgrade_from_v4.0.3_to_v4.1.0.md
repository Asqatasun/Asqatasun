# Upgrade from Asqatasun v4.0.3 to v4.1.0

Here is all the information you need to upgrade your Asqatasun instance. All information is given to be done manually.
You may use the provided script to automate it.

## Scripted upgrade

TBD

## Steps

The following steps detail what has to be done.

## 1. Stop Tomcat

```
service tomcat7 stop
```

## 2. Change mysql-client parameters for utf8mb4

It is **important to change this first** and foremost, or the mysql-client could not be able to have a
correct communication with the storage engine.

In you `/etc/mysql/conf.d/asqatasun.cnf`, adjust the following values

```
[client]
default-character-set=utf8mb4

[mysql]
default-character-set=utf8mb4

[mysqld]
collation-server = utf8mb4_general_ci
init-connect='SET NAMES utf8mb4'
character-set-server = utf8mb4
```

## 3. Backup database

Yes do it, because you know you should :)

```shell
mysqldump -u asqatasun -p asqatasun >/tmp/BACKUP_asqatasun_$(date +%Y-%m-%d).sql
```

## 4. Apply new character set + collation + reduction of size on index columns

Apply to the `asqatasun` database the following two scripts:

TODO: backup database

```shell
/usr/bin/mysql -u asqatasun -p asqatasun < engine/asqatasun-resources/src/main/resources/sql-update/asqatasun-40-update-from-4.0.3-to-4.1.0.sql
/usr/bin/mysql -u asqatasun -p asqatasun < web-app/tgol-resources/src/main/resources/sql-update/tgol-40-update-from-4.0.3-to-4.1.0.sql
```

## 5. Adjust `asqatasun.conf`

## 6. Copy new WAR file
