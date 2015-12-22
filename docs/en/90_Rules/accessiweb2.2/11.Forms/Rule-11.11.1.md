# AccessiWeb 2.2 - Rule 11.11.1

## Summary

This test consists in checking whether data types and formats are
provided in case of input errors

## Business description

Criterion : 11.11

Test : [11.11.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-11-1)

Test description :

For each form, for each input error, are each [data types and
formats](http://www.accessiweb.org/index.php/glossary-76.html#mTypeDonnes)
suggested, if necessary?

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
