# RGAA 3.0 -  Rule 8.3.1

## Summary

We check whether a language is specified for each textual element of the page

## Business description

### Criterion

[8.3](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-8-3)

### Test

[8.3.1](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-8-3-1)

### Description
For each Web page, does
    the <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mLangueDefaut">
  default human language</a> identification meet one of
    the following conditions?
    <ul><li> The human language identification (<code>lang</code> and/or
   <code>xml:lang</code> attribute) for the page is provided via the
   HTML element</li>
  <li> The human language identification (<code>lang</code> and/or
   <code>xml:lang</code> attribute) for the page is provided via
   each text element or one of the parent elements</li>
    </ul> 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

##### Set1

The `<html>` tag with a `"lang"` or `"xml:lang"` attribute.

##### Set2

The tags with a `"lang"` or `"xml:lang"` attribute.

##### Set3

The textual tags without `"lang"` or `"xml:lang"` attribute considering that
these attributes can be set to the current tag or to one of its
ascendants.

### Process

#### Test1

Test whether **Set2** is empty. If yes, raise a MessageA

#### Test2

Test whether **Set1** is empty and **Set2** and **Set3** are not. If yes, raise a MessageB

##### MessageA : Lang Attribute Missing On Whole Page

-   code : LangAttributeMissingOnWholePage
-   status: Failed
-   parameter : none
-   present in source : no

##### MessageB : Lang Attribute Missing On Html

-   code : LangAttributeMissingOnHtml
-   status: Failed
-   parameter : none
-   present in source : no

### Analysis

#### Passed

-   The `<html>` tag has a `"lang"` or a `"xml:lang"` attribute (**Set1** is not
    empty)
-   The language is provided for each textual element by the tag or by
    one of its parents (**Set1** is empty AND **Set2** is not empty AND **Set3** is
    empty)

#### Failed

-   The page has no language specification (**Set2** is empty).
-   Some textual tags are missing the language attribute (**Set1** is empty
    AND **Set2** is not empty AND **Set3** is not empty)




##  TestCases 

[TestCases files for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule080301/) 


