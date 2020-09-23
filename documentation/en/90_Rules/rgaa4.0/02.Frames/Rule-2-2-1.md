# RGAA 4.0 — Rule 2.2.1

## Summary

This test consists in checking the relevancy of the `title` attribute 
for each `<iframe>` and `<frame>` tag.

## Business description

### Criterion

[2.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-2-2)

### Test

[2.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-2-1)

### Description

> Pour chaque cadre (balise `<iframe>` ou `<frame>`) ayant un attribut `title`, le contenu de cet attribut est-il pertinent ?

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

All the `<iframe>` and the `<frame>` tags of the page with 
a `title` attribute (css selector : `iframe[title],frame[title]`)

### Process

#### Test1

For all elements of **Set1**, check whether the content of the `title` attribute is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test1**, raise a MessageA.

For each occurrence of false-result of **Test1**, raise a MessageB

#### MessageA

-   code: NotPertinentTitleOfIframe
-   status: Failed
-   parameter: `title` attribute, tag name, snippet
-   present in source: yes

#### MessageB

-   code: CheckTitleOfFramePertinence
-   status: Pre-Qualified
-   parameter: `title` attribute, tag name, snippet
-   present in source: yes

### Analysis

#### Failed

At least one `<iframe>` or `<frame>` tag has an irrelevant `title` attribute (**Test1** returns true for at least one element)

#### Pre-qualified

The content of the `title` attribute of all the `<iframe>` and `<frame>` tags needs to be manually checked (**Test1** returns false for all the elements of **Set1**) 

#### Not Applicable

The page has no `<iframe>` or `<frame>` tag with a `title` attribute (**Set1** is empty)

## Notes

The content of the `title` attribute is considered as not relevant if it is:

- empty,
- or only composed of non-alphanumerical characters,
- or identical to the `src` attribute.

## Files

- [TestCases files for rule 2.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule020201/)
- [Unit test file for rule 2.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule020201Test.java)
- [Class file for rule 2.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule020201.java)


