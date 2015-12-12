## Summary

This test consists in checking whether the content of the summary
attribute of a layout table is empty

## Business description

Criterion : 5.2

Test : [5.2.2](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-2-2)

Test description :

For each [layout
table](http://accessiweb.org/index.php/glossary-76.html#mTabMiseForme)
(table tag) with a summary attribute, is the content of this attribute
empty (summary=")?

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
the "PRESENTATION\_TABLE\_MARKER" parameter AND a "summary" attribute.

**Set2 (table tags not identified as presentation table from html
markers)**

All the `table` tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter AND a "summary" attribute. That means
select all the table tags of the page when these parameters are empty.

### Process

##### Test1

For each element of Set1, we check whether the "summary" attribute is
empty

For each element returning false in Test1, raise a MessageA.

##### Test2

For each element of Set2, we check whether the "summary" attribute is
empty

For each element returning false in Test2, raise a MessageB.

For each element returning true in Test2, raise a MessageC.

##### MessageA: Not empty summary of presentation table

-   code : NotEmptySummaryForPresentationTable
-   status: Failed
-   parameter : summary attribute, snippet
-   present in source : yes

##### MessageB : Check Nature of table with not empty summary attribute

-   code : CheckNatureOfTableWithNotEmptySummaryAttribute
-   status: NMI
-   parameter : summary attribute, snippet
-   present in source : yes

##### MessageC : Check Nature of table with empty summary attribute

-   code : CheckNatureOfTableWithEmptySummaryAttribute
-   status: NMI
-   parameter : summary attribute, snippet
-   present in source : yes

### Analysis

**Failed :**

Test1 returns true for at least one element (at least one table
identified as presentation table has a not empty summary)

**NA :**

Set1 AND Set2 are empty (the page has no data table or only tables
identified as data table)

**NMI :**

In all other cases

## Notes

No notes yet for that rule
