# Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/articles/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

## 0. Know nothing about Docker ?

... begin here !

* Get Started with [Docker for Windows](https://docs.docker.com/windows/)
* Get Started with [Docker for Mac OS X](https://docs.docker.com/mac/)
* Get Started with [Docker for Linux](https://docs.docker.com/linux/)

## 1. Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

```sh
docker pull asqatasun/asqatasun  
docker run --name asqatasun -d -p 8080:8080  asqatasun/asqatasun  
```

**AND** wait ~30 seconds before going to the next step (to allow the container to start).

## 2. Use your local Asqatasun

### Linux users

* In your browser, go to `http://localhost:8080/asqatasun/` 
* Use this user and this password :
    * `me@my-email.org`
    * `myAsqaPassword`

### MacOSX and Windows users

* Get the IP address with command `docker-machine ip default`
* In your browser, go to `http://<the_IP_address>:8080/asqatasun/` 
* Use this user and this password :
    * `me@my-email.org`
    * `myAsqaPassword`
    
    

<h2 id="docker-tips-tricks">Tips and tricks on using Docker containers</h2>

(The more [docker doc](https://docs.docker.com/engine/userguide/containers/usingdocker/) you read, the stronger you are. (c) Yoda)

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
docker run asqatasun
```

## Developer, you wanna play harder? Come on!

Have a look at the Documentation in [Contributor Doc > Docker_build](http://doc.asqatasun.org/en/30_Contributor_doc/Docker_build.html)

