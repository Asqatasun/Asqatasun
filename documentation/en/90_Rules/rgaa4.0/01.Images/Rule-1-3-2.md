# RGAA 4.0 - Rule 1.3.2

## Summary
No-check rule


## Business description

### Criterion
[1.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-3)

### Test
[1.3.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-3-2)

### Description
> Pour chaque [zone](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-d-une-image-reactive) (balise `<area>`) d’une [image réactive](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-reactive) [porteuse d’information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-porteuse-d-information), ayant une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image), cette alternative est-elle pertinente (hors cas particuliers) ?
> 
> * S’il est présent, le contenu de l’attribut `alt` est pertinent.
> * S’il est présent, le contenu de l’attribut `title` est pertinent.
> * S’il est présent, le contenu de l’attribut WAI-ARIA `aria-label` est pertinent.
> * S’il est présent, le [passage de texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#passage-de-texte-lie-par-aria-labelledby-ou-aria-describedby) associé via l’attribut WAI-ARIA `aria-labelledby` est pertinent.

#### Particular cases (criterion 1.3)
> Il existe une gestion de cas particuliers lorsque l’image est utilisée comme [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) ou comme [image-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test). Dans cette situation, où il n’est pas possible de donner une alternative pertinente sans détruire l’objet du CAPTCHA ou du test, le critère est non applicable.
> 
> Note : le cas des CAPTCHA et des [images-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test) est traité de manière spécifique par le [critère 1.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-4).

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

[TestCases files for rule 1.3.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40//Rgaa40Rule010302/)


