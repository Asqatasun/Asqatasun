# Asqatasun

Asqatasun is an opensource web site analyzer, used for web accessibility (a11y) and Search Engine Optimization (SEO).

## Features

* SEO measurement
    * run fully automated tests to track SEO issues
    * scan zillions of pages
    * create your own tests
* web accessibility assessment (RGAA 3, AccessiWeb, WCAG)
    * scan a whole site for a11y issues (crawler included)
    * scan a given page, and manually fulfill the audit to produce report
    * scan offline file (e.g. template being created but not online yet)
    * scan a user-workflow like site registration, form completion or e-commerce checkout with **Asqatasun scenarios**.
 
## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun

## Download

Latest Asqatasun version is available at http://download.asqatasun.org/asqatasun-latest.tar.gz

You can also play with the [Asqatasun Docker images](https://hub.docker.com/r/asqatasun/asqatasun/)
(but **DO** read the associated docs or your data will be lost !)

## Demo

@@@TODO

## Installation and documentation

How to install Asqatasun, how to run the Docker images, what hardware to provision.
All ansxers are ine the [Install Doc](docs/10_Install_doc/README.md)

## Contribute

We would be really glad to have you on board ! You can help in many ways:

* [Fill in bug report](https://github.com/Asqatasun/Asqatasun/issues)
* [Help translate Asqatasun]() @@@TODO Transifex URL
* Pull Requests are off course welcome
* [Create your own tests]() @@@TODO link to doc


## Universe: accessibility "a11y"

What tests are covered:

* all the "tag and attributes tests" like missing alt, table headers check, frame title...
* color contrast
* language specification
* downloadable files / office files (spreadsheet, word-processor...)
* switch of context
* ...

As of October 2015, this represents 173 accessibility tests.

## Universe: Search Engine Optimisation "SEO"

@@@TODO

## Contact and discussions

* [Asqatasun forum](http://forum.asqatasun.org/) 
* email to `asqatasun AT asqatasun dot org` (only English, French and klingon is spoken :) ) 
* [Twitter @Asqatasun](https://twitter.com/Asqatasun)

## Content of this last version (Asqatasun 4.0.0, 2015-12-xx)

Features :
- Docker images (+ Docker automated builds)
- Implementation of SEO rules
- Fork from Tanaguru

See full [Changelog](CHANGELOG.txt)


Have Fun
[Asqatasun Team](docs/en/asqatasun-team.md)
