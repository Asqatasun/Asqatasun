# RGAA 4.0 — Rule 8.7.1

## Summary

No-check rule

## Business description

### Criterion

[8.7](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-7)

### Test

[8.7.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-7-1)

### Description

> Dans chaque page web, chaque texte écrit dans une langue différente de la [langue par défaut](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#langue-par-defaut) vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * L’indication de langue est donnée sur l’élément contenant le texte (attribut `lang` et/ou `xml:lang`).
> * L’indication de langue est donnée sur un des éléments parents (attribut `lang` et/ou `xml:lang`).

#### Particular cases (criterion 8.7)

> Il y a une gestion de cas particuliers sur le changement de langue pour les cas suivants :
> 
> * Nom propre, le critère est non applicable ;
> * Nom commun de langue étrangère présent dans le dictionnaire officiel de la langue (voir note 1 ci-dessous) par défaut de la page web, le critère est non applicable ;
> * Le terme de langue étrangère soumis, via un [champ de formulaire](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#champ-de-saisie-de-formulaire) et rappelé dans la page (par exemple comme indication du terme recherché dans le cas d’un moteur de recherche), le critère est non applicable ;
> * Passage de texte dont la langue ne peut pas être déterminée : le critère est non applicable ;
> * Terme ou passage de texte issus d’une langue morte ou imaginaire pour laquelle il n’existe pas d’interprétation vocale: le critère est non applicable.
> 
> Note 1 : le dictionnaire officiel est celui recommandé par l’académie en charge de la langue en question. Pour la France, par exemple, le lien vers le dictionnaire officiel se trouve sur le site de l’Académie française à l’adresse suivante : [http://www.academie-francaise.fr/le-dictionnaire/la-9e-edition](http://www.academie-francaise.fr/le-dictionnaire/la-9e-edition). Pour toute demande auprès du service du dictionnaire de l’Académie française, utiliser le formulaire de contact du service du dictionnaire.
> 
> Note 2 : pour les noms communs de langue étrangère, absents dans le dictionnaire officiel de la langue par défaut de la page web, et qui sont passés dans le langage commun (exemple : newsletter) : le critère est applicable, uniquement lorsque l’absence d’indication de langue peut provoquer une incompréhension pour la restitution.

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

- [TestCases files for rule 8.7.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080701/)
- [Unit test file for rule 8.7.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080701Test.java)
- [Class file for rule 8.7.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080701.java)


