# RGAA 3.0 -  Rule 1.4.7

## Summary

This test consists in detecting captcha svg and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is well rendered by assistive technologies.

## Business description

### Criterion

[1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-4)

###Test

[1.4.7](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-4-7)

### Description

Pour chaque image vectorielle (balise `svg`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme image-test et ayant une alternative textuelle , l'alternative textuelle est-elle <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mRestitutionCorrecte">correctement restitu&eacute;e</a> par les technologies d'assistance ?

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

All the `<svg>` tags of the page not within a link with a not empty `"aria-label"` attribute or a not empty `<desc>` child tag  (css selector : svg[aria-label]:not([aria-label~=^\\s*$]:not(a svg), svg:not(a svg):has(desc:not(:matchesOwn(^\\s*$)))

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative restitution by assistive technologies

-    code : **CheckAtRestitutionOfAlternativeOfCaptcha** 
-    status: Pre-Qualified
-    parameter : `"title"` attribute, `"aria-label"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag with an `"aria-label"` attribute or a `<desc>` child tag identified as a captcha (**Set2** is empty)

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
