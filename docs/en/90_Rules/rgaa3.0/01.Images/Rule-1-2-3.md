# RGAA 3.0 -  Rule 1.2.3

## Summary

This test consists in checking whether the textual alternative of each decorative object image is empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-2)

### Test

[1.2.3](http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-2-3)

### Description

Pour chaque image objet sans <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mLegendeImage">l&eacute;gende</a> (balise `object` avec l'attribut `type="image/..."`) non porteuse d'information, l'alternative textuelle entre `<object>` et `</object>` est-elle vide ?

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

All the `<object>` tags with a `"type"` attribute equals to "image", not within a link and not identified as captcha (see Notes about captcha detection) (css selector : `object[type^=image]:not(a object)`)

#### Set2

All the elements of **Set1** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests

##### Test1

For each element of **Set1**, Check the presence of text between `<object>` tags.

For each occurrence of true-result of **Test1**, raise a MessageA

##### Test2

For each element of **Set2**, Check the presence of text between `<object>` tags.

For each occurrence of true-result of **Test2**, raise a MessageB

For each occurrence of false-result of **Test2**, raise a MessageC

#### Messages

##### MessageA : Decorative image with not empty alternative

-    code : DecorativeElementWithNotEmptyAltAttribute
-    status: Failed
-    parameter : `"data"` attribute, text, Snippet
-    present in source : yes

##### MessageB : Check the nature of the image with a not empty alternative

-    code : CheckNatureOfElementWithNotEmptyAltAttribute
-    status: Pre-qualified
-    parameter : text, Snippet
-    present in source : yes

##### MessageC : Check the nature of the image with a empty alternative

-    code : CheckNatureOfElementWithEmptyAltAttribute
-    status: Pre-qualified
-    parameter : text, Snippet
-    present in source : yes

### Analysis

#### Passed

All the `<object type="image>` tags are identified as decorative and don't have text between `<object>` tags (**Test1** returns false for all elements)

#### Failed

At least one `<object type="image>` identified as decorative have text between `<object>` tags (**Test1** returns true at least one element)

#### Not Applicable

The page has no `<object type="image>` tag (**Set1** is empty)

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
