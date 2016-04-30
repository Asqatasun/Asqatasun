# Prerequisites for Asqatasun web application installation

In few words : install packages, then configure.

## Packages to install

```sh
sudo apt-get -y --no-install-recommends install \
    wget \
    bzip2 \
    unzip \
    mysql-server \
    libmysql-java \
    tomcat7 \
    libspring-instrument-java \
    xvfb \
    libdbus-glib-1-2 \
    openjdk-7-jre
```

## Configure Open JDK as default

```sh
sudo update-java-alternatives -s java-1.7.0-openjdk
```

## Mysql Configuration

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

## Configure Tomcat 

Create the following symlinks : 
```sh
sudo ln -s /usr/share/java/spring3-instrument-tomcat.jar /usr/share/tomcat7/lib/spring3-instrument-tomcat.jar
sudo ln -s /usr/share/java/mysql-connector-java.jar /usr/share/tomcat7/lib/mysql-connector-java.jar
```

## Configure Xvfb

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

## Firefox

### For 32-bit architecture

Retrieve the [Firefox ESR v31](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2).

Install it on your file system and make sure the binary is executable for the tomcat7 user.

```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```

### For 64-bit architecture

Retrieve the [Firefox ESR 31](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2).

Install it on your file system and make sure the binary is executable for the tomcat7 user.

```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```

## Mail SMTP

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

