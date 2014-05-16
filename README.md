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

- [#71](https://github.com/Tanaguru/Tanaguru/issues/71) : Crawl parameters: included URL pattern is not shown in audit details

Features :

- [#74](https://github.com/Tanaguru/Tanaguru/issues/74) : Top 5 invalid tests: add level of test
- [#73](https://github.com/Tanaguru/Tanaguru/issues/73) : Add link to contrast finder
- [#68](https://github.com/Tanaguru/Tanaguru/issues/68) : List of pages invalidating a given test: enhance links
- [#67](https://github.com/Tanaguru/Tanaguru/issues/67) : List of pages invalidating a given test: add test label
- [#66](https://github.com/Tanaguru/Tanaguru/issues/66) : Fix weighted formula
- [#65](https://github.com/Tanaguru/Tanaguru/issues/65) : Add UnicityChecker
- [#64](https://github.com/Tanaguru/Tanaguru/issues/65) : Number of "pages tested" is not the good one
- Manage levels more generically (level 1,2,3) and delegate the naming to the referential i18n project


## Other opensource tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)

Have Fun

Tanaguru team
