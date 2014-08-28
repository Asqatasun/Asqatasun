### Summary

This test consists in checking whether the user is warned when he's
about to open a new window via an <applet\>, an <object\> or an <embed\>
tag

### Business description

Criterion : 13.2

Test : [13.2.2](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-2-2)

Test description :

On each Web page, for each window opening launched via an object, applet
or embed tag, is the user warned?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Set1 : All the <embed\>, <applet\> and <object\> tags (embed, applet,
object)

#### Process

The selection handles the process.

For each occurence of the Set1 raise a MessageA

##### MessageA: Check user is warned in case of new window open

-   code : CheckUserIsWarnedInCaseOfNewWindow
-   status: NMI
-   parameter : text, title attribute, snippet
-   present in source : yes

#### Analysis

##### NA

Selection is empty (The page has neither <object\>, nor <applet\> nor
<embed\> tag)

##### NMI

The selection is not empty

### Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(embed, applet, object) has been found on 6795 pages, i.e on 8.7% of the
pages
