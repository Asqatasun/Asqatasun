# AccessiWeb 2.2 - Rule 11.4.1

## Summary

This test consists in checking whether each label and the associated
form field are placed side by side

## Business description

Criterion : 11.4

Test : [11.4.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-4-1)

Test description :

In each form, are each control
[label](http://www.accessiweb.org/index.php/glossary-76.html#mEtiquette)
and its related control positioned next to each other?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `form` tags with child `label` tags

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

Selection is empty (The page has no `form` tag with `label` tag as
child)

#### Pre-qualified

The selection is not empty

## Notes

-   We only detect the elements of the scope of the test to determine
    whether the test is applicable
-   We assume here that the raised messages focus on the `form` element
    and not on the `label` elements

