# RGAA 4.0 — Rule 1.3.2

## Summary

This test consists in checking whether the textual alternative of each clickable area that convey information is relevant.

## Business description

### Criterion

[1.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-3)

### Test

[1.3.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-3-2)

### Description

> Pour chaque [zone](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-d-une-image-reactive) (balise `<area>`) d’une [image réactive](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-reactive) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), ayant une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image), cette alternative est-elle pertinente (hors cas particuliers) ?
> 
> * S’il est présent, le contenu de l’attribut `alt` est pertinent.
> * S’il est présent, le contenu de l’attribut `title` est pertinent.
> * S’il est présent, le contenu de l’attribut WAI-ARIA `aria-label` est pertinent.
> * S’il est présent, le [passage de texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#passage-de-texte-lie-par-aria-labelledby-ou-aria-describedby) associé via l’attribut WAI-ARIA `aria-labelledby` est pertinent.

#### Particular cases (criterion 1.3)

> Il existe une gestion de cas particuliers lorsque l’image est utilisée comme [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) ou comme [image-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test). Dans cette situation, où il n’est pas possible de donner une alternative pertinente sans détruire l’objet du CAPTCHA ou du test, le critère est non applicable.
> 
> Note : le cas des CAPTCHA et des [images-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test) est traité de manière spécifique par le [critère 1.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-4).

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

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute 
of an `<img>` tag, not within a link and not identified as captcha (see Notes about captcha detection) 

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

#### Set4

All the elements of **Set2** with a textual alternative (see Notes for details about textual alternative detection).

#### Set5

All the elements of **Set3** with a textual alternative (see Notes for details about textual alternative detection).

### Process

##### Test1

For each element of **Set4**, check whether the content of textual alternative is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test1**, raise a MessageA.

For each occurrence of false-result of **Test1**, raise a MessageB.

##### Test2

For each element of **Set5**, check whether the content of textual alternative is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test2**, raise a MessageC.

For each occurrence of false-result of **Test2**, raise a MessageD.

#### Messages

##### MessageA 

-    code : **NotPertinentAlt** 
-    status: Failed
-    parameter : `"alt"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"href"` attribute 
-    present in source : yes

##### MessageB 

-    code : **CheckPertinenceOfAltAttributeOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"href"` attribute 
-    present in source : yes

##### MessageC

-    code : **CheckNatureOfImageWithNotPertinentAlt** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute 
-    present in source : yes

##### MessageD

-    code : **CheckNatureOfImageAndAltPertinence** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"href"` attribute 
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<area>` tag of an image map with a textual alternative (**Set2** is empty)

#### Failed

At least one `<area>` tag, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute 
of an `<img>` tag, identified as informative, has a textual alternative that is not pertinent 
(**Test1** returns false for at least one element)

#### Pre-qualified

In all other cases

### Notes

### Textual alternative detection

The textual alternative can be set by the presence of any the following elements : 

* Text associated via the `aria-labelledby` WAI-ARIA attribute 
* Presence of an `aria-label` WAI-ARIA attribute
* Presence of an `alt` attribute

That order has to be respected to compute the textual alternative.

For instance, if an `aria-label` WAI-ARIA attribute and an `alt` attribute are both present, 
the content of the `aria-label` WAI-ARIA attribute is considered as the textual alternative.

### Textual alternative relevancy 

The content of the textual alternative is seen as not relevant if :

- empty
- only composed of non-alphanumerical characters
- it has an extension of image type (loaded by the nomenclature named **ImageFileExtensions** composed of : jpg, gif, jpeg, png, bmp)

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

- [TestCases files for rule 1.3.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010302/)
- [Unit test file for rule 1.3.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010302Test.java)
- [Class file for rule 1.3.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010302.java)


