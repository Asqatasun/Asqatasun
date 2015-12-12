# AccessiWeb 2.2 - Rule 11.14.2

## Summary

This test consists in checking whether some help tools are provided for
each text field

## Business description

Criterion : 11.14

Test : [11.14.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-14-2)

Test description :

Does each field of type text pass one of the conditions below, if
necessary?

-   A spell checking tool is available
-   typing suggestions are available

Level : [Or](/en/category/rules-design/accessiweb-11/level/or)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

-   All the `input` tags within a `form` with a "type" attribute
    equals to:
    -   "text"
    -   or "password"

-   AND all the `textarea` tags within a `form`

### Process

The selection handles the process.

For each occurence of the selection raise a MessageA

###### MessageA : Check Manually the elements

-   code :ManualCheckOnElements
-   status: NMI
-   parameter : tag name
-   present in source : yes

### Analysis

#### Not Applicable

Selection is empty

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable


