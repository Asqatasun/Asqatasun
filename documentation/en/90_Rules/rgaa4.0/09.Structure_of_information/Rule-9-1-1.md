# RGAA 4.0 — Rule 9.1.1

## Summary

This test consists in checking the relevancy of the headings hierarchy.

## Business description

### Criterion

[9.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-9-1)

### Test

[9.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-1-1)

### Description

> Dans chaque page web, la hiérarchie entre les [titres](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#titre) (balise `<hx>` ou balise possédant un attribut WAI-ARIA `role="heading"` associé à un attribut WAI-ARIA `aria-level`) est-elle pertinente ?

#### Technical notes (criterion 9.1)

> WAI-ARIA permet de définir des titres via le rôle `heading` et l’attribut `aria-level` (indication du niveau de titre). Bien qu’il soit préférable d’utiliser l’élément de titre natif en HTML `<hx>`, l’utilisation du rôle WAI-ARIA `heading` est compatible avec l’accessibilité.
> 
> Bien que la spécification HTML5 autorise l’utilisation exclusive de titres de niveau 1 (`h1`), le manque de support des technologies d’assistance oblige à utiliser une hiérarchie de titres pertinente.

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

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

We assume that the index of the first encountered <Hx> tag represents the index of reference for the document.

#### Test1

We check that the difference between the index of each element of **Set1** and the index of its previous element is not superior to 1.

For each occurrence of false-result of **Test1**, raise a MessageA

#### Test2

We check that the index of each element of **Set1** is not inferior to the index of reference.

For each occurrence of false-result of **Test1**, raise a MessageB

###### MessageA : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : previous heading tag, snippet
-   present in source : yes

###### MessageB : Header Tag Not Hierarchically Well defined

-   code : HeaderTagNotHierarchicallyWelldefined
-   status: Failed
-   parameter : first heading tag, snippet
-   present in source : yes

### Analysis

#### Passed

**Test1** or **Test2** return true for all the elements of **Set1**

#### Failed

**Test1** or **Test2** return false for at least one element of **Set1**

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)


## Files

- [TestCases files for rule 9.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule090101/)
- [Unit test file for rule 9.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090101Test.java)
- [Class file for rule 9.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090101.java)


