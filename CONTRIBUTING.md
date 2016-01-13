# Contributing to Asqatasun

:+1: First off, thanks for taking the time to contribute! :+1:


We would be really glad to have you on board ! 
You can help in many ways:

* [Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues)
* [Help translate Asqatasun]() @@@TODO Transifex URL
* Pull Requests are off course welcome
* [Create your own tests]() @@@TODO link to doc

## Fill in bug reports

[Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues).

* Please do not assign issue to anyone.
* You may assign an issue to yourself, meaning to others "I'm actually working on this issue".
* When closing an issue, please *always* add a comment explaining why you are closing it.

## Contribution to the code

Please always work on `develop` branch. `Master` is meant to be directly usable in production,
thus only contains tagged releases.

## Building Asqatasun

Instructions for Linux Ubuntu 14.04:

1) Pre-requisites: have OpenJDK 7:

```sh
apt-get install openjdk-7-jdk
```

2) Clone

```sh
git clone https://github.com/Asqatasun/Asqatasun.git
cd Asqatasun
```

3) Do some fantastic coding stuff :)

4) Build (from the top directory)

```
mvn clean install
```

5) Grab the `.tar.gz` from directory `web-app/asqatasun-web-app/target`. 
It should be named something like `asqatasun-X.Y.Z-<flavor>.i386.tar.gz`
(for instance `asqatasun-4.0.0-SNAPSHOT.i386.tar.gz`)

6) Install it directly (see [Install doc](http://doc.asqatasun.org/en/10_Install_doc/Asqatasun/index.html))
or [build your own Docker Image](http://doc.asqatasun.org/en/30_Contributor_doc/Docker_build.html). 
Tip: all documentation is also the repos (you just cloned) and also in the `.tar.gz`, in the `docs/` directory.

*Note:* if you want to contribute and provide Pull Requests, you should first 
fork the repos to your own Github account, then clone your own Asqatasun. (See 
"how to create pull requests" formerly named "how to be an Asqatasun developer" @@@TODO)

## Sources of inspiration for CONTRIBUTING.md

* For contributors doc [Gitlab Workflow](https://about.gitlab.com/handbook/#gitlab-workflow)
* [Atom's Contributing file](https://github.com/atom/atom/blob/master/CONTRIBUTING.md) that is really good and brightly written.