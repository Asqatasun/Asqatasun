
# Use Asqatasun with Docker

work in progress

## Archive

### Asqatasun 4.1.0
- no docker image for this version of Asqatasun
- you can use Vagrant instead to run Asqatasun 4.1.0 on your computer

see: https://gitlab.com/asqatasun/asqatasun-vagrant/-/tree/master/Ubuntu-18.04/Asqatasun_v4.1.0

```bash
git clone https://gitlab.com/asqatasun/asqatasun-vagrant.git
cd asqatasun-vagrant
vagrant up
vagrant ssh
    sudo -i  # Inside the box
    cd /vagrant
    ./asqatasun.sh
```


### Asqatasun 4.0.3
see: https://gitlab.com/asqatasun/Asqatasun/-/tree/v4.0.3/docker

```markdown
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

git clone https://github.com/Asqatasun/Asqatasun
cd  Asqatasun
git checkout develop
docker/build_and_run-with-docker.sh -l -s ${PWD} -d docker/SNAPSHOT-local

In your browser, go to
http://127.0.0.1:8085/asqatasun/
````
