# RGAA 4.0 — Rule 9.2.1

## Summary

Check for HTML5 pages, if the page contains:
- one and only one `<main>` tag without `hidden` attribute. 
- at least one `<header>` tag, except `<header>` that is direct child of `<article>` and `<section>` tags.
- at least one `<footer>` tag, except `<footer>` that is direct child of `<article>` and `<section>` tags.
- at least one `<nav>` tag.

A manual check is necessary to control the use of the different 
tags (`<main>`, `<nav>`, `<footer>` and `<header>`) according to this rule.

## Business description

### Criterion

[9.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-9-2)

### Test

[9.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-2-1)

### Description

> Dans chaque page web, la structure du document vérifie-t-elle ces conditions (hors cas particuliers) ?
> 
> * La [zone d’en-tête de la page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-d-en-tete) est structurée via une balise `<header>`.
> * Les [zones de navigation principales et secondaires](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#menu-et-barre-de-navigation) sont structurées via une balise `<nav>`.
> * La balise `<nav>` est réservée à la structuration des [zones de navigation principales et secondaires](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#menu-et-barre-de-navigation).
> * La [zone de contenu principal](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-de-contenu-principal) est structurée via une balise `<main>`.
> * La structure du document utilise une balise `<main>` visible unique.
> * La [zone de pied de page](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#zone-de-pied-de-page) est structurée via une balise `<footer>`.

#### Particular cases (criterion 9.2)

> Lorsque le doctype déclaré dans la page n’est pas le doctype HTML5, ce critère est non applicable.

#### Technical notes (criterion 9.2)

> La balise `<main>` peut être utilisée plusieurs fois dans le même document HTML. Néanmoins, il ne peut y avoir en permanence qu’une seule balise visible et lisible par les technologies d’assistances, les autres devant disposer d’un attribut `hidden` ou d’un style permettant de les masquer aux technologies d’assistances. A noter cependant que l’utilisation d’un style seul restera insuffisant pour assurer l’unicité d’une balise `<main>` visible en cas de désactivation des feuilles de styles.

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

All the `<nav>` tags. 

CSS selector : 
```jquery-css
nav
```

#### Set2

All the `<main>` tags without `hidden` attribute. 

CSS selector : 
```jquery-css
main:not([hidden])
```

#### Set3

All the `<header>` tags, except `<header>` that is direct child of `<article>` and `<section>` tags. 

CSS selector : 
```jquery-css
*:not(article):not(section) > header
```

#### Set4

All the `<footer>` tags, except `<footer>` that is direct child of `<article>` and `<section>` tags. 

CSS selector : 
```jquery-css
*:not(article):not(section) > footer
```

### Process

#### Tests

##### Test0

Test whether the page has a `doctype` that is not an HTML5 `doctype`:

- If yes, raise a MessageA.
- If no, launch Test1, Test2, Test3 and Test4.

##### Test1

Test emptiness of **Set1**. 
- If empty, raise a MessageB.
- If not empty, raise a MessageC.

##### Test2

Test whether **Set2** is not empty and contains only one element. 
- If empty, raise a MessageD.
- If not empty but contains multiple element, raise a MessageE.
- Else raise a MessageC.

##### Test3

Test emptiness of **Set3**. 
- If empty, raise a MessageF.
- If not empty, raise a MessageC.

##### Test4

Test emptiness of **Set4**. 
- If empty, raise a MessageG.
- If not empty, raise a MessageC.

#### Messages 

##### MessageA : Not Applicable

- status: Not Applicable

##### MessageB : `<nav>` tag is missing

- code : **NavElementMissing**
- status: Failed
- present in source: no

##### MessageC : Check manually the elements of the scope

- code: **ManualCheckOnElements**
- status: Pre-qualified
- parameter: snippet
- present in source: yes

##### MessageD : `<main>` tag is missing

- code : **NavElementMissing**
- status: Failed
- present in source: no

##### MessageE : multiple `<main>` tags

- code : **MainElementNotUnique**
- status: Failed
- parameter: snippet
- present in source: yes

##### MessageF : `<header>` tag is missing

- code : **HeaderElementMissing**
- status: Failed
- present in source: no


##### MessageG : `<footer>` tag is missing

- code : **FooterElementMissing**
- status: Failed
- present in source: no


### Analysis

#### Not Applicable

* The page has a `doctype` that is not an HTML5 `doctype`.

#### Failed

- multiple `<main>` tags without `hidden` attribute are found
- OR missing `<main>` tag
- OR missing `<header>` tag
- OR missing `<footer>` tag
- OR missing `<nav>` tag

#### Pre-qualified

In all other cases


## Files

- [TestCases files for rule 9.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule090201/)
- [Unit test file for rule 9.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090201Test.java)
- [Class file for rule 9.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule090201.java)
