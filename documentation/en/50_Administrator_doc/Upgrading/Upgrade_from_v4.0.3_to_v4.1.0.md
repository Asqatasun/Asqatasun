# Upgrade from Asqatasun v4.0.3 to v4.1.0

## TL;DR

1. Export data from host with v4.0.3
2. Install on another host a fresh v4.1.0
3. Import data from v4.0.3
4. Update SQL schema
5. Enjoy

## 1. Export data from v4.0.3

1. Stop Apache
1. Stop Tomcat
1. Then export DB with

```shell script
mysqldump \
  --user=asqatasun \
  -p \
  --databases asqatasun \
  --result-file=SAVE_DB_asqatasun.sql
```

## 2. Install Asqatasun v4.1.0

* Pre-requisites: Ubuntu 18.04, OpenJDK8
* Follow [Asqatasun Installation](https://doc.asqatasun.org/en/10_Install_doc/Asqatasun/index.html)

## 3. Import data from v4.0.3

```shell script
systemctl stop tomcat8.service
mysql --user=asqatasun -p -e "drop database asqatasun;"
mysql --user=asqatasun -p <SAVE_DB_asqatasun.sql
``` 

## 4. Update SQL schema

Grab the two following file:

* [asqatasun-40-update-from-4.0.3-to-4.1.0.sql](https://raw.githubusercontent.com/Asqatasun/Asqatasun/v4.1.0/engine/asqatasun-resources/src/main/resources/sql-update/asqatasun-40-update-from-4.0.3-to-4.1.0.sql)
* [tgol-40-update-from-4.0.3-to-4.1.0.sql](https://raw.githubusercontent.com/Asqatasun/Asqatasun/v4.1.0/web-app/tgol-resources/src/main/resources/sql-update/tgol-40-update-from-4.0.3-to-4.1.0.sql)

And apply the following (order in important):

```shell script
mysql --user=asqatasun -p asqatasun <asqatasun-40-update-from-4.0.3-to-4.1.0.sql
mysql --user=asqatasun -p asqatasun <tgol-40-update-from-4.0.3-to-4.1.0.sql
systemctl start tomcat8.service
```

## 5. Use Asqatasun 4.1.0

* Accounts, credentials and projects have been imported, so you can use your usual login / password

## Other information

One may have to update a few field definitions:

```mariadb
ALTER TABLE EVIDENCE_ELEMENT
    MODIFY `Element_Value` mediumtext NOT NULL;
ALTER TABLE PRE_PROCESS_RESULT
    MODIFY `Pre_Process_Value` mediumtext DEFAULT NULL;
ALTER TABLE PROCESS_REMARK
    MODIFY `Snippet` mediumtext DEFAULT NULL;
ALTER TABLE PROCESS_RESULT
    MODIFY `Indefinite_Value` mediumtext DEFAULT NULL;
ALTER TABLE TGSI_SCENARIO
    MODIFY `Content` mediumtext NOT NULL;
```
