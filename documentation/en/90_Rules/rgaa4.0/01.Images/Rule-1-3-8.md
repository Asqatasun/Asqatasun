# RGAA 4.0 — Rule 1.3.8

## Summary

This test consists in detecting informative canvas images and thus defining the applicability of the test.

## Business description

### Criterion

[1.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-3)

### Test

[1.3.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-3-8)

### Description

> Pour chaque image bitmap (balise `<canvas>`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information) et ayant un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif) entre `<canvas>` et `</canvas>`, ce [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif) est-il [correctement restitué par les technologies d’assistance](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#correctement-restitue-par-les-technologies-d-assistance) ?

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

@@@TODO

## Algorithm

### Selection

#### Set1

All the `<canvas>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection) (css selector : canvas:not(a canvas))

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check the pertinence of the alternative of the informative image

-    code : **CheckPertinenceOfAltAttributeOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and alternative presence and pertinence

-    code : **CheckNatureOfImageAndAltPertinence** 
-    status: Pre-Qualified
-    parameter : text, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no canvas image (**Set1** is empty)

#### Pre-Qualified

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

## Files

- [TestCases files for rule 1.3.8](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010308/)
- [Unit test file for rule 1.3.8](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010308Test.java)
- [Class file for rule 1.3.8](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010308.java)


