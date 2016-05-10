# RGAA 3.0 -  Rule 1.5.2

## Summary

This test consists in detecting buttons associated with an image used as a CAPTCHA.

## Business description

### Criterion

[1.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-5)

###Test

[1.5.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-5-2)

### Description

Chaque bouton associ&eacute; &agrave; une image (balise `input` avec l'attribut `type="image"`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> v&eacute;rifie-t-il une de ces conditions ? 
 
 * Il existe une autre forme de <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> non graphique, au moins  
 * Il existe une autre solution d'acc&egrave;s &agrave; la fonctionnalit&eacute; s&eacute;curis&eacute;e par le <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> 

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

[TestCases files for rule 1.5.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010502/) 


