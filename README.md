# Tanaguru

Tanaguru is an opensource (AGPL license) website assessment tool dedicated to

* accessibility (a11y) audits,  
* search engine optimisation (SEO) audits.

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

## Installation and documentation

http://www.tanaguru.org/en/content/documentation

## Business: accessibility

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, wordprocessor...)
* switch of context

By january 2014, this represents [~170 accessibility tests](http://www.tanaguru.org/en/content/accessiweb-22-coverage)

## Business: SEO

* test each page with a set of WCAG-inspired tests (e.g. precence+relevancy of h1, precence+relevancy of title, relevancy of hx structure...)
* test each site for duplicate content
 * duplicate title
 * duplicate h1

## Links

* [Tanaguru wiki](http://www.tanaguru.org) (registration is warmly welcomed, please email mfaure AT tanaguru. org)
* while waiting to move to Git/Github, [Tanaguru source code](https://adullact.net/scm/?group_id=663)

## Other tools

Last point to complete the vision:

* and create other tools if needed

See also:

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examplee
* [Tanaguru Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *propose* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Tanaguru Github account](https://github.com/Tanaguru)
