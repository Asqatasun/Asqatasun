FROM ubuntu:18.04
LABEL org.label-schema.schema-version = "1.0.0-rc.1"   \
      org.label-schema.name           = "Asqtasun SNAPSHOT - WebApp (Ubuntu 18.04 - Java 8)"
          # ----> documentation  http://label-schema.org/
################################################################################################################

# Environment variables
ENV    WWWPORT="8080"                        \
       DB_PORT="3306"                        \
       DB_HOST="asqatasun-db_dev"            \
       DB_NAME="asqatasun"                   \
       ASQA_RELEASE="5.0-SNAPSHOT"           \
       FIREFOX_VERSION="68.9.0esr"           \
       GECKODRIVER_VERSION="v0.26.0"         \
       FIREFOX_URL_PREFIX="https://download-installer.cdn.mozilla.net/pub/firefox/releases/"  \
       GECKODRIVER_URL_PREFIX="https://github.com/mozilla/geckodriver/releases/download/" \
       LOG_DIR="/var/log/asqatasun/"
ENV    ASQA_URL="http://localhost:${WWWPORT}/"
EXPOSE ${WWWPORT}

# Build variables
ARG FIREFOX_URL="${FIREFOX_URL_PREFIX}${FIREFOX_VERSION}/linux-x86_64/en-US/firefox-${FIREFOX_VERSION}.tar.bz2"
ARG GECKODRIVER_URL="${GECKODRIVER_URL_PREFIX}${GECKODRIVER_VERSION}/geckodriver-${GECKODRIVER_VERSION}-linux64.tar.gz"
ARG WAR_FILE="asqatasun-web-app-${ASQA_RELEASE}.war"

################################################################################################################

# System prerequisites
RUN apt-get update                    && \
    apt-get -y --no-install-recommends   \
            install openjdk-8-jre        \
                    libgtk-3-0           \
                    libdbus-glib-1-2     \
                    wget                 \
                    curl

# Asqatasun prerequisites
RUN cd /opt                                                     && \
    echo ${FIREFOX_URL}                                         && \
    wget ${FIREFOX_URL}                                         && \
    wget ${GECKODRIVER_URL}                                     && \
    tar xvf "firefox-${FIREFOX_VERSION}.tar.bz2"                && \
    tar xvf "geckodriver-${GECKODRIVER_VERSION}-linux64.tar.gz"

# Add a new Linux user
RUN adduser asqatasun
#RUN addgroup --system asqatasun && adduser --system asqatasun --group

RUN mkdir "${LOG_DIR}"                               && \
    chown asqatasun:asqatasun "${LOG_DIR}"

# Add WAR file
USER asqatasun:asqatasun
ADD "./${WAR_FILE}" "/home/asqatasun/app.war"

# Health Check of the Docker Container
HEALTHCHECK --timeout=3s    \
            --interval=10s  \
            CMD curl --fail ${ASQA_URL} || exit 1
            ######################################################################
            # doc: https://docs.docker.com/engine/reference/builder/#healthcheck
            # options:  --interval=DURATION (default: 30s)
            #           --timeout=DURATION (default: 30s)
            #           --retries=N (default: 3)
            # can be overridden at the command line 'docker run --health-cmd (...)'
            ######################################################################

################################################################################################################
CMD /usr/bin/java                                                            \
    -Dwebdriver.firefox.bin=/opt/firefox/firefox                             \
    -Dwebdriver.gecko.driver=/opt/geckodriver                                \
    -Dapp.logging.path=${LOG_DIR}                                            \
    -Djdbc.url="jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=false"  \
    -jar /home/asqatasun/app.war                                             \
    --server.port=${WWWPORT}
