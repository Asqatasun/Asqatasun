#!/bin/bash

# 20101220 koj
pkill firefox
echo "Firefox instances killed"
/etc/init.d/selenium stop 1>/dev/null && \
echo "Selenium process stopped"
/bin/sleep 10s && \
/etc/init.d/selenium start 1>/dev/null && \
echo "Selenium process started"
/bin/sleep 10s 