# RGAA 4.0 - Rule 9.2.1

## Summary
No-check rule


## Business description

### Criterion
[9.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-9-2)

### Test
[9.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-2-1)

### Description
> Dans chaque page web, la structure du document vérifie-t-elle ces conditions (hors cas particuliers) ?
> 
> * La [zone d’en-tête de la page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-d-en-tete) est structurée via une balise `<header>`.
> * Les [zones de navigation principales et secondaires](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#menu-et-barre-de-navigation) sont structurées via une balise `<nav>`.
> * La balise `<nav>` est réservée à la structuration des [zones de navigation principales et secondaires](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#menu-et-barre-de-navigation).
> * La [zone de contenu principal](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-de-contenu-principal) est structurée via une balise `<main>`.
> * La structure du document utilise une balise `<main>` visible unique.
> * La [zone de pied de page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-de-pied-de-page) est structurée via une balise `<footer>`.

#### Particular cases (criterion 9.2)
> Lorsque le doctype déclaré dans la page n’est pas le doctype HTML5, ce critère est non applicable.

#### Technical notes (criterion 9.2)
> La balise `<main>` peut être utilisée plusieurs fois dans le même document HTML. Néanmoins, il ne peut y avoir en permanence qu’une seule balise visible et lisible par les technologies d’assistances, les autres devant disposer d’un attribut `hidden` ou d’un style permettant de les masquer aux technologies d’assistances. A noter cependant que l’utilisation d’un style seul restera insuffisant pour assurer l’unicité d’une balise `<main>` visible en cas de désactivation des feuilles de styles.

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

#### No Tested
In all cases


##  TestCases

[TestCases files for rule 9.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40//Rgaa40Rule090201/)


