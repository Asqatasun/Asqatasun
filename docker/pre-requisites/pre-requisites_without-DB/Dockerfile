FROM asqatasun/asqatasun:base_wget
MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

# ##########################################################
#
#                      DISCLAIMER
#
# This is a fat container, that is absolutly not compliant to Docker best-practices
# https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/
# Don't use it for production as all data are wiped out at reboot / rebuild
# BUT for quick testing, that does the job :)
#
# ##########################################################

USER root
ENV  WWWPORT="8080"                                    \
     TOMCAT_WEBAPP_DIR="/var/lib/tomcat7/webapps"      \
     TOMCAT_USER="tomcat7"                             \
     ASQA_RELEASE="4.1.0-SNAPSHOT"

EXPOSE $WWWPORT


# ##########################################################
#
# Asqatasun installation
# cf http://doc.asqatasun.org/en/10_Install_doc/
#
# ##########################################################

# Add Asqatasun
# Install Asqatasun
RUN  cd  /root                                      && \
     apt-get update                                 && \
     apt-get -y --no-install-recommends      \
                install wget ca-certificates        && \
     wget http://binaries.asqatasun.org/asqatasun-${ASQA_RELEASE}.i386.tar.gz  && \
     tar  xvfz asqatasun-*.tar.gz                   && \
     mv   asqatasun*/  ./asqatasun/                 && \
     cd   asqatasun/install/                        && \
     ./pre-requisites.sh                            && \
     rm -rf  /root/asqatasun*                       && \
     apt-get clean                                  && \
     apt-get autoremove                             && \
     rm -rf  /var/lib/apt/lists/*

CMD  service xvfb start                             && \
     service tomcat7 start                          ;  \
     tail -f /var/log/tomcat7/catalina.out
