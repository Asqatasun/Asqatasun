# AccessiWeb 2.2 - Rule 11.2.2

## Summary

This test consists in checking whether the function of each form field
is defined through its title attribute

## Business description

Criterion : 11.2

Test : [11.2.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-2-2)

Test description :

Does each title attribute allow to know the exact function of the [form
field](http://www.accessiweb.org/index.php/glossary-76.html#mChpSaisie)
(input tag of type text, password, checkbox, radio, file, or textarea
and select tags) with which it is associated?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

-   All the `input` tags with a "title" attribute and a "type"
    attribute equals to:
    -   "text"
    -   or "password"
    -   or "checkbox"
    -   or "radio"
    -   or "file"

-   AND all the `textarea` tags with a "title" attribute
-   AND all the `select` tags with a "title" attribute

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

-   We only detect the elements of the scope of the test to determine
    whether the test is applicable
-   We assume here that the raised messages focus on the `form` element
    and not on the form fields

