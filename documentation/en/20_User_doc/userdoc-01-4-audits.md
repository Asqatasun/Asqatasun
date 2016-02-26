# Four kinds of audits

Asqatasun offers 4 kinds of audit. Choose the suitable kind depending on your need.

## Typical usages for [Page audit](userdoc-03-page-audit.md)

* *the* typical audit you run on a page to have insights about its level of accessibility
(be it a quick view, or an in-depth study with [Assisted Audit](userdoc-06-assisted-audit.md))

## Typical usages for [Scenario audit](userdoc-04-scenario-audit.md)

* automatically measure a form that is split in several pages 
* assess the page given by a search result
* audit a **web application** (full JS, AngularJS, ExtJS...)
* measure **different states of a very same page**

## Typical usages for Site audit

* have a global overview the accessibility level of an entire website (say 
50'000 pages)
* identify pages or parts of a site with excessive accessibility issues

## Typical usages for Offline file audit

* A developer is creating a template which is still on its machine (not yet on the 
webserver), and wants to have an **early statement of accessibility**
* User wants to audit an intranet page (not connected to the internet). Just save the 
page and send it as offline file to Asqatasun


<h2 id="audit-comparison">Comparison of audits</h2>

 Audit:                             | Page          | Scenario      | Site          | Offline file
----------------------------------- | ------------- | --------------| ------------- | -------------
DOM / Generated HTML support        | YES           | YES           | No            | No
Deals with CSS                      | YES           | YES           | No            | No
Deals with colors                   | YES           | YES           | No            | No
Comply with [robots.txt](http://www.robotstxt.org) | *Not Applicable* | *Not Applicable* | YES           | *Not Applicable*
