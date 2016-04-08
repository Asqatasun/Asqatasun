# RGAA 3.0 -  Rule 1.4.9

## Summary

This test consists in detecting captcha canvas and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is well rendered by assistive technologies.

## Business description

### Criterion

[1.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-4)

###Test

[1.4.9](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-4-9)

### Description

Pour chaque image bitmap (balise `canvas`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme image-test et ayant une alternative textuelle , l'alternative textuelle est-elle <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mRestitutionCorrecte">correctement restitu&eacute;e</a> par les technologies d'assistance ?

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

All the `<canvas>` tags of the page not within a link with a not empty content (css selector : canvas:not(a canvas):not(:matchesOwn(^\\s*$)))

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative restitution by assistive technologies

-    code : **CheckAtRestitutionOfAlternativeOfCaptcha** 
-    status: Pre-Qualified
-    parameter : text tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<canvas>` tag with a not empty content identified as a captcha (**Set2** is empty)

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
