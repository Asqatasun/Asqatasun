# AccessiWeb 2.2 - Rule 11.3.1

## Summary

This test consists in checking whether each label associated with a form
fields with a same function is defined identicaly

## Business description

Criterion : 11.3

Test : [11.3.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-3-1)

Test description :

Is each [label](http://www.accessiweb.org/index.php/glossary-76.html#mEtiquette) related with a form field with the same function and that is repeated several times on a same Web page consistent?

Level : [Silver](/en/category/rules-design/accessiweb-11/level/argent)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

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

