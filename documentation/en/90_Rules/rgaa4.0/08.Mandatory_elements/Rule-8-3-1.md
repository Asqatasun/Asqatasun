# RGAA 4.0 — Rule 8.3.1

## Summary

We check whether a language is specified for each textual element of the page

## Business description

### Criterion

[8.3](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-3)

### Test

[8.3.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-3-1)

### Description

> Pour chaque page web, l’indication de langue par défaut vérifie-t-elle une de ces conditions ?
> 
> * L’indication de la langue de la page (attribut `lang` et/ou `xml:lang`) est donnée pour l’élément `html`.
> * L’indication de la langue de la page (attribut `lang` et/ou `xml:lang`) est donnée sur chaque élément de texte ou sur l’un des éléments parents.

#### Particular cases (criterion 8.3)

> Il y a une gestion de cas particulier sur la conformité du code HTML.
> 
> Pour accompagner la prise en charge progressive de HTML par les navigateurs, les APIs d’accessibilité et les technologies d’assistance, certains critères peuvent exiger la présence d’attributs ou de balises déclarés « obsolètes » en HTML. Par ailleurs, dans la mesure où des balises ou des attributs déclarés « obsolètes » sont utilisés, ils restent soumis aux autres critères du RGAA (exemple la balise `<marquee>` serait non conforme par rapport au [critère 13.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-13-8)) et leur support d’accessibilité doit être vérifié au regard de l’environnement de test (ou « base de référence ») retenu.

### Level

**A**


## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

##### Set1

The `<html>` tag with a `lang` or `xml:lang` attribute.

##### Set2

The tags with a `lang` or `xml:lang` attribute.

##### Set3

The textual tags without `lang` or `xml:lang` attribute considering that
these attributes can be set to the current tag or to one of its
ascendants.

### Process

#### Test1

Test whether **Set2** is empty. If yes, raise a MessageA

#### Test2

Test whether **Set1** is empty and **Set2** and **Set3** are not. If yes, raise a MessageB

##### MessageA : Lang Attribute Missing On Whole Page

- code : LangAttributeMissingOnWholePage
- status: Failed
- parameter: none
- present in source : no

##### MessageB : Lang Attribute Missing On Html

- code : LangAttributeMissingOnHtml
- status: Failed
- parameter: none
- present in source: no

### Analysis

#### Passed

- The `<html>` tag has a `lang` or a `xml:lang` attribute (**Set1** is not empty)
- The language is provided for each textual element by the tag or by
  one of its parents (**Set1** is empty AND **Set2** is not empty AND **Set3** is empty)

#### Failed

- The page has no language specification (**Set2** is empty).
- Some textual tags are missing the language attribute (**Set1** is empty
  AND **Set2** is not empty AND **Set3** is not empty)

## Files

- [TestCases files for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080301/)
- [Unit test file for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080301Test.java)
- [Class file for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080301.java)
