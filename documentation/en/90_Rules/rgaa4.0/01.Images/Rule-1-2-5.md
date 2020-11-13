# RGAA 4.0 — Rule 1.2.5

## Summary

This test consists in checking whether the textual alternative of each `<canvas>` tag is empty or whether aria 
attributes are well-used to make them ignored by assistive technologies.

## Business description

### Criterion

[1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-2)

### Test

[1.2.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-5)

### Description

> Chaque image bitmap (balise `<canvas>`) [de décoration](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-de-decoration), sans [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), vérifie-t-elle ces conditions ?
> 
> * La balise `<canvas>` possède un attribut WAI-ARIA `aria-hidden="true"`.
> * La balise `<canvas>` et ses enfants sont dépourvus d’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image).
> * Il n’y a aucun texte faisant office d’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) entre `<canvas>` et `</canvas>`.

#### Technical notes (criterion 1.2)

> Lorsqu’une image est associée à une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), la note technique WCAG recommande de prévoir systématiquement une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) (cf. [critère 1.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-9)). Dans ce cas le critère 1.2 est non applicable.
> 
> Dans le cas d’une image vectorielle (balise `<canvas>`) de décoration qui serait affichée au travers d’un élément `<use href="...">` enfant de l’élément `<canvas>`, le test 1.2.4 s’appliquera également à la balise `<canvas>` associée par le biais de la balise `<use>`.
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

### Selection

#### Set1

All the `<canvas>` tags not identified as captcha (see Notes about captcha detection) and without legend, 
with an empty textual alternative and without aria mechanism to make them ignored by assistive technologies 

css-selector : `canvas[aria-hidden=true]:not(a canvas):not([title]):not([aria-label]):not([aria-labelledby]):not(figure:has(figcaption) canvas)`

#### Set2

All the elements of **Set1** identified as decorative by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative, nor as decorative by marker usage 
(see Notes for details about detection through marker)

#### Set4

All the `<canvas>` not within a link and not identified as captcha (see Notes about captcha detection)

css-selector : `canvas:not(a canvas):not(figure:has(figcaption) canvas)`

#### Set5

All the elements of **Set1** are removed from **Set4** : 

**Set5** = **Set4** - **Set1**

In other words, we keep here all the elements with a not empty textual alternative or without aria mechanism to make 
them ignored by assistive technologies. 

#### Set6

All the elements of **Set5** identified as decorative by marker usage (see Notes for details about detection through marker)

#### Set7

All the elements of **Set5** identified neither as informative, nor as decorative by marker usage (see Notes for details about detection through marker)

### Process

##### Test1

For each element of **Set3**, raise a MessageA.

##### Test2

For each element of **Set6**, raise a MessageB.

##### Test3

For each element of **Set7**, raise a MessageC.

#### Messages

##### MessageA

-    code : **CheckNatureOfElementWithoutTextualAlternative** 
-    status: Pre-Qualified
-    parameter : text, `"aria-label"` attribute, `"computed accessible name"` 
-    present in source : yes

##### MessageB

-    code : **DecorativeElementWithNotEmptyTextualAlternative** 
-    status: Failed
-    parameter : text, `"aria-label"` attribute, `"computed accessible name"` 
-    present in source : yes

##### MessageC

-    code : **CheckNatureOfElementWithTextualAlternative** 
-    status: Pre-Qualified
-    parameter : text, `"aria-label"` attribute, `"computed accessible name"` 
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<canvas>` tag (**Set1** and **Set4** are empty) or they are all marked as informative

#### Passed 

The page only contains `<canvas>` tags marked as decorative, with an empty textual alternative or with an aria mechanism to make 
them ignored by assistive technologies :  (**Set2** is not empty AND **Set3** and **Set5** are empty)

#### Failed

At least one `<canvas>` tag identified as decorative, has a not empty textual alternative (**Set6** is not empty)

#### Pre-qualified

In all other cases

## Notes

### Textual alternative detection

The textual alternative can be set by the presence of any the following elements : 

* Text associated via the `aria-labelledby` WAI-ARIA attribute 
* Presence of an `aria-label` WAI-ARIA attribute
* Presence of text within the tag

That order has to be respected to compute the textual alternative.

For instance, if an `aria-label` WAI-ARIA attribute and some text within the tag are both present, 
the content of the `aria-label` WAI-ARIA attribute is considered as the textual alternative.

If none of these elements are present, two other conditions exist to set the textual alternative :
 
* A link, or a button is present just before or just after the element
* A mechanism exists to let the user replace the element by an alternative content.

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

- [TestCases files for rule 1.2.5](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010205/)
- [Unit test file for rule 1.2.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010205Test.java)
- [Class file for rule 1.2.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010205.java)


