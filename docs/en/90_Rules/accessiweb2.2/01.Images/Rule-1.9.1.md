## Summary

This test consists in detecting the images on the page.

## Business description

Criterion : 1.9

Test : [1.9.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-9-1)

Test description :

Each [image of text](http://www.accessiweb.org/index.php/glossary-76.html#mImgText) (img tag) must be replaced with [styled text](http://www.accessiweb.org/index.php/glossary-76.html#mTexteStyle) if possible. Does this rule have been followed (except in special cases)?

Level : [Gold](/en/category/rules-design/accessiweb-11/level/gold)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level : [semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1**

All the `img` tags of the page

### Process

**Test1**

For each element of Set1, raise a MessageA

##### MessageA : Check manually the elements of the scope

-   code : ManualCheckOnElements
-   status: Pre-qualified
-   parameter : `src` attribute, Snippet
-   present in source : yes

### Analysis

#### Not Applicable

Set1 is empty (the page has no `img` tag)

#### Pre-qualified

In all other cases
