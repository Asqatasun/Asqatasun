### Summary

We check whether a language is specified for each textual element of the page

### Business description

Criterion : 8.3

Test : [8.3.1](http://www.braillenet.org/accessibilite/referentiel-aw21-en/index.php#test-8-3-1)

Test description :

For each Web page, does the [default human language](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mLangueDefaut) specification pass one of the conditions below?

-   The page human language specification (`lang` and/or `xml:lang` attribute) is provided for the Html element
-   The page human language specification (`lang` and/or `xml:lang` attribute) is provided for each text element or on one of the parent elements

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[decidable](/en/category/rules-design/accessiweb-11/decision-level/decidable)

### Algorithm

#### Selection

##### Set 1 :

The `<html>` tag with a `lang` or `xml:lang` attribute.

##### Set 2 :

The tags with a `lang` or `xml:lang` attribute.

##### Set 3 :

The textual tags without `lang` or `xml:lang` attribute considering that these attributes can be set to the current tag or to one of its ascendants. (see Notes for the definition of the textual tags)

#### Process

The selection handles the process.

#### Analysis

##### Passed

-   The `<html>` tag has a `lang` or a `xml:lang` attribute (Set1 is not empty)
-   The language is provided for each textual element by the tag or by one of its parents (Set1 is empty AND Set2 is not empty AND Set3 is empty)

##### Failed

-   The page has no language specification (Set2 is empty). In this case, raise a Message 1
-   Some textual tags are missing the language attribute (Set1 is empty AND Set2 is not empty AND Set3 is not empty). In this case, raise a Message 2

##### Message 1: Lang Attribute Missing On Whole Page

-   code : LangAttributeMissingOnWholePage
-   status: Failed
-   parameter : none
-   present in source : no

##### 

Message 2: Lang Attribute Missing On Html

-   code : LangAttributeMissingOnHtml
-   status: Failed
-   parameter : none
-   present in source : no

### Notes

No notes yet for that rule
