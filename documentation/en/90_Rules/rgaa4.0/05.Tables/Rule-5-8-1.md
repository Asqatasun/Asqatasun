# RGAA 4.0 - Rule 5.8.1

## Summary

No-check rule

## Business description

### Criterion

[5.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-5-8)

### Test

[5.8.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-8-1)

### Description

> Chaque [tableau de mise en forme](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#tableau-de-mise-en-forme) (balise `<table>`) vérifie-t-il ces conditions ?
> 
> * Le tableau de mise en forme (balise `<table>`) ne contient pas de balises `<caption>`, `<th>`, `<thead>`, `<tfoot>`, `<colgroup>` ou de balises ayant un attribut WAI-ARIA `role="rowheader"`, `role="columnheader"`.
> * Les cellules du tableau de mise en forme (balises `<td>`) ne possèdent pas d’attributs `scope`, `headers`, `axis`.

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

- [TestCases files for rule 5.8.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule050801/)
- [Unit test file for rule 5.8.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050801Test.java)
- [Class file for rule 5.8.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule050801.java)


