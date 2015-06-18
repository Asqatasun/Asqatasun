# Rule 5.4.1

## Summary

This tests consists in checking the presence of a caption child node on
the data table nodes. Data table nodes are characterized by HTML
markers. When tables cannot be characterized, the test is applied but
the result is semi-decidable.

## Business description

### Criterion

[5.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-5-4)

### Test

[5.4.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-5-4-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTabDonnee">tableau de donn&eacute;es</a> (balise `table`) a-t-il une balise `caption` ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1 (table tags identified as data table from html markers)**

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as data table from html markers)**

All the `<table>` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter. That means select all the `<table>` tags of
the page when these parameters are empty.

### Process

#### Test1 (only applied when the "DATA_TABLE_MARKER" parameter is not empty) :

For each element of **Set1** (data tables identified by a html marker), test
whether the node has a `<caption>` child node :

For each occurence of false-result of **Test1**, raise a MessageA

#### Test2 :

For each element of **Set2** (tables not identified as data tables), test
whether the node has a `<caption>` child node :

For each occurence of false-result of **Test2**, raise a MessageB

For each occurence of true-result of **Test2**, raise a MessageC

###### MessageA : Caption Missing

-   code : CaptionMissing
-   status: Failed
-   parameter : Snippet
-   present in source : yes

###### MessageB : Check the nature of table without caption child

-   code : CheckNatureOfTableWithoutCaptionChildElement
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageC : Check the nature of table without caption child

-   code : CheckNatureOfTableWithCaptionChildElement
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

#### Not Applicable

Selections are empty (The page has no `<table>` tag that means that **Set1** and **Set2** are empty)

#### Failed

At least one data table has no `<caption>` child tag (**Test1** returns false for at least one element)

#### Passed

All the tables of page are identified as data tables and they all have a `<caption>` child tag (**Test1** returns true and **Set2** is empty)

#### Pre-qualified:

on all other cases

## Notes 

Complex tables are seen as a subset of data tables. That's tables identified as complex tables are added to the set of identified data tables.

