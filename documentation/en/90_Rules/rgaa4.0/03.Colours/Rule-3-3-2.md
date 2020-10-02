# RGAA 4.0 — Rule 3.3.2

## Summary

No-check rule

## Business description

### Criterion

[3.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-3-3)

### Test

[3.3.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-3-3-2)

### Description

> Dans chaque page web, le rapport de [contraste](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contraste) des différentes couleurs composant un élément graphique, lorsqu’elles sont nécessaires à sa compréhension, et la [couleur d’arrière-plan contiguë](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#couleur-d-arriere-plan-contigue-et-couleur-contigue), vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * Le rapport de [contraste](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contraste) est de 3:1, au moins.
> * Un [mécanisme](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#mecanisme-qui-permet-d-afficher-un-rapport-de-contraste-conforme) permet un rapport de [contraste](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contraste) de 3:1, au moins.

#### Particular cases (criterion 3.3)

> Les cas suivants sont non applicables pour ce critère :
> 
> * Composant d’interface inactif (exemple un bouton avec un attribut `disabled`) sur lequel aucune action n’est possible.
> * Composant d’interface pour lequel l’apparence est gérée par les styles natifs du navigateur sans aucune modification par l’auteur (exemple le style au focus natif dans chrome ou firefox).
> * Composant d’interface pour lequel la couleur n’est pas nécessaire pour identifier le composant ou son état (exemple un groupe de liens faisant office de navigation dont la position dans la page, la taille et la couleur du texte permettent de comprendre qu’il s’agit de liens même si la couleur du soulignement des liens avec le fond blanc n’a pas un ratio de 3:1 et que le texte lui a un ratio de 4.5:1).
> * Élément graphique ou parties d’élément graphique non porteur d’information ou ayant une alternative (description longue, informations identiques visibles dans la page).
> * Élément graphique ou parties d’élément graphique faisant partie d’un logo ou du nom de marque d’un organisme ou d’une société.
> * Élément graphique ou parties d’élément graphique dont la présentation est essentielle à l’information véhiculée (exemple drapeaux, logotypes, photos de personnes ou de scènes, captures d’écran, diagrammes médicaux, carte de chaleurs).
> * Élément graphique ou parties d’élément graphique dynamiques dont le contraste au survol / focus est suffisant.

### Level

**AA**


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

- [TestCases files for rule 3.3.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule030302/)
- [Unit test file for rule 3.3.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030302Test.java)
- [Class file for rule 3.3.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030302.java)


