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

All the `<title>` or  `<desc>` tags who are the children of the Set3 elements 

#### Set6

All the `<title>` or  `<desc>` tags who are the children of the Set4 elements 

### Process

#### Tests

##### Test1

For each element of Set1, Check the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test1, raise a MessageA

##### Test2

For each element of Set2, Check the presence of the `"role"` attribute with value `"img"`.

For each occurrence of false-result of Test2, raise a MessageB

##### Test3 

For each element of Set3 and children of Set3 elements don't have `aria-label`, `aria-describedby` or `aria-labelledby` attribute

For each occurrence of false-result of Test3, raise a MessageC

##### Test4 

For each element of Set4 and children of Set4 elements don't have `aria-label`, `aria-describedby` or `aria-labelledby` attribute

For each occurrence of false-result of Test4, raise a MessageD

##### Test5 

For each element of Set5 don't have `<title>` or `<desc>` tags or they are empty

For each occurrence of false-result of Test5, raise a MessageE

##### Test6 

For each element of Set6 don't have `<title>` or `<desc>` tags or they are empty

For each occurrence of false-result of Test6, raise a MessageF

##### Test7 

For each element of Set3 and children of Set3 elements don't have `title` attribute

For each occurrence of false-result of Test7, raise a MessageG

##### Test8 

Test if elements of Set4 and children of Set4 elements don't have `title` attribute

For each occurrence of false-result of Test8, raise a MessageH

#### Messages

##### MessageA : Decorative svg without role img attribute 

-    code : DecorativeSvgWithoutRoleImgAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Suspected decorative svg without role img attribute 

-    code : SuspectedDecorativeSvgWithoutRoleImgAttribute
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageC : Decorative svg or children with Aria attribute

-    code : DecorativeSvgOrChildrenWithAriaAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageD : Suspected decorative svg or children with Aria attribute

-    code : SuspectedDecorativeSvgOrChildrenWithAriaAttribute
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageE : Decorative svg without empty `<title>` or  `<desc>` tags

-    code : DecorativeSvgWithNotEmptyTitleOrDescTags
-    status: Failed
-    parameter : tag name
-    present in source : yes

##### MessageF : Suspected decorative svg without empty `<title>` or  `<desc>` tags

-    code : SuspectedDecorativeSvgWithNotEmptyTitleOrDescTags
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageG : Decorative svg or children with `title` attribute

-    code : DecorativeSvgWithTitleAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageH : Suspected decorative svg or children with `title` attribute

-    code : SuspectedDecorativeSvgWithTitleAttribute
-    status: NMI
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag (Set1 and Set2 are empty)

#### Failed

Test1, Test3, Test5, Test7 returns false for at least one element (one svg identified as decorative and don't have role img or have `aria-label`, `aria-describedby`, `aria-labelledby` or `title` attribute or have `<title>` or  `<desc>` tags not empty)

#### Passed

Test1, Test3, Test5, Test7 returns true for all elements (all svg identified as decorative and have role img and without `aria-label`, `aria-describedby`, `aria-labelledby` or `title` attribute and without `<title>` or  `<desc>` tags or they are empty)

#### Pre-qualified

In all other cases
