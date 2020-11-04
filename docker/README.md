# Use Asqatasun with Docker

work in progress

## Build and launch Asqatasun 

### Prerequisites

* git
* JDK-8 is required
* maven `3.1` (at least) is required
* Docker `19.03.0` (at least) is required
* Docker-Compose `1.27.0` (at least) is required

Do have a look at:

* https://docs.docker.com/compose/install/#uninstallation
* https://docs.docker.com/compose/install/#install-compose-on-linux-systems

### Ports, URL and credentials (user/password)

| Service  | Port | URL                                     | User                         | Password                        |
|----------|------|-----------------------------------------|------------------------------|---------------------------------|
| Database | 9924 | `jdbc:mysql://localhost:9924/asqatasun` | `asqatasunDatabaseUserLogin` | `asqatasunDatabaseUserP4ssword` |
| API      | 8986 | `http://localhost:8986`                 | `admin@asqatasun.org`        | `myAsqaPassword`                |
| Webapp   | 8982 | `http://localhost:8982`                 | `admin@asqatasun.org`        | `myAsqaPassword`                |

Tip: if you copy `.env.dist` file to `.env` file, you can change port numbers, IP adresses and database credentials.

### Launch Asqatasun webapp

- Build Asqatasun
- Build docker images
- Launch Asqatasun webapp

```bash
git clone https://gitlab.com/asqatasun/Asqatasun.git
cd Asqatasun

# Build Asqatasun
#################

# Option 1: build Asqatasun with running tests 
docker/build_and_run-with-docker.sh  \
     --log-build                     \
     -s ${PWD}                       \
     -d docker/app_SNAPSHOT-local 

# Option 2: build Asqatasun without running tests (faster)
docker/build_and_run-with-docker.sh  \
     --skip-build-test               \
     -s ${PWD}                       \
     -d docker/app_SNAPSHOT-local 

# Build docker image + launch Asqatasun and database
####################################################

# Option 1: build docker image + launch Asqatasun and database (uses same database, if it already exists)
cd docker/app_SNAPSHOT-local
docker-compose up --build

# Option 2: build docker image + launch Asqatasun and a new database
cd docker/app_SNAPSHOT-local
docker-compose rm -fsv
docker-compose up --build
```

* In your browser, go to `http://127.0.0.1:8982/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`
    
    
### Launch Asqatasun webapp + API

- Build Asqatasun
- Build docker images
- Launch Asqatasun webapp + API

```bash
git clone https://gitlab.com/asqatasun/Asqatasun.git
cd Asqatasun

# Build Asqatasun
#################

# build Asqatasun without running tests (faster)
docker/build_and_run-with-docker.sh  \
     --skip-build-test               \
     -s ${PWD}                       \
     -d docker/global_SNAPSHOT-local

# Build docker image + launch Asqatasun (webapp + API) and database
###################################################################

# Option 1: build docker image + launch Asqatasun (webapp + API) and database (uses same database, if it already exists)
cd docker/global_SNAPSHOT-local
docker-compose up --build

# Option 2: build docker image + launch Asqatasun (webapp + API) and a new database
cd docker/global_SNAPSHOT-local
docker-compose rm -fsv
docker-compose up --build
```

* In your browser:
  - Webapp: go to `http://127.0.0.1:8982/` 
  - API documentation: go to `http://127.0.0.1:8986/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

#### Launch an audit via API
```bash
ASQA_USER="admin%40asqatasun.org" 
ASQA_PASSWORD="myAsqaPassword"
API_PREFIX_URL="http://${ASQA_USER}:${ASQA_PASSWORD}@localhost:8986"
API_URL="${API_PREFIX_URL}/api/v1/audit/run"

URL_TO_AUDIT=https://www.wikidata.org
curl -X POST \
     "${API_URL}"                                                             \
     -H  "accept: */*"                                                        \
     -H  "Content-Type: application/json"                                     \
     -d "{                                                                    \
            \"urls\": [    \"${URL_TO_AUDIT}\"  ],                            \
                           \"referential\": \"RGAA_4_0\",                     \
                           \"level\": \"AA\",                                 \
                           \"contractId\": 1,                                 \
                           \"tags\": [    \"tag_1\"  ]                        \
         }"
```


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
