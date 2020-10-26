# RGAA 4.0 — Rule 1.1.8

## Summary

This test consists in checking whether each `<canvas>` tag , carrying information, owns a textual alternative.

## Business description

### Criterion

[1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-1)

### Test

[1.1.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-8)

### Description

> Chaque image bitmap (balise `<canvas>`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), vérifie-t-elle une de ces conditions ?
> 
> * La balise `<canvas>` possède une alternative textuelle
> * Un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif) est présent entre les balises `<canvas>` et `</canvas>`
> * L’élément `<canvas>` est immédiatement suivi d’un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent) permettant d’accéder à un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif).
> * Un mécanisme permet à l’utilisateur de remplacer l’élément `<canvas>` par un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif).

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

All the `<canvas>` tags, not within a link and not identified as captcha (see Notes about captcha detection)

#### Set2

All the elements of **Set1** identified as informative image by marker usage 
(see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage 
(see Notes for details about detection through marker)

### Process

##### Test1

For each element of **Set2**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of false-result of **Test1**, raise a MessageA.

##### Test2

For each element of **Set3**, check whether the tag owns a textual alternative (see Notes for details about textual alternative detection). 

For each occurrence of true-result of **Test2**, raise a MessageB.

For each occurrence of false-result of **Test2**, raise a MessageC.

#### Messages

##### MessageA 

-    code : **CheckPresenceOfAlternativeMechanismForInformativeImage** 
-    status: Failed
-    parameter : tag text, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute
-    present in source : yes

##### MessageB 

-    code : **CheckNatureOfElementWithTextualAlternative** 
-    status: Pre-Qualified
-    parameter : tag text, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute
-    present in source : yes

##### MessageC

-    code : **CheckNatureOfElementWithoutTextualAlternative** 
-    status: Pre-Qualified
-    parameter : tag text, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute
-    present in source : yes

### Analysis

#### Not Tested

The page has no `<canvas>` (**Set1** is empty)

#### Failed

At least one `<canvas>` tag, identified as informative, has no textual alternative 
(**Test1** returns false for at least one element)

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

- [TestCases files for rule 1.1.8](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010108/)
- [Unit test file for rule 1.1.8](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010108Test.java)
- [Class file for rule 1.1.8](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010108.java)


