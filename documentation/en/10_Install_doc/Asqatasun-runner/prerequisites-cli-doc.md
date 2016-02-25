# Prerequisites for Asqatasun-Runner 

## Open JDK

You need to have a JDK and JRE installed. openjdk-7-jre and openjdk-7-jdk are suggested.
Install the following packages
```sh
sudo aptitude install openjdk-7-jre
sudo update-java-alternatives -s java-1.7.0-openjdk
```

## Mysql

### Installation

Install the following packages
```sh
sudo aptitude install mysql-server-5.5 libmysql-java
```

### Configuration

Edit the my.cnf mysql configuration file.
```sh
sudo vi /etc/mysql/my.cnf
```

Set the max_allowed_packet option to 64M (default is 16M)
```sh
max_allowed_packet = 64M
```

Restart mysql service
```sh
sudo service mysql restart
```

## Xvfb

Install the following packages
```sh
sudo aptitude install xvfb
```

Create the startup script in /etc/init.d/xvfb-tanaguru-cli
```sh
sudo touch /etc/init.d/xvfb-tanaguru-cli
```

Add the following content to the xvfb startup script. Don't forget to replace the $My_User string with the name of the user that will perform the analysis (jenkins for instance)
```sh
#!/bin/sh

set -e

RUN_AS_USER=$My_User
OPTS=":99 -screen 1 1024x768x24 -nolisten tcp"
XVFB_DIR=/usr/bin
PIDFILE=/var/run/xvfb-tanaguru-cli

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
```

start Xvfb
```sh
sudo chmod +x /etc/init.d/xvfb-tanaguru-cli
sudo /etc/init.d/xvfb-tanaguru-cli start
```

Configure Xvfb to run at startup
```sh
sudo update-rc.d xvfb-tanaguru-cli defaults
```

## Firefox

### For 32-bit architecture
Retrieve the [lastest esr version of firefox](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2).
Install it on your file system and make sure the binary is executable for the user that will perform the analysis (jenkins for instance)
```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```

### For 64-bit architecture
Retrieve the [lastest esr version of firefox](http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2).
Install it on your file system and make sure the binary is executable for the user that will perform the analysis (jenkins for instance)
```sh
cd /opt
sudo wget http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-x86_64/en-US/firefox-31.4.0esr.tar.bz2
sudo tar xvfj firefox-31.4.0esr.tar.bz2
sudo mv firefox firefox-31.4.0esr
sudo ln -s firefox-31.4.0esr firefox
```
