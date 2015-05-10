[![Build Status](https://travis-ci.org/Tanaguru/Tanaguru.svg?branch=master)](https://travis-ci.org/Tanaguru/Tanaguru) 

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

As of January 2014, this represents [~170 accessibility tests](http://www.tanaguru.org/en/content/accessiweb-22-coverage)

## Contact and discussions

* [Tanaguru discussion space](http://discuss.tanaguru.org) 
* email to `tanaguru AT tanaguru dot org` (only English, French and klingon is spoken :) ) 
* [Twitter @TanaguruApp](https://twitter.com/tanaguruapp)

## Content of this last version (Tanaguru 3.0.5, 2015-03-16)

Bugs:

- [#107](https://github.com/Tanaguru/Tanaguru/issues/107) : Provide a way to set-up proxy credentials
- [#105](https://github.com/Tanaguru/Tanaguru/issues/105) : Set "esapiPropertyValue" property of tokenManager bean to use the system property confDir value instead of hard-coded value
- [#102](https://github.com/Tanaguru/Tanaguru/issues/102) : Assisted Audit : Complete action is ineffective

Features : 

- UI improvements
- Correction of bug on buttons that apply automatic result on assisted audit 
- User guide documentation 

See full [Changelog](CHANGELOG.txt)

## Other Open Source tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)

Have Fun

Tanaguru team
