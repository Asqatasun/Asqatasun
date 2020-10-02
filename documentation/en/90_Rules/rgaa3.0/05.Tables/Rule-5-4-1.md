# RGAA 3.0 -  Rule 5.4.1

## Summary

This tests consists in checking the presence of a caption child node on the data `<table>` tags. Data `<table>` tags are characterized by HTML markers. When tables cannot be characterized, the test  is applied but the result is semi-decidable.

## Business description

### Criterion

[5.4](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-5-4)

### Test

[5.4.1](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-5-4-1)

### Description
Does each <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mTabDonnee">data
  table</a> (<code>table</code> tag) have a <code>caption</code> tag? 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1 (table tags identified as data table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as data table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter. That means select all the `<table>` tags of
the page when these parameters are empty.

### Process

#### Test1 (only applied when the "DATA_TABLE_MARKER" parameter is not empty) :

For each element of **Set1** (data tables identified by a html marker), test whether the node has a `<caption>` child node :

For each occurence of false-result of **Test1**, raise a MessageA

#### Test2 :

For each element of **Set2** (tables not identified as data tables), test whether the node has a `<caption>` child node :

For each occurence of false-result of **Test2**, raise a MessageB

For each occurence of true-result of **Test2**, raise a MessageC

###### MessageA : Caption Missing on data tables

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

The page has no `<table>` tag (**Set1** and **Set2** are empty)

#### Failed

At least one data table has no `<caption>` child tag (**Test1** returns false for at least one element)

#### Passed

All the tables of the page are identified as data tables and they all have a `<caption>` child tag (**Test1** returns true and **Set2** is empty)

#### Pre-qualified:

In all other cases



##  TestCases 

[TestCases files for rule 5.4.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule050401/) 


