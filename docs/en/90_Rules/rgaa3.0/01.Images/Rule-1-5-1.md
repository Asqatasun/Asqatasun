# RGAA 3.0 -  Rule 1.5.1

## Summary

This test consists in detecting images associated with an image used as a CAPTCHA.

## Business description

### Criterion

[1.5](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-5)

### Test

[1.5.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-5-1)

### Description

Chaque image (balises `img`, `area`, `object`, `embed`, `svg`, `canvas`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe une autre forme de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> non graphique, au moins 
 * Il existe une autre solution d'acc&egrave;s &agrave; la fonctionnalit&eacute; s&eacute;curis&eacute;e par le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> 

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<img>` tags of the page not within a link  (css selector : `img:not(a img)`)

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set3

All the `<object>` tags with a `"type"` attribute that starts with "image/...", not within a link (css selector : object[type^=image]:not(a object))

#### Set4

All the elements of **Set2** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

##### Set5

All the `<embed>` tags of the page not within a link and with a `"type"` attribute that starts with "image/..." (css selector : embed[type^=image]:not(a embed))

#### Set6

All the elements of **Set5** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set7

All the `<svg>` tags of the page not within a link (css selector : svg:not(a svg))

#### Set8

All the elements of **Set7** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set9

All the `<canvas>` tags of the page not within a link (css selector : canvas:not(a canvas))

#### Set10

All the elements of **Set9** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set11

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag 

#### Set12

All the elements of **Set11** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, **Set4**, **Set6**, **Set8**, **Set10** and **Set12**, raise a MessageA

##### MessageA 

-    code : **CheckCaptchaAlternativeAccess** 
-    status: Pre-Qualified
-    parameter : tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

No `<img>`, `<area>`, `<object>` with a `"type"` attribute equals to "image", `<embed>` with a `"type"` attribute equals to "image", <canvas> or <svg> identified as a CAPTCHA has been found on the page (**Set2** AND **Set4** AND **Set6** AND **Set8** AND **Set10** AND **Set12** are empty)

#### Pre-qualified

At least one `<img>`, `<area>`, `<object>` with a `"type"` attribute equals to "image", `<embed>` with a `"type"` attribute equals to "image", <canvas> or <svg> identified as a CAPTCHA has been found on the page (**Set2** OR **Set4** OR **Set6** OR **Set8** OR **Set10** OR **Set12** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element
