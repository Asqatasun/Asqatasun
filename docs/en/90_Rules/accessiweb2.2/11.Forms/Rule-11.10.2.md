# AccessiWeb 2.2 - Rule 11.10.2

## Summary

This test consists in checking whether errors on form fields are
displayed and enough visible and understandable

## Business description

Criterion : 11.10

Test : [11.10.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-10-2)

Test description :

For each form, are input errors indicated in a relevant way?

Level : Bronze

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
