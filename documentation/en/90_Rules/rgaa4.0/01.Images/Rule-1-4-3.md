# RGAA 4.0 - Rule 1.4.3

## Summary

No-check rule

## Business description

### Criterion

[1.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-1-4)

### Test

[1.4.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-4-3)

### Description

> Pour chaque [bouton](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#bouton-formulaire) de type image (balise `<input>` avec l’attribut `type="image"`) utilisé comme [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha) ou comme [image-test](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-test), ayant une [alternative textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image), cette alternative est-elle pertinente ?
> 
> * S’il est présent, le contenu de l’attribut `alt` est pertinent.
> * S’il est présent, le contenu de l’attribut `title` est pertinent.
> * S’il est présent, le contenu de l’attribut WAI-ARIA `aria-label` est pertinent.
> * S’il est présent, le [passage de texte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#passage-de-texte-lie-par-aria-labelledby-ou-aria-describedby) associé via l’attribut WAI-ARIA `aria-labelledby` est pertinent.

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

- [TestCases files for rule 1.4.3](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule010403/)
- [Unit test file for rule 1.4.3](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010403Test.java)
- [Class file for rule 1.4.3](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule010403.java)


