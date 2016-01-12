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
    ASQA_ADMIN_PASSWD="myAsqaPassword"  \
    ASQA_RELEASE="4.0.0-SNAPSHOT"

EXPOSE $WWWPORT

# ##########################################################
#
# Asqatasun pre-requisites
# @@@TODO provide suitable link from http://doc.asqatasun.org/
#
# ##########################################################

WORKDIR /root
ADD pre-requisites.sh xvfb /root/
RUN /root/pre-requisites.sh

# ##########################################################
#
# Asqatasun installation
# @@@TODO provide suitable link from http://doc.asqatasun.org/
#
# ##########################################################

# Add Asqatasun
ADD http://download.asqatasun.org/asqatasun-${ASQA_RELEASE}.tar.gz /root/
RUN tar xvfz asqatasun-${ASQA_RELEASE}.tar.gz && \
    mv asqatasun*/ ./asqatasun/

# The two following commands are needed for local testing when a release is created
#ADD asqatasun-${ASQA_RELEASE}.i386.tar.gz /root/
#RUN mv asqatasun*/ ./asqatasun/

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
