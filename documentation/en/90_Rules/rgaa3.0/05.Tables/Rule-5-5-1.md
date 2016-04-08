# RGAA 3.0 -  Rule 5.5.1

## Summary

This test consists in checking the relevancy of the caption of data tables.

## Business description

### Criterion

[5.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-5-5)

### Test

[5.5.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-5-5-1)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTabDonnee">tableau de donn&eacute;es</a> (balise `table`) ayant une balise `caption`, le contenu de cette balise donne-t-il le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTitreTab">titre</a> du tableau ?

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

All the `<table>` tags with an "id" attribute or a "class" attribute or a "role" attribute that matches one of the values set by the user through the "DATA_TABLE_MARKER" parameter AND with a `<caption>` child tag.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class" attribute or a "role" attribute that matches one the values set by the use through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with a `<caption>` child tag. That means select all the `<table>` tags of the page when these parameters are empty.

#### Set3

The `<caption>` child tag of each element of **Set1**.

#### Set4

The `<caption>` child tag of each element of **Set2**.

### Process

#### Test1

For all elements of **Set3** (caption of data tables identified by a html marker), check whether the content of the `<caption>` tag is not relevant (see Notes for details about relevancy test). 

For each occurence of false-result of **Test1**, raise a MessageA. Raise a MessageB instead.

#### Test2

For all elements of **Set4** (tables not identified by a html marker), check whether the content of the `<caption>` tag is not relevant (see Notes for details about relevancy test). 

For each occurence of false-result of **Test2**, raise a MessageC. Raise a MessageD instead.

##### MessageA : Not Pertinent caption of data table

-   code : NotPertinentCaptionForDataTable
-   status: Failed
-   parameter : tag text, snippet
-   present in source : yes

##### MessageB : Check the pertinence of caption of data table

-   code : CheckCaptionPertinenceForDataTable
-   status: Pre-Qualified
-   parameter : tag text, snippet
-   present in source : yes

##### MessageC : Check the nature of table with not pertinent caption child tag

-   code : CheckNatureOfTableForNotPertinentCaption
-   status: Pre-Qualified
-   parameter : tag text, snippet
-   present in source : yes

##### MessageD : Check the nature of table and the pertinenceof the caption child tag

-   code : CheckNatureOfTableAndCaptionPertinence
-   status: Pre-Qualified
-   parameter : title attribute, snippet
-   present in source : yes

### Analysis

#### Failed

At least one table identified as data table has a not pertinent caption (**Test1** return false for at least one element)

#### Not Applicable

The page has no `<table>` tag or only table identified as presentation table (**Set1** AND **Set2** are empty)

#### Pre-Qualified

In all other cases

## Notes

The content of the `<caption>` tag is seen as not relevant if :

- empty
- only composed of non-alphanumerical characters
