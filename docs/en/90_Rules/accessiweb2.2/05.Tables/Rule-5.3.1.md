## Summary

This test consists in checking whether the linearised content of a
layout table is understandable

## Business description

Criterion : 5.3

Test : [5.3.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-3-1)

Test description :

For each [layout
table](http://accessiweb.org/index.php/glossary-76.html#mTabMiseForme),
is the linearised content still understandable?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

**Set1 (table tags identified as presentation table from html markers)**

All the `table` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "PRESENTATION\_TABLE\_MARKER" parameter.

**Set2 (table tags not identified as data table or presentation table
from html markers)**

All the `table` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter. That means select all the table tags of
the page when these parameters are empty.

### Process

##### Test1 (only applied when the "PRESENTATION\_TABLE\_MARKER" parameter is not empty) :

For each element of Set1 (presentation tables identified by a html
marker), raise a MessageA

##### Test2 :

For each element of Set2 (tables not identified as data table), raise a
MessageB

##### MessageA : Check linearised content

-   code :CheckLinearisedContent
-   status: NMI
-   parameter : Snippet
-   present in source : yes

##### MessageB : Check nature of table and linearised content if table is presentation table

-   code :CheckNatureOfTableAndLinearisedContent
-   status: NMI
-   parameter : Snippet
-   present in source : yes

### Analysis

#### Not Applicable

The page has no `table` tag, or only has data table identified by
markers (Set1 AND Set2 are empty)

#### Pre-qualified

In all other cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
