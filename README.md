
[![License : AGPL v3](https://img.shields.io/badge/license-AGPL3-blue.svg)](https://github.com/Asqatasun/Asqatasun/blob/master/LICENSE)
[![Build Status](https://api.travis-ci.org/Asqatasun/Asqatasun.svg?branch=master)](https://travis-ci.org/Asqatasun/Asqatasun/branches)
[![Release](https://img.shields.io/github/release/asqatasun/asqatasun.svg)](https://github.com/Asqatasun/Asqatasun/releases/latest)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](https://github.com/Asqatasun/Asqatasun/blob/develop/CONTRIBUTING.md)
[![Code of Conduct](https://img.shields.io/badge/code%20of-conduct-ff69b4.svg?style=flat-square)](https://github.com/Asqatasun/Asqatasun/blob/develop/CODE_OF_CONDUCT.md)

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

[![https://app.asqatasun.org](https://github.com/Asqatasun/Asqatasun/blob/develop/documentation/en/Images/app.asqatasun.org_FR_690x340.png)](https://app.asqatasun.org)

 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun


## Installation and documentation

Four ways to read the doc:

* Online: [doc.asqatasun.org](http://doc.asqatasun.org/en/) 
* In the `documentation/` directory if you cloned the repos or downloaded the .tar.gz
* Download [Asqatasun doc in PDF format, 9Mb](https://www.gitbook.com/download/pdf/book/asqatasun/asqatasun?lang=en)
* Download [Asqatasun doc in EPUB format, 3Mb](https://www.gitbook.com/download/epub/book/asqatasun/asqatasun?lang=en)

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
* email to `asqatasun AT asqatasun dot org` (only English, French and Klingon is spoken :) ) 

## Contribute

We would be really glad to have you on board! You can help in many ways:

1. Use Asqatasun on your sites !
1. Give us [feedback on the forum](http://forum.asqatasun.org)
1. [Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues)
1. [Contribute](CONTRIBUTING.md) code

---

## License

 [AGPL v3](https://github.com/Asqatasun/Asqatasun/blob/master/LICENSE) 

## Major changes of this last version (Asqatasun 4.1.0-rc.2, 2019-04-27)

(See CHANGELOG.md for detailed info)

Major features:

* New grade (Asqatasun meter) made of A, B, C, D, E, F instead of 0-100% [#252](https://github.com/Asqatasun/Asqatasun/pull/252), [#248](https://github.com/Asqatasun/Asqatasun/pull/248)
* I18N: Add german translation [#172](https://github.com/Asqatasun/Asqatasun/issues/172)
* JDK upgraded from Java7 to Java8 [#253](https://github.com/Asqatasun/Asqatasun/issues/253)
* DB character encoding is now `utf8mb4` [#255](https://github.com/Asqatasun/Asqatasun/issues/255)
* Support for Ubuntu 18.04 [#269](https://github.com/Asqatasun/Asqatasun/issues/269), [#281](https://github.com/Asqatasun/Asqatasun/issues/281)

Major bug fixes:

* Incorrect string value: '\xF0\x9F\x99\x82" ...' for column 'Source', aka utf8mb4 should default encoding to be able to deal with smileys [#123](https://github.com/Asqatasun/Asqatasun/issues/123) 
* [#137 - Fixed Rgaa 3.2016, 8.9.1: unit tests fail](https://github.com/Asqatasun/Asqatasun/issues/#137)
* [#126 - Create a contract pointing to an internal URL, even if the domain does not end with a valid gTLD](https://github.com/Asqatasun/Asqatasun/issues/126)
* [#119 - contrast ratio link: fixed ratio parameter](https://github.com/Asqatasun/Asqatasun/issues/119)
* [#146 - site-audit in error: added "check for redirection" as possible explanation](https://github.com/Asqatasun/Asqatasun/issues/146)
* [#179 - site-audit in error: fixed the robots.txt URL in error message](https://github.com/Asqatasun/Asqatasun/issues/179)
* [#203 - No allow starting an audit if no referential is activated for the current project](https://github.com/Asqatasun/Asqatasun/issues/203)
* [#204 - No allow starting website audit if URL is not defined for the current project](https://github.com/Asqatasun/Asqatasun/issues/204)
* [#216 - I18N: standardize "project" and "contract" naming (en/fr/es)](https://github.com/Asqatasun/Asqatasun/issues/216)
* [#208 - Webapp UX - Admin user can quickly add a new project to his account](https://github.com/Asqatasun/Asqatasun/issues/208)
* [#211 - Webapp UX - Admin user can quickly update his expired contract](https://github.com/Asqatasun/Asqatasun/issues/211)
* [#27 - Contract creation: verify at least one referential is selected](https://github.com/Asqatasun/Asqatasun/issues/27)
* [#28 - Contract creation: forbid contract without URL and with website audit enabled](https://github.com/Asqatasun/Asqatasun/issues/28)


Upgrade-o-meter: 

* Please see Documentation > Administrator_doc > Upgrading

See full [Changelog](https://github.com/Asqatasun/Asqatasun/blob/master/CHANGELOG.md) for details.


## Have Fun

Happy testing !

[Asqatasun Team](https://github.com/Asqatasun/Asqatasun/blob/master/documentation/en/asqatasun-team.md)


