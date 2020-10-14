# RGAA 4.0 — Rule 8.6.1

## Summary

This test consists in checking the relevancy of the `title` tag in the page.

## Business description

### Criterion

[8.6](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-6)

### Test

[8.6.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-6-1)

### Description

> Pour chaque page web ayant un [titre de page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#titre-de-page) (balise `<title>`), le contenu de cette balise est-il pertinent ?

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Set1

The content of the `<title>` tag within the `<head>` 

CSS selector:
```jquery-css
head title
```

### Process

#### Test1

We check whether the title is found amoung the blacklisted titles loaded by the nomenclature "UnexplicitPageTitle". 

For each element returning false in Test1, raise a MessageA. 
Raise a MessageB instead.

###### MessageA : Irrelevant title

- code: NotPertinentTitle
- status: Failed
- parameter: the textual content, snippet
- present in source: yes

###### MessageB : Check title pertinence

- code: CheckTitlePertinence
- status: Pre-Qualified
- parameter: the textual content, snippet
- present in source: yes

### Analysis

####  Not Applicable

The page has no `<title>` tag (**Set1** is empty)

#### Failed

The title of the page has been found in the blacklist (**Test1** returns true)

#### Pre-Qualified

The title of the page has not been found in the blacklist (**Test1** returns false)


## Files

- [TestCases files for rule 8.6.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080601/)
- [Unit test file for rule 8.6.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080601Test.java)
- [Class file for rule 8.6.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080601.java)
