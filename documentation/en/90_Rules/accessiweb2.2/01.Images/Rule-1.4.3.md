# AccessiWeb 2.2 - Rule 1.4.3

## Summary

This test consists in checking the pertinence of the altenative
associated with an applet image used as a CAPTCHA.

## Business description

Criterion : 1.4

Test : [1.4.3](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-3)

Test description :

For each image (applet tag) used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) or as [test image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest), with an alt attribute, does the content of this attribute allow to identify the kind and the purpose of the image?

Level : Bronze

## Technical description

Scope : page

Decision level :
semidecidable

## Algorithm

### Selection

All the `applet` tags with a `alt` attribute

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `applet` tag with a `alt`
attribute)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
