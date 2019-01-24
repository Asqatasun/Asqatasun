# Upgrade from Asqatasun v4.0.3 to v4.1.0

Here is all the information you need to upgrade your Asqatasun instance. All information is given to be done manually.
You may use the provided script to automate it.

## Scripted upgrade

## Steps

The following steps detail what has to be done.

## Stop Tomcat

```
service tomcat7 stop
```

## Upgrade database schema with utf8mb4

### 1. Change mysql-client parameters

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

### 2. Reduce size of VARCHAR columns and indexes

### 3. Apply new character set + collation

* to columns
* to tables
* to database
* to procedures

## Adjust `asqatasun.conf`

## Copy new WAR file
