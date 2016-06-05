# RGAA 3.0 -  Rule 5.8.1
## Summary

This test consists in checking whether each layout table does not use
elements used for data tables.

## Business description

### Criterion

[5.8](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-5-8)

### Test

[5.8.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-5-8-1)

### Description
Does each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTabMiseForme">layout
  table</a> (<code>table</code> tag) meet the following conditions?
    <ul><li> The <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTabMiseForme">layout
    table</a> (<code>table</code> tag) does not have any <code>caption</code>,
   <code>th</code>, <code>thead</code>, <code>tfoot</code> tags</li>
  <li> The cells of the <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTabMiseForme">layout
    table</a> (<code>td</code> tag) have no <code>scope</code>, <code>headers</code>,
   <code>colgroup</code>, <code>axis</code> attributes.</li>
    </ul> 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**semidecidable**

## Algorithm

### Selection

#### Set1 (table tags identified as presentation table from html markers)

All the `<table>` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "PRESENTATION_TABLE_MARKER" parameter

#### Set2 (table tags not identified as data table or presentation table
from html markers)**

All the `<table>` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
user through the "PRESENTATION_TABLE_MARKER" parameter or the
"DATA_TABLE_MARKER" parameter. That means select all the table tags of
the page when the parameters are empty.

### Process

#### Test1 :

For each element of **Set1**, test the absence of one the following tags as
children tag :

-   caption
-   th
-   thead
-   tfoot
-   colgroup
-   td[scope]
-   td[headers]
-   td[axis]

For each occurence of false-result of **Test1**, raise a MessageA

#### Test2 :

For each element of **Set2**, test the absence of one the following tags as
children tag :

-   caption
-   th
-   thead
-   tfoot
-   colgroup
-   td[scope]
-   td[headers]
-   td[axis]

For each occurence of false-result of **Test2**, raise a MessageB

For each occurence of true-result of **Test2**, raise a MessageC

###### MessageA : Presentation table with forbidden markup

-   code :PresentationTableWithForbiddenMarkup
-   status: Failed
-   parameter : Snippet
-   present in source : yes

###### MessageB : Check the table is a data table

-   code :CheckTableIsDataTable
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

###### MessageC : Check the table is a presentation table

-   code :CheckTableIsPresentationTable
-   status: Pre-Qualified
-   parameter : Snippet
-   present in source : yes

### Analysis

#### Passed

All the tables of the page are identified by marker and none of the presentation ones
contain forbidden child element (**Test1** returns true for all elements and **Set2** is empty)

#### Failed

At least one table
identified as presentation table by marker contains forbidden child
element (**Test1** returns false for at least one element)

#### Not Applicable 

The page has no table tags or only data table
identified by marker (**Set1** and **Set2** are empty)

#### Pre-qualified :

The page contains not identified tables that need to be manually
checked and no table identified as presentation contain forbidden child
element (**Set1** is empty or **Test1** returns true for all elements AND **Set2** is not
empty)

## Notes

Complex tables are seen as a subset of data tables. That's tables identified as complex tables are added to the set of identified data tables and so excluded from the test.



##  TestCases 

[TestCases files for rule 5.8.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule050801/) 


