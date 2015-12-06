# Rule 1.4.6

## Summary

This test consists in detecting captcha svg and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-4)

###Test

[1.4.6](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-4-6)

### Description

Chaque image vectorielle (balise `svg`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgTest">image-test</a> et ayant une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mAltTexteImg">alternative textuelle</a> via la propri&eacute;t&eacute; `aria-label` ou la balise `desc` v&eacute;rifie-t-elle ces conditions ? 
 
 * l'alternative textuelle impl&eacute;ment&eacute;e via la propri&eacute;t&eacute; `aria-label` permet d'identifier la nature et la fonction de l'image et est identique &agrave; l'attribut `title` s'il est pr&eacute;sent 
 * l'alternative textuelle impl&eacute;ment&eacute;e via la balise `desc` permet d'identifier la nature et la fonction de l'image et est identique &agrave; l'attribut `title` de la balise `svg` s'il est pr&eacute;sent 


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

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
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
