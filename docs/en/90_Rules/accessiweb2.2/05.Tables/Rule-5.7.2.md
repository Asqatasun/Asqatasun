# AccessiWeb 2.2 - Rule  5.7.2

## Summary

This test consists in checking whether each header of a data table
applied to the whole row or to the whole column uses an appropriate
scope attribute.

## Business description

Criterion : 5.7

Test : [5.7.2](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-7-2)

Test description : Does each header (th tag) applied to the whole row or
the whole column and having a scope attribute pass one of the conditions
below?

-   The header has a scope attribute with the "row" value for row
    headers
-   The header has a scope attribute with the "col" value for column
    headers

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

**Set1 (table tags identified as data table from html markers)**

All the `table` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA\_TABLE\_MARKER" parameter AND with `th` child tags.

**Set2 (table tags not identified as data table from html markers)**

All the `table` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter AND with `th` child tags. That means
select all the table tags of the page when these parameters are empty.

### Process

**Test1**

For each occurence of Set1, raise a MessageA

**Test2**

For each occurence of Set2, raise a MessageB

###### MessageA : Check the definition of headers for data tables

-   code :CheckDefinitionOfHeaderForDataTable
-   status: NMI
-   parameter : snippet
-   present in source : yes

###### MessageB : Check the nature of table and the definition of headers for data tables

-   code :CheckNatureOfTableAndHeadersDefinition
-   status: NMI
-   parameter : snippet
-   present in source : yes

### Analysis

**NA :**

Set1 AND Set2 are empty (The page has no `table` tag with `th` child
tags or only tables identified as presentation table)

**NMI : \
**

In all other cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
