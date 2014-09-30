### Summary

This test consists in checking the pertinence of the altenative
associated with an area of an image map used as a CAPTCHA.

### Business description

Criterion : 1.4

Test : [1.4.2](http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-2)

Test description :

For each
[area](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mZone)
(area tag) of an [image
map](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mImgReactive),
used as
[CAPTCHA](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mcaptcha)
or as [test
image](http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mImgTest),
and with an alt attribute, does the content of this attribute allow to
identify the kind and the purpose of the image?

Level : [Bronze](/en/category/rules-design/accessiweb-11/level/bronze)

### Technical description

Scope : [page](/en/category/rules-design/accessiweb-11/scope/page)

Decision level :
[semidecidable](/en/category/rules-design/accessiweb-11/decision-level/semidecidable)

### Algorithm

#### Selection

All the <area\> tags with a "alt" attribute

#### Process

The selection handles the process

#### Analysis

##### NA

Selection is empty (The page has no `area` tag with a "alt" attribute)

##### NMI

The selection is not empty

### Notes

No notes yet for that rule
