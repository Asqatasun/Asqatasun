# RGAA 4.0 - Rule 8.2.1

## Summary

No-check rule

## Business description

### Criterion

[8.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-8-2)

### Test

[8.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-2-1)

### Description

> Pour chaque déclaration de [type de document](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#type-de-document), le code source généré de la page vérifie-t-il ces conditions (hors cas particuliers) ?
> 
> * Les balises, attributs et valeurs d’attributs respectent les [règles d’écriture](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#regles-d-ecriture);
> * L’imbrication des balises est conforme;
> * L’ouverture et la fermeture des balises sont conformes;
> * Les valeurs d’attribut id sont uniques dans la page;
> * Les attributs ne sont pas doublés sur un même élément.

#### Particular cases (criterion 8.2)

> Il y a une gestion de cas particulier sur la conformité du code HTML.
> 
> Pour accompagner la prise en charge progressive de HTML par les navigateurs, les APIs d’accessibilité et les technologies d’assistance, certains critères peuvent exiger la présence d’attributs ou de balises déclarés « obsolètes » en HTML. Par ailleurs, dans la mesure où des balises ou des attributs déclarés « obsolètes » sont utilisés, ils restent soumis aux autres critères du RGAA (exemple la balise `<marquee>` serait non conforme par rapport au critère 13.8) et leur support d’accessibilité doit être vérifié au regard de l’environnement de test (ou « base de référence ») retenu.

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

- [TestCases files for rule 8.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule080201/)
- [Unit test file for rule 8.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080201Test.java)
- [Class file for rule 8.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule080201.java)


