# RGAA 4.0 — Rule 1.2.1

## Summary

This test consists in checking whether the textual alternative of each decorative image is empty or whether aria 
attributes are well-used to make them ignored by assistive technologies.

## Business description

### Criterion

[1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-2)

### Test

[1.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-1)

### Description

> Chaque image (balise `<img>`) [de décoration](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-de-decoration), sans [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), vérifie-t-elle une de ces conditions ?
> 
> * La balise `<img>` possède un attribut alt vide (`alt=""`) et est dépourvue de tout autre attribut permettant de fournir une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image).
> * La balise `<img>` possède un attribut WAI-ARIA `aria-hidden="true"` ou `role="presentation"`.

#### Technical notes (criterion 1.2)

> Lorsqu’une image est associée à une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), la note technique WCAG recommande de prévoir systématiquement une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) (cf. [critère 1.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-9)). Dans ce cas le critère 1.2 est non applicable.
> 
> Dans le cas d’une image vectorielle (balise `<svg>`) de décoration qui serait affichée au travers d’un élément `<use href="...">` enfant de l’élément `<svg>`, le test 1.2.4 s’appliquera également à la balise `<svg>` associée par le biais de la balise `<use>`.
> 
> Un attribut WAI-ARIA `role="presentation"` peut être utilisé sur les images de décoration et les zones non cliquables de décoration. Le rôle `"none"` introduit en ARIA 1.1 et synonyme du rôle `"presentation"` peut être aussi utilisé. Il reste préférable cependant d’utiliser le rôle `"presentation"` en attendant un support satisfaisant du rôle `"none"`.

### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable with marker**

## Algorithm

### Selection

#### Set1

All the `<img>` tags, not within a link, not identified as captcha (see Notes about captcha detection) and without legend with an empty 
textual alternative

css-selector : `img[alt~=^$]:not([usemap]):not(a img):not([title]):not([aria-label]):not([aria-labelledby]):not([aria-hidden=true]):not([role=presentation]):not(figure:has(figcaption) img)`

#### Set2

All the elements of **Set1** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

#### Set4

All the `<img>` tags, not within a link, not identified as captcha (see Notes about captcha detection) and without legend  
with an aria mechanism to make them ignored by assistive technologies 

css-selector : `img[role=presentation]:not([usemap]):not(a img):not(figure:has(figcaption) img),img[aria-hidden=true]:not([usemap]):not(a img):not(figure:has(figcaption) img)`

#### Set5

All the elements of **Set4** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set6

All the elements of **Set4** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

#### Set7

All the `<img>` tags, not within a link, not identified as captcha (see Notes about captcha detection) and without legend,  
without any an aria mechanism to make them ignored by assistive technologies and with any attribute to set a textual alternative.
 
css-selector : `"img[title]:not([usemap]):not(a img):not([aria-hidden=true]):not([role=presentation]):not(figure:has(figcaption) img),
                 img[aria-label]:not([usemap]):not(a img):not([aria-hidden=true]):not([role=presentation]):not(figure:has(figcaption) img),
                 img[aria-labelledby]:not([usemap]):not(a img):not([aria-hidden=true]):not([role=presentation]):not(figure:has(figcaption) img),
                 img[alt]:not([usemap]):not(a img):not([alt~=^\\s*$]):not([aria-hidden=true]):not([role=presentation]):not(figure:has(figcaption) img)`

#### Set8

All the elements of **Set7** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set9

All the elements of **Set7** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests 

##### Test1

For each element of **Set3**, raise a MessageA.

##### Test2

For each element of **Set6**, raise a MessageB.

##### Test3

For each element of **Set8**, raise a MessageC.

##### Test4

For each element of **Set9**, raise a MessageD.  

#### Messages

##### MessageA

-    code : **CheckNatureOfElementWithoutTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"role"` attribute, `"src"` attribute 
-    present in source : yes

##### MessageB

-    code : **CheckNatureOfElementHiddenWithAria** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"role"` attribute, `"src"` attribute 
-    present in source : yes

##### MessageC 

-    code : **DecorativeElementWithNotEmptyTextualAlternative** 
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"role"` attribute, `"src"` attribute 
-    present in source : yes

##### MessageD

-    code : **CheckNatureOfElementWithTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"role"` attribute, `"src"` attribute 
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag (**Set1**, **Set4** and **Set7** are empty) or all the images are marked as informative

#### Passed 

The page only contains images marked as decorative, with an empty textual alternative or with an aria mechanism to make 
them ignored by assistive technologies :  (**Set2** or **Set5** are not empty AND **Set3**, **Set6**, **Set8**, **Set9** are empty)

#### Failed

At least one `<img>` tag identified as decorative, has a not empty textual alternative (**Set8** is not empty)

#### Pre-qualified

In all other cases

## Notes

### Textual alternative detection

The textual alternative can be set by the presence of any the following elements : 

* Text associated via the `aria-labelledby` WAI-ARIA attribute 
* Presence of an `aria-label` WAI-ARIA attribute
* Presence of an `alt` attribute
* Presence of a `title` attribute

That order has to be respected to compute the textual alternative.

For instance, if an `aria-label` WAI-ARIA attribute and an `alt` attribute are both present, 
the content of the `aria-label` WAI-ARIA attribute is considered as the textual alternative.

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

## Files

- [TestCases files for rule 1.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010201/)
- [Unit test file for rule 1.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010201Test.java)
- [Class file for rule 1.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010201.java)


