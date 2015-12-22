# AccessiWeb 2.2 - Rule 1.2.5

## Summary

This test consists in checking whether each embedded image that doesn't
handle any information has an empty alternative.

## Business description

Criterion : 1.2

Test : [1.2.5](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-5)

Test description :

Does each embedded image (embed tag with type="image/..." attribute)
that does not convey information, pass one of the conditions below?

* The noembed element is not on the page
* The noembed element does not contain any image description

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `embed` tags with a "type" attribute that starts with
"image/..."

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `embed` tag with a "type" attribute
that starts with "image/...")

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
