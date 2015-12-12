## Summary

This test consists in checking the pertinence of the altenative
associated with an image used as a CAPTCHA.

## Business description

Criterion : 1.4

Test : [1.4.1](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-1)

Test description :

For each image (img tag) used as
[CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) or as [test image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest), with an alt attribute, does the content of this attribute allow to identify the kind and the purpose of the image?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `img` tags of the page with an `alt` attribute.

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `img` tag with a `alt` attribute )

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
