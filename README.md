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

Bugs:

- [#86](https://github.com/Tanaguru/Tanaguru/issues/86) : AW22 8.8.1 Case sensitivity of lang definition

Features :

- Upgrade Java from 1.6 to 1.7
- Upgrade Hibernate from 3.6.0.Final to 4.3.5.Final
- Add new "referential-creator-maven-plugin" and "referential-creator" projects 
- Create "rule demo project"
- Refactor rule packaging to be handled by one project 
- Harmonisation of Referential names
- Improve rules test management 

## Other opensource tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)

Have Fun

Tanaguru team
