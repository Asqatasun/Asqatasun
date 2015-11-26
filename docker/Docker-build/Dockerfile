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

ENV WWWPORT="8080" \
    DATABASE_DB="asqatasun" \
    DATABASE_HOST="localhost" \
    DATABASE_USER="asqatasun" \
    DATABASE_PASSWD="asqaP4sswd" \
    TOMCAT_WEBAPP_DIR="/var/lib/tomcat7/webapps" \
    TOMCAT_USER="tomcat7" \
    ASQA_URL="http://localhost:8080/asqatasun/" \
    ASQA_ADMIN_EMAIL="me@my-email.org" \
    ASQA_ADMIN_PASSWD="myAsqaPassword" 

EXPOSE $WWWPORT

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
ADD asqatasun-4.0.0-beta1.i386.tar.gz /root/
# /!\ The ADD command automacally untars and unzips the ADDed file
RUN mv asqatasun*/ ./asqatasun/

# Install Asqatasun
WORKDIR /root/asqatasun
RUN service mysql start && \
    sleep 5 && \
    echo "yes\n" | ./install.sh  \
        --database-db $DATABASE_DB \ 
        --database-host $DATABASE_HOST \
        --database-user $DATABASE_USER \
        --database-passwd $DATABASE_PASSWD \
        --asqatasun-url $ASQA_URL \
        --tomcat-webapps $TOMCAT_WEBAPP_DIR \
        --tomcat-user $TOMCAT_USER \
        --asqa-admin-email $ASQA_ADMIN_EMAIL \
        --asqa-admin-passwd $ASQA_ADMIN_PASSWD \
        --firefox-esr-binary-path /opt/firefox/firefox \
        --display-port :99

CMD service mysql start && \
    sleep 5 && \
    service xvfb start && \
    service tomcat7 start ; \
    tail -f /var/log/tomcat7/catalina.out \
         /var/log/asqatasun/asqatasun.log
