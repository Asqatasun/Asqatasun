# RGAA 3.2016 - Rule 5.7.4
## Summary
This test consists in checking whether each cell associated with one or
several headers is well-defined.

To do so, we detect the tables on the page, and let the user make the control manually.

## Business description

### Criterion
[5.7](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-5-7)

### Test
[5.7.4](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-5-7-4)

### Description
<div lang="fr">Chaque cellule (balise <code lang="en">td</code> ou <code lang="en">th</code>) associ&#xE9;e &#xE0; un ou plusieurs en-t&#xEA;tes poss&#xE9;dant un attribut <code lang="en">id</code> v&#xE9;rifie-t-elle ces conditions&nbsp;? <ul><li>La cellule poss&#xE8;de un attribut <code lang="en">headers</code>&nbsp;;</li> <li>L&#x2019;attribut <code lang="en">headers</code> poss&#xE8;de la liste des valeurs des en-t&#xEA;tes associ&#xE9;s &#xE0; la cellule.</li> </ul></div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1 (table tags identified as data table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with `<th>` child tags OR with `<td>` child tags.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with `<th>` child tags OR with `<td>` child tags. That means
select all the table tags of the page when these parameters are empty.

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

The page has no `<table>` tag with `<th>` child
tags `<td>` child tags or only tables identified as presentation table (**Set1** AND **Set2** are empty)

#### Pre-Qualified 

In all other cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable.

Complex tables are seen as a subset of data tables. That's tables identified as complex tables are added to the set of identified data tables.



##  TestCases

[TestCases files for rule 5.7.4](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule050704/)


