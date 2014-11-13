### Summary

This test consists in checking the pertinence of the altenative associated with an object image used as a CAPTCHA.

### Business description

Criterion : 1.4

Test : [1.4.5](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-5)

Test description :

For each [object image](http://www.accessiweb.org/index.php/glossary-76.html#mImgObj) (`<object>` tag with the attribute type="image/...") used as [CAPTCHA](http://www.accessiweb.org/index.php/glossary-76.html#mcaptcha) or as [test image](http://www.accessiweb.org/index.php/glossary-76.html#mImgTest), and with a [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg), does the [text alternative](http://www.accessiweb.org/index.php/glossary-76.html#mAltTexteImg)
between `<object>` and `</object>` allow to identify the kind and the purpose of the image?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the `<object>` tags with a `type` attribute that starts with "image/..."

#### Process

The selection handles the process

#### Analysis

##### NA

Selection is empty (The page has no `<object>` tag with a `type` attribute that starts with "image/...")

##### NMI

The selection is not empty

### Notes

No notes yet for that rule
