# RGAA 3.2016 - Rule 1.5.1

## Summary
This test consists in detecting images associated with an image used as a CAPTCHA.

## Business description

### Criterion
[1.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-5)

### Test
[1.5.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-5-1)

### Description
<div lang="fr">Chaque image (balises <code lang="en">img</code>, <code lang="en">area</code>, <code lang="en">object</code>, <code lang="en">embed</code>, <code lang="en">svg</code>, <code lang="en">canvas</code>) utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#captcha">CAPTCHA</a> v&#xE9;rifie-t-elle une de ces conditions&nbsp;? <ul><li>Il existe une autre forme de CAPTCHA non graphique, au moins&nbsp;;</li> <li>Il existe une autre solution d&#x2019;acc&#xE8;s &#xE0; la fonctionnalit&#xE9; s&#xE9;curis&#xE9;e par le CAPTCHA.</li> </ul></div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<img>` tags of the page not within a link  (css selector : `img:not(a img)`)

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set3

All the `<object>` tags with a `"type"` attribute that starts with "image/...", not within a link (css selector : `object[type^=image]:not(a object)`)

#### Set4

All the elements of **Set2** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

##### Set5

All the `<embed>` tags of the page not within a link and with a `"type"` attribute that starts with `image/...` (css selector : `embed[type^=image]:not(a embed)`)

#### Set6

All the elements of **Set5** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set7

All the `<svg>` tags of the page not within a link (css selector : `svg:not(a svg)`)

#### Set8

All the elements of **Set7** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set9

All the `<canvas>` tags of the page not within a link (css selector : `canvas:not(a canvas)`)

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

No `<img>`, `<area>`, `<object>` with a `"type"` attribute equals to "image", 
`<embed>` with a `type` attribute equals to `image`, `<canvas>` or `<svg>` 
identified as a CAPTCHA has been found on the page (**Set2** AND **Set4** AND **Set6** AND **Set8** AND **Set10** AND **Set12** are empty)

#### Pre-qualified

At least one `<img>`, `<area>`, `<object>` with a `"type"` attribute equals to "image", 
`<embed>` with a `type` attribute equals to `image`, `<canvas>` or `<svg>` identified
 as a CAPTCHA has been found on the page (**Set2** OR **Set4** OR **Set6** OR **Set8** OR **Set10** OR **Set12** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases

[TestCases files for rule 1.5.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010501/)


