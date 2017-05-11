
[![License : AGPL v3](https://img.shields.io/badge/license-AGPL3-blue.svg)](https://github.com/Asqatasun/Asqatasun/blob/master/LICENSE)
[![Release](https://img.shields.io/github/release/asqatasun/asqatasun.svg)](https://github.com/Asqatasun/Asqatasun/releases/latest)
[![Build Status](https://api.travis-ci.org/Asqatasun/Asqatasun.svg?branch=master)](https://travis-ci.org/Asqatasun/Asqatasun/branches)

# Asqatasun

![Asqatasun Logo](http://forum.asqatasun.org/uploads/default/original/1X/e16a2b9b7f5a4dc756f03630923290c695c762c9.png)


Asqatasun is an opensource web site analyzer, used for web accessibility (a11y) and Search Engine Optimization (SEO).

## Features

* **SEO measurement**
    * run fully automated tests to track SEO issues
    * scan zillions of pages
    * create your own tests
* **web accessibility assessment** `#a11y` (RGAA 3, AccessiWeb, WCAG)
    * scan a whole site for a11y issues (crawler included)
    * scan a given page, and manually fulfill the audit to produce report
    * scan offline file (e.g. template being created but not online yet)
    * scan a user-workflow like site registration, form completion or e-commerce checkout with **Asqatasun scenarios**.

## Demo
https://app.asqatasun.org
 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

![5 types of accessibility results](https://github.com/Asqatasun/Asqatasun/blob/master/documentation/en/20_User_doc/Images/screenshot_20150307_ASQATASUN_5_types_of_result.png)

---


## Installation and documentation

How to install Asqatasun ? How to run the Docker images ? What hardware to provision ?
All answers are in the [Asqatasun Doc](http://doc.asqatasun.org/en/) (or in the `documentation/`
directory if you cloned the repos or downloaded the .tar.gz).

Takeaway:

* [Asqatasun doc in PDF, 9Mb](https://www.gitbook.com/download/pdf/book/asqatasun/asqatasun?lang=en)
* [Asqatasun doc in EPUB, 3Mb](https://www.gitbook.com/download/epub/book/asqatasun/asqatasun?lang=en)

## Download

* [lastest version of Asqatasun, .tar.gz, 83Mb](http://download.asqatasun.org/asqatasun-latest.tar.gz)

And also: 

* [Asqatasun Docker images](https://hub.docker.com/r/asqatasun/asqatasun/)
(but do read the [associated doc](http://doc.asqatasun.org/en/10_Install_doc/Docker/index.html) or your data will be lost !)
* **Ansible** roles are available in the `/Ansible` directory of the `.tar.gz`.

---

## Universe: accessibility "a11y"

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, word-processor...)
* switch of context
* ...

As of June 2016, this represents 173 accessibility tests.

## Universe: Search Engine Optimisation "SEO"

What tests are covered:

* at the scope of the entire site (i.e. site-wide):
    * non-uniqueness (duplicate) of `<h1>`
    * non-uniqueness (duplicate) of `<title>`
    * non-uniqueness (duplicate) of `<meta description>`
    * duplicate pages
    * presence of robots.txt / sitemap.xml
* at the scope of the page:
    * non-relevancy of content of `<h1>`
    * non-relevancy of content of `<title>`
    * non-relevancy of content of `<meta description>`
    * non-relevancy of content of link-text `<a href="">...</a>`
    * non-relevancy of `<h1>`...`<h6>` structure
    * rewrite-rule presence 
    * ...

---

## Contact and discussions

* [Asqatasun forum](http://forum.asqatasun.org/) 
* [Twitter @Asqatasun](https://twitter.com/Asqatasun)
* email to `asqatasun AT asqatasun dot org` (only English, French and klingon is spoken :) ) 

## We want you! (aka Contribute)

We would be really glad to have you on board! You can help in many ways:

1. Use Asqatasun on your sites !
1. [Help translate Asqatasun](https://www.transifex.com/asqatasun/asqatasun/) 
1. Give us [feedback on the forum](http://forum.asqatasun.org) or [fill in bug report](https://github.com/Asqatasun/Asqatasun/issues)
1. Help us in improving the SEO rules: come, code or [discuss](http://forum.asqatasun.org)!

[Pull Requests](https://github.com/Asqatasun/Asqatasun/pulls) are always welcome! 
Everything is summarized in the [CONTRIBUTING](https://github.com/Asqatasun/Asqatasun/blob/master/CONTRIBUTING.md) file.

Create your own tests.

---

## License

 [AGPL v3](https://github.com/Asqatasun/Asqatasun/blob/master/LICENSE) 

## Content of this last version (Asqatasun 4.0.3, 2016-08-22)

### Features

* Docker/single-container: reduced weight from 450 MB to 290 MB
* Asqatasun-Runner: RGAA 3.0 by default, enabled SEO and disabled RGAA 2
* Referential RGAA 3.0: english translation added (source : https://github.com/DISIC/rgaa_referentiel_en)

### Bugs

* [#115 Build failure on project asqatasun-engine with openJDK-8](https://github.com/Asqatasun/Asqatasun/issues/115)
* [#113 Build failure on the projects webApp and runner with openJDK-8 on Ubuntu 16.04](https://github.com/Asqatasun/Asqatasun/issues/113)
* [#114 Runner - install/engine folder not present in the tarball](https://github.com/Asqatasun/Asqatasun/issues/114)
* [#117 Fixed /rules/referential-creator/(...)/pom.vm](https://github.com/Asqatasun/Asqatasun/issues/117)

### Documentation

* Updated runner doc (WIP)
* Added "the referential creation" + "Create-a-rule" (WIP)

### Upgrade-o-meter 

* no database change, just replace the `.war` file

See full [Changelog](https://github.com/Asqatasun/Asqatasun/blob/master/CHANGELOG.md)


### Have Fun

Happy coding !

[Asqatasun Team](https://github.com/Asqatasun/Asqatasun/blob/master/documentation/en/asqatasun-team.md)


