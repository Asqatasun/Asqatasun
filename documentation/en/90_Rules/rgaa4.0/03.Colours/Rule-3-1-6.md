# RGAA 4.0 — Rule 3.1.6

## Summary

This test consists in detecting all the tags for no time-based media.

## Business description

### Criterion

[3.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-3-1)

### Test

[3.1.6](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-3-1-6)

### Description

> Pour chaque [média non temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-non-temporel) [véhiculant une information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#image-vehiculant-une-information-donnee-par-la-couleur), l’[information](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#information-donnee-par-la-couleur) ne doit pas être donnée uniquement par la couleur. Cette règle est-elle respectée ?

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

- [TestCases files for rule 3.1.6](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule030106/)
- [Unit test file for rule 3.1.6](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030106Test.java)
- [Class file for rule 3.1.6](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule030106.java)
