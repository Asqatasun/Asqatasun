# Rule 5.7.1
## Summary

This test consists in checking whether each header of a data table
applied to the whole row or to the whole column have a unique id
attribute or a scope attribute.

## Business description

### Criterion

[5.7](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-5-7)

### Test

[5.7.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-5-7-1)

### Description

Chaque en-t&ecirc;te (balise `th`) s'appliquant &agrave; la totalit&eacute; de la ligne ou de la colonne poss&egrave;de-t-il un attribut `id` unique ou un attribut `scope` ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

**Set1 (table tags identified as data table from html markers)**

All the <table\> tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA\_TABLE\_MARKER" parameter AND with <th\> child tags.

**Set2 (table tags not identified as data table from html markers)**

All the <table\> tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter AND with <th\> child tags. That means
select all the table tags of the page when these parameters are empty.

### Process

**Test1**

For each occurence of Set1, raise a MessageA

**Test2**

For each occurence of Set2, raise a MessageB

###### MessageA : Check the definition of headers for data tables

-   code :CheckDefinitionOfHeaderForDataTable
-   status: NMI
-   parameter : snippet
-   present in source : yes

###### MessageB : Check the nature of table and the definition of headers for data tables

-   code :CheckNatureOfTableAndHeadersDefinition
-   status: NMI
-   parameter : snippet
-   present in source : yes

### Analysis

#### Not Applicable

Set1 AND Set2 are empty (The page has no <table\> tag with <th\> child
tags or only tables identified as presentation table)

#### Pre-qualified

In all other cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
