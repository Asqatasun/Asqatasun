# AccessiWeb 2.2 - Rule 1.2.2

## Summary

This test consists in checking whether each non clickable area that
doesn't handle any information is defined with an empty `alt` attribute.

## Business description

Criterion : 1.2

Test : [1.2.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-2)

Test description :

For each [non clickable area](http://www.accessiweb.org/index.php/glossary-76.html#mZoneNonCliquable)
(area tag with the nohref attribute), that does not convey information, and that has an alt attribute, is the content of this attribute empty (alt="")?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `area` tags with a "nohref" and a `alt` attribute

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `area` tag with a "nohref" and a
`alt` attribute)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
