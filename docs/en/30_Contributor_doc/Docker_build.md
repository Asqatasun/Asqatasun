# Build Docker images for Asqatasun

## Build locally

Let say we want to compile Asqatasun 4.0.0 "SNAPSHOT" release and create a Docker image with it.
By "locally", we mean we won't download Asqatasun as we will build it.

### 1. Grab source code

```shell
git clone https://github.com/Asqatasun/Asqatasun.git
git checkout develop    # Please always work on develop, see CONTRIBUTING.md
```

### 2. Compile Asqatasun

```shell
cd Asqatasun ; mvn clean install
```
(Internet connection needed the very first time to download Maven dependencies).

In the meantime, enjoy your coffee break :) 

### 3. Copy Asqatasun tar.gz to the suitable Docker directory

```shell
cp web-app/asqatasun-web-app/target/asqatasun-4.0.0-SNAPSHOT.i386.tar.gz docker/single-container
```

### 4. Modify the Dockerfile for local usage

Comment the following line :

```shell
#ADD http://download.asqatasun.org/asqatasun-${ASQA_RELEASE}.i386.tar.gz /root/
#RUN tar xvfz asqatasun-${ASQA_RELEASE}.i386.tar.gz && \
#    mv asqatasun*/ ./asqatasun/
```

... and uncomment the following ones

```shell
ADD asqatasun-${ASQA_RELEASE}.i386.tar.gz /root/
RUN mv asqatasun*/ ./asqatasun/
```

### 5. Set the release wanted in the Dockerfile


At the beginning if the Dockerfile, replace `ASQA_RELEASE="SNAPSHOT"` by `ASQA_RELEASE="4.0.0-SNAPSHOT"`


### 6. Build the Docker Image

```shell
cd docker/single-container
docker build -t asqatasun/asqatasun:SNAPSHOT .
```

### 7. Remove old containers

In case you had older containers, just wipe them (so backup before if you want to keep your data)

```shell
docker stop asqatasun ; docker rm asqatasun
```

### 8. Run Docker Image

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

You can now browse http://localhost:8080/asqataun/ and login with the following credentials:

* login: `me@my-email.org`
* password: `myAsqaPassword`

### 9. Troubleshooting

