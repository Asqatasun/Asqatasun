# RGAA 4.0 — Rule 1.1.7

## Summary

No-check rule

## Business description

### Criterion

[1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-1)

### Test

[1.1.7](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-7)

### Description

> Chaque image embarquée (balise `<embed>` avec l’attribut `type="image/…"`) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), vérifie-t-elle une de ces conditions ?
> 
> * La balise `<embed>` possède une alternative textuelle
> * L’élément `<embed>` est immédiatement suivi d’un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent) permettant d’accéder à un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif).
> * Un mécanisme permet à l’utilisateur de remplacer l’élément `<embed>` par un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif).

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

- [TestCases files for rule 1.1.7](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010107/)
- [Unit test file for rule 1.1.7](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010107Test.java)
- [Class file for rule 1.1.7](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010107.java)


