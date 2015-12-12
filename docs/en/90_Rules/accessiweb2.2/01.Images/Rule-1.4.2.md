## Summary

This test consists in checking the pertinence of the altenative
associated with an area of an image map used as a CAPTCHA.

## Business description

Criterion : 1.4

Test : [1.4.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-2)

Test description :

For each
[area](http://www.accessiweb.org/index.php/glossary-76.html#mZone) (area tag) of an [image map](http://www.accessiweb.org/index.php/glossary-76.html#mImgReactive), used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) or as [test image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest), and with an alt attribute, does the content of this attribute allow to identify the kind and the purpose of the image?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

## Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

## Algorithm

### Selection

All the `area` tags with a `alt` attribute

### Process

The selection handles the process

### Analysis

#### Not Applicable

Selection is empty (The page has no `area` tag with a `alt` attribute)

#### Pre-qualified

The selection is not empty

## Notes

No notes yet for that rule
