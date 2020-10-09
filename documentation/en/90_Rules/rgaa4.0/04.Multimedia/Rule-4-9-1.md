# RGAA 4.0 — Rule 4.9.1

## Summary

This test consists in detecting all the tags for no time-based media.

## Business description

### Criterion

[4.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-9)

### Test

[4.9.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-9-1)

### Description

> Pour chaque [média non temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-non-temporel) ayant une alternative, cette alternative permet-elle d’accéder au même contenu et à des fonctionnalités similaires ?

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**


## Algorithm

### Selection

#### Set1
- All the following tags:
  - `<svg>`
  - `<canvas>`
  - `<object>`
  - `<embed>`
  
css selector :
```css
svg, 
canvas,
object[data],
embed[src]
```

### Process

#### Test1

Test whether **Set1** is not empty. If yes, raise a MessageA.

##### MessageA : Check manually the elements of the scope

- code: ManualCheckOnElements
- status: Pre-qualified
- parameter: snippet
- present in source: yes

### Analysis

#### Not Applicable

The page has no `<svg>`, `<canvas>`, `<object>` or `<embed>` tag.

#### Pre-qualified

In all other cases


## Files

- [TestCases files for rule 4.9.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040901/)
- [Unit test file for rule 4.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040901Test.java)
- [Class file for rule 4.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040901.java)


