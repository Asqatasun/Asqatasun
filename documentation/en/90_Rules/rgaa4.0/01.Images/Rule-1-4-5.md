# RGAA 4.0 — Rule 1.4.5

## Summary

This test consists in detecting captcha embedded images and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-4)

### Test

[1.4.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-4-5)

### Description

> Pour chaque image embarquée (balise `<embed>` avec l’attribut `type="image/…"`) utilisée comme [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) ou comme [image-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test), ayant une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) ou un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif), cette alternative est-elle pertinente ?
> 
> * S’il est présent, le contenu de l’attribut `title` est pertinent.
> * S’il est présent, le contenu de l’attribut WAI-ARIA `aria-label` est pertinent.
> * S’il est présent, le [passage de texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#passage-de-texte-lie-par-aria-labelledby-ou-aria-describedby) associé via l’attribut WAI-ARIA `aria-labelledby` est pertinent.
> * S’il est présent le [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif) est pertinent.

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

All the `<embed>` tags with a `type="image"` attribute, not within a link 

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

#### Set3

All the elements of **Set2** with a textual alternative (see Notes for details about textual alternative detection).

### Process

For each element of **Set3**, raise a MessageA

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
-    status: Pre-Qualified
-    parameter : `"title"` attribute, `"aria-label"` attribute, `"computed accessible name"`, `"src"` attribute 
-    present in source : yes

### Analysis

#### Not Tested

The page has no `<embed>` tag with a `type="image"` attribute with a textual alternative, identified as a captcha (**Set3** is empty)

#### Pre-qualified

In all other cases

## Notes

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element

### Textual alternative detection

The textual alternative can be set by the presence of any the following elements : 

* Text associated via the `aria-labelledby` WAI-ARIA attribute 
* Presence of an `aria-label` WAI-ARIA attribute
* Presence of a `title` attribute

That order has to be respected to compute the textual alternative.

For instance, if an `aria-label` WAI-ARIA attribute and an `title` attribute are both present, 
the content of the `aria-label` WAI-ARIA attribute is considered as the textual alternative.

If none of these elements are present, two other conditions exist to set the textual alternative :
 
* A link, or a button is present just before or just after the element
* A mechanism exists to let the user replace the element by an alternative content.

## Files

- [TestCases files for rule 1.4.5](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010405/)
- [Unit test file for rule 1.4.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010405Test.java)
- [Class file for rule 1.4.5](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010405.java)


