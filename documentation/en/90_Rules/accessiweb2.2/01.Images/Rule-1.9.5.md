# AccessiWeb 2.2 - Rule 1.9.5

## Summary

This test consists in checking whether each text image object is
replaced with styled text.

## Business description

Criterion : 1.9

Test : [1.9.5](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-9-5)

Test description :

Each [text image object](http://www.accessiweb.org/index.php/glossary-76.html#mImgTextObj) (object tag with the attribute type="image/...") must be replaced with [styled text](http://www.accessiweb.org/index.php/glossary-76.html#mTexteStyle) if possible. Does this rule have been followed ([except in special cases](http://www.accessiweb.org/index.php/glossary-76.html#cpCrit19- "Special cases for criterion 1.9"))?

Level : Gold

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `object` tags with a "type" attribute that starts with
"image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `object` tag with a "type"
attribute that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
