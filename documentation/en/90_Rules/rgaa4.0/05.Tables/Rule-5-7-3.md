# RGAA 4.0 - Rule 5.7.3

## Summary
No-check rule


## Business description

### Criterion
[5.7](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-5-7)

### Test
[5.7.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-7-3)

### Description
> Pour chaque contenu de balise `<th>` ne s’appliquant pas à la totalité de la ligne ou de la colonne, la balise `<th>` vérifie-t-elle ces conditions ?
> 
> * La balise `<th>` ne possède pas d’attribut `scope`.
> * La balise `<th>` ne possède pas d’attribut WAI-ARIA `role="rowheader"` ou `"columnheader"`.
> * La balise `<th>` possède un attribut `id` unique.

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

#### No Tested
In all cases


##  TestCases

[TestCases files for rule 5.7.3](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40//Rgaa40Rule050703/)


