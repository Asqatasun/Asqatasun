# CHANGELOG

Asqatasun 4.1.0, 2018-1x-xx
---------------------------

- thanks:
  - @Barogthor       for [PR #228](https://github.com/Asqatasun/Asqatasun/issues/228)
  - @Grey360         for [PR #227](https://github.com/Asqatasun/Asqatasun/issues/227)
  - @Tyraelium       for [PR #226](https://github.com/Asqatasun/Asqatasun/issues/226)
  - @Mickaeldicurzio for [PR #224](https://github.com/Asqatasun/Asqatasun/issues/224)
  - @Haradwaith      for [PR #236](https://github.com/Asqatasun/Asqatasun/issues/236)
  - @allrib          for [PR #223](https://github.com/Asqatasun/Asqatasun/issues/223)
                     and [PR #231](https://github.com/Asqatasun/Asqatasun/issues/231)
  - @selfthinker - Feature request [#202](https://github.com/Asqatasun/Asqatasun/issues/202)
  - @jeremychauvet - Bug report: [#239](https://github.com/Asqatasun/Asqatasun/issues/239)
  - @mgifford    - User feedback: [#205](https://github.com/Asqatasun/Asqatasun/issues/205),
                                  [#206](https://github.com/Asqatasun/Asqatasun/issues/206)
                              and [#208](https://github.com/Asqatasun/Asqatasun/issues/208)

- WIP:
  - [#123 - Incorrect string value: '\xF0\x9F\x99\x82" ...' for column 'Source'](https://github.com/Asqatasun/Asqatasun/issues/123)
  - [#163 - Added the new version of RGAA 3.2017](https://github.com/Asqatasun/Asqatasun/issues/163)
  - [#137 - Fixed Rgaa 3.2016, 8.9.1: unit tests fail](https://github.com/Asqatasun/Asqatasun/issues/#137)
  - refactoring install.sh and pre-requisites.sh
    - added install-SQL.sh and pre-requisites-SQL.sh
    - @@@TODO test docker images
    - @@@TODO Docker Hub: update build config (new paths)
    - @@@TODO update documentation
    - @@@TODO fix ansible
    - @@@TODO test tomcat8


### Upgrade-o-meter
- [#154 - Build - Maven 3.1 is required (needed for org.owasp:dependency-check-maven)](https://github.com/Asqatasun/Asqatasun/issues/154)
- [#213 - SQL procedure / CONTRACT_create requires 2 added parameters (timeSpan, timeSpanUnit)](https://github.com/Asqatasun/Asqatasun/issues/213)

### Features
- [#172 - i18n: Add german translation](https://github.com/Asqatasun/Asqatasun/issues/172) 

### Security
None

### Outdated dependencies
- [#239 - PHPLoc repository no longer responds](https://github.com/Asqatasun/Asqatasun/issues/239)
- [#234 - Upgraded itextpdf (5.5.13 instead of 5.5.8)](https://github.com/Asqatasun/Asqatasun/issues/234)
- [#235 - Upgraded jhighlight (1.0.3 instead of 1.0)](https://github.com/Asqatasun/Asqatasun/issues/235)
- [#221 - Upgraded mysql-connector-java (5.1.46 instead of 5.1.15)](https://github.com/Asqatasun/Asqatasun/issues/221)
- [#233 - Upgraded yuicompressor-maven-plugin (1.5.1 instead of 1.3.0)](https://github.com/Asqatasun/Asqatasun/issues/233)
- [#232 - Upgraded maven-clean-plugin (3.0.0 instead of 2.5)](https://github.com/Asqatasun/Asqatasun/issues/232)
- [#230 - Upgraded maven-jar-plugin (3.0.2 instead of 2.5)](https://github.com/Asqatasun/Asqatasun/issues/230)
- [#229 - Upgraded maven-war-plugin (3.2.0 instead of 2.4)](https://github.com/Asqatasun/Asqatasun/issues/229)
- [#245 - Upgraded maven-surefire-plugin (3.0.0-M1 instead of 2.17)](https://github.com/Asqatasun/Asqatasun/issues/245)
- [#199 - Upgraded maven-compiler-plugin (3.7.0 instead of 3.1)](https://github.com/Asqatasun/Asqatasun/issues/199)

### Bugs
- [#128 - Fixed Rgaa 3.0, 8.9.1: unit tests fail](https://github.com/Asqatasun/Asqatasun/issues/#128)
- [#183 - Fixed build_and_run-with-docker.sh: "-t" option is used 2 times](https://github.com/Asqatasun/Asqatasun/issues/183)
- [#142 - Fixed SQL Procedure delete-audit-by-id](https://github.com/Asqatasun/Asqatasun/issues/142)
- [#149 - Fixed install.sh: tomcat parameters are missing](https://github.com/Asqatasun/Asqatasun/issues/149)
- [#243 - Fixed Build on debian/ubuntu: failed to execute goal maven-surefire](https://github.com/Asqatasun/Asqatasun/issues/243)
- Enhanced pre-requisites to allow database name containing hyphen "-"
- Webapp:
    - [#126 - Create a contract pointing to an internal URL, even if the domain does not end with a valid gTLD](https://github.com/Asqatasun/Asqatasun/issues/126)
    - [#119 - contrast ratio link: fixed ratio parameter](https://github.com/Asqatasun/Asqatasun/issues/119)
    - [#146 - site-audit in error: added "check for redirection" as possible explanation](https://github.com/Asqatasun/Asqatasun/issues/146)
    - [#179 - site-audit in error: fixed the robots.txt URL in error message](https://github.com/Asqatasun/Asqatasun/issues/179)
    - [#138 - `<form:errors path="scenarioFile">`, allowed `<abbr>` in error message](https://github.com/Asqatasun/Asqatasun/issues/138)
    - [#203 - No allow starting an audit if no referential is activated for the current project](https://github.com/Asqatasun/Asqatasun/issues/203)
    - [#204 - No allow starting website audit if URL is not defined for the current project](https://github.com/Asqatasun/Asqatasun/issues/204)
    - [#27 - Contract creation: verify at least one referential is selected](https://github.com/Asqatasun/Asqatasun/issues/27)
    - [#28 - Contract creation: forbid contract without URL and with website audit enabled](https://github.com/Asqatasun/Asqatasun/issues/28)

### Configuration
- [#202 - Webapp: changed the default to only show "failed" and "pre-qualified" results)](https://github.com/Asqatasun/Asqatasun/issues/202)

### Improvement
- [#208 - Webapp UX - Admin user can quickly add a new project to his account](https://github.com/Asqatasun/Asqatasun/issues/208)
- [#211 - Webapp UX - Admin user can quickly update his expired contract](https://github.com/Asqatasun/Asqatasun/issues/211)
- [#216 - Webapp UX - i18n: standardize "project" and "contract" appelations (en/fr/es)](https://github.com/Asqatasun/Asqatasun/issues/216)
- [#197 - Webapp - Allowed the HTTP header "Cache-Control: immutable" for static files (css, js, png,...)](https://github.com/Asqatasun/Asqatasun/issues/197)
- [#215 - SQL procedure / CONTRACT_create: increase the default contract duration (3 years instead of 1 years)](https://github.com/Asqatasun/Asqatasun/issues/215)
- [#213 - SQL procedure / CONTRACT_create: add option to choose the contract duration in year, month or day](https://github.com/Asqatasun/Asqatasun/issues/213)
- [#139 - Install.sh - Added new SQL procedures: list_running_acts + Last_audits](https://github.com/Asqatasun/Asqatasun/issues/139)
- [#168 - Tar.gz - CHANGELOG and README files in html format](https://github.com/Asqatasun/Asqatasun/issues/168)
- [#45  - Tar.gz - Documentation files in html format](https://github.com/Asqatasun/Asqatasun/issues/45)
- [#152 - Dockerfile: add HEALTHCHECK instruction](https://github.com/Asqatasun/Asqatasun/issues/152)
- [#241 - Added some information to the MANIFEST.MF files : commit, buid timestamp](https://github.com/Asqatasun/Asqatasun/issues/241)
- Added some maven report plugins:
    - [#185 - Unit tests coverage report (Jacocoo)](https://github.com/Asqatasun/Asqatasun/issues/185)
    - [#155 - OWASP Dependency-Check](https://github.com/Asqatasun/Asqatasun/issues/155)
    - [#198 - Checkstyle](https://github.com/Asqatasun/Asqatasun/issues/198)
    - [#156 - Javadoc](https://github.com/Asqatasun/Asqatasun/issues/156)
- `build_and_run-with-docker.sh` script:
    - [#182 - Added --krash-test   option](https://github.com/Asqatasun/Asqatasun/issues/182)
    - [#169 - Added --log-build    option](https://github.com/Asqatasun/Asqatasun/issues/169) 
    - [#145 - Added --build-only-* options](https://github.com/Asqatasun/Asqatasun/issues/145)
    - [#144 - Added option to skip unit tests](https://github.com/Asqatasun/Asqatasun/issues/144)

### Refactoring
- [#200 - Prerequisites : libspring-instrument-java is not needed](https://github.com/Asqatasun/Asqatasun/issues/200)
- [#127 - Unit tests : replaced tgqa.org domain name by asqatasun.ovh](https://github.com/Asqatasun/Asqatasun/issues/127)

### Documentation
- [#227 - Minor orthographic fixes to CONTRIBUTING.md](https://github.com/Asqatasun/Asqatasun/issues/227)
- [#222 - Have CONTRIBUTING.md more friendly and explicit for beginners](https://github.com/Asqatasun/Asqatasun/issues/222)
- [#206 - Improved user documentation](https://github.com/Asqatasun/Asqatasun/issues/206)
- [#166 - Updated "Contributor > Build a Docker image"](https://github.com/Asqatasun/Asqatasun/issues/166)
- [#147 - Added documentation on how to run a krashtest campaign](https://github.com/Asqatasun/Asqatasun/issues/147)
- Added "howto configuring Apache frontend with AJP connector and HTTPS Let's Encrypt"
- Added "Crawler management" with howto increase maxDocuments in site-audit
- Reorganised INSTALL section

### Task
- [#170 - webapp + rules: converted i18n files in UTF8 character encoding](https://github.com/Asqatasun/Asqatasun/issues/170)
- [#173 - webapp / i18n files: converted all HTML entities to their applicable UTF-8 characters](https://github.com/Asqatasun/Asqatasun/issues/173)
- [#174 - webapp / i18n files: removing unnecessary escaped characters](https://github.com/Asqatasun/Asqatasun/issues/174)


Asqatasun 4.0.3, 2016-08-22
---------------------------
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


Asqatasun 4.0.2, 2016-06-17
---------------------------
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


Asqatasun 4.0.1, 2016-03-18
---------------------------
### Security
- [#83 - Upgrade Apache Commons Collections to v3.2.2](https://github.com/Asqatasun/Asqatasun/issues/83) 

### Bugs
- [#85 - Audit full-site : accept the new gTLDs (eg .jobs, .paris)](https://github.com/Asqatasun/Asqatasun/issues/85)


Asqatasun 4.0.0, 2016-03-02
---------------------------
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


Asqatasun 4.0.0-rc.1, 2016-01-12
--------------------------------
### Bugs
- [#43 - Updated Mysql config](https://github.com/Asqatasun/Asqatasun/issues/43) : add UTF-8
to support any type of charset including cyrillic + enhance way to modify Mysql conf

### Features
- devops: Ansible role + Vagrantfile
- Documentation: huge refactor; Sections done: Install, Contrib > Docker build, Contrib > Release
- New logo integrated in the app


Asqatasun 4.0.0-beta.2, 2015-11-27
----------------------------------
### Features
- devops: Docker images (+ Docker automated builds)
- Implementation of SEO rules
- Fork from Tanaguru


Tanaguru 3.1.0, 2015-07-02
--------------------------
### Features
- Implementation of Rgaa3 rules (173 rules)


Tanaguru 3.0.6, 2015-06-26
--------------------------
### Features
- Most of the implementation of Rgaa3 rules (about 150 rules)
- Refactoring (clean-up) of Data Service layer usage
- Package naming modification : now all packages starts with "org.asqatasun"
- Rules commons improvments : use of Pair to define a combinaison of Result/Message
- Rule TU improvments : provide new methods to test rule result and remarks
- Add the "COMPLEX_TABLE_MARKER" 


Tanaguru 3.0.5, 2015-03-16
--------------------------
### Bugs
- tg-gh-issue_#107 : Provide a way to set-up proxy credentials
- tg-gh-issue_#105 : Set "esapiPropertyValue" property of tokenManager bean to use the system property confDir value instead of hard-coded value
- tg-gh-issue_#102 : Assisted Audit : Complete action is ineffective

### Features 
- UI improvments
- Correction of bug on buttons that apply automatic result on assisted audit 
- User guide documentation 


Tanaguru 3.0.4, 2015-02-12
--------------------------
### Bugs
- tg-gh-issue_#95 : Provide mechanism to set the size of the firefox screen when loading the page
- tg-gh-issue_#93 : CLI install instructions broken 

### Features
- Spanish language integration
- A11y improvments in audit result page
- Initialisation of Rgaa3 referential context
- Initialisation of assisted audit
- Addition of cli script options 


Tanaguru 3.0.3, 2014-07-06
--------------------------
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


Tanaguru 3.0.2, 2014-05-16
--------------------------
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


Tanaguru 3.0.1, 2014-04-24
--------------------------
### Bugs
- Handle rel canonical tags with relative Urls on crawl

### Features
- update table representation for non canonical pages :only display link and rank
- Add the inclusion option to the crawler tg-gh-issue_#59 


Tanaguru 3.0.0, 2014-02-19
--------------------------
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


Tanaguru 3.0.0-beta9, 2014-01-17
--------------------------------
### Bugs
- tg-gh-issue_#637 : Tanaguru rejects pages with the time type "application/xhtml+xml" on site audit 

### Features 
- tg-gh-issue_#638 : Crawler : the pages that defines a link tag with the rel attribute equals to canonical must be excluded from the crawl 


Tanaguru 3.0.0-beta8, 2013-12-18
--------------------------------
### Bugs
- tg-gh-issue_#636 : Clean-up step at the end of audit may cause deadlock on CONTENT table 


Tanaguru 3.0.0-beta7, 2013-12-11
--------------------------------
### Bugs
- Display correct breadcrumb for test details page

### AW22 referential
- Complete (links, downloadable documents detection, hidden elements, contrasts)

### Features
- SEO referential -> rules about duplicate search now consider link elements with rel=canonical attribute
- A11y referential -> do not consider transparent as white color for contrast rules (ContrastChecker)


Tanaguru 3.0.0-beta6, 2013-12-05
--------------------------------
### UI Evolution 
- add shortcuts to audit actions in home view
- Add truncated view on test result that lead to a page that displays all the remarks
- Online Demo link and login page evolution
- Specific rendering for test of site scope (seo rules)

### AW22 referential 
- Complete (links, downloadable documents detection, hidden elements, contrasts)

### SEO referential
- now based on rules-commons


Tanaguru 3.0.0-beta5, 2013-10-21
--------------------------------
### Features
- tg-gh-issue_#634 : Installation script 
- tg-gh-issue_#633 : Refactor js script that extract colors to only use native Javascript 
- tg-gh-issue_#631 : Clean-up maven configuration 

### Bugs
- tg-gh-issue_#632 : Css adaptation : catch exception when trying to retrieve file as external resource 
- tg-gh-issue_#630 : Encoding issue with filled-in URL  


Tanaguru 3.0.0-beta4, 2013-10-09
--------------------------------
### Features
- Accessiweb 2.2 implementation : rules about color contrast and language detection
- Improvment of UI


Tanaguru 3.0.0-beta3, 2013-08-02
--------------------------------
### Bugs
- tg-gh-issue_#596: Only consider pages with Http status code equals 
to 200 in the Top 10 of invalid URLs tables of the synthesis page 


Tanaguru 3.0.0-beta2, 2013-06-04
--------------------------------
### Features
- tg-gh-issue_#595 : 	Management of Krash audits 
- tg-gh-issue_#593 :	Link the act entity to the audit instead of the webresource 
- tg-gh-issue_#592 :	Embed a java library to highlight source code instead of using geshi 
- tg-gh-issue_#591 :  Upgrade apache commons libraries 


Tanaguru 3.0.0-beta1, 2013-04-05
--------------------------------
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

Tanaguru 2.1.1, 2013-04-15
--------------------------
### Bugs
- tg-gh-issue_#589 -> syntax of web.xml invalid 
- tg-gh-issue_#588 -> Provide a mechanism to avoid multiple form validation when submitting an audit 
- tg-gh-issue_#587 -> Downgrade version of commons-collections due to compatibily issue encountered on deployment on Jboss 
- tg-gh-issue_#540 -> CastException occured while auditing multiple files. 


Tanaguru 2.1.0, 2012-11-07
--------------------------
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


Tanaguru 2.0.1, 2012-09-19
--------------------------
### Bugs
- tg-gh-issue_#510 -> The externalCssRetriever when a new css is fetched during adaptationThe cssContentAdapter implementation doesn't alert 

### Features
- tg-gh-issue_#509 ->	Make the different treatment window parameters of the AuditCommand implementation configurable 
- tg-gh-issue_#506 -> Linkify the URLs (SEO 6.4.1 + SEO 7.6.1) 


Tanaguru 2.0.0, 2012-08-30
--------------------------
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


Tanaguru 1.5.2-RC1, 2012-05-04
------------------------------
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


Tanaguru 1.5.1, 2012-05-04
--------------------------
### Bugs
- tg-gh-issue_#325 -> The upload module doesn't work when the Proxy properties (ProxyPort and ProxyPort) are set 


Tanaguru 1.5.0, 2012-02-03
--------------------------
Same as 1.5.0-RC1



Tanaguru 1.5.0-RC2, 2012-01-27
--------------------------
Same as 1.5.0-RC1



Tanaguru 1.5.0-RC1, 2012-01-24
------------------------------
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



Tanaguru 1.4.2-RC1, 2012-01-16
------------------------------
### Features 
- tg-gh-issue_#249 -> Allow Tanaguru to be used behind a web proxy 


Tanaguru 1.4.0-RC1, 2011-10-26
------------------------------
### Bugs
- tg-gh-issue_#45 -> add licence header to all source files 

### Features
- tg-gh-issue_#229 -> ContentLoader Implementation 
- tg-gh-issue_#228 -> AuditService interface 
- tg-gh-issue_#227 -> Update the ContentDAO interface to add a boolean option when retrieve a Content with its RelatedContent 



Tanaguru 1.3.0-RC1, 2011-08-09
------------------------------
### Features
- tg-gh-issue_#199 -> Add tunable parameters for the crawler component 
- tg-gh-issue_#208 -> Parameterization component 



Tanaguru 1.2.1-RC2, 2011-07-07
------------------------------
### Bugs
- tg-gh-issue_#207 -> Tanaguru consolidation can cause java heap space error in case of large set of pages in the same audit 


Tanaguru 1.2.0, 2011-06-21
--------------------------
### Bugs
- tg-gh-issue_#197 -> Crawler : remove the version of tanaguru in the heritrix user-agent declaration 

### Features
- tg-gh-issue_#193 -> Realize content relationship association while adapting instead of while crawling 


Tanaguru 1.0.0, 2011-04-08
--------------------------
### Bugs
- tg-gh-issue_#57 -> Tanaguru web-app cannot deal with URL containing special characters 
- tg-gh-issue_#134 -> Have a simple README at the root of the svn to get people started 

### Features 
- tg-gh-issue_#58 -> Tanaguru web-app is not thread safe 
