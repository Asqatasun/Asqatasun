# RGAA 4.0 — Rule 1.1.1

## Summary

This test consists in checking whether each `<img>` tag or tag with is a `role="img"` WAI-ARIA attribute, carrying 
information, owns a textual alternative.

## Business description

### Criterion

[1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-1)

### Test

[1.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-1)

### Description

> Chaque image (balise `<img>` ou balise possédant l’attribut WAI-ARIA `role="img"`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information) a-t-elle une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) ?

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

All the `<img>` tags and tags with is a `role="img"` attribute, not within a link and not identified as captcha 
(see Notes about captcha detection) 

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests 

##### Test1

For each element of **Set2**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of false-result of **Test1**, raise a MessageA.

##### Test2

For each element of **Set3**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of true-result of **Test2**, raise a MessageB.

For each occurrence of false-result of **Test2**, raise a MessageC.

#### Messages

##### MessageA 

-    code : **NotPertinentAlt** 
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute 
-    present in source : yes

##### MessageB 

-    code : **CheckNatureOfElementWithTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute 
-    present in source : yes

##### MessageC

-    code : **CheckNatureOfElementWithoutTextualAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute 
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag or a tag with is a `role="img"`attribute (**Set1** is empty)

#### Failed

At least one `<img>` tag or one tag with is a `role="img"`attribute, identified as informative, has no textual alternative 
(**Test1** returns false for at least one element)

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

- [TestCases files for rule 1.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010101/)
- [Unit test file for rule 1.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010101Test.java)
- [Class file for rule 1.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010101.java)


