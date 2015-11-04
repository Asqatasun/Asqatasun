FROM ubuntu:14.04
MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

# ##########################################################
#
#                      DISCLAIMER
#
# This is a fat container, that is absolutly not compliant to Docker best-practices
# https://docs.docker.com/articles/dockerfile_best-practices/
# Don't use it for production as all data are wiped out at reboot / rebuild
# BUT for quick testing, that does the job :)
#
# ##########################################################

USER root

# ##########################################################
#
# Asqatasun pre-requesites
# https://github.com/Asqatasun/Asqatasun/blob/master/docs/prerequisites-webapp-doc.md
#
# ##########################################################

WORKDIR /root
ADD pre-requesites.sh /root/
ADD xvfb /root/
RUN /root/pre-requesites.sh

# ##########################################################
#
# Asqatasun installation
# https://github.com/Asqatasun/Asqatasun/blob/master/docs/INSTALL.md
#
# ##########################################################

# Add Asqatasun
# @@@TODO: Remplace with downloading the .tar.gz
ADD asqatasun-3.2.0-SNAPSHOT.i386.tar.gz /root/
# /!\ Trick here: the ADD command automacally untars and unzips the ADDed file
RUN mv asqatasun*/ ./asqatasun/

# Install Asqatasun
WORKDIR /root/asqatasun
RUN service mysql start && \
    sleep 5 && \
    echo "yes\n" | ./install.sh  --database-db asqatasun \ 
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

RUN service tomcat7 restart
RUN tail -f /var/log/asqatasun/asqatasun.log
