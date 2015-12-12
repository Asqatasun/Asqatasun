# AccessiWeb 2.2 - Rule 1.6.1

## Summary

This test consists in checking whether an informative image has a
detailed description if necessary

## Business description

Criterion : 1.6

Test : [1.6.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-1)

Test description :

Does each [image that conveys
information](http://www.accessiweb.org/index.php/glossary-76.html#mImgInfo)
(img tag) that needs a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee), pass one of the conditions below?

-   A longdesc attribute providing the [url](http://www.accessiweb.org/index.php/glossary-76.html#mUrl) of a page that contains the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) is available
-   An alt attribute that contains the reference to a [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee) just next to the image is available
-   There is an [adjacent link](http://www.accessiweb.org/index.php/glossary-76.html#mLienAdj) allowing to access to the content of the [detailed description](http://www.accessiweb.org/index.php/glossary-76.html#mDescDetaillee)

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `img` of the page

### Process

The selection handles the process

### Analysis

#### Not Applicable:

The selection is empty (the page has no `img` tag)

#### Pre-qualified:

The seletion is not empty

## Notes

No notes yet for that rule
