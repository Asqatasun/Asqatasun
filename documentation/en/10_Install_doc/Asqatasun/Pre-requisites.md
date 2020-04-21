# Prerequisites for Asqatasun web application installation

You should have already done these steps:

1. [Check Hardware provisioning](Hardware_network_provisioning.md)
2. [Download Asqatasun](Download.md)

## Check Pre-requisites : automated way

### Note: Java8 required since v4.1.0

Since Asqatasun v4.1.0, Java 8 (OpenJDK8) is required. 

### Pre-requisites script

Once you have extracted the tarball, to Check pre-requisites, you can run the scripts:

```sh
cd install
./pre-requisites.sh
./pre-requisites-SQL.sh
```

### A few noteworthy information

* These scripts are intended to be used on a freshly installed Ubuntu 18.04 (no MariaDB, 
no Tomcat already installed).
* You can use the default values or **adjust them** to suite your needs (directly edit the file).
* This script must be run as root
* Pre-requisites are important, and **each detail is important** (e.g. MariaDB specific 
configuration, or Tomcat user configuration, or Firefox version requirement...), so please
do care about it :)

### Pre-defined values for MYSQL

* MariaDB root password: `mysqlRootPassword`
* MariaDB asqatasun password: `asqaP4sswd`

### Don't like our script ?

If you have a Tomcat or Mysql already installed, or if you don't feel comfortable
running a script as root, you can review the manual way just below.

## Next step (after automated way)

Go to [Installation](Installation.md).


## Check Pre-requisites : manual way

If you don't feel comfortable running a script as root, here are the
required steps for completing the pre-requisites.

In a few words : install packages, then configure.

(Ever need help ? Go to [Asqatasun Forum](http://forum.asqatasun.org))

### Packages to install

```sh
sudo apt-get -y --no-install-recommends install \
    bzip2 \
    libdbus-glib-1-2 \
    libgtk2.0-0 \
    libmysql-java \
    mariadb-server \
    openjdk-8-jre \
    tomcat8 \
    unzip \
    wget \
    xvfb \
```

### Configure Open JDK as default

```sh
sudo update-java-alternatives -s java-1.8.0-openjdk-amd64
```

### MariaDB Configuration

Create a config file for Mysql dedicated to Asqatasun:

```sh
cat >/etc/mysql/conf.d/asqatasun.cnf <<EOF
[client]
default-character-set=utf8mb4

[mysql]
default-character-set=utf8mb4

[mysqld]
collation-server = utf8mb4_general_ci
init-connect='SET NAMES utf8mb4'
character-set-server = utf8mb4
max_allowed_packet = 64M
innodb_file_per_table = 1
innodb_log_file_size = 256M
EOF
```

Then:

```shell script
echo '!include /etc/mysql/conf.d/asqatasun.cnf' >>/etc/mysql/my.cnf
```

(This may seem counterintuitive, but this is a workaround for a bug in MariaDB packaging on Debian/Ubuntu.
See https://github.com/Asqatasun/Asqatasun/issues/311 for more details.)


Restart mysql service

```sh
sudo service mysql restart
```

### MariaDB: database creation

Create an empty schema and a asqatasun user. Grant this asqatasun user permissions 
to create, update and delete objects for this schema. The charset of the database 
has to be set to "UTF-8".

```mysql
GRANT USAGE ON * . * TO 'AsqatasunUser'@'localhost' IDENTIFIED BY 'AsqatasunPassword';
CREATE DATABASE IF NOT EXISTS `AsqatasunDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `AsqatasunDatabase` . * TO 'AsqatasunUser'@'localhost';
FLUSH PRIVILEGES;
```

where `AsqatasunUser` is the asqatasun user, `AsqatasunPassword` is the asqatasun user password,
and `AsqatasunDatabase` is the asqatasun database.

### Configure Tomcat 

Create the following symlinks : 
```sh
sudo ln -s /usr/share/java/mysql-connector-java.jar /usr/share/tomcat8/lib/mysql-connector-java.jar
```

### Configure Xvfb

Copy XVFB unit script to its location. From install directory, do:

```shell script
sudo cp install/xvfb@.service /etc/systemd/system
sudo chmod +x /etc/systemd/system/xvfb@.service
```

Configure Xvfb to run at startup and launch it

```shell script
sudo /bin/systemctl enable "xvfb@:99.service"
sudo /bin/systemctl start "xvfb@:99.service"
```

### Firefox

Retrieve the [Firefox ESR 31](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2).

Install it on your file system and make sure the binary is executable for the tomcat8 user.

```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```

### Mail SMTP

Asqatasun works better with email (informing you when an audit is finished, or if gives an error).
Here a the steps to install locally an SMTP server. You can also use online services 
such as MailJet or Mandrill and configure it into `/etc/asqatasun/asqatasun.org`

Install the following packages
```sh
sudo aptitude install postfix mailutils
sudo dpkg-reconfigure postfix
```
Once the configuration is displayed, options are :

* configuration type: satellite
* SMTP relay: &lt;none&gt; (this is the trick, don't type anything here)

## Next step

Congratulations, you survived the manual pre-requisites stage :)

You can go to [Installation](Installation.md).
