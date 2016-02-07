# AccessiWeb 2.2 - Rule 5.6.1

## Summary

This test consists in checking whether each colum header of a data table
is defined with a `th` tag

## Business description

Criterion : 5.6

Test : [5.6.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-6-1)

Test description :

For each [data
table](http://accessiweb.org/index.php/glossary-76.html#mTabDonnee)
(table tag), does each [column
header](http://accessiweb.org/index.php/glossary-76.html#mEnteteTab)
have a th tag?

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
the "DATA\_TABLE\_MARKER" parameter.

**Set2 (table tags not identified as data table from html markers)**

All the `table` tags without an "id" attribute or a "class" attribute
equal to the value of the "DATA\_TABLE\_MARKER" parameter or the
"PRESENTATION\_TABLE\_MARKER" parameter associated with the audit. That
means select all the table tags of the page when the parameter is empty.

### Process

**Test1**

For each occurence of Set1, raise a MessageA

**Test2**

For each occurence of Set2, raise a MessageB

###### MessageA : Check the usage of headers for data tables

-   code :CheckUsageOfHeaderForDataTable
-   status: NMI
-   parameter : snippet
-   present in source : yes

###### MessageB : Check the nature of table and the usage of headers for data tables

-   code :CheckNatureOfTableAndUsageOfHeaders
-   status: NMI
-   parameter : snippet
-   present in source : yes

### Analysis

**NA : \
**

Set1 AND Set2 are empty (The page has no `table` tag or only tables
identified as presentation table)

**NMI : \
**

In all others cases

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
