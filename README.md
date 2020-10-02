
[![License : AGPL v3](https://img.shields.io/badge/license-AGPL3-blue.svg)](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/LICENSE)
[![Build Status](https://gitlab.com/asqatasun/Asqatasun/badges/master/pipeline.svg)](https://gitlab.com/asqatasun/Asqatasun/pipelines?scope=branches)
[![Release](https://img.shields.io/github/release/asqatasun/asqatasun.svg)](https://gitlab.com/asqatasun/Asqatasun/-/releases)
[![Contributing welcome](https://img.shields.io/badge/contributing-welcome-brightgreen.svg?style=flat-square)](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/CONTRIBUTING.md)
[![Code of Conduct](https://img.shields.io/badge/code%20of-conduct-ff69b4.svg?style=flat-square)](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/CODE_OF_CONDUCT.md)
 
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

[![https://app.asqatasun.org](https://gitlab.com/asqatasun/Asqatasun/-/raw/master/documentation/en/Images/app.asqatasun.org_FR_690x340.png)](https://app.asqatasun.org)

 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

## Installation and documentation

Four ways to read the doc:

* Online: [doc.asqatasun.org](http://doc.asqatasun.org/en/) 
* In the `documentation/` directory if you cloned the repos or downloaded the .tar.gz

## Download

* [lastest version of Asqatasun, .tar.gz, 83Mb](http://download.asqatasun.org/asqatasun-latest.tar.gz)

And also: 

* [Asqatasun with Vagrant](https://gitlab.com/asqatasun/asqatasun-vagrant/-/tree/master/Ubuntu-18.04-local)
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

This represents 173 accessibility tests.

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

We would be glad to have you on board! You can help in many ways:

1. Use Asqatasun on your sites !
1. Give us [feedback on the forum](http://forum.asqatasun.org)
1. [Fill in bug report](https://gitlab.com/asqatasun/Asqatasun/-/issues)
1. [Contribute](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/CONTRIBUTING.md) code

---

## License

 [AGPL v3](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/LICENSE) 

## Major changes of this last version (Asqatasun 4.1.0, 2020-04-03)

Thanks:

- @Barogthor       for [PR #228](https://github.com/Asqatasun/Asqatasun/issues/228)
- @Grey360         for [PR #227](https://github.com/Asqatasun/Asqatasun/issues/227)
- @Tyraelium       for [PR #226](https://github.com/Asqatasun/Asqatasun/issues/226)
- @Mickaeldicurzio for [PR #224](https://github.com/Asqatasun/Asqatasun/issues/224)
- @Haradwaith      for [PR #236](https://github.com/Asqatasun/Asqatasun/issues/236)
- @allrib          for [PR #223](https://github.com/Asqatasun/Asqatasun/issues/223) and [PR #231](https://github.com/Asqatasun/Asqatasun/issues/231)
- @selfthinker - Feature request [#202](https://github.com/Asqatasun/Asqatasun/issues/202)
- @jeremychauvet - Bug report: [#239](https://github.com/Asqatasun/Asqatasun/issues/239)
- @mgifford    - User feedback: [#205](https://github.com/Asqatasun/Asqatasun/issues/205), [#206](https://github.com/Asqatasun/Asqatasun/issues/206) and [#208](https://github.com/Asqatasun/Asqatasun/issues/208)
- Asqatasun team: @dzc34, @jkowalczyk, @mfaure

### Added

For users:

* New grade (Asqatasun meter) made of A, B, C, D, E, F instead of 0-100% [#252](https://github.com/Asqatasun/Asqatasun/pull/252), [#248](https://github.com/Asqatasun/Asqatasun/pull/248)
* I18N: Add german translation [#172](https://github.com/Asqatasun/Asqatasun/issues/172)

For developers and ops:

* Add Vagrant installation
* Support for Ubuntu 18.04 [#269](https://github.com/Asqatasun/Asqatasun/issues/269), [#281](https://github.com/Asqatasun/Asqatasun/issues/281)
* SQL procedure: Add option to choose the contract duration [#213](https://github.com/Asqatasun/Asqatasun/issues/213)
* Enhanced pre-requisites to allow database name containing hyphen "-"
* SQL procedure / CONTRACT_create: increase the default contract duration (3 years instead of 1 years) [#215](https://github.com/Asqatasun/Asqatasun/issues/215)
* SQL procedure / CONTRACT_create: add option to choose the contract duration in year, month or day [#213](https://github.com/Asqatasun/Asqatasun/issues/213)
* Install.sh - Added new SQL procedures: list_running_acts + Last_audits [#139](https://github.com/Asqatasun/Asqatasun/issues/139)
* added code quality tools
    - Unit tests coverage report (Jacocoo) [#185](https://github.com/Asqatasun/Asqatasun/issues/185)
    - OWASP Dependency-Check [#155](https://github.com/Asqatasun/Asqatasun/issues/155)
    - Checkstyle [#198](https://github.com/Asqatasun/Asqatasun/issues/198)
    - Javadoc [#156](https://github.com/Asqatasun/Asqatasun/issues/156)
* `build_and_run-with-docker.sh` script:
    - Added `--krash-test` option [#182](https://github.com/Asqatasun/Asqatasun/issues/182)
    - Added `--log-build` option [#169](https://github.com/Asqatasun/Asqatasun/issues/169) 
    - Added `--build-only-*` options [#145](https://github.com/Asqatasun/Asqatasun/issues/145)
    - Added option to skip unit tests [#144](https://github.com/Asqatasun/Asqatasun/issues/144)
* Documentation :
    - Have CONTRIBUTING.md more friendly and explicit for beginners [#222](https://github.com/Asqatasun/Asqatasun/issues/222)
    - Added documentation on how to run a krashtest campaign [#147](https://github.com/Asqatasun/Asqatasun/issues/147)
    - Added "howto configuring Apache frontend with AJP connector and HTTPS Let's Encrypt"
    - Added "Crawler management" with howto increase maxDocuments in site-audit

### Breaking changes

For developers and ops:

* JDK upgraded from Java7 to Java8 [#253](https://github.com/Asqatasun/Asqatasun/issues/253)
* DB character encoding is now `utf8mb4` [#255](https://github.com/Asqatasun/Asqatasun/issues/255)
* Build - Maven 3.1 is required (needed for org.owasp:dependency-check-maven) [#154](https://github.com/Asqatasun/Asqatasun/issues/154)

### Changed

For users:

* Changed the default to only show "failed" and "pre-qualified" results) [#202](https://github.com/Asqatasun/Asqatasun/issues/202)
* Default email for login is now `admin@asqatasun.org` [#267](https://github.com/Asqatasun/Asqatasun/issues/267)
* Improved krash emails ergonomy [#276](https://github.com/Asqatasun/Asqatasun/issues/276)

For developers and ops:

* Update version of dependency (DynamicJasper)
* Adjust release script to manage release branch [#315](https://github.com/Asqatasun/Asqatasun/issues/315)
* Configure Travis deployment only on tags [#317](https://github.com/Asqatasun/Asqatasun/issues/317)

### Fixed

For users:

* Incorrect string value: '\xF0\x9F\x99\x82" ...' for column 'Source', aka utf8mb4 should default encoding to be able
 to deal with smileys [#123](https://github.com/Asqatasun/Asqatasun/issues/123) 
* Fixed Rgaa 3.2016, 8.9.1: unit tests fail [#137](https://github.com/Asqatasun/Asqatasun/issues/#137)
* Create a contract pointing to an internal URL, even if the domain does not end with a valid gTLD [#126](https://github.com/Asqatasun/Asqatasun/issues/126)
* Contrast ratio link: fixed ratio parameter [#119](https://github.com/Asqatasun/Asqatasun/issues/119)
* Site-audit in error: added "check for redirection" as possible explanation [#146](https://github.com/Asqatasun/Asqatasun/issues/146)
* Site-audit in error: fixed the robots.txt URL in error message [#179](https://github.com/Asqatasun/Asqatasun/issues/179)
* Don't allow starting an audit if no referential is activated for the current project [#203](https://github.com/Asqatasun/Asqatasun/issues/203)
* Don't allow starting website audit if URL is not defined for the current project [#204](https://github.com/Asqatasun/Asqatasun/issues/204)
* I18N: standardize "project" and "contract" naming (en/fr/es) [#216](https://github.com/Asqatasun/Asqatasun/issues/216)
* Webapp UX - Admin user can quickly add a new project to his account [#208](https://github.com/Asqatasun/Asqatasun/issues/208)
* Webapp UX - Admin user can quickly update his expired contract [#211](https://github.com/Asqatasun/Asqatasun/issues/211)
* Contract creation: verify at least one referential is selected [#27](https://github.com/Asqatasun/Asqatasun/issues/27)
* Contract creation: forbid contract without URL and with website audit enabled [#28](https://github.com/Asqatasun/Asqatasun/issues/28)

For developers and ops:

* Ubuntu 18.04 ensure MariaDB/Mysql configuration file is included [#311](https://github.com/Asqatasun/Asqatasun/issues/311) 
  [#313](https://github.com/Asqatasun/Asqatasun/issues/313)
* Make release script more reliable [#286](https://github.com/Asqatasun/Asqatasun/issues/286)
* `<form:errors path="scenarioFile">`, allowed `<abbr>` in error message [#138](https://github.com/Asqatasun/Asqatasun/issues/138)
* Prerequisites: libspring-instrument-java is no more needed [#200](https://github.com/Asqatasun/Asqatasun/issues/200)
* Unit tests: replaced tgqa.org domain name by asqatasun.ovh [#127](https://github.com/Asqatasun/Asqatasun/issues/127)
* I18N:
    * webapp + rules: converted i18n files in UTF8 character encoding [#170](https://github.com/Asqatasun/Asqatasun/issues/170)
    * webapp / i18n files: converted all HTML entities to their applicable UTF-8 characters [#173](https://github.com/Asqatasun/Asqatasun/issues/173)
    * webapp / i18n files: removing unnecessary escaped characters [#174](https://github.com/Asqatasun/Asqatasun/issues/174)


See full [Changelog](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/CHANGELOG.md) for details.

## Upgrade-o-meter: 

* Please see Documentation > Administrator_doc > [Upgrading](https://doc.asqatasun.org/en/50_Administrator_doc/Upgrading/README.md)



## Have Fun

Happy testing !

[Asqatasun Team](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/asqatasun-team.md)


