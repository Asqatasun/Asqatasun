## Summary

This tests consists in checking the presence of the summary attribute on
the data table nodes. Data table nodes are characterized by HTML
markers. When tables cannot be characterized, the test is applied but
the result is semi- decidable.

## Business description

Criterion : 5.1

Test : [5.1.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-1-1)

Test description :

Does each [data
table](http://accessiweb.org/index.php/glossary-76.html#mTabDonnee)
(table tag) have a summary attribute?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1 (table tags identified as data table from html markers)**

All the `table` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA\_TABLE\_MARKER" parameter.

**Set2 (table tags not identified as data table from html markers)**

All the `table` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter. That means select all the table tags of
the page when these parameters are empty.

### Process

##### Test1 (only applied when the "DATA\_TABLE\_MARKER" parameter is not empty) :

For each element of Set1 (data tables identified by a html marker), test
whether the node has a "summary" attribute :

For each occurence of false-result of Test1, raise a MessageA

##### Test2 :

For each element of Set2 (tables not identified as data table), test
whether the node has a "summary" attribute :

For each occurence of false-result of Test2, raise a MessageB

For each occurence of true-result of Test2, raise a MessageC

##### MessageA : Missing summary attribute

-   code :SummaryMissing
-   status: Failed
-   parameter : tag name
-   present in source : yes

##### MessageB : Missing summary attribute on uncharacterized table tag

-   code :CheckNatureOfTableWithoutSummaryAttribute
-   status: NMI
-   parameter : tag name
-   present in source : yes

##### MessageC : summary attribute present on uncharacterized table tag

-   code :CheckNatureOfTableWithSummaryAttribute
-   status: NMI
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selections are empty (The page has no table tags that means that Set1
and Set2 are empty)

#### Failed

Test1 returns false

#### Passed

Test1 returns true and Set2 is empty

#### Pre-qualified:

on all other cases

## Notes

No notes yet for that rule
