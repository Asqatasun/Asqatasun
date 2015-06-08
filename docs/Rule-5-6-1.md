# Rule 5.6.1

## Summary

This test consists in checking whether each colum header of a data table is defined with a `<th>` tag

## Business description

### Criterion

[5.6](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-5-6)

### Test

[5.6.1](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-5-6-1)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mTabDonnee">tableau de donn&eacute;es</a> (balise `table`), chaque <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/glossaire.htm#mEnteteTab">en-t&ecirc;te de colonnes</a> a-t-il une balise `th` ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1 (table tags identified as data table from html markers)

All the `<table>` tags with an `"id"` attribute or a `"class"` attribute or a
`"role"` attribute that matches one of the values set by the user through
the "DATA_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags without an `"id"` attribute or a `"class"` attribute
equal to the value of the "DATA_TABLE_MARKER" parameter or the
"PRESENTATION_TABLE_MARKER" or the "COMPLEX_TABLE_MARKER" parameter associated with the audit. That
means select all the `<table>` tags of the page when the parameter is empty.

### Process

#### Test1

For each occurence of **Set1**, raise a MessageA

#### Test2

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

#### Not Applicable

The page has no `<table>` tag or only tables identified as presentation table (**Set1** AND **Set2** are empty)

#### Pre-Qualified

In all others cases

## Notes

We only detect the elements of the scope of the test to determine whether the test is applicable
