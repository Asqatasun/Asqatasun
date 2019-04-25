# Build Docker images for Asqatasun

## Prerequisites
* maven 
* Docker

**Note:** [Docker 1.12 (at least)](https://docs.docker.com/engine/installation/linux/docker-ce/ubuntu/) and Maven 3.1 (at least) are required. 

## Build with our automated script (recommended)

We have created a shell script to ease the tasks of building locally with Docker. Basically, the script:

1. compiles Asqatasun,
1. creates a docker image,
1. runs a container from this image,
1. displays all info to connect to the container
1. eventually runs the functional tests


To enjoy it, **from the Asqatasun source directory**, do:

```
./docker/build_and_run-with-docker.sh --source-dir $(pwd) --docker-dir docker/SNAPSHOT-local
```

Example of output:

```
 -------------------------------------------------------
 Asqatasun is now running ........ HTTP code = 200
 -------------------------------------------------------
 Container .. asqa
 Image ...... asqatasun/asqatasun:2017-09-22
 CMD ........ docker run -p 8085:8080 --name asqa -d asqatasun/asqatasun:2017-09-22
 -------------------------------------------------------
 Shell ...... docker exec -ti asqa /bin/bash
 Log ........ docker logs -f  asqa
 URL ........ http://localhost:8085/asqatasun/
```

A lot of useful options are also available to speed up the process when you want to build / test / iterate. You may run `./docker/build_and_run-with-docker.sh -h` to see them.


## Build manually

Let say we want to compile Asqatasun 4.x.y "SNAPSHOT" release and create a Docker image with it.
By "locally", we mean we won't download Asqatasun as we will build it.

### 1. Grab source code

```shell
git clone https://github.com/Asqatasun/Asqatasun.git
```

### 2. Compile Asqatasun

```shell
cd Asqatasun
git checkout develop    # Please always work on develop, see CONTRIBUTING.md
mvn clean install
```
(Internet connection needed the very first time to download Maven dependencies).

In the meantime, enjoy your coffee break :) 

### 3. Copy Asqatasun tar.gz to the suitable Docker directory

```shell
cp web-app/asqatasun-web-app/target/asqatasun-*-SNAPSHOT.i386.tar.gz docker/SNAPSHOT-local
```

### 4. Build the Docker Image

```shell
cd docker/SNAPSHOT-local
docker build -t asqatasun/asqatasun:SNAPSHOT .
```

Verify the image is actually built

```shell
docker images
```

You should see something like this:

```
REPOSITORY                   TAG                 IMAGE ID            CREATED             SIZE
asqatasun/asqatasun          SNAPSHOT            84a5bf7bb437        2 minutes ago       833.1 MB
```


### 5. Remove old containers

In case you had older containers, just wipe them (so backup before if you want to keep your data)

```shell
docker stop asqatasun ; docker rm asqatasun
```

### 6. Run Docker Image

```shell
docker run -p 8080:8080 --name asqatasun asqatasun/asqatasun:SNAPSHOT
```

Your Asqatasun is ready to use when you see on the console something like:

```shell
==> /var/log/tomcat7/catalina.out 
Dec 14, 2015 5:05:59 PM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler ["http-bio-8080"]
Dec 14, 2015 5:05:59 PM org.apache.catalina.startup.Catalina start
INFO: Server startup in 27268 ms
```

You can now browse `http://localhost:8080/asqatasun/` and login with the following credentials:

* login: `admin@asqatasun.org`
* password: `myAsqaPassword`

### 7. Troubleshooting

