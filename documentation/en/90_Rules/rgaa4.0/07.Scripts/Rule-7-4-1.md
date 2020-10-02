# RGAA 4.0 — Rule 7.4.1

## Summary

No-check rule

## Business description

### Criterion

[7.4](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-7-4)

### Test

[7.4.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-4-1)

### Description

> Chaque [script](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#script) qui initie un [changement de contexte](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#changement-de-contexte) vérifie-t-il une de ces conditions ?
> 
> * L’utilisateur est averti par un texte de l’action du script et du type de changement avant son déclenchement.
> * Le changement de contexte est initié par un bouton (input de type `submit`, `button` ou `image` ou balise `<button>`) explicite.
> * Le changement de contexte est initié par un lien explicite.

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

- [TestCases files for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule070401/)
- [Unit test file for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070401Test.java)
- [Class file for rule 7.4.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule070401.java)


