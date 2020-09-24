# RGAA 4.0 — Rule 7.3.1

## Summary

No-check rule

## Business description

### Criterion

[7.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-7-3)

### Test

[7.3.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-3-1)

### Description

> Chaque élément possédant un gestionnaire d’événement contrôlé par un script vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * L’élément est [accessible par le clavier et tout dispositif de pointage](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#accessible-et-activable-par-le-clavier-et-tout-dispositif-de-pointage).
> * Un élément [accessible par le clavier et tout dispositif de pointage](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#accessible-et-activable-par-le-clavier-et-tout-dispositif-de-pointage) permettant de réaliser la même action est présent dans la page.

#### Particular cases (criterion 7.3)

> Il existe une gestion de cas particulier lorsque la fonctionnalité dépend de l’utilisation d’un gestionnaire d’événement sans équivalent universel ; par exemple, une application de dessin à main levée ne pourra pas être rendue contrôlable au clavier. Dans ces situations, le critère est non applicable.

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

- [TestCases files for rule 7.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule070301/)
- [Unit test file for rule 7.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070301Test.java)
- [Class file for rule 7.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070301.java)


