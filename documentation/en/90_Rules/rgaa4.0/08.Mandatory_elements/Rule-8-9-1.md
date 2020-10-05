# RGAA 4.0 — Rule 8.9.1

## Summary

This test consists in searching patterns indicating that forbidden tags
(not div, span or table) are used for layout purpose.

## Business description

### Criterion

[8.9](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-9)

### Test

[8.9.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1)

### Description

> Dans chaque page web les balises (à l’exception de `<div>`, `<span>` et `<table>`) ne doivent pas être utilisées [uniquement à des fins de présentation](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#uniquement-a-des-fins-de-presentation). Cette règle est-elle respectée ?

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

All the `<a>` tags without "href", "name" or "id" attribute
(a:not([href]):not([name]):not([id]))

#### Set2

All the fieldset not within a form (fieldset:not(form
fieldset):not(*[role=search] fieldset):not(*[role=form] fieldset))

### Process

#### Test1

We check whether **Set1** AND **Set2** are empty. If true, raise a
MessageA

###### MessageA : No suspect pattern detected

-   code :NoPatternDetected
-   status: Pre-Qualified
-   present in source : no

For each occurence of the **Set1** raise a MessageB

###### MessageB : Link without target

-   code :LinkWithoutTarget
-   status: Failed
-   parameter : snippet
-   present in source : yes

For each occurence of the **Set2** raise a MessageC

###### MessageC : Fieldset not within a form

-   code :FieldsetNotWithinForm
-   status: Failed
-   parameter : snippet
-   present in source : yes

Test1 :

### Analysis

#### Failed :

The page contains a link without target or a fieldset not within a form (**Test1** returns false)

#### Pre-qualified :

**Test1** returns true

## Notes

On latest webdev data set (2013-10-30, 78,000 pages), links without
target (a:not([href]):not([name]):not([id])) have been found on 18256
pages, i.e on 23% of the pages.

On latest webdev data set (2013-10-30, 78,000 pages), fieldsets not
within form (fieldset:not(form fieldset):not(*[role=search]
fieldset):not(*[role=form] fieldset)) have been found on 982 pages, i.e
on 1.25% of the pages.



## Files

- [TestCases files for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080901/)
- [Unit test file for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080901Test.java)
- [Class file for rule 8.9.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080901.java)


