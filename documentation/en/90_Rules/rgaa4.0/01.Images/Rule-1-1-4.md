# RGAA 4.0 — Rule 1.1.4

## Summary

This test consists in checking whether each `<area>` of a server image map is doubled by a link in the page.

## Business description

### Criterion

[1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-1)

### Test

[1.1.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-4)

### Description

> Chaque zone cliquable d’une image réactive côté serveur est-t-elle doublée d’un lien dans la page ?

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

All the `<img>` tags with an `"ismap"` attribute and all the `<input>` tags with a `"type"` attribute equals to "image" and an `"ismap"` attribute (css selector : `img[ismap],input[type=image][ismap]`)

### Process

For each element of Set1, produce a MessageA

#### Messages

##### MessageA : Check a link is associated with the server-sided image map

-    code : CheckALinkIsAssociatedWithTheServerSidedImageMap
-    status: Pre-qualified (NMI)
-    case : For each element of Set1
-    parameter : `"alt"` attribute, `"src"` attribute, tag name
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tag with a `"ismap"` attribute (**Set1** is empty)

#### Pre-qualified

**Set1** is not empty

## Notes

We only detect the elements of the **Set1** to determine whether the test is applicable


## Files

- [TestCases files for rule 1.1.4](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010104/)
- [Unit test file for rule 1.1.4](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010104Test.java)
- [Class file for rule 1.1.4](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010104.java)


