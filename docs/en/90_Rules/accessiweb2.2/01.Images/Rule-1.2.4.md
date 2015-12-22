# AccessiWeb 2.2 - Rule 1.2.4

## Summary

This test consists in checking whether each object image that doesn't handle any information is defined with an empty `alt` attribute.

## Business description

Criterion : 1.2

Test : [1.2.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-4)

Test description :

For each [object image](http://www.accessiweb.org/index.php/glossary-76.html#mImgObj)
(object tag with the attribute type="image/...") that does not convey information, is the [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg)
between `<object>` and `</object>` empty?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `object` tags with a `type` attribute that starts with "image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `object` tag with a `type` attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
