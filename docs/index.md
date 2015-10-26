# Asqatasun

Asqatasun is an opensource (AGPL license) website assessment tool. It is dedicated to accessibility (a11y) audits, and focuses on reliability and high level of automation.

## Problem & solution

Making your website better is hard. Better for users, better for developers,
better for the client. [W3C standards](http://www.w3.org/TR/) can bring a lot of
 help, but they are kind of tedious to understand and implement.

[Asqatasun](http://asqatasun.org/) helps you in making your web site 
accessible. It tests your pages, whole site,  or application and gives you 
precise and detailed insights on the identified issues.

You can [install Asqatasun by yourself](http://www.tanaguru.org/en/content/ubuntu-prerequisites-tanaguru-3x) or use 
[Asqatasun in SaaS](https://my.tanaguru.com/)

## Features

### Four kinds of audit

* offline file audit
* page audit
* entire website audit (Heritrix crawler embedded)
* scenario audit (pages with sign-up, multiple-steps forms, electronic procedures, web applications...)

(That's the DOM that is tested, thus dealing with JS/AJAX/ARIA)

### Accessibility Analytics

 * Metrics and graphs to follow your accessibility growth

### Customization

* Create your own rules
* Optimise++ with integration in Quality Assurance tools like Travis, Jenkins...
* multi-lingual: English, Spanish, French, and [any other language you would add](https://crowdin.com/project/tanaguru) :)

## Vision

1. Automate as much as we can and even more :)
2. Be 200% reliable (don't give erroneous result)
3. have technological fun !

## Download

[Asqatasun latest release (.tar.gz, ~100Mb)](http://www.tanaguru.org/Download/tanaguru-latest.tar.gz)

## Demo

Directly on [Asqatasun SaaS](https://my.tanaguru.com/)

## Installation and documentation

* [Asqatasun User doc](user-doc.md)
* Asqatasun Install doc
* Asqatasun Maintenance doc
* Asqatasun Developer doc

[Asqatasun wiki](http://www.tanaguru.org) (registration is warmly welcomed, please email mfaure AT tanaguru. org)

More precisely:

* [Installation](http://www.tanaguru.org/en/content/tanaguru-3x)
* [Configuration](http://www.tanaguru.org/en/content/configuration)
* [How to build](http://www.tanaguru.org/en/content/how-build)

## Business: accessibility

What tests are covered ? The ones we like most:

* color contrast *with solutions offered*
* *language change* in the page

And the usual ones:

* all usual tests like the "tag and attributes tests" (missing alt, table headers check, frame title...)
* downloadable files / office files (spreadsheet, wordprocessor...)
* switch of context
* and much more tedious silly tests you won't have to deal with

By january 2014, this represents [~170 accessibility tests](http://www.tanaguru.org/en/content/accessiweb-22-coverage)

## Support and discussions

* [Asqatasun discussion space](http://discuss.asqatasun.org) 

## Contribute

- [Issue Tracker](https://github.com/Asqatasun/Asqatasun/issues)
- [Source Code](https://github.com/Asqatasun/Asqatasun)
- [How to build](http://www.tanaguru.org/en/content/how-build)

## Contact 

* email to `asqatasun AT asqatasun dot org` (only english, french and klingon is spoken :) ) 
* [Twitter @Asqatasun](https://twitter.com/Asqatasun)

## Other opensource tools

* [KBAccess](http://www.kbaccess.org/) : database of good and bad examples of web accessibility
* [Asqatasun Contrast-Finder](http://contrast-finder.tanaguru.com/) : for a given wrong contrast, *offers* good color combination
* [Accessibility observatory](http://observatoire-accessibilite.org/) : have an overview of the accessibility of a large set of websites
 
All these projects are opensource and also under the umbrella of [Asqatasun Github account](https://github.com/Asqatasun)

Have Fun

[Asqatasun Team](asqatasun-team.md)
