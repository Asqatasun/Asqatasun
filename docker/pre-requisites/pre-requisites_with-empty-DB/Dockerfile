FROM asqatasun/asqatasun:pre-requisites_without-DB
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
     DATABASE_DB="asqatasun"                           \
     DATABASE_HOST="localhost"                         \
     DATABASE_USER="asqatasun"                         \
     DATABASE_PASSWD="asqaP4sswd"                      \
     ASQA_RELEASE="4.1.1-SNAPSHOT"

EXPOSE $WWWPORT



# ##########################################################
#
# Asqatasun installation
# cf http://doc.asqatasun.org/en/10_Install_doc/
#
# ##########################################################

#
RUN  cd      /root                                  && \
     wget https://raw.githubusercontent.com/Asqatasun/Asqatasun/develop/web-app/tgol-resources/src/main/resources/installation/pre-requisites-SQL.sh  && \
     chmod +x pre-requisites-SQL.sh                 && \
     ./pre-requisites-SQL.sh                        && \
     rm -f  pre-requisites-SQL.sh                   && \
     apt-get clean                                  && \
     apt-get autoremove                             && \
     rm -rf  /var/lib/apt/lists/*            

CMD  service mysql start                            && \
     sleep   5                                      && \
     service xvfb start                             && \
     service tomcat7 start                          ;  \
     tail -f /var/log/tomcat7/catalina.out
