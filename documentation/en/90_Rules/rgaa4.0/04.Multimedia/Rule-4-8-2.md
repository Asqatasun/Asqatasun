# RGAA 4.0 — Rule 4.8.2

## Summary

No-check rule

## Business description

### Criterion

[4.8](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-8)

### Test

[4.8.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-8-2)

### Description

> Chaque [média non temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-non-temporel) associé à une alternative vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * La page référencée par le [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent) est accessible.
> * L’alternative dans la page, référencée par le [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent), est accessible.

#### Particular cases (criterion 4.8)

> Il existe une gestion de cas particulier lorsque :
> 
> * Le [média non temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-non-temporel) est utilisé à des fins décoratives (c’est-à-dire qu’il n’apporte aucune information);
> * Le média non temporel est diffusé dans un [environnement maîtrisé](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#environnement-maitrise);
> * Le média non temporel est inséré via JavaScript en vérifiant la présence et la version du plug-in, en remplacement d’un [contenu alternatif](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#contenu-alternatif) déjà présent.
> 
> Dans ces situations, le critère est non applicable.

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

- [TestCases files for rule 4.8.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040802/)
- [Unit test file for rule 4.8.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040802Test.java)
- [Class file for rule 4.8.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040802.java)


