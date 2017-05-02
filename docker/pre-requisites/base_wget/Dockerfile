FROM ubuntu:14.04
MAINTAINER Matthieu Faure <mfaure@asqatasun.org>

RUN  apt-get update                             && \
     apt-get -y --no-install-recommends   \
                  install wget            \
                          curl            \
                          ca-certificates       && \
     apt-get clean                              && \
     apt-get autoremove                         && \
     rm -rf  /var/lib/apt/lists/*

