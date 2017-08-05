# RGAA 3.2016 - Rule 1.5.2

## Summary
This test consists in detecting buttons associated with an image used as a CAPTCHA.

## Business description

### Criterion
[1.5](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-5)

### Test
[1.5.2](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-5-2)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#bouton-formulaire">bouton</a> associ&#xE9; &#xE0; une image (balise <code lang="en">input</code> avec l&#x2019;attribut <code lang="en">type="image"</code>) utilis&#xE9;e comme <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/glossaire.html#captcha">CAPTCHA</a> v&#xE9;rifie-t-il une de ces conditions&nbsp;? <ul><li>Il existe une autre forme de CAPTCHA non graphique, au moins&nbsp;;</li> <li>Il existe une autre solution d&#x2019;acc&#xE8;s &#xE0; la fonctionnalit&#xE9; s&#xE9;curis&#xE9;e par le CAPTCHA.</li> </ul></div>

### Level
**A**

## Technical description

### Scope
**Page**

### Decision level
**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<input>` tags with a `"type"` attribute equals to "image" (css selector : `input[type=image]`) 

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA 

-    code : **CheckCaptchaAlternativeAccess** 
-    status: Pre-Qualified
-    parameter : tag name, snippet
-    present in source : yes

### Analysis

#### Pre-qualified

At least one `<input>` tags with a `"type"` attribute equals to "image" identified as a CAPTCHA has been found on the page (**Set2** is not empty)

#### Not Applicable

No `<input>` tag with a `"type"` attribute equals to "image" identified as a CAPTCHA has been found on the page (**Set2** is empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases

[TestCases files for rule 1.5.2](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010502/)


