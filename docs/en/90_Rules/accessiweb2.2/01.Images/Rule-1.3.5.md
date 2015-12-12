# AccessiWeb 2.2 - Rule 1.3.5

## Summary

This test consists in checking whether each object image that handles information has a relevant `alt` attribute.

## Business description

Criterion : 1.3

Test : [1.3.5](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-3-5)

Test description :

For each [object image](http://www.accessiweb.org/index.php/glossary-76.html#mImgObj) (`<object>` tag with the attribute type="image/...") that conveys information and with a [text
alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg), is the [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg) between `<object>` and `</object>` relevant (except in [special cases](http://www.accessiweb.org/index.php/glossary-76.html#cpCrit1-3 "Special cases for criterion 1.3"))?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `<object>` tags with a `type` attribute that starts with "image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `<object>` tag with a `type` attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
