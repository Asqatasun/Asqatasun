# Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

## 0. Know nothing about Docker ?

... begin here !

* Get Started with [Docker for Windows](https://docs.docker.com/engine/installation/windows/)
* Get Started with [Docker for Mac OS X](https://docs.docker.com/engine/installation/mac/)
* Get Started with [Docker for Linux](https://docs.docker.com/engine/installation/linux/)

## 1. Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

```sh
docker pull asqatasun/asqatasun  
docker run --name asqatasun -d -p 8080:8080 asqatasun/asqatasun
```

**AND** wait ~30 seconds before going to the next step (to allow the container to start).
 
Note: Linux user and willing to test your `localhost` ? See tip [Testing 127.0.0.1 with Asqatasun Docker](#testing-localhost-127.0.0.1-with-Asqatasun-Docker).

## 2. Use your local Asqatasun

### Linux users

* In your browser, go to `http://localhost:8080/asqatasun/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

### MacOSX and Windows users

* Get the IP address with command `docker-machine ip default`
* In your browser, go to `http://<the_IP_address>:8080/asqatasun/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

## 3. Use "-SNAPSHOT" Docker image (bleeding edge but unstable)

** /!\ Advanced users only /!\ **

We also provide a Docker image for the `develop` branch (called "-SNAPSHOT"). Be aware that this **not for production**, but for testing purpose only. Said differently, this can be broken or can harm your data.

OK now you've been warned, so let's jump into it :) 

First grab the image

```sh
docker pull asqatasun/asqatasun:SNAPSHOT
```

Then run you "-SNAPSHOT" container. Here we'll use different port-mapping to have both the "stable" and "unstable" builds of Asqatasun running at the same time:

```sh
docker run --rm --name asqa-snapshot -p 8282:8080 asqatasun/asqatasun:SNAPSHOT
```

Explanations:

* `--rm` deletes the container when exiting
* `-p 8282:8080` maps Asqatasun port (8080) to your 8282 host port

Then to use it, point your browser to `http://localhost:8282/asqatasun/` (or adapt the IP if you are on MacOSX / Windows)

<h2 id="docker-tips-tricks">Tips and tricks on using Docker containers</h2>

(The more [docker doc](https://docs.docker.com/engine/tutorials/usingdocker/) you read, the stronger you are. (c) Yoda)

### Get the logs

Once your container is launched:

```sh
docker logs -tf asqatasun 
```

(equivalent of a `tail -f /var/log/asqatasun.log /var/log/tomcat7/catalina.out`)

### Container available only on localhost (Linux users)

Make your container available only on 127.0.0.1 ; thus no one on the network can access you container. (You can always analyse any URL, be it local or not.)

```sh
docker run --name asqatasun -d -p 127.0.0.1:8080:8080  asqatasun/asqatasun  
```

### Have your container launched at boot

Add the option `--restart always`:

```sh
docker run --name asqatasun --restart always -d -p 8080:8080  asqatasun/asqatasun  
```

### Error *The name "/asqatasun" is already in use by container*

Say you have once run the `docker run ...` command, and the next day you reboot and your container is no longer here. If you try another time the same `docker run ...` command you get the error:

```sh
docker: Error response from daemon: Conflict. The name "/asqatasun" is already in use by container c3aa3d084d0f3f2053e746c3374cb746b96b9600d988de76d7ded244ad27302d. 
You have to remove (or rename) that container to be able to reuse that name.
See 'docker run --help'.
```

Then you have to start, not run, your (already existing) container:

```sh
docker start asqatasun
```

<h3 id="testing-localhost-127.0.0.1-with-Asqatasun-Docker">Testing your <code>localhost</code> or <code>127.0.0.1</code> webserver (Linux users only)</h3>

Consider we don't have Docker. The site we want to analyze is reachable via `http://localhost/`
or `http://127.0.0.1` (or even `http://localhost:9000/my-app/`).

Now back to a few Docker basics. Docker's principle is to run *isolated* containers. That means
the container (your Asqatasun Docker image) is isolated even from your physical host.
Thus if you ask Asqatasun to analyze 127.0.0.1, it will try to analyze the webserver of the
Asqatasun Docker image, not your host's one.

To cope with that issue, run the container with one more option (`--add-host` and the suitable value):

```sh
docker pull asqatasun/asqatasun  
docker run --name asqatasun -d -p 8080:8080 --add-host dockerhost:`ip addr show docker0 | grep 'inet ' | awk '{print $2}' | cut -d/ -f1` asqatasun/asqatasun
```

Don't forget to wait ~30 seconds for the services to be up, and:

* In your browser, go to `http://localhost:8080/asqatasun/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

Then you just have to type `dockerhost` instead of `localhost` for your local site to be analyzed.

Examples:

My application URL *without* Docker | Equivalent URL *inside* Docker
------------------------------------|------------------------------
`http://127.0.0.1`                  | `http://dockerhost`
`http://localhost/`                 | `http://dockerhost/`
`http://localhost:9000/my-app/`     | `http://dockerhost:9000/my-app/`

For all other kinds of URLs (`192.168.1.25`, `www.mysite.com` or `mysite.local`), nothing changes.

### Docker cheat sheet

See [Docker Commands and Best Practices Cheat Sheet](http://zeroturnaround.com/rebellabs/docker-commands-and-best-practices-cheat-sheet/)

## Developer, you wanna play harder? Come on!

Have a look at the Documentation in [Contributor Doc > Build > Build Docker image](../../30_Contributor_doc/Build/Build_Docker_image.md)

