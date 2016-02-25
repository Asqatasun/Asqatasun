# Asqatasun

![Asqatasun Logo](http://forum.asqatasun.org/uploads/default/original/1X/e16a2b9b7f5a4dc756f03630923290c695c762c9.png)


Asqatasun is an opensource web site analyzer, used for web accessibility (a11y) and Search Engine Optimization (SEO).

## Features

* **SEO measurement**
    * run fully automated tests to track SEO issues
    * scan zillions of pages
    * create your own tests
* **web accessibility assessment** (RGAA 3, AccessiWeb, WCAG)
    * scan a whole site for a11y issues (crawler included)
    * scan a given page, and manually fulfill the audit to produce report
    * scan offline file (e.g. template being created but not online yet)
    * scan a user-workflow like site registration, form completion or e-commerce checkout with **Asqatasun scenarios**.
 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

![5 types of accessibility results](/documentation/en/20_User_doc/Images/screenshot_20150307_ASQATASUN_5_types_of_result.png)


---


## Demo

@@@TODO



## Installation and documentation

* How to install Asqatasun ?
* How to run the Docker images ?
* what hardware to provision ?
* ...

All answers are in the [Asqatasun Doc](http://doc.asqatasun.org/en/) (or in the `documentation/`
directory if you cloned the repos or downloaded the .tar.gz).


## Download

* http://download.asqatasun.org/asqatasun-latest.tar.gz
* [Asqatasun Docker images](https://hub.docker.com/r/asqatasun/asqatasun/) (but do read the [associated doc][link_doc_docker] or your data will be lost !)

[link_doc_docker]: http://doc.asqatasun.org/en/10_Install_doc/Docker/index.html  "Use Asqatasun with a single container Docker"

@@@TODO Ansible role + Vagrantfile


---


## Universe: accessibility "a11y"

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, word-processor...)
* switch of context
* ...

As of February 2016, this represents 173 accessibility tests.

## Universe: Search Engine Optimisation "SEO"

@@@TODO


---


## Contact and discussions

* [Asqatasun forum](http://forum.asqatasun.org/) 
* [Twitter @Asqatasun](https://twitter.com/Asqatasun)
* email to `asqatasun AT asqatasun dot org` (only English, French and klingon is spoken :) ) 

## Contribute

We would be really glad to have you on board ! You can help in many ways:

* [Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues)
* [Help translate Asqatasun](https://www.transifex.com/asqatasun/asqatasun/) 
* [Pull Requests](https://github.com/Asqatasun/Asqatasun/pulls) are off course welcome
* [Create your own tests](#) @@@TODO link to doc

Everything is summarized in the [CONTRIBUTING](CONTRIBUTING.md) file.




---

## License

 [AGPL v3](LICENSE) 


## Content of this last version (Asqatasun 4.0.0, 2016-02-xx)

Features :
- devops : Ansible role + Vagrantfile
- devops : Docker images (+ Docker automated builds)
- Implementation of SEO rules
- Fork from Tanaguru

See full [Changelog](CHANGELOG.txt)


Have Fun
[Asqatasun Team](asqatasun-team.md)
