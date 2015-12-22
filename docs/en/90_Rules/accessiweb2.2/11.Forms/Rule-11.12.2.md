# AccessiWeb 2.2 - Rule 11.12.2

## Summary

This test consists in checking whether recovery or confirmation
mechanisms are provided in case of financial, legal or personal data
deletion

## Business description

Criterion : 11.12

Test : [11.12.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-12-2)

Test description :

For each form, does the deletion of financial, legal or personal data
pass one of the conditions below?

-   A mechanism allows to recover data that have been deleted by the
    user
-   An explicit mechanism confirming deletion, via a form field or an
    additional step, is available

Level : Silver

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `form` tags

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

Selection is empty (The page has no `form` tag)

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the scope of the test to determine
whether the test is applicable
