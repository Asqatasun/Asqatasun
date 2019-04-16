../../RELEASE/README.md
[![License : AGPL v3](https://img.shields.io/badge/license-AGPL3-blue.svg)](https://github.com/Asqatasun/Asqatasun/blob/master/LICENSE) [![Release](https://img.shields.io/github/release/asqatasun/asqatasun.svg)](https://github.com/Asqatasun/Asqatasun/releases/latest) [![Build Status](https://api.travis-ci.org/Asqatasun/Asqatasun.svg?branch=master)](https://travis-ci.org/Asqatasun/Asqatasun/branches)

# Asqatasun

Opensource web site analyser, used for web accessibility and SEO 

## Use Asqatasun with a single container Docker

- This is a fat container, that is absolutely not compliant to [Docker best-practices](https://docs.docker.com/engine/userguide/eng-image/dockerfile_best-practices/)
- Don't use it for production as all data are wiped out at reboot / rebuild
- BUT for quick testing, that does the job :)

### 0. Know nothing about Docker ?

... begin here !

* Get Started with [Docker for Windows](https://docs.docker.com/engine/installation/windows/)
* Get Started with [Docker for Mac OS X](https://docs.docker.com/engine/installation/mac/)
* Get Started with [Docker for Linux](https://docs.docker.com/engine/installation/linux/)

### 1. Create a container from [Docker Hub](https://hub.docker.com/r/asqatasun/asqatasun/)

```sh
docker pull asqatasun/asqatasun  
docker run --name asqatasun -d -p 8080:8080  asqatasun/asqatasun  
```

**AND** wait ~30 seconds before going to the next step (to allow the container to start).

### 2. Use your local Asqatasun

#### Linux users

* In your browser, go to `http://localhost:8080/asqatasun/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

#### MacOSX and Windows users

* Get the IP address with command `docker-machine ip default`
* In your browser, go to `http://<the_IP_address>:8080/asqatasun/` 
* Use this user and this password :
    * `admin@asqatasun.org`
    * `myAsqaPassword`

### More links

* The whole [Documentation of Asqatasun](http://doc.asqatasun.org/en/)
* More doc on [how to use this Docker image](http://doc.asqatasun.org/en/10_Install_doc/Docker/index.html#docker-tips-tricks)
    * how to restart a container
    * how to have your container automatically launched at boot
    * ...

## User Feedback

If you have any problems with or questions about this image or Asqatasun itself, please contact us :

* [Asqatasun forum](http://forum.asqatasun.org/) 
* [Twitter @Asqatasun](https://twitter.com/Asqatasun)
* [Asqatasun on Github](https://github.com/Asqatasun/Asqatasun)

## Contributing

You are invited to contribute new features, fixes, or updates, large or small; we are always thrilled to receive pull requests, and do our best to process them as fast as we can.

Before you start to code, we recommend discussing your plans through a [GitHub issue](https://github.com/Asqatasun/Asqatasun/issues), especially for more ambitious contributions. This gives other contributors a chance to point you in the right direction, give you feedback on your design, and help you find out if someone else is working on the same thing.


