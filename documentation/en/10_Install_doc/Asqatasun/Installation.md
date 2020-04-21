# Asqatasun Installation

You should have already done these steps:

1. [Check Hardware provisioning](Hardware_network_provisioning.md)
2. [Download Asqatasun](Download.md)
3. [Check pre-requisites](Pre-requisites.md)

(Ever need help ? Go to [Asqatasun Forum](https://forum.asqatasun.org).)

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

* **--database-user** : MySQL user for Asqatasun
* **--database-passwd** : Password of the user specified by --database-user. if this user already exists, please ensure you give its correct password. If not, the user will be automatically created.
* **--database-db** : Database for Asqatasun
* **--asqatasun-url** : URL where asqatasun will be deployed (e.g. `http://localhost:8080/asqatasun`)
* **--tomcat-webapps** : Tomcat webapps directory, without trailing slash (e.g. /var/lib/tomcat8/webapps)
* **--tomcat-user** : Unix user name for the tomcat service (e.g. tomcat8)
* **--asqa-admin-email** : Email of the Asqatasun admin user (by the way, it's you !)
* **--asqa-admin-passwd** : The asqatasun application admin password
* **--firefox-esr-binary-path** : Path to Firefox-ESR binary (e.g. /opt/firefox/firefox) you installed in Asqatasun pre-requisites.
* **--display-port** : Xorg display port (e.g. ":99"). For debug purpose, you may want asqatasun to display firefox instances in the current X session (for desktop). In this case, use ":0" as value and execute the "xhost +" in a terminal to authorize the process (owned by tomcat) to use the X server.

### Example of install-script invocation

```sh
sudo ./install.sh --database-user asqatasun \
                  --database-passwd $MyAsqatasunPassWord \
                  --database-db asqatasun \
                  --database-host localhost \
                  --asqatasun-url http://localhost:8080/asqatasun/ \
                  --tomcat-webapps /var/lib/tomcat8/webapps \
                  --tomcat-user tomcat8 \
                  --asqa-admin-email me@email.com \
                  --asqa-admin-passwd toto42 \
                  --firefox-esr-binary-path /opt/firefox/firefox \
                  --display_port :99
```

## Next step

You can go to [Add an Apache frontend](Apache_frontend.md).
