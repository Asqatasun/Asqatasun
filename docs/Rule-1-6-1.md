# Rule 1.6.1

## Summary

This test consists in detecting informative image.

Human check will be then needed to determine whether these detected elements have a detailed description if necessary.

## Business description

### Criterion

[1.6](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-6)

### Test

[1.6.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-6-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgInfo">image porteuse d'information</a> (balise `img`) qui n&eacute;cessite une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a>, v&eacute;rifie-t-elle une de ces conditions ? 
 
 * Il existe un attribut `longdesc` qui donne l'adresse (`url`) d'une page contenant la <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mDescDetaillee">description d&eacute;taill&eacute;e</a> 
 * Il existe un attribut `alt` contenant la r&eacute;f&eacute;rence &agrave; une description d&eacute;taill&eacute;e adjacente &agrave; l'image 
 * Il existe un lien adjacent (via une `url` ou une `ancre`) permettant d'acc&eacute;der au contenu de la description d&eacute;taill&eacute;e 

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

All the `<img>` tags of the page not within a link (css selector : `img:not(a img)`)

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA 

-    code : **CheckLongdescDefinitionOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"longdesc"` attribute, `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

#### Test2

For each element of **Set3**, raise a MessageB

##### MessageB 

-    code : **CheckNatureOfImageAndLongdescDefinition** 
-    status: Pre-Qualified
-    parameter : `"longdesc"` attribute, `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable:

The page has no `<img>` tags or only image links or only images identified as decorative by marker  (**Set1** is empty, or **Set2** AND **Set3** are empty)

#### Pre-qualified:

The page has at least one `<img>` tag that is not an image link and not identified as decorative by marker (**Set2** or **Set3** is not empty)

## Notes

**Informative images** markers are set through the *INFORMATIVE_IMAGE_MARKER* parameter.

**Decorative images** markers are set through the *DECORATIVE_IMAGE_MARKER* parameter.

The value(s) passed as marker(s) will be checked against the following attributes:

- `class`
- `id`
- `role`
