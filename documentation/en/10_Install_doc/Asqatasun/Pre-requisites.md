# Prerequisites for Asqatasun web application installation

You should have already done these steps:

1. [Check Hardware provisioning](Hardware_network_provisioning.md)
2. [Download Asqatasun](Download.md)

## Check Pre-requisites : automated way

### Note: Java8 required since v4.1.0

Since Asqatasun v4.1.0, Java 8 (OpenJDK8) is required. 

### Pre-requisites script

Once you have extracted the tarball, to Check pre-requisites, you can run the script:

```sh
cd install
./pre-requisites.sh
./pre-requisites-SQL.sh
```

### A few noteworthy information

* This script is intended to be used on a freshly installed Ubuntu 14.04 (no Mysql, 
no Tomcat already installed).
* You can use the default values or **adjust them** to suite your needs (directly edit the file).
* This script must be run as root
* Pre-requisites are important, and **each detail is important** (e.g. Mysql specific 
configuration, or Tomcat user configuration, or Firefox version requirement...), so please
do care about it :)

### Pre-defined values for MYSQL

* Mysql root password: `mysqlRootPassword`
* Mysql asqatasun password: `asqaP4sswd`

### Don't like our script ?

If you have a Tomcat or Mysql already installed, or if you don't feel comfortable
with running a script as root, you can review the manual way just below.

## Next step (after automated way)

Go to [Installation](Installation.md).


## Check Pre-requisites : manual way

If you don't feel comfortable with running a script as root, here are the
required steps for completing the pre-requisites.

In a few words : install packages, then configure.

(Ever need help ? Go to [Asqatasun Forum](http://forum.asqatasun.org))

### Packages to install

```sh
sudo apt-get -y --no-install-recommends install \
    wget \
    bzip2 \
    unzip \
    mysql-server \
    libmysql-java \
    tomcat7 \
    xvfb \
    libdbus-glib-1-2 \
    openjdk-8-jre
```

### Configure Open JDK as default

```sh
sudo update-java-alternatives -s java-1.7.0-openjdk
```

### Mysql Configuration

Create a config file for Mysql dedicated to Asqatasun:

```sh
cat >/etc/mysql/conf.d/asqatasun.cnf <<EOF
[client]
default-character-set=utf8

[mysql]
default-character-set=utf8

[mysqld]
collation-server = utf8_general_ci
init-connect='SET NAMES utf8'
character-set-server = utf8
max_allowed_packet = 64M
innodb_file_per_table = 1
EOF
```

Restart mysql service
```sh
sudo service mysql restart
```

### Mysql: database creation

Create an empty schema and a asqatasun user. Grant this asqatasun user permissions 
to create, update and delete objects for this schema. The charset of the database 
has to be set to "UTF-8".

```sql
GRANT USAGE ON * . * TO '$AsqatasunUser'@'localhost' IDENTIFIED BY '$AsqatasunPassword';
CREATE DATABASE IF NOT EXISTS `$AsqatasunDatabase` CHARACTER SET utf8;
GRANT ALL PRIVILEGES ON `$AsqatasunDatabase` . * TO '$AsqatasunUser'@'localhost';
FLUSH PRIVILEGES;
```

where `$AsqatasunUser` is the asqatasun user, `$AsqatasunPassword` is the asqatasun user password, and `$AsqatasunDatabase` is the asqatasun database.

### Configure Tomcat 

Create the following symlinks : 
```sh
sudo ln -s /usr/share/java/mysql-connector-java.jar /usr/share/tomcat7/lib/mysql-connector-java.jar
```

### Configure Xvfb

Create the startup script in /etc/init.d/xvfb

```sh
cat >/etc/init.d/xvfb <<EOF
#!/bin/sh
### BEGIN INIT INFO
# Provides:          xvfb
# Required-Start:    $remote_fs $syslog
# Required-Stop:     $remote_fs $syslog
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: XVFB - Virtual X server display
# Description:       XVFB - Virtual X server display
### END INIT INFO

# Author: Matthieu Faure <mfaure@asqatasun.org>

# Do NOT "set -e"
# TODO: improve with help from /etc/init.d/skeleton

RUN_AS_USER=tomcat7
OPTS=":99 -screen 1 1024x768x24 -nolisten tcp"
XVFB_DIR=/usr/bin
PIDFILE=/var/run/xvfb

case $1 in

start)
	start-stop-daemon --chuid $RUN_AS_USER -b --start --exec $XVFB_DIR/Xvfb --make-pidfile --pidfile $PIDFILE -- $OPTS &
;;

stop)
	start-stop-daemon --stop --user $RUN_AS_USER --pidfile $PIDFILE
	rm -f $PIDFILE
;;

restart)
	if start-stop-daemon --test --stop --user $RUN_AS_USER --pidfile $PIDFILE >/dev/null; then
		$0 stop
	fi;
	$0 start
;;

*)
	echo "Usage: $0 (start|restart|stop)"
	exit 1
;;

esac

exit 0

EOF
```

Configure Xvfb to run at startup and launch it
```sh
sudo chmod +x /etc/init.d/xvfb
sudo update-rc.d xvfb defaults
sudo /etc/init.d/xvfb start

```

### Firefox

#### For 32-bit architecture

Retrieve the [Firefox ESR v31](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2).

Install it on your file system and make sure the binary is executable for the tomcat7 user.

```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```

#### For 64-bit architecture

Retrieve the [Firefox ESR 31](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2).

Install it on your file system and make sure the binary is executable for the tomcat7 user.

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
