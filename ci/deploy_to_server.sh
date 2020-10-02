#!/usr/bin/env bash

set -ve

SSH_KEY=${SSH_KEY?SSH_KEY must be set}

eval "$(ssh-agent -s)"
ssh-add <(echo "${SSH_KEY}")
mkdir -p ~/.ssh
if [[ -f /.dockerenv ]]; then
    echo -e "Host *\n\tStrictHostKeyChecking no\n\n" > ~/.ssh/config
fi

CI_PROJECT_NAME=asqatasun
USER=${USER?USER must be set}
HOST=${HOST?HOST must be set}
WEBAPP_ARTIFACT_DIR=${ARTIFACT_DIR?ARTIFACT_DIR must be set}
WEBAPP_ARTIFACT_FILE=${ARTIFACT_FILE?ARTIFACT_FILE must be set}
REST_SERVER_ARTIFACT_DIR=${ARTIFACT_DIR?ARTIFACT_DIR must be set}
REST_SERVER_ARTIFACT_FILE=${ARTIFACT_FILE?ARTIFACT_FILE must be set}

REMOTE_DIR="$CI_PROJECT_NAME/deploy-$(date +"%Y%m%d-%H%M%S")"
ssh "${USER}"@"${HOST}" bash -c "'mkdir -p $REMOTE_DIR'"
scp "${WEBAPP_ARTIFACT_DIR}"/"${WEBAPP_ARTIFACT_FILE}" "$USER"@"$HOST":~/"$REMOTE_DIR"
scp "${REST_SERVER_ARTIFACT_DIR}"/"${REST_SERVER_ARTIFACT_FILE}" "$USER"@"$HOST":~/"$REMOTE_DIR"

# copy docker and databases scripts and install dependencies for dev env

ssh "$USER"@"$HOST" bash -c "'cd $REMOTE_DIR && APP_VERSION=$APP_VERSION CI_PROJECT_NAME=$CI_PROJECT_NAME WEBAPP_ARTIFACT_FILE=$WEBAPP_ARTIFACT_FILE REST_SERVER_ARTIFACT_FILE=$REST_SERVER_ARTIFACT_FILE ./update_app.sh'"
ssh "$USER"@"$HOST" bash -c "'rm -rf $REMOTE_DIR'"
