# AccessiWeb 2.2 - Rule 8.8.1

## Summary

This test consists in checking whether each change of language is valid

## Business description

Criterion : 8.8

Test : [8.8.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-8-8-1)

Test description :

On each Web page, is each language change (`lang` and/or `xml:lang` attribute) valid?

Level : [Silver](/en/category/rules-design/accessiweb-11/level/argent)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the tags different from `<html>` that have a `lang` or a `xml:lang` attribute

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

Selection is empty (The page has no tag different from `html` with a `lang` or a `xml:lang` attribute)

#### Pre-qualified

The selection is not empty

## Notes

We only detect the elements of the scope of the test to determine whether the test is applicable
