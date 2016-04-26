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

## More links

* More doc on [how to use this Docker image](http://doc.asqatasun.org/en/10_Install_doc/Docker/index.html#docker-tips-tricks)
   * how to restart a container
   * how to have your container automatically launched at boot
   * ...
* The whole [Documentation of Asqatasun](http://doc.asqatasun.org)
* Get in touch at the [Asqatasun Forum](http://forum.asqatasun.org/)

And also

* [Asqatasun on Twitter](https://twitter.com/Asqatasun)
* [Asqatasun on Github](https://github.com/Asqatasun/Asqatasun)
