# Use Asqatasun with Docker

## Launch Asqatasun with Docker

Consult our [dedicated repository to use Asqatasun with Docker](https://gitlab.com/asqatasun/asqatasun-docker) :

- [Using Docker - Asqatasun 5.x](https://gitlab.com/asqatasun/asqatasun-docker/-/tree/main/5.x/)
- [Using Docker - Asqatasun 4.x](https://gitlab.com/asqatasun/asqatasun-docker/-/tree/main/4.x/)

## Build Asqatasun and launch it with Docker

The following documentation is only for people who want to **compile** Asqatasun from source code and run it with Docker.

### Prerequisites

- git
- JDK-8 is required
- maven `3.6` (at least) is required
- [Docker](https://docs.docker.com/engine/install/) `19.03.0` (at least) is required
- [Docker-Compose](https://docs.docker.com/compose/install/) `1.27.0` (at least) is required

### All `Docker-Compose` files avalaible

- [Asqatasun **API**](api_SNAPSHOT-local)
- [Asqatasun **Webapp**](webapp_SNAPSHOT-local)
- [Asqatasun **API** + **Webapp**](all_SNAPSHOT-local)

### Ports, URL and credentials (user/password)

| Service  | Port | URL                                     | User                         | Password                        |
|----------|------|-----------------------------------------|------------------------------|---------------------------------|
| Database | 3306 | `jdbc:mysql://localhost:3306/asqatasun` | `asqatasunDatabaseUserLogin` | `asqatasunDatabaseUserP4ssword` |
| API      | 8081 | `http://localhost:8081`                 | `admin@asqatasun.org`        | `myAsqaPassword`                |
| Webapp   | 8080 | `http://localhost:8080`                 | `admin@asqatasun.org`        | `myAsqaPassword`                |

Tip:
if you copy `.env.dist` file to `.env` file,
you can change **port** numbers, **IP** adresses and **database** credentials.

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
     -d docker/webapp_SNAPSHOT-local 

# Option 2: build Asqatasun without running tests (faster)
docker/build_and_run-with-docker.sh  \
     --skip-build-test               \
     -s ${PWD}                       \
     -d docker/webapp_SNAPSHOT-local 

# Build docker image + launch Asqatasun and database
####################################################

# Option 1: build docker image + launch Asqatasun and database (uses same database, if it already exists)
cd docker/webapp_SNAPSHOT-local
docker-compose up --build

# Option 2: build docker image + launch Asqatasun and a new database
cd docker/webapp_SNAPSHOT-local
docker-compose rm -fsv
docker-compose up --build
```

* In your browser, go to `http://127.0.0.1:8080/` 
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
     -d docker/all_SNAPSHOT-local

# Build docker image + launch Asqatasun (webapp + API) and database
###################################################################

# Option 1: build docker image + launch Asqatasun (webapp + API) and database (uses same database, if it already exists)
cd docker/all_SNAPSHOT-local
docker-compose up --build

# Option 2: build docker image + launch Asqatasun (webapp + API) and a new database
cd docker/all_SNAPSHOT-local
docker-compose rm -fsv
docker-compose up --build
```

* In your browser:
  - Webapp: go to `http://127.0.0.1:8080/` 
  - API documentation: go to `http://127.0.0.1:8081/`    (**Swagger** playground)
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

#### Launch an audit via API
```bash
ASQA_USER="admin%40asqatasun.org" 
ASQA_PASSWORD="myAsqaPassword"
API_PREFIX_URL="http://${ASQA_USER}:${ASQA_PASSWORD}@localhost:8081"
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
