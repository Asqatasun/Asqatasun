# AccessiWeb 2.2 - Rule 1.4.4

## Summary

This test consists in checking the pertinence of the altenative
associated with a button image used as a CAPTCHA.

## Business description

Criterion : 1.4

Test : [1.4.4](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-4)

Test description :

For each button associated with an image (input tag with the attribute
type="image") used as
[CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha)
or as [test
image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest),
with an alt attribute, does the content of this attribute allow to
identify the kind and the purpose of the image?

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
