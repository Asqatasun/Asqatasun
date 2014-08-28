### Summary

This test consists in checking the relevancy of the summary attribute
for each data table. Data table nodes are characterized by HTML markers.
When tables cannot be characterized, the test is applied but the result
is semi- decidable.

### Business description

Criterion : 5.2

Test : [5.2.1](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-2-1)

Test description :

For each [data
table](http://accessiweb.org/index.php/glossary-76.html#mTabDonnee)
(table tag) with a summary attribute, is the content of this attribute
relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

**Set1 (table tags identified as data table from html markers)**

All the <table\> tags with an "id" attribute or a "class" attribute or a
"role" attribute that matches one of the values set by the user through
the "DATA\_TABLE\_MARKER" parameter AND a "summary" attribute.

**Set2 (table tags not identified as data table from html markers)**

All the <table\> tags that don't have an "id" attribute or a "class"
attribute or a "role" attribute that matches one the values set by the
use through the "PRESENTATION\_TABLE\_MARKER" parameter or the
"DATA\_TABLE\_MARKER" parameter AND a "summary" attribute. That means
select all the table tags of the page when these parameters are empty.

#### Process

##### Test1

For each element of Set1, we check whether the "summary" attribute is
not empty

For each element returning false in Test1, raise a MessageA.

##### Test2

For each element of Set1, we check whether the "summary" attribute
doesn't only contain non alphanumerical characters.

For each element returning false in Test2, raise a MessageA.

**Test3**

If Test1 AND Test2 return true (no pattern detected), raise a
MessageB**.**

##### Test4

For each element of Set2, we check whether the "summary" attribute
doesn't only contain non alphanumerical characters.

For each element returning false in Test4, raise a MessageC.

For each element returning true in Test4, raise a MessageD.

##### MessageA: Not Pertinent summary of data table

-   code : NotPertinentSummaryForDataTable
-   status: Failed
-   parameter : summary attribute, snippet
-   present in source : yes

##### MessageB : Check Pertinence of summary of data table

-   code : CheckSummaryPertinenceForDataTable
-   status: NMI
-   parameter : summary attribute, snippet
-   present in source : yes

##### MessageC : Check Nature of table with not Pertinent summary attribute

-   code : CheckNatureOfTableForNotPertinentSummary
-   status: NMI
-   parameter : summary attribute, snippet
-   present in source : yes

##### MessageD : Check Nature of table and summary attribute pertinence

-   code : CheckNatureOfTableAndSummaryPertinence
-   status: NMI
-   parameter : summary attribute, snippet
-   present in source : yes

****

#### Analysis

**Failed :**

Test1 or Test2 returns true for at least one element (at least one table
identified as data table has a not pertinent summary)

**NA :**

Set1 AND Set2 are empty (the page has no data table or only table
identified as presentation table)

**NMI :**

In all other cases

### Notes

No notes yet for that rule
