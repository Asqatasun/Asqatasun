# RGAA 4.0 - Rule 1.2.3

## Summary

No-check rule

## Business description

### Criterion

[1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-2)

### Test

[1.2.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-3)

### Description

> Chaque [image objet](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-objet) (balise `<object>` avec l’attribut `type="image/…"`) [de décoration](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-de-decoration), sans [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), vérifie-t-elle ces conditions ?
> 
> * La balise `<object>` possède un attribut WAI-ARIA `aria-hidden="true"`.
> * La balise `<object>` est dépourvue d’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image).
> * Il n’y a aucun texte faisant office d’[alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) entre `<object>` et `</object>`.

#### Technical notes (criterion 1.2)

> Lorsqu’une image est associée à une [légende](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#legende), la note technique WCAG recommande de prévoir systématiquement une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image) (cf. [critère 1.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-1-9)). Dans ce cas le critère 1.2 est non applicable.
> 
> Dans le cas d’une image vectorielle (balise `<svg>`) de décoration qui serait affichée au travers d’un élément `<use href="...">` enfant de l’élément `<svg>`, le test 1.2.4 s’appliquera également à la balise `<svg>` associée par le biais de la balise `<use>`.
> 
> Un attribut WAI-ARIA `role="presentation"` peut être utilisé sur les images de décoration et les zones non cliquables de décoration. Le rôle `"none"` introduit en ARIA 1.1 et synonyme du rôle `"presentation"` peut être aussi utilisé. Il reste préférable cependant d’utiliser le rôle `"presentation"` en attendant un support satisfaisant du rôle `"none"`.

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

- [TestCases files for rule 1.2.3](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010203/)
- [Unit test file for rule 1.2.3](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010203Test.java)
- [Class file for rule 1.2.3](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010203.java)


