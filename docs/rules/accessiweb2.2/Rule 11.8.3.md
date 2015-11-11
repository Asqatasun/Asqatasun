### Summary

This test consists in checking the relevancy of the label attribute of
each optgroup tag

### Business description

Criterion : 11.8

Test : [11.8.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-8-3)

Test description :

For each list item grouping (optgroup tag) with a label attribute, is
the content of the label attribute relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the `select` tags within a `form` tag with a "label" attribute

#### Process

The selection handles the process.

For each occurence of the selection raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: NMI
-   parameter : tag name
-   present in source : yes

#### Analysis

##### NA

Selection is empty (The page has no `select` tag within a `form` tag
with a "label" attribute)

##### NMI

The selection is not empty

### Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
