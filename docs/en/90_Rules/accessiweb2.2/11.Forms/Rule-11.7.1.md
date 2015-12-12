# AccessiWeb 2.2 - Rule 11.7.1

## Summary

This test consists in checking whether each legend associated with a
fieldset is relevant

## Business description

Criterion : 11.7

Test : [11.7.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-7-1)

Test description :

In each form, is each legend (legend tag) related with a form field
grouping (fieldset tag) relevant?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `legend` tags with a `fieldset` ancestor that has a `form`
ancestor

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
