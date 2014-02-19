# Tanaguru

Tanaguru is an opensource (AGPL license) website assessment tool dedicated to

* accessibility (a11y) audits

## Features

Four kinds of audit:

* offline file audit
* page audit
* entire website audit (Heritrix crawler embedded)
* scenario audit 

(That's the DOM that is tested, thus dealing with JS/AJAX/ARIA)
 
## Vision

1. Automate as much as we can and more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

## Download

http://www.tanaguru.org/Download/tanaguru-latest.tar.gz

## Demo

http://beta.tanaguru.com/

## Installation and documentation

[Tanaguru wiki](http://www.tanaguru.org) (registration is warmly welcomed, please email mfaure AT tanaguru. org)

More precisely:

* [Installation](http://www.tanaguru.org/en/content/tanaguru-3x)
* [Configuration](http://www.tanaguru.org/en/content/configuration)
* [How to build](http://www.tanaguru.org/en/content/how-build)

## Business: accessibility

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, wordprocessor...)
* switch of context
* ...

By january 2014, this represents [~170 accessibility tests](http://www.tanaguru.org/en/content/accessiweb-22-coverage)

## Contact and mailing-list

We have a mailing lists dedicated to Tanaguru user and development
discussion. To subscribe, send an empty message to :

        tanaguru-users-fr@lists.adullact.net

Or you can send an email to 

        tanaguru AT tanaguru dot org 

(only english, french and klingon is spoken :) ) 

## Content of this (last) version

Bugs :
#47 : The language detection tests return bad results for uppercase submitted text

Features : 
- Rgaa 2.2 referential full supported
- Postgresql management (creation and insertion scripts)
- Crawl optimisations (deal with canonical, improve fetch counting, improve fetch of testable data)
#50 : Provide a way to set-up whether the cookies have to be considered while crawling
#49 : [SEO 6.4.1] For a given title value, sort URLs
#46 : Override default user agent used by apache library to test URL before launching effectively the audit
#43 : Disable the load of Css content for audit of SEO type
#37 : Mail server is not configurable://github.com/Tanaguru/Tanaguru/issues/37)

## Other opensource tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)

Have Fun

Tanaguru team
