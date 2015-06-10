# Rule 1.2.4

## Summary

This test consists in checking whether the ARIA attribute of each decorative vector image (`<svg>` tag) are implemented correctly and checking each decorative vector image or children have no `"title"` or `"desc"` attribute unless they are empty.

## Business description

### Criterion

[1.2](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#crit-1-2)

###Test

[1.2.4](http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-1-2-4)

### Description

Chaque image vectorielle de d&eacute;coration (balise `<svg>`) non porteuse d'information et poss&eacute;dant une alternative v&eacute;rifie-t-elle ces conditions ? 
 
 * La balise `<svg>` poss&egrave;de un `role="img"` 
 * La balise `<svg>` ou l'un de ses enfants est d&eacute;pourvue de role, propri&eacute;t&eacute; ou &eacute;tat ARIA visant &agrave; labelliser l'image vectorielle (`aria-label`, `aria-describedby`, `aria-labelledby` par exemple). 
 * Les balises `<title>` et `<desc>` sont absentes ou vides 
 * La balise `<svg>` ou l'un de ses enfants est d&eacute;pourvue d'attribut `title` 

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semidecidable**

## Algorithm

### Selection

#### Set1

All the `<svg>` tags of the page (css selector : `svg`) and with an `id` attribute or a `class` attribute that matches one of the values set by the user through the `"DECORATIVE_SVG_MARKER"` parameter.

#### Set2

All the `<svg>` tags of the page (css selector : `svg`) that don't have an `id` attribute or a `class` attribute that matches one the values set by the use through the `"DECORATIVE_SVG_MARKER"` parameter or the `"INFORMATIVE_SVG_MARKER"` parameter. 
That means select all the `<svg>` tags not within an `<a>` tag when these parameters are empty.

#### Set3

All the elements of set1 with a `"role"` attribute with value `"img"` 

#### Set4

All the elements of set2 with a `"role"` attribute with value `"img"` 

#### Set5

All the `<title>` or  `<desc>` tags who are the children of the Set1 elements 

#### Set6

All the `<title>` or  `<desc>` tags who are the children of the Set2 elements 

### Process

#### Tests

##### Test1

For each element of Set1, Check the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test1, raise a MessageA

##### Test2 

Test if elements of Set2 and children of Set2 elements don't have `aria-label`, `aria-describedby` or `aria-labelledby` attribute

For each occurrence of false-result of Test2, raise a MessageB

##### Test3 

Test if elements of Set3 are empty

For each occurrence of false-result of Test3, raise a MessageC

##### Test4 

Test if elements of Set2 and children of Set2 elements don't have `title` attribute

For each occurrence of false-result of Test4, raise a MessageD

#### Messages

##### MessageA : Suspected decorative svg Missing role img attribute

-    code : SuspectedDecorativeSvgRoleImgMissing
-    status: NMI
-    parameter : tag name
-    present in source : yes

##### MessageB : Suspected decorative svg or children with `aria-label`, `aria-describedby` or `aria-labelledby` attribute

-    code : SuspectedDecorativeSvgWithNotEmptyAriaAttribute
-    status: NMI
-    case : For each element returning false in Test2
-    parameter : tag name
-    present in source : yes

##### MessageC : Suspected decorative svg with not empty `<title>` or  `<desc>` tags

-    code : SuspectedDecorativeSvgWithNotEmptyTitleOrDescTags
-    status: NMI
-    case : For each element returning false in Test3
-    parameter : tag name
-    present in source : yes

##### MessageD : Suspected decorative svg or children with `title` attribute

-    code : SuspectedDecorativeSvgWithTitleAttribute
-    status: NMI
-    case : For each element returning false in Test4
-    parameter : tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag (Set1 is empty)

#### Pre-qualified

In all other cases






