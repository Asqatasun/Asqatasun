# RGAA 4.0 — Rule 8.5.1

## Summary

This test consists in detecting the presence of the `<title>` tag

## Business description

### Criterion

[8.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-5)

### Test

[8.5.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-5-1)

### Description

> Chaque page web a-t-elle un [titre de page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#titre-de-page) (balise `<title>`) ?

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

The `<title>` tag of the page within the `<head>` tag (head title)

### Process

#### Test1

Test whether **Set1** is not empty. If false, raise a MessageA.

###### MessageA : Title tag missing

-   code : TitleTagMissing
-   status: Pre-Qualified
-   present in source : no

### Analysis

#### Passed

The `<title>` tag is present on the page (**Test1** returns true)

#### Failed

The `<title>` tag is not present on the page (**Test1** returns false)



## Files

- [TestCases files for rule 8.5.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080501/)
- [Unit test file for rule 8.5.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080501Test.java)
- [Class file for rule 8.5.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080501.java)


