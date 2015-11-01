FROM tomcat:7-jre7
MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

USER root

# ##########################################################
#
# Pre-requesites of Asqatasun first
# https://github.com/Asqatasun/Asqatasun/blob/master/docs/prerequisites-webapp-doc.md
#
# ##########################################################

# Install packages
RUN apt-get update -qq 
RUN DEBIAN_FRONTEND=noninteractive apt-get -yqq install debconf apt-utils
# Pre-define Mysql root passwd
RUN echo 'mysql-server mysql-server/root_password password mysqlRootPassword' | debconf-set-selections
RUN echo 'mysql-server mysql-server/root_password_again password mysqlRootPassword' | debconf-set-selections
RUN DEBIAN_FRONTEND=noninteractive apt-get -yqq install wget bzip2 mysql-server libspring-instrument-java xvfb && \
    rm -rf /var/lib/apt/lists/*
RUN service mysql start

RUN mysql -u root --password="mysqlRootPassword" --execute="show databases;" 

# /!\ TODO /!\
# custom mysql buffer packet 64M

# Add symlinks 
RUN ln -s /usr/share/java/spring3-instrument-tomcat.jar /usr/local/tomcat/bin/spring3-instrument-tomcat.jar
RUN ln -s /usr/share/java/mysql-connector-java.jar /usr/local/tomcat/bin/mysql-connector-java.jar

# Configure XVFB
ADD xvfb /etc/init.d/
RUN chmod +x /etc/init.d/xvfb
RUN /etc/init.d/xvfb start
RUN update-rc.d xvfb defaults
RUN service xvfb start

# Install Firefox
WORKDIR /opt
RUN wget -q http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/31.4.0esr/linux-i686/en-US/firefox-31.4.0esr.tar.bz2
RUN tar xvfj firefox-31.4.0esr.tar.bz2
RUN mv firefox firefox-31.4.0esr
RUN ln -s firefox-31.4.0esr firefox

# Create Asqatasun database
RUN mysql -u root --password="mysqlRootPassword" --execute="GRANT USAGE ON * . * TO 'asqatasun'@'localhost' IDENTIFIED BY 'asqaP4sswd'; \
    CREATE DATABASE IF NOT EXISTS asqatasun CHARACTER SET utf8; \
    GRANT ALL PRIVILEGES ON asqatasun . * TO 'asqatasun'@'localhost'; \
    FLUSH PRIVILEGES;"

# ##########################################################
#
# Asqatasun installation
# https://github.com/Asqatasun/Asqatasun/blob/master/docs/INSTALL.md
#
# ##########################################################

# Add Asqatasun
WORKDIR /root
ADD asqatasun-latest.tar.gz /root/
#RUN ls -Al /root/
# /!\ Trick here: Docker automacally untars and unzips the ADDed file
RUN mv asqatasun*/ ./asqatasun/

# Install Asqatasun
WORKDIR /root/asqatasun
RUN echo "yes\n" | ./install.sh  --database-db asqatasun \ 
				 --database-host localhost \
				 --database-user asqatasun \
				 --database-passwd asqaP4sswd \
				 --asqatasun-url http://localhost:8080/asqatasun/ \
				 --tomcat-webapps /var/lib/tomcat7/webapps \
				 --tomcat-user tomcat7 \
				 --asqa-admin-email me@email.org \
				 --asqa-admin-passwd emailPassword \
				 --firefox-esr-binary-path /opt/firefox/firefox \
				 --display-port :99 
