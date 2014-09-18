### Summary

This test consist in checking whether an informative embedded image has
a detailed description if necessary

### Business description

Criterion : 1.6

Test : [1.6.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-6-4)

Test description :

Does each embedded image that conveys information (embed tag), requiring
a [detailed
description](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDescDetaillee),
pass one of the conditions below?

-   Between <noembed\> and </noembed\> there is a reference to a
    [detailed
    description](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDescDetaillee)
    available on the page
-   An [adjacent
    link](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mLienAdj)
    (via a
    [url](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mUrl)
    or an anchor) allowing to access to the content of the [detailed
    description](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDescDetaillee)
    is available

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the <embed\> tags

#### Process

The selection handles the process

#### Analysis

##### NA

Selection is empty (The page has no <embed\> tag)

##### NMI

The selection is not empty

### Notes

No notes yet for that rule
