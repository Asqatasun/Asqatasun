# RGAA 3.0 -  Rule 1.6.4

## Summary

This test consists in detecting informative image form inputs and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-6)

###Test

[1.6.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-6-4)

### Description

Chaque bouton de formulaire de type image (balise `input` avec l'attribut `type="image"`), qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-il une de ces conditions ? 
 
 * Il existe un attribut `alt` contenant la r&eacute;f&eacute;rence &agrave; une description d&eacute;taill&eacute;e adjacente &agrave; l'image 
 *  Il existe un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLienAdj">lien adjacent</a> (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a> 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

##### Set1

All the `<input>` tags with the "type" attribute equals to "image" and not identified as captcha (see Notes about captcha detection) (css selector : `input[type=image]`)

#### Set2

All the elements of **Set2** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set2

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check detailed description definition of informative images

-    code : **CheckLongdescDefinitionOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description definition

-    code : **CheckNatureOfImageAndLongdescDefinition** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no image form input (**Set1** is empty)

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
