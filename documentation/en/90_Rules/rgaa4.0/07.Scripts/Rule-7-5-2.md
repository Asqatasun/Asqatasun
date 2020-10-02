# RGAA 4.0 — Rule 7.5.2

## Summary

No-check rule

## Business description

### Criterion

[7.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-7-5)

### Test

[7.5.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-5-2)

### Description

> Chaque [message de statut](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#message-de-statut) qui présente une suggestion, ou avertit de l’existence d’une erreur utilise-t-il l’attribut WAI-ARIA `role="alert"` ?

#### Technical notes (criterion 7.5)

> Les rôles WAI-ARIA `log`, `status` et `alert` ont implicitement une valeur d’attribut WAI-ARIA `aria-live` et `aria-atomic`. On pourra donc considérer (conformément à la spécification WAI-ARIA 1.1) que :
> 
> * Un attribut WAI-ARIA `aria-live="polite"` associé à un message de statut peut valoir pour un rôle WAI-ARIA `log`.
> * Un attribut WAI-ARIA `aria-live="polite"` et un attribut `aria-atomic="true"` associés à un message de statut peuvent valoir pour un rôle WAI-ARIA `status`.
> * Un attribut `aria-live="assertive"` et un attribut `aria-atomic="true"` associés à un message de statut peuvent valoir pour un rôle WAI-ARIA `alert`.
> 
> C’est sous réserve que la nature du message de statut satisfasse bien à la correspondance implicitement établie. Dans le cas d’un message de statut indiquant la progression d’un processus et matérialisé graphiquement par une barre de progression, un rôle WAI-ARIA `progressbar` explicite est nécessaire.

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

- [TestCases files for rule 7.5.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule070502/)
- [Unit test file for rule 7.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070502Test.java)
- [Class file for rule 7.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070502.java)


