### Summary

This test consists in checking whether some help is provided for each
form

### Business description

Criterion : 11.14

Test : [11.14.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-14-1)

Test description :

Does each form pass one of the conditions below?

-   There is a link to a help page
-   Indications before the form are available
-   Indications before form fields are available
-   an assistant is available

Level : [Or](/en/category/rules-design/accessiweb-11/level/or)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the <form\> tags

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

Selection is empty (The page has no <form\> tag)

##### NMI

The selection is not empty

### Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
