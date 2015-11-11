### Summary

This test consists in checking whether the user is warned when he's
about to open a new window via a form control.

### Business description

Criterion : 13.2

Test : [13.2.3](http://accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-2-3)

Test description :

On each Web page, for each window opening launched via a form control,
is the user warned?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

Set1 : all the `form`, `select` not within a `form`, `textarea` not
within a `form`, `input` not within a `form` and `button` not within
a `form` tags (form, select:not(form select), textarea:not(form
textarea), input:not(form input), button:not(form button))

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

Set1 is empty (The page has neither `form`, nor `select` within a
`form`, nor `input` within a `form`, nor `button`within a `form`,
nor `textarea` within a `form` tag)

##### NMI

The selection is not empty

### Notes

We detect the elements of the scope of the test to determine whether the
test is applicable.

On latest webdev data set (2013-10-30, 78,000 pages), the test scope
(form, select:not(form select), textarea:not(form textarea),
input:not(form input), button:not(form button)) has been found on 65632
pages, i.e on 84% of the pages (the test returns NA in 16% of the cases)
