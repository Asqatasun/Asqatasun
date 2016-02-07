# RGAA 3.0 -  Rule 1.8.5

## Summary

This test consists in detecting informative embedded images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.8](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-8)

### Test

[1.8.5](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-8-5)

### Description

Chaque image texte embarqu&eacute;e (balise `embed` avec l'attribut `type="image/..."`) porteuse d'information, en l'absence d'un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mMecaRempl">m&eacute;canisme de remplacement</a>, doit si possible &ecirc;tre remplac&eacute;e par du <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTexteStyle">texte styl&eacute;</a>. Cette r&egrave;gle est-elle respect&eacute;e (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit1-8" title="Cas particuliers pour le crit&egrave;re 1.8">hors cas particuliers</a>) ?

### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<embed>` tags with a `"type"` attribute that starts with "image/..."  not identified as captcha (see Notes about captcha detection)  (embed[type^=image])

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA

#### Test2

For each element of **Set3**, raise a MessageB

##### MessageA : Check text styled presence of informative image

-    code : **CheckStyledTextPresenceOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and text styled presence

-    code : **CheckNatureOfImageAndStyledTextPresence** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no embedded image (**Set1** is empty)

#### Pre-Qualified

In all other cases

## Notes

### Markers 

**Informative images** markers are set through the **INFORMATIVE_IMAGE_MARKER** parameter.

**Decorative images** markers are set through the **DECORATIVE_IMAGE_MARKER** parameter.

The value(s) passed as marker(s) will be checked against the following attributes:

- `class`
- `id`
- `role`

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element
