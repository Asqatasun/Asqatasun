[![Build Status](https://travis-ci.org/Tanaguru/Tanaguru.svg?branch=develop)](https://travis-ci.org/Tanaguru/Tanaguru) 

# Tanaguru

Tanaguru is an opensource (AGPL license) website assessment tool. It is dedicated to accessibility (a11y) audits, and focuses on reliability and high level of automation.

## Features

Four kinds of audit:

* offline file audit
* page audit
* entire website audit (Heritrix crawler embedded)
* scenario audit 

(That's the DOM that is tested, thus dealing with JS/AJAX/ARIA)
 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

## Download

http://www.tanaguru.org/Download/tanaguru-latest.tar.gz

## Demo

https://my.tanaguru.com/

## Installation and documentation

See [all Tanaguru Docs](http://tanaguru.readthedocs.org/)

Usefull links:

* [Installation](http://tanaguru.readthedocs.org/en/develop/prerequisites-webapp-doc/)
* [Configuration](http://tanaguru.readthedocs.org/en/develop/configuration-webapp-doc/)
* [User Doc](http://tanaguru.readthedocs.org/en/develop/user-doc/)

## Business: accessibility

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, wordprocessor...)
* switch of context
* ...

By january 2014, this represents [~170 accessibility tests](http://www.tanaguru.org/en/content/accessiweb-22-coverage)

## Contact and discussions

* [Tanaguru discussion space](http://discuss.tanaguru.org) 
* email to `tanaguru AT tanaguru dot org` (only english, french and klingon is spoken :) ) 
* [Twitter @TanaguruApp](https://twitter.com/tanaguruapp)

## Content of this last version (Tanaguru 3.0.6, 2015-06-26)

Features :

- Most of the implementation of Rgaa3 rules (about 150 rules)
- Refactoring (clean-up) of Data Service layer usage
- Package naming modification : now all packages starts with "org.tanaguru"
- Rules commons improvments : use of Pair to define a combinaison of Result/Message
- Rule TU improvments : provide new methods to test rule result and remarks
- Add the "COMPLEX_TABLE_MARKER"

See full [Changelog](CHANGELOG.txt)

## Other opensource tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)

Have Fun

Tanaguru team
