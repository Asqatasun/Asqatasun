# Rule 5.7.2

## Summary

The test consists in detecting data or complex tables on the page.

Checking whether each header of a data table applied to the whole row or to the whole column uses an appropriate scope attribute has to be done manually.

## Business description

### Criterion

[5.7](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-5-7)

### Test

[5.7.2](http://references.modernisation.gouv.fr/referentiel-technique-0#test-5-7-2)

### Description

Chaque en-t&ecirc;te (balise `th`) s'appliquant &agrave; la totalit&eacute; de la ligne ou de la colonne et poss&eacute;dant un attribut `scope` v&eacute;rifie-t-il une de ces conditions ? 
 
 *  L'en-t&ecirc;te poss&egrave;de un attribut `scope` avec la valeur `"row"` pour les en-t&ecirc;tes de ligne 
 *  L'en-t&ecirc;te poss&egrave;de un attribut `scope` avec la valeur `"col"` pour les en-t&ecirc;tes de colonne 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1 (table tags identified as data or complex table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a "role" attribute that matches one of the values set by the user through the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with `<th>` child tags.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class" attribute or a "role" attribute that matches one the values set by the user through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with `<th>` child tags. 

That means select all the `<table>` tags of the page when these parameters are empty.

### Process

#### Test1

For each occurence of **Set1**, raise a MessageA

#### Test2

For each occurence of **Set2**, raise a MessageB

###### MessageA : Check the definition of headers for data tables

-   code :CheckDefinitionOfHeaderForDataTable
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

###### MessageB : Check the nature of table and the definition of headers for data tables

-   code :CheckNatureOfTableAndHeadersDefinition
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `<table>` tag with `<th>` child tags or only tables identified as presentation table (**Set1** AND **Set2** are empty)

#### Pre-Qualified 

In all other cases

## Notes

We only detect the elements of the scope of the test to determine whether the test is applicable.

Complex tables are seen as a subset of data tables. That's tables identified as complex tables are added to the set of identified data tables.
