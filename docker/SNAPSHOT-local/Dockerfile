FROM asqatasun/asqatasun:pre-requisites_with-empty-DB

# #### DISCLAIMER ###################################################################
#
#   This is a fat container, that is absolutly not compliant to Docker best-practices
#   https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/
#
#   Don't use it for production as all data are wiped out at reboot / rebuild
#   BUT for quick testing, that does the job :)
# ###################################################################################


# #### FROM  #######################################################
#   Ubuntu:14.04
#   |-- asqatasun/asqatasun:base_wget
#       |-- asqatasun/asqatasun:only-DB
#           |-- asqatasun/asqatasun:pre-requisites_without-DB
#               |-- asqatasun/asqatasun:pre-requisites_with-empty-DB  
####################################################################

RUN \
    apt-get update && \
    apt-get -y install software-properties-common && \
    add-apt-repository -y ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get -y install openjdk-8-jdk && \
    update-java-alternatives -s java-1.8.0-openjdk-amd64

USER root
ENV  JAVA_HOME="/usr/lib/jvm/java-8-openjdk-amd64/"    \
     WWWPORT="8080"                                    \
     DATABASE_DB="asqatasun"                           \
     DATABASE_HOST="localhost"                         \
     DATABASE_USER="asqatasun"                         \
     DATABASE_PASSWD="asqaP4sswd"                      \
     TOMCAT_WEBAPP_DIR="/var/lib/tomcat7/webapps"      \
     TOMCAT_USER="tomcat7"                             \
     ASQA_URL="http://localhost:8080/asqatasun/"       \
     ASQA_ADMIN_EMAIL="admin@asqatasun.org"                \
     ASQA_ADMIN_PASSWD="myAsqaPassword"                \
     ASQA_RELEASE="4.1.0-SNAPSHOT"

EXPOSE $WWWPORT

# ##########################################################
#
# Asqatasun installation
# cf http://doc.asqatasun.org/en/10_Install_doc/
#
# ##########################################################

# Add Asqatasun
ADD ./asqatasun-${ASQA_RELEASE}.i386.tar.gz /root/

# Install Asqatasun
RUN  cd  /root                                      && \
     mv   asqatasun*/    ./asqatasun/               && \
     service mysql start                            && \
     sleep 5                                        && \
     cd   /root/asqatasun/install/                  && \
     echo "yes\n" | ./install-SQL.sh           \
        --database-db       $DATABASE_DB       \
        --database-host     $DATABASE_HOST     \
        --database-user     $DATABASE_USER     \
        --database-passwd   $DATABASE_PASSWD        && \
     echo "yes\n" | ./install.sh               \
        --database-db       $DATABASE_DB       \
        --database-host     $DATABASE_HOST     \
        --database-user     $DATABASE_USER     \
        --database-passwd   $DATABASE_PASSWD   \
        --asqatasun-url     $ASQA_URL          \
        --tomcat-webapps    $TOMCAT_WEBAPP_DIR \
        --tomcat-user       $TOMCAT_USER       \
        --asqa-admin-email  $ASQA_ADMIN_EMAIL  \
        --asqa-admin-passwd $ASQA_ADMIN_PASSWD \
        --firefox-esr-binary-path /opt/firefox/firefox \
        --display-port      :99                     && \
     rm -rf  /root/asqatasun*


# Health Check of the Docker Container
HEALTHCHECK --timeout=3s    \
            --interval=10s  \
            CMD curl --fail http://localhost:8080/asqatasun/ || exit 1
            ######################################################################
            # doc: https://docs.docker.com/engine/reference/builder/#healthcheck
            # options:  --interval=DURATION (default: 30s)
            #           --timeout=DURATION (default: 30s)
            #           --retries=N (default: 3)
            # can be overridden at the command line 'docker run --health-cmd (...)'
            ######################################################################

CMD  service mysql start                            && \
     sleep   5                                      && \
     service xvfb start                             && \
     echo "JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/" >> /etc/default/tomcat7 && \
     service tomcat7 start                          ;  \
            ls -l /usr/share/tomcat7/lib/spring3-instrument-tomcat.jar  ;  \
            ls -l /usr/share/java/spring3-instrument-tomcat.jar         ;  \
            ls -l /usr/share/java/spring3-instrument-tomcat-3.x.jar     ;  \
     tail -f /var/log/tomcat7/catalina.out     \
             /var/log/asqatasun/asqatasun.log


