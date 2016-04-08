# RGAA 3.0 -  Rule 1.4.4

## Summary

This test consists in detecting captcha object images and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-4)

### Test

[1.4.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-4-4)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgObj">image objet</a> (balise `object` avec l'attribut `type="image/..."`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgTest">image-test</a>, et ayant une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mAltTexteImg">alternative textuelle</a>, l'<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mAltTexteImg">alternative textuelle</a> permet-elle d'identifier la nature et la fonction de l'image ?

### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1 

All the `<object>` tags with a `"type"` attribute that starts with "image/...", not within a link  (css selector : object[type^=image]:not(a object))

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
-    status: Pre-Qualified
-    parameter : text, `"data"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<object>` tag with an `"type"` attribute that starts with "image" identified as a captcha (**Set2** is empty)

#### Pre-qualified

In all other cases

## Notes

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element
