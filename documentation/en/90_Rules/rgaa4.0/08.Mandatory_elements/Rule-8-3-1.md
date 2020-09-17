# RGAA 4.0 - Rule 8.3.1

## Summary

No-check rule

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

@@@TODO


## Algorithm

### Selection

None

### Process

None

### Analysis

#### Not Tested

In all cases


## Files

- [TestCases files for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080301/)
- [Unit test file for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080301Test.java)
- [Class file for rule 8.3.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080301.java)


