# AccessiWeb 2.2 - Rule 5.5.1

## Summary

This test consists in checking the relevancy of the caption of data
tables.

## Business description

Criterion : 5.5

Test : [5.5.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-5-1)

Test description :

For each [data
table](http://accessiweb.org/index.php/glossary-76.html#mTabDonnee)
(table tag) with a caption tag, does the content of this tag provide the
[title](http://accessiweb.org/index.php/glossary-76.html#mTitreTab) of
the table?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

**Set1 (table tags identified as data table from html markers) : \
**

All the `table` tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA\_TABLE\_MARKER" parameter AND with a `caption` child tag.

**Set2 (table tags not identified as data table from html markers) :\
**

All the `table` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter AND with a `caption` child tag. That
means select all the table tags of the page when these parameters are
empty.

**Set3 :**

The `caption` child tag of each element of Set1.

**Set4 :**

The `caption` child tag of each element of Set2.

### Process

**Test1 :**

For each element of Set3 (caption of data tables identified by a html
marker), test whether the text is not empty.

For each occurence of false-result of Test1, raise a MessageA

**Test2 :**

For each element of Set3 (caption of data tables identified by a html
marker), test whether the text doesn't only contain non alphanumerical
characters.

For each occurence of false-result of Test2, raise a MessageA

**Test3 :**

If Test1 AND Test2 return true (no pattern detected), raise a
MessageB.****

**Test4 :**

For each element of Set4, test whether the text is not empty.

For each occurence of false-result of Test4, raise a MessageC

**Test5 :**

For each element of Set4, test whether the text doesn't only contain non
alphanumerical characters.

For each occurence of false-result of Test5, raise a MessageC

**Test6 :**

If Test4 AND Test5 return true (no pattern detected), raise a
MessageD.****

##### MessageA : Not Pertinent caption of data table

-   code : NotPertinentCaptionForDataTable
-   status: Failed
-   parameter : tag text, snippet
-   present in source : yes

##### MessageB : Check the pertinence of caption of data table

-   code : CheckCaptionPertinenceForDataTable
-   status: NMI
-   parameter : tag text, snippet
-   present in source : yes

##### MessageC : Check the nature of table with not pertinent caption child tag

-   code : CheckNatureOfTableForNotPertinentCaption
-   status: NMI
-   parameter : tag text, snippet
-   present in source : yes

##### MessageD : Check the nature of table and the pertinenceof the caption child tag

-   code : CheckNatureOfTableAndCaptionPertinence
-   status: NMI
-   parameter : title attribute, snippet
-   present in source : yes

### Analysis

**Failed :**

Test1 OR Test2 return false for at least one element (at least one table
identified as data table has a not pertinent caption)**\
**

**NA : \
**

Set1 AND Set2 are empty (the page has no `table` tag or only table
identified as presentation table)

**NMI :**

In all other cases

## Notes


