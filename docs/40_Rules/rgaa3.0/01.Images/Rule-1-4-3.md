# Rule 1.4.3

## Summary

This test consists in detecting captcha form buttons and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-4)

###Test

[1.4.3](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-4-3)

### Description

Chaque bouton associ&eacute; &agrave; une image (balise `input` avec l'attribut `type="image"`) utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgTest">image-test</a>, ayant un attribut `alt` v&eacute;rifie-t-il ces conditions ? 
 
 * le contenu de l'attribut `alt` permet d'identifier la nature et la fonction du bouton 
 * s'il est pr&eacute;sent, le contenu de l'attribut `title` est identique au contenu de l'attribut `alt` 


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

All the `<input>` tags with the "type" attribute equals to "image" and an `"alt"` attribute (css selector : `input[alt][type=image]`)

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<input>` tag with an `"type"` attribute equals to "image" and an `"alt"` attribute identified as a captcha (**Set2** is empty)

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
