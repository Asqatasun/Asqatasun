# CHANGELOG

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## Asqatasun 5.0.0-rc.1, 2020-11-16

### Added

- Added accessibility referential **RGAA v4**
- Added REST **API** `v1.0.0` with Swagger playground

### Changed

- Documentation website is being revamped: [https://doc.asqatasun.org/v5/en/](https://doc.asqatasun.org/v5/en/)
- Docker images have their own dedicated repository: [https://gitlab.com/asqatasun/asqatasun-docker](https://gitlab.com/asqatasun/asqatasun-docker)
- Audits are now performed using **Firefox 78** instead of Firefox 34
- Scenario audits now use **Selenium IDE** instead of the deprecated Selenium Builder
- Database model is now managed by **Flyway**, useful for easier model upgrade
- Whole application is now based on **SpringBoot**
  - Tomcat server is no longer needed
  - a single java file (`.war`) contains all that is needed for webapp (except Firefox and database server)
  - a single java file (`.jar`) contains all that is needed for REST server

### Removed

- Disabled site audit (crawler) [#488](https://gitlab.com/asqatasun/Asqatasun/-/issues/488). 
  This will be re-implemented for v5.1.0 [#521](https://gitlab.com/asqatasun/Asqatasun/-/issues/521)
- Following pre-requisites are no longer necessary: `XVFB` and `Tomcat` server. 

### Fixed

- Allow IPv6 client  [#357](https://gitlab.com/asqatasun/Asqatasun/-/issues/357) 



## Asqatasun 4.1.0, 2020-04-03

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



## Asqatasun 4.1.0-rc.5, 2020-03-26

### Fixed

For developers:

* Update version of dependency (DynamicJasper)
* Adjust release script to manage release branch [#315](https://github.com/Asqatasun/Asqatasun/issues/315)
* Configure Travis deployment only on tags [#317](https://github.com/Asqatasun/Asqatasun/issues/317)


## Asqatasun 4.1.0-rc.4, 2019-05-01

### Fixed

* Make release script more reliable [#286](https://github.com/Asqatasun/Asqatasun/issues/286)

## Asqatasun 4.1.0-rc.3, 2019-04-30

### Fixed

* CSS and images are not loaded in v4.1.0-rc.2 [#285](https://github.com/Asqatasun/Asqatasun/issues/285)

## Asqatasun 4.1.0-rc.2, 2019-04-27

### Added

* Support for Ubuntu 18.04 [#269](https://github.com/Asqatasun/Asqatasun/issues/269), [#281](https://github.com/Asqatasun/Asqatasun/issues/281)

### Changed

* Default email for login is now `admin@asqatasun.org` [#267](https://github.com/Asqatasun/Asqatasun/issues/267)
* Improved krash emails ergonomy [#276](https://github.com/Asqatasun/Asqatasun/issues/276)

## Asqatasun 4.1.0-rc.1, 2019-03-29

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
* SQL procedure: Add option to choose the contract duration [#213](https://github.com/Asqatasun/Asqatasun/issues/213)
* I18N: Add german translation [#172](https://github.com/Asqatasun/Asqatasun/issues/172)

For developers and ops:

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

### Changed

* JDK upgraded from Java7 to Java8 [#253](https://github.com/Asqatasun/Asqatasun/issues/253)
* DB character encoding is now `utf8mb4` [#255](https://github.com/Asqatasun/Asqatasun/issues/255)
* Build - Maven 3.1 is required (needed for org.owasp:dependency-check-maven) [#154](https://github.com/Asqatasun/Asqatasun/issues/154)
* Changed the default to only show "failed" and "pre-qualified" results) [#202](https://github.com/Asqatasun/Asqatasun/issues/202)

### Fixed

For users:

* Incorrect string value: '\xF0\x9F\x99\x82" ...' for column 'Source', aka utf8mb4 should default encoding to be able to deal with smileys [#123](https://github.com/Asqatasun/Asqatasun/issues/123) 
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

* `<form:errors path="scenarioFile">`, allowed `<abbr>` in error message [#138](https://github.com/Asqatasun/Asqatasun/issues/138)
* Prerequisites: libspring-instrument-java is no more needed [#200](https://github.com/Asqatasun/Asqatasun/issues/200)
* Unit tests: replaced tgqa.org domain name by asqatasun.ovh [#127](https://github.com/Asqatasun/Asqatasun/issues/127)
* I18N:
    * webapp + rules: converted i18n files in UTF8 character encoding [#170](https://github.com/Asqatasun/Asqatasun/issues/170)
    * webapp / i18n files: converted all HTML entities to their applicable UTF-8 characters [#173](https://github.com/Asqatasun/Asqatasun/issues/173)
    * webapp / i18n files: removing unnecessary escaped characters [#174](https://github.com/Asqatasun/Asqatasun/issues/174)

### Upgrade-o-meter

The recommended upgrade path consists in exporting Asqatasun data, installing v4.1.0 as a new intance (along Java8) and re-importing data.

We are aware that this may be difficult to some installations, but contributions are welcomed and we are always happy to bring in new contributors :)

## Asqatasun 4.0.3, 2016-08-22

### Upgrade-o-meter 

- no database change
- just replace the `.war` file

### Features

- Docker/RELEASE: reduced weight from 450 MB to 290 MB
- Asqatasun-Runner: RGAA 3.0 by default, enabled SEO and disabled RGAA 2
- Referential RGAA 3.0: english translation added (source : https://github.com/DISIC/rgaa_referentiel_en)

### Bugs

- [#115 - Build failure on project asqatasun-engine with openJDK-8](https://github.com/Asqatasun/Asqatasun/issues/115)
- [#113 - Build failure on the projects webApp and runner with openJDK-8 on Ubuntu 16.04](https://github.com/Asqatasun/Asqatasun/issues/113)
- [#114 - Runner - install/engine folder not present in the tarball](https://github.com/Asqatasun/Asqatasun/issues/114)
- [#117 - Fixed /rules/referential-creator/(...)/pom.vm](https://github.com/Asqatasun/Asqatasun/issues/117)

### Documentation

- Updated runner doc (WIP)
- Added "the referential creation" + "Create-a-rule" (WIP)


## Asqatasun 4.0.2, 2016-06-17

### Upgrade-o-meter 

- no database change
- just replace the `.war` file

### Features

- Enhanced script docker/build_and_run-with-docker.sh

### Security

- Upgrade Apache Commons FileUpload to v1.3.1 
- Upgrade Apache httpclient to v4.3.6

### Bugs

- [#25 - Localhost URLs should not be blocked](https://github.com/Asqatasun/Asqatasun/issues/25)
- [#104 - Rgaa 3 - rule 8.9.1 : Changed actual result of rule when fieldset is used without form](https://github.com/Asqatasun/Asqatasun/issues/104) 
- [#105 - MySQL - Enable innodb_file_per_table ](https://github.com/Asqatasun/Asqatasun/issues/105)
- release/bump_asqatasun.sh : use --push option for pushing new tag

### Configuration

- max-documents for site-audits lowered from 10'000 to 1'000 
- disabled Rgaa-2 rules

### Documentation

- [#88 - DOC Docker - Add tip for Mac OS X and Windows users](https://github.com/Asqatasun/Asqatasun/issues/88) 


## Asqatasun 4.0.1, 2016-03-18

### Security

- [#83 - Upgrade Apache Commons Collections to v3.2.2](https://github.com/Asqatasun/Asqatasun/issues/83) 

### Bugs

- [#85 - Audit full-site : accept the new gTLDs (eg .jobs, .paris)](https://github.com/Asqatasun/Asqatasun/issues/85)


## Asqatasun 4.0.0, 2016-03-02

### Features

- devops:
    - Docker images (+ Docker automated builds)
    - Ansible role + Vagrantfile
- Implementation of SEO rules
- RGAA 3 translated in English
- Documentation: huge refactor 
- New translation system on Transifex https://www.transifex.com/asqatasun/asqatasun/
- New logo integrated in the app
- Fork from Tanaguru

### Bugs

- [#58 - PDF - clicking on it returns to an error page](https://github.com/Asqatasun/Asqatasun/issues/58)
- [#43 - Mysql config: add UTF-8 
       to support any type of charset including cyrillic + enhance way to modify Mysql conf](https://github.com/Asqatasun/Asqatasun/issues/43)


## Asqatasun 4.0.0-rc.1, 2016-01-12

### Bugs

- [#43 - Updated Mysql config](https://github.com/Asqatasun/Asqatasun/issues/43) : add UTF-8
to support any type of charset including cyrillic + enhance way to modify Mysql conf

### Features

- devops: Ansible role + Vagrantfile
- Documentation: huge refactor; Sections done: Install, Contrib > Docker build, Contrib > Release
- New logo integrated in the app

## Asqatasun 4.0.0-beta.2, 2015-11-27

### Features

- devops: Docker images (+ Docker automated builds)
- Implementation of SEO rules
- Fork from Tanaguru

## Tanaguru 3.1.0, 2015-07-02

### Features

- Implementation of Rgaa3 rules (173 rules)

## Tanaguru 3.0.6, 2015-06-26

### Features

- Most of the implementation of Rgaa3 rules (about 150 rules)
- Refactoring (clean-up) of Data Service layer usage
- Package naming modification : now all packages starts with "org.asqatasun"
- Rules commons improvments : use of Pair to define a combinaison of Result/Message
- Rule TU improvments : provide new methods to test rule result and remarks
- Add the "COMPLEX_TABLE_MARKER" 

## Tanaguru 3.0.5, 2015-03-16

### Bugs

- tg-gh-issue_#107 : Provide a way to set-up proxy credentials
- tg-gh-issue_#105 : Set "esapiPropertyValue" property of tokenManager bean to use the system property confDir value instead of hard-coded value
- tg-gh-issue_#102 : Assisted Audit : Complete action is ineffective

### Features 

- UI improvments
- Correction of bug on buttons that apply automatic result on assisted audit 
- User guide documentation 


## Tanaguru 3.0.4, 2015-02-12

### Bugs

- tg-gh-issue_#95 : Provide mechanism to set the size of the firefox screen when loading the page
- tg-gh-issue_#93 : CLI install instructions broken 

### Features

- Spanish language integration
- A11y improvments in audit result page
- Initialisation of Rgaa3 referential context
- Initialisation of assisted audit
- Addition of cli script options 


## Tanaguru 3.0.3, 2014-07-06

### Bugs

- tg-gh-issue_#86 : AW22 8.8.1 Case sensitivity of lang definition

### Features

- Upgrade Java from 1.6 to 1.7
- Upgrade Hibernate from 3.6.0.Final to 4.3.5.Final
- Add new "referential-creator-maven-plugin" and "referential-creator" projects 
- Create "rule demo project"
- Refactor rule packaging to be handled by one project 
- Harmonisation of Referential names
- Improve rules test management 


## Tanaguru 3.0.2, 2014-05-16

### Bugs

- tg-gh-issue_#71 : Crawl parameters: included URL pattern is not shown in audit details 

### Features

- tg-gh-issue_#74 : Top 5 invalid tests: add level of test 
- tg-gh-issue_#73 : Add link to contrast finder 
- tg-gh-issue_#68 : List of pages invalidating a given test: enhance links  
- tg-gh-issue_#67 : List of pages invalidating a given test: add test label 
- tg-gh-issue_#66 : Fix weighted formula 
- tg-gh-issue_#65 : Add UnicityChecker  
- tg-gh-issue_#64 : Number of "pages tested" is not the good one 
Manage levels more generically (level 1,2,3) and delegate the naming to the referential i18n project


## Tanaguru 3.0.1, 2014-04-24


### Bugs

- Handle rel canonical tags with relative Urls on crawl

### Features
- update table representation for non canonical pages :only display link and rank
- Add the inclusion option to the crawler tg-gh-issue_#59 


## Tanaguru 3.0.0, 2014-02-19


### Bugs

- tg-gh-issue_#47 : The language detection tests return bad results for uppercase submitted text 

### Features 

- Rgaa 2.2 referential full supported
- Postgresql management (creation and insertion scripts)
- Crawl optimisations (deal with canonical, improve fetch counting, improve fetch of testable data)
- tg-gh-issue_#50 : Provide a way to set-up whether the cookies have to be considered while crawling 
- tg-gh-issue_#49 : SEO 6.4.1 : for a given title value, sort URLs 
- tg-gh-issue_#46 : Override default user agent used by apache library to test URL before launching effectively the audit 
- tg-gh-issue_#43 : Disable the load of Css content for audit of SEO type 
- tg-gh-issue_#37 : Mail server is not configurable 


## Tanaguru 3.0.0-beta9, 2014-01-17

### Bugs

- tg-gh-issue_#637 : Tanaguru rejects pages with the time type "application/xhtml+xml" on site audit 

### Features 

- tg-gh-issue_#638 : Crawler : the pages that defines a link tag with the rel attribute equals to canonical must be excluded from the crawl 


## Tanaguru 3.0.0-beta8, 2013-12-18

### Bugs

- tg-gh-issue_#636 : Clean-up step at the end of audit may cause deadlock on CONTENT table 


## Tanaguru 3.0.0-beta7, 2013-12-11

### Bugs

- Display correct breadcrumb for test details page

### AW22 referential

- Complete (links, downloadable documents detection, hidden elements, contrasts)

### Features

- SEO referential -> rules about duplicate search now consider link elements with rel=canonical attribute
- A11y referential -> do not consider transparent as white color for contrast rules (ContrastChecker)


## Tanaguru 3.0.0-beta6, 2013-12-05

### UI Evolution 
- add shortcuts to audit actions in home view
- Add truncated view on test result that lead to a page that displays all the remarks
- Online Demo link and login page evolution
- Specific rendering for test of site scope (seo rules)

### AW22 referential 

- Complete (links, downloadable documents detection, hidden elements, contrasts)

### SEO referential

- now based on rules-commons


## Tanaguru 3.0.0-beta5, 2013-10-21

### Features

- tg-gh-issue_#634 : Installation script 
- tg-gh-issue_#633 : Refactor js script that extract colors to only use native Javascript 
- tg-gh-issue_#631 : Clean-up maven configuration 

### Bugs

- tg-gh-issue_#632 : Css adaptation : catch exception when trying to retrieve file as external resource 
- tg-gh-issue_#630 : Encoding issue with filled-in URL  


## Tanaguru 3.0.0-beta4, 2013-10-09

### Features

- Accessiweb 2.2 implementation : rules about color contrast and language detection
- Improvment of UI


## Tanaguru 3.0.0-beta3, 2013-08-02

### Bugs

- tg-gh-issue_#596: Only consider pages with Http status code equals 
to 200 in the Top 10 of invalid URLs tables of the synthesis page 


## Tanaguru 3.0.0-beta2, 2013-06-04

### Features

- tg-gh-issue_#595 : 	Management of Krash audits 
- tg-gh-issue_#593 :	Link the act entity to the audit instead of the webresource 
- tg-gh-issue_#592 :	Embed a java library to highlight source code instead of using geshi 
- tg-gh-issue_#591 :  Upgrade apache commons libraries 


## Tanaguru 3.0.0-beta1, 2013-04-05

### Bugs

- tg-gh-issue_#538 : The sort contract by mark doesn't work in the contract view 

### Features

- tg-gh-issue_#590 : Provide a self-structured csv entry that handles ProcessRemarks info and Evidence Elements data 
- tg-gh-issue_#583 : Use css-phloc as css adapter and parser 
- tg-gh-issue_#582 : Use jsoup as html adapter 
- tg-gh-issue_#581 : Create sebuilder-interpreter-tool subproject based on sebuilder-interpreter api
- tg-gh-issue_#580 : Upgrade to Heritrix 3.1.0 version
- tg-gh-issue_#579 : Clean-up dependencies
- tg-gh-issue_#552 : Clean-up SSPHandler and DOMHandler to remove unused primitives
- tg-gh-issue_#551 : Provide a mechanism to avoid to launch tests that return NOT_TESTED as result [OPTIMIZATION]
- tg-gh-issue_#550 : Improve ergonomy of audit result pages
- tg-gh-issue_#549 : Compute criterion results while analysing
- tg-gh-issue_#548 : Introduce the criterionStatistics entity
- tg-gh-issue_#547 : Add an audit result view with criterion results
- tg-gh-issue_#534 : Button "audit again"
- tg-gh-issue_#283 : Separate NMI from Untested
- tg-gh-issue_#163 : Add a "please wait" page after submit and before audit result

## Tanaguru 2.1.1, 2013-04-15

### Bugs

- tg-gh-issue_#589 -> syntax of web.xml invalid 
- tg-gh-issue_#588 -> Provide a mechanism to avoid multiple form validation when submitting an audit 
- tg-gh-issue_#587 -> Downgrade version of commons-collections due to compatibily issue encountered on deployment on Jboss 
- tg-gh-issue_#540 -> CastException occured while auditing multiple files. 


## Tanaguru 2.1.0, 2012-11-07

### Bugs

- tg-gh-issue_#516 : site-wide exports: add "page+site" results 
- tg-gh-issue_#327 : Error on adaptation encountered on some pages of a site audit causes Fatal error in processor component 

### Features

- tg-gh-issue_#524 -> Provide a UI to override test weights for a given user  
- tg-gh-issue_#523 -> Add weight field to the TEST entity and use it to compute the raw mark  
- tg-gh-issue_#522 -> Provide a page that lists the pages invalidating a given test  
- tg-gh-issue_#520 -> Provide a back office interface  
- tg-gh-issue_#515 -> list of pages: change link destination (audit // page itself)  
- tg-gh-issue_#514 -> Bar graph on page audit-synthesis: put red at the bottom  
- tg-gh-issue_#416 -> Avoid the usage of type wildcard  
- tg-gh-issue_#271 -> Add console to the project-page  


## Tanaguru 2.0.1, 2012-09-19
 
### Bugs

- tg-gh-issue_#510 -> The externalCssRetriever when a new css is fetched during adaptationThe cssContentAdapter implementation doesn't alert 

### Features

- tg-gh-issue_#509 ->	Make the different treatment window parameters of the AuditCommand implementation configurable 
- tg-gh-issue_#506 -> Linkify the URLs (SEO 6.4.1 + SEO 7.6.1) 


## Tanaguru 2.0.0, 2012-08-30

### Bugs

- tg-gh-issue_#446 -> Audit crash on www.letc.fr: infinite loop with @import 
- tg-gh-issue_#428 -> the getImageFromUrl() method of the SSPHandler implementation can't deal with embedded pictures 
- tg-gh-issue_#426 -> The snapshot displayed in the synthesis result page in the case of a Scenario Audit is wrong 
- tg-gh-issue_#424 -> The content of the scenario, present as a hidden field in the audit scenario set-up form, has to be removed 
- tg-gh-issue_#422 -> Site-audit unable to run -> mail content shrunk 
- tg-gh-issue_#419 -> Page audit fails if "http://" is missing in the URL 
- tg-gh-issue_#414 -> .ods Export for SEO doesn't work 
- tg-gh-issue_#407 -> bad link in email sent for a scenario audit 
- tg-gh-issue_#404 -> Site audits crash when at least one SSP among the set of SSP hasn't been adapted 
- tg-gh-issue_#398 -> Audit Options: rename "Accessibility level" into "Level" 
- tg-gh-issue_#395 -> Parts of <head> deleted by Adapter Component 

### Features

- tg-gh-issue_#425 -> Add getter/setter to the "timeout" attribute of the TgolHighlighter class to allow to overidde the default value by spring configuration 
- tg-gh-issue_#418 -> Launch page-audits via webdriver 
- tg-gh-issue_#408 -> Email sent for a scenario: should include the scenario name 
- tg-gh-issue_#406 -> Add a link to the source code in the page result page 
- tg-gh-issue_#405 -> Remove the weighted mark from the different views 
- tg-gh-issue_#402 -> Add new view to set-up scenario and launch audit 
- tg-gh-issue_#401 -> Implementation of Har file reader 
- tg-gh-issue_#400 -> Retrieve external CSS while adapting SSP (if not already retrieved during the crawl) 
- tg-gh-issue_#399 -> Add a rank field to the WebResource entity 
- tg-gh-issue_#365 -> Add referential info in the audit list contract info page 
- tg-gh-issue_#337 -> Evolution of AuditService API to enable to launch a scenario audit 
- tg-gh-issue_#336 -> Create a new Scenario loader module with an implementation that uses the WebDriver API 
- tg-gh-issue_#280 -> Export: PDF format 
- tg-gh-issue_#279 -> Export: CSV format  
- tg-gh-issue_#270 -> Audit sites with a given scenario 
- tg-gh-issue_#269 -> Audit sites with authentication 


## Tanaguru 1.5.2-RC1, 2012-05-04

### Bugs

- tg-gh-issue_#285 -> The css parser misinterprets selectors with multiple values 
- tg-gh-issue_#330 -> Controller parameters type not controlled when converted as Numbers 

### Features

- tg-gh-issue_#288 -> Provide a way to externalise web-app resources (use of a cdn) 
- tg-gh-issue_#318 -> Handle the "base" tag in Css adaptation to get external resources 
- tg-gh-issue_#319 -> Create a "work in progress" page for a page audit that lasts longer than a given delay 
- tg-gh-issue_#331 -> TGSI Model update : change TGSI_RESTRICTION table to TGSI_OPTION table with a "Is_Restriction" field 
- tg-gh-issue_#332 -> TGSI Model update : Refactoring due to Contract management modifications 
- tg-gh-issue_#333 -> Create CHANGELOG.txt that list the features and bugs of each version 


## Tanaguru 1.5.1, 2012-05-04

### Bugs

- tg-gh-issue_#325 -> The upload module doesn't work when the Proxy properties (ProxyPort and ProxyPort) are set 


## Tanaguru 1.5.0, 2012-02-03

Same as 1.5.0-RC1



## Tanaguru 1.5.0-RC2, 2012-01-27

Same as 1.5.0-RC1


## Tanaguru 1.5.0-RC1, 2012-01-24

### Bugs

- tg-gh-issue_#236 -> Add a licence file 
- tg-gh-issue_#248 -> The page list of an audit is sorted by weighted mark instead of raw mark 

### Features 

- tg-gh-issue_#184 -> have a specific TITLE tag for audit 1page / 10pages / site 
- tg-gh-issue_#250 -> Modify the name of the user-agent used by heritrix 
- tg-gh-issue_#254 -> Evolution of the web-application interface to use the bootstrap UI toolkit 
- tg-gh-issue_#255 -> Add a console to sort results in the result page 
- tg-gh-issue_#256 -> Display all the audits of a contract in the contract page 
- tg-gh-issue_#257 -> Add an indicator to indicate that an audit is running for a given contract 



## Tanaguru 1.4.2-RC1, 2012-01-16

### Features 

- tg-gh-issue_#249 -> Allow Tanaguru to be used behind a web proxy 


## Tanaguru 1.4.0-RC1, 2011-10-26

### Bugs

- tg-gh-issue_#45 -> add licence header to all source files 

### Features

- tg-gh-issue_#229 -> ContentLoader Implementation 
- tg-gh-issue_#228 -> AuditService interface 
- tg-gh-issue_#227 -> Update the ContentDAO interface to add a boolean option when retrieve a Content with its RelatedContent 


## Tanaguru 1.3.0-RC1, 2011-08-09

### Features

- tg-gh-issue_#199 -> Add tunable parameters for the crawler component 
- tg-gh-issue_#208 -> Parameterization component 


## Tanaguru 1.2.1-RC2, 2011-07-07

### Bugs

- tg-gh-issue_#207 -> Tanaguru consolidation can cause java heap space error in case of large set of pages in the same audit 


## Tanaguru 1.2.0, 2011-06-21

### Bugs

- tg-gh-issue_#197 -> Crawler : remove the version of tanaguru in the heritrix user-agent declaration 

### Features

- tg-gh-issue_#193 -> Realize content relationship association while adapting instead of while crawling 


## Tanaguru 1.0.0, 2011-04-08

### Bugs

- tg-gh-issue_#57 -> Tanaguru web-app cannot deal with URL containing special characters 
- tg-gh-issue_#134 -> Have a simple README at the root of the svn to get people started 

### Features 

- tg-gh-issue_#58 -> Tanaguru web-app is not thread safe 
