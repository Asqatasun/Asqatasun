
# Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/articles/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

## 1. Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

```shell
     docker pull asqatasun/asqatasun  
     docker run --name asqa_test -d -p 8080:8080  asqatasun/asqatasun  
```

## 2. Use your local Asqatasun

- In your browser, go to http://localhost:8080/asqatasun/ 
- Use this user and this password :
  - `me@my-email.org`
  - `myAsqaPassword`

## Developer, you wanna play harder? Come on!

### Build locally (with downloaded Asqatasun)

Create a container from the DockerFile:

```shell
git clone https://github.com/Asqatasun/Asqatasun.git  
cd Asqatasun/docker/single-container 
docker build -t test_asqatasun . 
docker run --name asqa_test -d -p 8080:8080 test_asqatasun
```

Then play with it:

- In your browser, go to http://localhost:8080/asqatasun/ 
- Use this user and this password :
  - `me@my-email.org`
  - `myAsqaPassword`

### Build locally with locally built Asqatasun

You can also hack the Dockerfile to use your own locally compiled Asqatsun in your Docker.
To do so:

1) Build Asqatasun locally (please always use `develop` branch, see CONTRIBUTING.md)

```sh
git checkout develop        # always work on develop, cf CONTRIBUTING.md
mvn clean install
cp web-app/asqatasun-web-app/target/asqatasun-*.tar.gz docker/single-container
```

2) In the Dockerfile, change `ASQA_RELEASE` variable from `latest` to `4.0.0-SNAPSHOT` 
(case matters). `4.0.0` should be adjusted to the current version (to determine it, 
see the same value on the `master` branch).

3) In the Dockerfile, comment the following lines:

```
ADD http://download.asqatasun.org/asqatasun-${ASQA_RELEASE}.tar.gz /root/
RUN tar xvfz asqatasun-${ASQA_RELEASE}.tar.gz && \
    mv asqatasun*/ ./asqatasun/
```

4) and UNcomment these ones:

```
#ADD asqatasun-${ASQA_RELEASE}.i386.tar.gz /root/
#RUN mv asqatasun*/ ./asqatasun/
```

As you understand, the file `asqatasun-${ASQA_RELEASE}.i386.tar.gz` is the `.tar.gz` you just build and copied.

5) Build the Docker image:


```sh
cd Asqatasun/docker/single-container 
docker build -t asqatasun:local-snapshot . 
docker run --name asqatasun_local_snapshot -d -p 8080:8080 asqatasun:local-snapshot
```

6) Use your Asqatasun:

- In your browser, go to http://localhost:8080/asqatasun/ 
- Use this user and this password :
    - `me@my-email.org`
    - `myAsqaPassword`