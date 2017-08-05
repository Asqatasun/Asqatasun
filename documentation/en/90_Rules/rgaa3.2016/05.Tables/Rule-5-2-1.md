# RGAA 3.2016 - Rule 5.2.1

## Summary
This test consists in checking the relevancy of the caption of complex tables.

## Business description

### Criterion
[5.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-5-2)

### Test
[5.2.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-5-2-1)

### Description
<div lang="fr">Pour chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#tableau-de-donnes-complexe">tableau de donn&#xE9;e complexes</a> (balise <code lang="en">table</code>) ayant un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#rsum-de-tableau">r&#xE9;sum&#xE9;</a>, celui-ci est-il pertinent&nbsp;?</div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1 (table tags identified as complex table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a "role" attribute that matches one of the values set by the user through the "COMPLEX_TABLE_MARKER" parameter AND with a `<caption>` child tag.

#### Set2 (table tags not identified as complex table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class" attribute or a "role" attribute that matches one the values set by the use through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter AND with a `<caption>` child tag. That means select all the `<table>` tags of the page when these parameters are empty.

#### Set3

The `<caption>` child tag of each element of **Set1**.

#### Set4

The `<caption>` child tag of each element of **Set2**.

### Process

#### Test1

For all elements of **Set3** (caption of complex tables identified by a html marker), check whether the content of the `<caption>` tag is not relevant (see Notes for details about relevancy test). 

For each occurence of false-result of **Test1**, raise a MessageA. Raise a MessageB instead.

#### Test2

For all elements of **Set4** (tables not identified by a html marker), check whether the content of the `<caption>` tag is not relevant (see Notes for details about relevancy test). 

For each occurence of false-result of **Test2**, raise a MessageC. Raise a MessageD instead.

##### MessageA : Not Pertinent caption of complex table

-   code : NotPertinentCaptionForComplexTable
-   status: Failed
-   parameter : tag text, snippet
-   present in source : yes

##### MessageB : Check the pertinence of caption of complex table

-   code : CheckCaptionPertinenceForComplexTable
-   status: Pre-Qualified
-   parameter : tag text, snippet
-   present in source : yes

##### MessageC : Check table nature is complex with not pertinent caption child tag

-   code : CheckTableIsComplexForNotPertinentCaption
-   status: Pre-Qualified
-   parameter : tag text, snippet
-   present in source : yes

##### MessageD : Check the nature of table and the pertinenceof the caption child tag

-   code : CheckTableIsComplexAndCaptionPertinence
-   status: Pre-Qualified
-   parameter : title attribute, snippet
-   present in source : yes

### Analysis

#### Failed

At least one table identified as complex table has a not pertinent caption (**Test1** return false for at least one element)

#### Not Applicable

The page has no `<table>` tag or only tables identified as presentation or data tables (**Set1** AND **Set2** are empty)

#### Pre-Qualified

In all other cases

## Notes

The content of the `<caption>` tag is seen as not relevant if :

- empty
- only composed of non-alphanumerical characters



##  TestCases

[TestCases files for rule 5.2.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule050201/)


