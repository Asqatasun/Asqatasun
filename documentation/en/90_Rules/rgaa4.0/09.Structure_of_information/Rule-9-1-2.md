# RGAA 4.0 — Rule 9.1.2

## Summary

This test consists in checking whether each heading of the page is relevant

## Business description

### Criterion

[9.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-9-1)

### Test

[9.1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-1-2)

### Description

> Dans chaque page web, le contenu de chaque [titre](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#titre) (balise `<hx>` ou balise possédant un attribut WAI-ARIA `role="heading"` associé à un attribut WAI-ARIA `aria-level`) est-il pertinent ?

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

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<Hx>` tags where x is comprise between 1 and 6 and all the tags with a `"role"` attribute equals to "heading" and an `"aria-level"` attribute (h1, h2, h3, h4, h5, h6, [role=heading][aria-level])

### Process

#### Test1

For each element of **Set1**, we check whether the content of the tag is pertinent (see Notes about relevancy detection).

For each element returning false in **Test1**, raise a MessageA. Raise a MessageB instead.

###### MessageA : Not pertinent heading

-    code : NotPertinentHeading
-    status: Failed
-    parameter : tag text, tag name, snippet
-    present in source : yes

###### MessageB : Check heading pertinence

-    code : CheckHeadingPertinence
-    status: Pre-Qualified
-    parameter : tag text, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

- The page has no <H> tag
- The page has no tag with heading semantic (role="heading" and aria-level="Integer") 

(**Set1** is empty)

#### Failed

At least one element of **Set1** has an empty content or a content only composed of non alphanumerical characters (**Test1** returns false for at least one element)

#### Pre-Qualified

In all other cases

## Notes

***Definition of not-pertinent legend :***

A heading is seen as not-pertinent in the following cases :

-   the content of the tag is empty
-   the content of the tag only contains non alphanumerics characters



## Files

- [TestCases files for rule 9.1.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule090102/)
- [Unit test file for rule 9.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090102Test.java)
- [Class file for rule 9.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090102.java)


