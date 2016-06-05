# RGAA 3.0 -  Rule 5.1.1

## Summary

This tests consists in checking the presence of a caption child node on the complex `<table>` tags. Complex `<table>` tags are characterized by HTML markers. When tables cannot be characterized, the test  is applied but the result is semi-decidable.

## Business description

### Criterion

[5.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-5-1)

###Test

[5.1.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-5-1-1)

### Description
Does each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTabDonneeComplexe">complex data
  table</a> (<code>table</code> tag) have a summary, provided
    through the <code>caption</code> tag? 


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

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "COMPLEX_TABLE_MARKER" parameter.

#### Set2 (table tags not identified as complex table from html markers)

All the `<table>` tags that don't have an "id" attribute or a "class" attribute or a "role" attribute that matches one the values set by the user through the "PRESENTATION_TABLE_MARKER" parameter or the "DATA_TABLE_MARKER" parameter or the "COMPLEX_TABLE_MARKER" parameter. 
That means select all the `<table>` tags of the page when these parameters are empty.

### Process

#### Test1

For each occurence of **Set1**, test the presence of a `<caption>` child tag.

For each element returning false in **Test1**, raise a MessageA.

#### Test1

For each occurence of **Set2**, test the presence of a `<caption>` child tag.

For each occurence of false-result of **Test2**, raise a MessageB

For each occurence of true-result of **Test2**, raise a MessageC

###### MessageA : Caption Missing on complex tables

-   code : CaptionMissingOnComplexTable
-   status: Failed
-   parameter : Snippet
-   present in source : yes

###### MessageB : Check the table without caption child is not a complex table

-   code : CheckTableWithoutCaptionChildElementIsNotComplex
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageC : Check the table with a caption child is a complex table

-   code : CheckTableWithCaptionChildElementIsComplex
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `<table>` tag (**Set1** and **Set2** are empty)

#### Failed

At least one complex table has no `<caption>` child tag (**Test1** returns false for at least one element)

#### Passed

All the tables of the page are identified as complex tables and they all have a `<caption>` child tag (**Test1** returns true and **Set2** is empty)

#### Pre-qualified:

In all other cases



##  TestCases 

[TestCases files for rule 5.1.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule050101/) 


