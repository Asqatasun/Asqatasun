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

In your `/etc/mysql/conf.d/asqatasun.cnf`, as user **root**, adjust the following values

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

```shell
cd engine/asqatasun-resources/src/main/resources/sql-update/
/usr/bin/mysql -u asqatasun -p asqatasun <asqatasun-40-update-from-4.0.3-to-4.1.0.sql
cd web-app/tgol-resources/src/main/resources/sql-update/
/usr/bin/mysql -u asqatasun -p asqatasun <tgol-40-update-from-4.0.3-to-4.1.0.sql
```

## X. Delete + recreate SQL procedures

```shell
cd web-app/tgol-resources/src/main/resources/sql-management/
cat /dev/null >all-procedures.sql
for i in \
    PROCEDURE_AUDIT_delete_from_user_email.sql \
    PROCEDURE_CONTRACT_delete_from_label.sql \
    PROCEDURE_CONTRACT_functionality_add_from_contract_label.sql \
    PROCEDURE_CONTRACT_functionality_add_from_user_email.sql \
    PROCEDURE_CONTRACT_functionality_remove_from_contract_label.sql \
    PROCEDURE_CONTRACT_functionality_remove_from_user_email.sql \
    PROCEDURE_CONTRACT_referential_add_from_contract_label.sql \
    PROCEDURE_CONTRACT_referential_add_from_user_email.sql \
    PROCEDURE_CONTRACT_referential_remove_from_contract_label.sql \
    PROCEDURE_CONTRACT_referential_remove_from_user_email.sql \
    ; do
    cat "${i}" >>all-procedures.sql
done
/usr/bin/mysql -u asqatasun -p asqatasun <all-procedures.sql
```

## X. Adjust `asqatasun.conf`

* change jdbc.url=
* Switch correct Contrast-Finder URL: `contrastfinderServiceUrl=http://contrast-finder.tanaguru.com`
* change release: `asqatasunVersion=4.0.3`

NOTE: /!\ do not forget to update the original asqatasun.conf file

## X. Copy new WAR file

```shell
tar cvfz /tmp/SAVE-asqatasun-webapp.tar.gz /var/lib/tomcat7/webapps/asqatasun/
rm -rf  /var/lib/tomcat7/webapps/asqatasun/
cp asqatasun-web-app-4.1.0-SNAPSHOT.war /var/lib/tomcat7/webapps/asqatasun.war
```

## X. Reload Mysql

```shell
service mysql restart
```

## X. Restart Tomcat

```shell
service tomcat7 start
```

## X. Verify

`tail -f /var/log/tomcat7/catalina.out /var/log/asqatasun/asqatasun.log`
