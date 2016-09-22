# RGAA 3.2016 - Rule 5.6.2

## Summary
This test consists in checking whether each row header of a data table
is defined with a `<th>` tag.

To do so, we detect the tables on the page, and let the user make the control manually.

## Business description

### Criterion
[5.6](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#crit-5-6)

### Test
[5.6.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-5-6-2)

### Description
<div lang="fr">Pour chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#tableau-de-donnes">tableau de donn&#xE9;es</a> (balise <code lang="en">table</code>), chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#entte-de-colonne-ou-de-ligne">en-t&#xEA;te de lignes</a> a-t-il une balise <code lang="en">th</code>&nbsp;?</div>

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
the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags without an "id" attribute or a "class" attribute
equal to the value of the "DATA_TABLE_MARKER" parameter or the
"PRESENTATION_TABLE_MARKER" parameter  or the "COMPLEX_TABLE_MARKER" parameter associated with the audit. That
means select all the table tags of the page when the parameter is empty.

### Process

#### Test1

For each occurence of **Set1**, raise a MessageA

#### Test2

For each occurence of **Set2**, raise a MessageB

###### MessageA : Check the usage of headers for data tables

-   code :CheckUsageOfHeaderForDataTable
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

###### MessageB : Check the nature of table and the usage of headers for data tables

-   code :CheckNatureOfTableAndUsageOfHeaders
-   status: Pre-Qualified
-   parameter : snippet
-   present in source : yes

### Analysis

#### Not Applicable 

The page has no `<table>` tag or only tables
identified as presentation table (**Set1** AND **Set2** are empty)

#### Pre-Qualified 

In all others cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable.

Complex tables are seen as a subset of data tables. That's tables identified as complex tables are added to the set of identified data tables.



##  TestCases

[TestCases files for rule 5.6.2](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule050602/)


