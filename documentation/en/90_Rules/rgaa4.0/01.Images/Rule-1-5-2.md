# RGAA 4.0 — Rule 1.5.2

## Summary

This test consists in detecting buttons associated with an image used as a CAPTCHA.

## Business description

### Criterion

[1.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-5)

### Test

[1.5.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-5-2)

### Description

> Chaque bouton associé à une image (balise `input` avec l’attribut `type="image"`) utilisée comme [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) vérifie-t-il une de ces conditions ?
> 
> * Il existe une autre forme de [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) non graphique, au moins.
> * Il existe une autre solution d’accès à la fonctionnalité sécurisée par le [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha).

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<input>` tags with a `"type"` attribute equals to "image" (css selector : `input[type=image]`) 

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA 

-    code : **CheckCaptchaAlternativeAccess** 
-    status: Pre-Qualified
-    parameter : tag name, snippet
-    present in source : yes

### Analysis

#### Pre-qualified

At least one `<input>` tags with a `"type"` attribute equals to "image" identified as a CAPTCHA has been found on the page (**Set2** is not empty)

#### Not Applicable

No `<input>` tag with a `"type"` attribute equals to "image" identified as a CAPTCHA has been found on the page (**Set2** is empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



## Files

- [TestCases files for rule 1.5.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010502/)
- [Unit test file for rule 1.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010502Test.java)
- [Class file for rule 1.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010502.java)


