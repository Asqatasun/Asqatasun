
# Use Asqatasun with Docker

## Single container
- This is a fat container, that is absolutly not compliant to [Docker best-practices](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

##  Multiple containers

Work in progress

## Builds Asqatasun and runs a new Docker container

- builds Asqatasun from sources with maven,
- builds a new Docker image
- runs a container based the freshly built image

###  Linux users
```bash
git clone https://github.com/Asqatasun/Asqatasun
cd  Asqatasun
git checkout develop
docker/build_and_run-with-docker.sh -l -s ${PWD} -d docker/single-container-SNAPSHOT-local
```

In your browser, go to
`http://127.0.0.1:8085/asqatasun/`
