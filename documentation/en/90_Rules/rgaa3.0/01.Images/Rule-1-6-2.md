# RGAA 3.0 -  Rule 1.6.2

## Summary

This test consists in detecting informative object images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-6)

### Test

[1.6.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-6-2)

### Description

Chaque image objet porteuse d'information (balise `object` avec l'attribut `type="image/..."`), qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe un lien adjacent (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a> 
 * Il existe une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a> clairement identifiable adjacente &agrave; l'image objet 


### Level

**A**

## Technical description

### Scope

**page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<object>` tags with a `"type"` attribute that starts with "image/...", not within a link and not identified as captcha (see Notes about captcha detection)  (css selector : object[type^=image]:not(a object))

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check detailed description definition of informative images

-    code : **CheckLongdescDefinitionOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, `"data"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description definition

-    code : **CheckNatureOfImageAndLongdescDefinition** 
-    status: Pre-Qualified
-    parameter : text, `"data"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no object image (**Set1** is empty)

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
