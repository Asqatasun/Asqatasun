
--------------------------------
Asqatasun 4.x.x, 2016-xx-xx
---------------------------

Features:
None

Security:
None

Bugs:
None

Configuration:
None

Documentation:
- Reorganised INSTALL section
- Added "howto configuring Apache frontend with AJP connector and HTTPS Let's Encrypt"
- Added "Crawler management" with howto increase maxDocuments in site-audit

Upgrade-o-meter (borrowed from folks at Gitlab):
None


--------------------------------
Asqatasun 4.0.3, 2016-08-22
--------------------------------

Features:
- Docker/single-container: reduced weight from 450 MB to 290 MB
- Asqatasun-Runner: RGAA 3.0 by default, enabled SEO and disabled RGAA 2
- Referential RGAA 3.0: english translation added (source : https://github.com/DISIC/rgaa_referentiel_en)

Security:
None

Bugs:
- [#115 Build failure on project asqatasun-engine with openJDK-8](https://github.com/Asqatasun/Asqatasun/issues/115)
- [#113 Build failure on the projects webApp and runner with openJDK-8 on Ubuntu 16.04](https://github.com/Asqatasun/Asqatasun/issues/113)
- [#114 Runner - install/engine folder not present in the tarball](https://github.com/Asqatasun/Asqatasun/issues/114)
- [#117 Fixed /rules/referential-creator/(...)/pom.vm](https://github.com/Asqatasun/Asqatasun/issues/117)

Configuration:
None

Documentation:
- Updated runner doc (WIP)
- Added "the referential creation" + "Create-a-rule" (WIP)

Upgrade-o-meter (borrowed from folks at Gitlab):
- no database change, just replace the `.war` file


--------------------------------
Asqatasun 4.0.2, 2016-06-17
--------------------------------

Features:
- Enhanced script docker/compile_and_build_docker_image.sh

Security:
- Upgrade Apache Commons FileUpload to v1.3.1 
- Upgrade Apache httpclient to v4.3.6

Bugs:
- [#25](https://github.com/Asqatasun/Asqatasun/issues/25): localhost URLs should not be blocked  
- [#104](https://github.com/Asqatasun/Asqatasun/issues/104): Rgaa 3 - rule 8.9.1 : Changed actual result of rule when fieldset is used without form
- [#105](https://github.com/Asqatasun/Asqatasun/issues/105): MySQL - Enable innodb_file_per_table 
- release/bump_asqatasun.sh : use --push option for pushing new tag

Configuration:
- max-documents for site-audits lowered from 10'000 to 1'000 
- disabled Rgaa-2 rules

Documentation:
- [#88](https://github.com/Asqatasun/Asqatasun/issues/88): DOC Docker - Add tip for Mac OS X and Windows users 

Upgrade-o-meter (borrowed from folks at Gitlab):
- no database change, just replace .war


--------------------------------
Asqatasun 4.0.1, 2016-03-18
--------------------------------

Security:
- [#83](https://github.com/Asqatasun/Asqatasun/issues/83): Upgrade Apache Commons Collections to v3.2.2

Bugs:
- [#85](https://github.com/Asqatasun/Asqatasun/issues/85): Audit full-site : accept the new gTLDs (eg .jobs, .paris)


--------------------------------
Asqatasun 4.0.0, 2016-03-02
--------------------------------

Features:

- devops:
    - Docker images (+ Docker automated builds)
    - Ansible role + Vagrantfile
- Implementation of SEO rules
- RGAA 3 translated in English
- Documentation: huge refactor 
- New translation system on Transifex https://www.transifex.com/asqatasun/asqatasun/
- New logo integrated in the app
- Fork from Tanaguru

Bugs:
- [#43](https://github.com/Asqatasun/Asqatasun/issues/43): Mysql config: add UTF-8 
to support any type of charset including cyrillic + enhance way to modify Mysql conf
- [#58](https://github.com/Asqatasun/Asqatasun/issues/58): PDF - clicking on it returns to an error page


--------------------------------
Asqatasun 4.0.0-rc.1, 2016-01-12
--------------------------------

Bugs:
- [#43](https://github.com/Asqatasun/Asqatasun/issues/43) : Mysql config: add UTF-8 
to support any type of charset including cyrillic + enhance way to modify Mysql conf

Features:
- devops: Ansible role + Vagrantfile
- Documentation: huge refactor; Sections done: Install, Contrib > Docker build, Contrib > Release
- New logo integrated in the app

----------------------------------
Asqatasun 4.0.0-beta.2, 2015-11-27
----------------------------------

Features:
- devops: Docker images (+ Docker automated builds)
- Implementation of SEO rules
- Fork from Tanaguru

--------------------------
Tanaguru 3.1.0, 2015-07-02
--------------------------

Features :

- Implementation of Rgaa3 rules (173 rules)

--------------------------
Tanaguru 3.0.6, 2015-06-26
--------------------------

Features :

- Most of the implementation of Rgaa3 rules (about 150 rules)
- Refactoring (clean-up) of Data Service layer usage
- Package naming modification : now all packages starts with "org.asqatasun"
- Rules commons improvments : use of Pair to define a combinaison of Result/Message
- Rule TU improvments : provide new methods to test rule result and remarks
- Add the "COMPLEX_TABLE_MARKER" 

--------------------------
Tanaguru 3.0.5, 2015-03-16
--------------------------
Bugs:

- #tg-github-107 : Provide a way to set-up proxy credentials
- #tg-github-105 : Set "esapiPropertyValue" property of tokenManager bean to use the system property confDir value instead of hard-coded value
- #tg-github-102 : Assisted Audit : Complete action is ineffective

Features : 

- UI improvments
- Correction of bug on buttons that apply automatic result on assisted audit 
- User guide documentation 

--------------------------
Tanaguru 3.0.4, 2015-02-12
--------------------------
Bugs:

- #tg-github-95 : Provide mechanism to set the size of the firefox screen when loading the page
- #tg-github-93 : CLI install instructions broken 


Features :

- Spanish language integration
- A11y improvments in audit result page
- Initialisation of Rgaa3 referential context
- Initialisation of assisted audit
- Addition of cli script options 


--------------------------
Tanaguru 3.0.3, 2014-07-06
--------------------------
Bugs:

- #tg-github-86 : AW22 8.8.1 Case sensitivity of lang definition

Features :

- Upgrade Java from 1.6 to 1.7
- Upgrade Hibernate from 3.6.0.Final to 4.3.5.Final
- Add new "referential-creator-maven-plugin" and "referential-creator" projects 
- Create "rule demo project"
- Refactor rule packaging to be handled by one project 
- Harmonisation of Referential names
- Improve rules test management 

--------------------------
Tanaguru 3.0.2, 2014-05-16
--------------------------
Bugs:
- #tg71 : Crawl parameters: included URL pattern is not shown in audit details 

Features :
- #tg74 : Top 5 invalid tests: add level of test 
- #tg73 : Add link to contrast finder 
- #tg68 : List of pages invalidating a given test: enhance links  
- #tg67 : List of pages invalidating a given test: add test label 
- #tg66 : Fix weighted formula 
- #tg65 : Add UnicityChecker  
- #tg64 : Number of "pages tested" is not the good one 
Manage levels more generically (level 1,2,3) and delegate the naming to the referential i18n project

--------------------------
Tanaguru 3.0.1, 2014-04-24
--------------------------
Bugs:
- Handle rel canonical tags with relative Urls on crawl

Features :
- update table representation for non canonical pages :only display link and rank
- Add the inclusion option to the crawler #tg59 

--------------------------
Tanaguru 3.0.0, 2014-02-19
--------------------------
Bugs :
- #tg47 : The language detection tests return bad results for uppercase submitted text 

Features : 
- Rgaa 2.2 referential full supported
- Postgresql management (creation and insertion scripts)
- Crawl optimisations (deal with canonical, improve fetch counting, improve fetch of testable data)
- #tg50 : Provide a way to set-up whether the cookies have to be considered while crawling 
- #tg49 : [SEO 6.4.1] For a given title value, sort URLs 
- #tg46 : Override default user agent used by apache library to test URL before launching effectively the audit 
- #tg43 : Disable the load of Css content for audit of SEO type 
- #tg37 : Mail server is not configurable 

--------------------------
Tanaguru 3.0.0-beta9, 2014-01-17
--------------------------
Bugs :
- #tg637 : Tanaguru rejects pages with the time type "application/xhtml+xml" on site audit 

Features : 
- #tg638 : Crawler : the pages that defines a link tag with the rel attribute equals to canonical must be excluded from the crawl 

--------------------------
Tanaguru 3.0.0-beta8, 2013-12-18
--------------------------
Bugs : 
- #tg636 : Clean-up step at the end of audit may cause deadlock on CONTENT table 

--------------------------
Tanaguru 3.0.0-beta7, 2013-12-11
--------------------------
Bugs : 
- Display correct breadcrumb for test details page

AW22 referential : 
- Complete (links, downloadable documents detection, hidden elements, contrasts)

Features :
SEO referential -> rules about duplicate search now consider link elements with rel=canonical attribute
A11y referential -> do not consider transparent as white color for contrast rules (ContrastChecker)

--------------------------
Tanaguru 3.0.0-beta6, 2013-12-05
--------------------------
UI Evolution : 
- add shortcuts to audit actions in home view
- Add truncated view on test result that lead to a page that displays all the remarks
- Online Demo link and login page evolution
- Specific rendering for test of site scope (seo rules)

AW22 referential : 
- Complete (links, downloadable documents detection, hidden elements, contrasts)

SEO referential : 
- now based on rules-commons

--------------------------
Tanaguru 3.0.0-beta5, 2013-10-21
--------------------------
Features :
- #tg634 : Installation script 
- #tg633 : Refactor js script that extract colors to only use native Javascript 
- #tg631 : Clean-up maven configuration 

Bugs : 
- #tg632 : Css adaptation : catch exception when trying to retrieve file as external resource 
- #tg630 : Encoding issue with filled-in URL  

--------------------------
Tanaguru 3.0.0-beta4, 2013-10-09
--------------------------
Features :
Accessiweb 2.2 implementation : rules about color contrast and language detection
Improvment of UI

--------------------------
Tanaguru 3.0.0-beta3, 2013-08-02
--------------------------
Bugs:
- #tg596: Only consider pages with Http status code equals to 200 in the Top 10 of invalid URLs tables of the synthesis page 

--------------------------
Tanaguru 3.0.0-beta2, 2013-06-04
--------------------------
Bugs:

Features:
- #tg595 : 	Management of Krash audits 
- #tg593 :	Link the act entity to the audit instead of the webresource 
- #tg592 :	Embed a java library to highlight source code instead of using geshi 
- #tg591 :  Upgrade apache commons libraries 

--------------------------
Tanaguru 3.0.0-beta1, 2013-04-05
--------------------------

Bugs : 
- #tg538 : The sort contract by mark doesn't work in the contract view 

Features:
- #tg590 : Provide a self-structured csv entry that handles ProcessRemarks info and Evidence Elements data 
- #tg583 : Use css-phloc as css adapter and parser 
- #tg582 : Use jsoup as html adapter 
- #tg581 : Create sebuilder-interpreter-tool subproject based on sebuilder-interpreter api
- #tg580 : Upgrade to Heritrix 3.1.0 version
- #tg579 : Clean-up dependencies
- #tg552 : Clean-up SSPHandler and DOMHandler to remove unused primitives
- #tg551 : Provide a mechanism to avoid to launch tests that return NOT_TESTED as result [OPTIMIZATION]
- #tg550 : Improve ergonomy of audit result pages
- #tg549 : Compute criterion results while analysing
- #tg548 : Introduce the criterionStatistics entity
- #tg547 : Add an audit result view with criterion results
- #tg534 : Button "audit again"
- #tg283 : Separate NMI from Untested
- #tg163 : Add a "please wait" page after submit and before audit result

Tanaguru 2.1.1, 2013-04-15
--------------------------
Bugs:
- #tg589 -> syntax of web.xml invalid 
- #tg588 -> Provide a mechanism to avoid multiple form validation when submitting an audit 
- #tg587 -> Downgrade version of commons-collections due to compatibily issue encountered on deployment on Jboss 
- #tg540 -> CastException occured while auditing multiple files. 

--------------------------
Tanaguru 2.1.0, 2012-11-07
--------------------------

Bugs:
- #tg516 : site-wide exports: add "page+site" results 
- #tg327 : Error on adaptation encountered on some pages of a site audit causes Fatal error in processor component 

Features:
- #tg524 -> Provide a UI to override test weights for a given user  
- #tg523 -> Add weight field to the TEST entity and use it to compute the raw mark  
- #tg522 -> Provide a page that lists the pages invalidating a given test  
- #tg520 -> Provide a back office interface  
- #tg515 -> list of pages: change link destination (audit // page itself)  
- #tg514 -> Bar graph on page audit-synthesis: put red at the bottom  
- #tg416 -> Avoid the usage of type wildcard  
- #tg271 -> Add console to the project-page  

--------------------------
Tanaguru 2.0.1, 2012-09-19
--------------------------
Bugs:
- #tg510 -> The externalCssRetriever when a new css is fetched during adaptationThe cssContentAdapter implementation doesn't alert 

Features:
- #tg509 ->	Make the different treatment window parameters of the AuditCommand implementation configurable 
- #tg506 -> Linkify the URLs (SEO 6.4.1 + SEO 7.6.1) 

--------------------------
Tanaguru 2.0.0, 2012-08-30
--------------------------
Bugs:
- #tg446 -> Audit crash on www.letc.fr: infinite loop with @import 
- #tg428 -> the getImageFromUrl() method of the SSPHandler implementation can't deal with embedded pictures 
- #tg426 -> The snapshot displayed in the synthesis result page in the case of a Scenario Audit is wrong 
- #tg424 -> The content of the scenario, present as a hidden field in the audit scenario set-up form, has to be removed 
- #tg422 -> Site-audit unable to run -> mail content shrunk 
- #tg419 -> Page audit fails if "http://" is missing in the URL 
- #tg414 -> .ods Export for SEO doesn't work 
- #tg407 -> bad link in email sent for a scenario audit 
- #tg404 -> Site audits crash when at least one SSP among the set of SSP hasn't been adapted 
- #tg398 -> Audit Options: rename "Accessibility level" into "Level" 
- #tg395 -> Parts of <head> deleted by Adapter Component 

Features:
- #tg425 -> Add getter/setter to the "timeout" attribute of the TgolHighlighter class to allow to overidde the default value by spring configuration 
- #tg418 -> Launch page-audits via webdriver 
- #tg408 -> Email sent for a scenario: should include the scenario name 
- #tg406 -> Add a link to the source code in the page result page 
- #tg405 -> Remove the weighted mark from the different views 
- #tg402 -> Add new view to set-up scenario and launch audit 
- #tg401 -> Implementation of Har file reader 
- #tg400 -> Retrieve external CSS while adapting SSP (if not already retrieved during the crawl) 
- #tg399 -> Add a rank field to the WebResource entity 
- #tg365 -> Add referential info in the audit list contract info page 
- #tg337 -> Evolution of AuditService API to enable to launch a scenario audit 
- #tg336 -> Create a new Scenario loader module with an implementation that uses the WebDriver API 
- #tg280 -> Export: PDF format 
- #tg279 -> Export: CSV format  
- #tg270 -> Audit sites with a given scenario 
- #tg269 -> Audit sites with authentication 


--------------------------
Tanaguru 1.5.2-RC1, 2012-05-04
--------------------------
Bugs:
- #tg285 -> The css parser misinterprets selectors with multiple values 
- #tg330 -> Controller parameters type not controlled when converted as Numbers 

Features:
- #tg288 -> Provide a way to externalise web-app resources (use of a cdn) 
- #tg318 -> Handle the "base" tag in Css adaptation to get external resources 
- #tg319 -> Create a "work in progress" page for a page audit that lasts longer than a given delay 
- #tg331 -> TGSI Model update : change TGSI_RESTRICTION table to TGSI_OPTION table with a "Is_Restriction" field 
- #tg332 -> TGSI Model update : Refactoring due to Contract management modifications 
- #tg333 -> Create CHANGELOG.txt that list the features and bugs of each version 

--------------------------
Tanaguru 1.5.1, 2012-05-04
--------------------------
Bugs:
- #tg325 -> The upload module doesn't work when the Proxy properties (ProxyPort and ProxyPort) are set 

Features:
None


--------------------------
Tanaguru 1.5.0, 2012-02-03
--------------------------
Same as 1.5.0-RC1


--------------------------
Tanaguru 1.5.0-RC2, 2012-01-27
--------------------------
Same as 1.5.0-RC1


------------------------------
Tanaguru 1.5.0-RC1, 2012-01-24
------------------------------
Bugs:
- #tg236 -> Add a licence file 
- #tg248 -> The page list of an audit is sorted by weighted mark instead of raw mark 

Features : 
- #tg184 -> have a specific TITLE tag for audit 1page / 10pages / site 
- #tg250 -> Modify the name of the user-agent used by heritrix 
- #tg254 -> Evolution of the web-application interface to use the bootstrap UI toolkit 
- #tg255 -> Add a console to sort results in the result page 
- #tg256 -> Display all the audits of a contract in the contract page 
- #tg257 -> Add an indicator to indicate that an audit is running for a given contract 


------------------------------
Tanaguru 1.4.2-RC1, 2012-01-16
------------------------------
Bugs : 
None 

Features : 
- #tg249 -> Allow Tanaguru to be used behind a web proxy 


------------------------------
Tanaguru 1.4.0-RC1, 2011-10-26
------------------------------
Bugs :
- #tg45 -> add licence header to all source files 

Features:
- #tg229 -> ContentLoader Implementation 
- #tg228 -> AuditService interface 
- #tg227 -> Update the ContentDAO interface to add a boolean option when retrieve a Content with its RelatedContent 


------------------------------
Tanaguru 1.3.0-RC1, 2011-08-09
------------------------------
Bugs :
None

Features:
- #tg199 -> Add tunable parameters for the crawler component 
- #tg208 -> Parameterization component 


------------------------------
Tanaguru 1.2.1-RC2, 2011-07-07
------------------------------
Bugs:
- #tg207 -> Tanaguru consolidation can cause java heap space error in case of large set of pages in the same audit 

Features:
None


--------------------------
Tanaguru 1.2.0, 2011-06-21
--------------------------
Bugs :
- #tg197 -> Crawler : remove the version of tanaguru in the heritrix user-agent declaration 

Features:
- #tg193 -> Realize content relationship association while adapting instead of while crawling 


--------------------------
Tanaguru 1.0.0, 2011-04-08
--------------------------
Bugs :
- #tg57 -> Tanaguru web-app cannot deal with URL containing special characters 
- #tg134 -> Have a simple README at the root of the svn to get people started 

Features : 
- #tg58 -> Tanaguru web-app is not thread safe 
