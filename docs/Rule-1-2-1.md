# Rule 1.2.1

## Summary

This test consists in checking whether the `alt` attribute of each decorative image is empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-2)

### Test

[1.2.1](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgDeco">image de d&eacute;coration</a> sans <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLegendeImage">l&eacute;gende</a> (balise `img`) et ayant un attribut `alt` v&eacute;rifie-t-elle ces conditions : 
 
 * le contenu de l'attribut alt est vide (`alt=""`) 
 * l'image de d&eacute;coration ne poss&egrave;de pas d'attribut `title` 

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

All the `<img>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection) (css selector : `img:not(a img)`)

#### Set2

All the elements of **Set1** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests

##### Test1 

For each element of **Set2** (decorative `<img>` identified by a html marker), check that the content of the `"alt"` attribute is empty. 

For each element returning false in **Test1**, raise a MessageA.

##### Test2 

For each element of **Set2** (decorative `<img>` identified by a html marker), check that the `"title"` attribute is missing. 

For each element returning false in **Test2**, raise a MessageB. 

##### Test3

For each element of **Set3** (`<img>` tags not identified as decorative image), check that the content of the `"alt"` attribute empty. 

For each element returning false in **Test3**, raise a MessageC.
For each element returning true in **Test3**, raise a MessageD.

##### Test4 

For each element of **Set3** (decorative `<img>` identified by a html marker), check that the `"title"` attribute is missing. 

For each element returning false in **Test4**, raise a MessageC.
For each element returning true in **Test4**, raise a MessageD.

#### Messages

##### MessageA : Decorative image with not empty alternative

-    code : DecorativeElementWithNotEmptyAltAttribute
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, `"src"` attribute, Snippet
-    present in source : yes

##### MessageB : Decorative image with a title attribute

-    code : DecorativeElementWithTitleAttribute
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, `"src"` attribute, Snippet
-    present in source : yes

##### MessageC : Check the nature of the image with a not empty alternative

-    code : CheckNatureOfElementWithNotEmptyAltAttribute
-    status: Pre-qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"src"` attribute, Snippet
-    present in source : yes

##### MessageD : Check the nature of the image with a empty alternative

-    code : CheckNatureOfElementWithEmptyAltAttribute
-    status: Pre-qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"src"` attribute, Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with an `"alt"` attribute (**Set1** and **Set2** are empty)

#### Failed

At least one `<img>` identified as decorative has a not empty alternative (**Test1** OR **Test2** returns false for at least one element)

#### Passed

All `<img>` of the page are identified and all `img` identified as decorative have a empty alternative (**Set3** is empty and **Test1** AND **Test2** returns true for all element)

#### Pre-qualified

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
