### Summary

This test consists in checking whether the detailed description of an
informative image is relevant.

### Business description

Criterion : 1.7

Test : [1.7.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-7-1)

Test description :

Does each [image that conveys
information](http://www.accessiweb.org/index.php/glossary-76.html#mImgInfo)
(img tag or input as image) with a [detailed
description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee)
pass one of the conditions below?

-   The [detailed
    description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee)
    via the url referred by the longdesc attribute is relevant
-   the [detailed
    description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee)
    on the page and identified in the alt attribute is relevant
-   The [detailed
    description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee)
    via an [adjacent
    link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj)
    is relevant

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

##### Set1

All the `img` tags of the page

##### Set2

All the `input` tags with the "type" attribute equals to "image"

#### Process

The selection handles the process

#### Analysis

##### NA

Set1 AND Set2 are empty (The page has no `img` tag and no `input` tag
with a type" attribute equals to "image")

##### NMI

Set1 OR Set2 is not empty

### Notes

No notes yet for that rule
