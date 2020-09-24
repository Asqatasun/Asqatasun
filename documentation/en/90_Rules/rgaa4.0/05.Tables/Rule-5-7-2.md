# RGAA 4.0 — Rule 5.7.2

## Summary

No-check rule

## Business description

### Criterion

[5.7](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-5-7)

### Test

[5.7.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-7-2)

### Description

> Pour chaque contenu de balise `<th>` s’appliquant à la totalité de la ligne ou de la colonne et possédant un attribut `scope`, la balise `<th>` vérifie-t-elle une de ces conditions ?
> 
> * La balise `<th>` possède un attribut scope avec la valeur `"row"` pour les [en-tête de lignes](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#en-tete-de-colonne-ou-de-ligne).
> * La balise `<th>` possède un attribut scope avec la valeur `"col"` pour les [en-tête de colonnes](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#en-tete-de-colonne-ou-de-ligne).

#### Particular cases (criterion 5.7)

> Dans le cas de tableaux de données ayant des en-têtes sur une seule ligne ou une seule colonne, les en-têtes peuvent être structurés à l’aide de balise `<th>` sans attribut `scope`.

#### Technical notes (criterion 5.7)

> Si l’attribut `headers` est implémenté sur une cellule déjà reliée à un en-tête (de ligne ou de colonne) avec l’attribut `scope` (avec la valeur `col` ou `row`), c’est l’en-tête ou les en-têtes référencés par l’attribut `headers` qui seront restitués aux technologies d’assistance. Les en-têtes reliés avec l’attribut `scope` seront ignorés.

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

- [TestCases files for rule 5.7.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule050702/)
- [Unit test file for rule 5.7.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050702Test.java)
- [Class file for rule 5.7.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050702.java)


