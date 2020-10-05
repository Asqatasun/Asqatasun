# RGAA 4.0 — Rule 8.1.1

## Summary

This tests checks whether a document type is available on the page.

## Business description

### Criterion

[8.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-1)

### Test

[8.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-1-1)

### Description

> Pour chaque page web, le [type de document](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#type-de-document) (balise `doctype`) est-il présent ?

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

#### Set1

The `<!doctype>` tag on the page

### Process

The selection handles the process

### Analysis

#### Failed

The page has no doctype (**Set1** is empty)

#### Passed

A doctype is available on the page (**Set1** is empty)


## Files

- [TestCases files for rule 8.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080101/)
- [Unit test file for rule 8.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080101Test.java)
- [Class file for rule 8.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080101.java)
