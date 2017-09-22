# Asqatasun Documentation

## Asqatasun (whole application)

This is the typical and default installation of Asqatasun, setting up the application so it can be used by any user with its browser.

* [Install Asqatasun on a server](Asqatasun/README.md)
* [Try Asqatasun with Docker](Docker/README.md) (for testing purpose only as all data will be lost)
* Ansible roles are available in the `/ansible` directory of the 
[lastest version of Asqatasun, .tar.gz, 75Mb](http://download.asqatasun.org/asqatasun-latest.tar.gz).

## Asqatasun-Runner (for Jenkins)

Asqatasun-Runner is an Asqatasun without web-app (thus not needing server like Apache and Tomcat). 
It is to be used with command line only. It is typically used for [Asqatasun Jenkins plugin](https://github.com/Asqatasun/Asqatasun-Jenkins-Plugin).

**Note:** before installing, please be sure to have read [Asqatasun Jenkins plugin - install doc](https://github.com/Asqatasun/Asqatasun-Jenkins-Plugin/tree/develop/documentation), especially the Architecture page.

* [Asqatasun-Runner Prerequisites](Asqatasun-runner/prerequisites-runner.md)
* [Asqatasun-Runner installation](Asqatasun-runner/install-runner.md)
* [Asqatasun-Runner usage](Asqatasun-runner/usage-runner.md)
