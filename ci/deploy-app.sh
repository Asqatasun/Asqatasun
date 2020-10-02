#!/bin/bash -e
# Gitlab-ci deploy this file into server instance and execute it
#
# only "systemctl stop asqatasun-webapp.service" and "systemctl start asqatasun-webapp.service"
# only "systemctl stop asqatasun-rest-server.service" and "systemctl start asqatasun-rest-server.service"
#
# to see logs : sudo journalctl -u asqatasun-webapp.service
# to see logs : sudo journalctl -u asqatasun-rest-server.service
# to see logs as tail : sudo journalctl -f -u asqatasun-webapp.service
# to see logs as tail : sudo journalctl -f -u asqatasun-rest-server.service

# stop service
echo "stop service"
sudo /bin/systemctl stop asqatasun-webapp.service
sudo /bin/systemctl stop asqatasun-rest-server.service
sleep 10

HOME_DIR=/home/asqatasun

# move jar file into app folder
echo "move jar"
cp "${HOME_DIR}/webapp/deploy/asqatasun-webapp.war" "${HOME_DIR}/webapp/previous"
mv "${WEBAPP_ARTIFACT_FILE}" "${HOME_DIR}/webapp/deploy/asqatasun-webapp.war"

cp "${HOME_DIR}/rest-server/deploy/asqatasun-server.war" "${HOME_DIR}/rest-server/previous"
mv "${REST_SERVER_ARTIFACT_FILE}" "${HOME_DIR}/rest-server/deploy/asqatasun-rest-server.jar"

# start service
echo "start service"
sudo /bin/systemctl start asqatasun-webapp.service
# Sleep before starting other service to avoir fyway to be executed twice at the same time, and thus avoid concurrent access on db creation
sleep 60
sudo /bin/systemctl start asqatasun-rest-server.service

# echo "sleep 40s then check version"
sleep 40
status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://localhost:8091/rest/actuator/health)
if [[ "${status_code}" -ne 200 ]] ; then
  echo "Status code of http://localhost:8091/rest/actuator/health is ${status_code} : health check failed"
  exit 1
fi
