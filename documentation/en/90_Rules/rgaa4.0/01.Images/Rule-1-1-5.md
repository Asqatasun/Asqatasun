# RGAA 4.0 — Rule 1.1.5

## Summary

This test consists in checking whether each `<svg>` tag, carrying information, owns a textual alternative.

## Business description

### Criterion

[1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-1)

### Test

[1.1.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-5)

### Description

> Chaque image vectorielle (balise `<svg>`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), vérifie-t-elle ces conditions ?
> 
> * La balise `<svg>` possède un attribut WAI-ARIA `role="img"`.
> * La balise `<svg>` a une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image).

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

All the `<svg>` tags, not within a link and not identified as captcha (see Notes about captcha detection)

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

##### Test1

For each element of **Set2**, check whether the tag contains a `role="img"` attribute. 

For each occurrence of false-result of **Test1**, raise a MessageA.

##### Test2

For each element of **Set2**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of false-result of **Test2**, raise a MessageB.

##### Test3

For each element of **Set3**, check whether the tag contains a `role="img"` attribute. 

For each occurrence of true-result of **Test3**, raise a MessageC.

For each occurrence of false-result of **Test3**, raise a MessageD.

##### Test4

For each element of **Set3**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of true-result of **Test4**, raise a MessageE.

For each occurrence of false-result of **Test4**, raise a MessageF.

#### Messages

##### MessageA 

-    code : **AltMissing** 
-    status: Failed
-    parameter : `"role"` attribute, `"aria-label"` attribute, `"computed accessible name"`
-    present in source : yes

##### MessageB 

-    code : **CheckNatureOfElementWithTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"role"` attribute, `"aria-label"` attribute, `"computed accessible name"`, tag name
-    present in source : yes

##### MessageC

-    code : **CheckNatureOfElementWithoutTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"role"` attribute, `"aria-label"` attribute, `"computed accessible name"`, tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag (**Set1** is empty)

#### Failed

At least one `<svg>` tag, identified as informative, has no textual alternative (**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

## Notes

### Textual alternative detection

The textual alternative can be set by the presence of any the following elements : 

* Text associated via the `aria-labelledby` WAI-ARIA attribute 
* Presence of an `aria-label` WAI-ARIA attribute

That order has to be respected to compute the textual alternative.

For instance, if some text associated via the `aria-labelledby` WAI-ARIA attribute and an `aria-label` WAI-ARIA attribute 
are both present, the content of the text associated via the `aria-labelledby` WAI-ARIA attribute is considered as the textual alternative.

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

- [TestCases files for rule 1.1.5](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010105/)
- [Unit test file for rule 1.1.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010105Test.java)
- [Class file for rule 1.1.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010105.java)


