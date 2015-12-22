# AccessiWeb 2.2 - Rule 11.13.2

## Summary

This test consists in checking whether recovery or confirmation
mechanisms are provided in case of data deletion

## Business description

Criterion : 11.13

Test : [11.13.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-13-2)

Test description :

For each form, does data deletion pass one of the conditions below?

-   A mechanism allows to recover data that has been deleted by the user
-   An explicit mechanism confirming deletion, via a form field or an
    additional step, is available

Level : Gold

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
