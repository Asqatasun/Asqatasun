# RGAA 4.0 — Rule 1.7.4

## Summary

No-check rule

## Business description

### Criterion

[1.7](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-7)

### Test

[1.7.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-7-4)

### Description

> Chaque image embarquée (balise `<embed>` avec l’attribut type=`"image/…"`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), ayant une [description détaillée](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#description-detaillee-image), vérifie-t-elle ces conditions ?
> 
> * La [description détaillée](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#description-detaillee-image) dans la page et signalée par l’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) est pertinente.
> * La [description détaillée](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#description-detaillee-image) adjacente à l’image embarquée est pertinente.
> * La [description détaillée](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#description-detaillee-image) via un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent) est pertinente.
> * Le [passage de texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#passage-de-texte-lie-par-aria-labelledby-ou-aria-describedby) associé via l’attribut WAI-ARIA `aria-describedby` est pertinent.

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

@@@TODO


## Algorithm

### Selection

None

### Process

None

### Analysis

#### Not Tested

In all cases


## Files

- [TestCases files for rule 1.7.4](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010704/)
- [Unit test file for rule 1.7.4](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010704Test.java)
- [Class file for rule 1.7.4](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010704.java)


