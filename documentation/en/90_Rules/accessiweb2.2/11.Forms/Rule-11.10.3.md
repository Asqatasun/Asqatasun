# AccessiWeb 2.2 - Rule 11.10.3

## Summary

This test consists in checking whether data formats and types are
provided in some way for each mandatory field

## Business description

Criterion : 11.10

Test : [11.10.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-10-3)

Test description :

For each form, is for each mandatory field the [data and/Or format
type](http://www.accessiweb.org/index.php/glossary-76.html#mTypeDonnes)
specified, if necessary?

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
