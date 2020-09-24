# RGAA 4.0 — Rule 2.1.1

## Summary

This test consists in checking the presence of the `"title"` attribute 
for all the `<iframe>` and the `<frame>` elements of the page.

## Business description

### Criterion

[2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-2-1)

### Test

[2.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-1-1)

### Description

> Chaque cadre (balise `<iframe>` ou `<frame>`) a-t-il un attribut `title` ?

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

All the `<iframe>`  and the `<frame>` tags of the page (css selector : `iframe,frame`)

### Process

#### Test1 :

For each element of **Set1**, test the presence of the `"title"` attribute.

For each occurrence of false-result of **Test1**, raise a MessageA

##### MessageA: Title attribute is missing

-   code : TitleAttributeMissing
-   status: Failed
-   parameter : `"src"` attribute, tag name, snippet
-   present in source : yes

### Analysis

#### Passed

All the `<iframe>` and the `<frame>` tags have an `"title"` attribute (**Test1** returns true for all the elements of **Set1**)

#### Failed

At least one `<iframe>` or `<frame>` tag has no `"title"` attribute (**Test1** returns false for at least one element of **Set1**)

#### Not Applicable

The page has no `<iframe>` or `<frame>` tag (**Set1** is empty)


## Files

- [TestCases files for rule 2.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule020101/)
- [Unit test file for rule 2.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule020101Test.java)
- [Class file for rule 2.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule020101.java)
