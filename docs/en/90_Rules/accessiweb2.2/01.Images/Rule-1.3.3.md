# AccessiWeb 2.2 - Rule 1.3.3

## Summary

This test consists in checking whether each button associated with an
image that handles information has a relevant `alt` attribute.

## Business description

Criterion : 1.3

Test : [1.3.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-3-3)

Test description :

For each button that is associated with an image (input tag with the
attribute type="image") that has an alt attribute, is the content of
this attribute relevant (except in [special
cases](http://www.accessiweb.org/index.php/glossary-76.html#CP1-3 "Special cases for criterion 1.3"))?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `input` tags with a `alt` attribute and the type attribute
equals to "image"

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no`input` tags with a `alt` attribute
and the type attribute equals to "image")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
