# RGAA 4.0 — Rule 4.1.1

## Summary

No-check rule

## Business description

### Criterion

[4.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-1)

### Test

[4.1.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-1-1)

### Description

> Chaque [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) pré-enregistré seulement audio, vérifie-t-il, si nécessaire, l’une de ces conditions (hors cas particuliers) ?
> 
> * Il existe une [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel) accessible via un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent).
> * Il existe une [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel) adjacente clairement identifiable.

#### Particular cases (criterion 4.1)

> Il existe une gestion de cas particulier lorsque :
> 
> * Le [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) est utilisé à des fins décoratives (c’est-à-dire qu’il n’apporte aucune information);
> * Le média temporel est lui-même une alternative à un contenu de la page (une vidéo en langue des signes ou la vocalisation d’un texte, par exemple);
> * Le média temporel est utilisé pour accéder à une version agrandie;
> * Le média temporel est utilisé comme un [CAPTCHA](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#captcha);
> * Le média temporel fait partie d’un test qui deviendrait inutile si la [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel), les [sous-titres synchronisés](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#sous-titres-synchronises-objet-multimedia) ou l’[audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) étaient communiqués;
> * Pour les services de l’État, les collectivités territoriales et leurs établissement : si le média temporel a été publié entre le 23 septembre 2019 et le 23 septembre 2020 sur un site internet, intranet ou extranet créé depuis le 23 septembre 2018, il est exempté de l’obligation d’accessibilité;
> * Pour les personnes de droit privé mentionnées aux 2° à 4° du I de l’article 47 de la loi du 11 février 2005 : si le média temporel a été publié avant le 23 septembre 2020, il est exempté de l’obligation d’accessibilité.
> 
> Dans ces situations, le critère est non applicable.
> 
> Ce cas particulier s’applique également aux critères 4.2, 4.3, 4.5.

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

- [TestCases files for rule 4.1.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/v5/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040101/)
- [Unit test file for rule 4.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040101Test.java)
- [Class file for rule 4.1.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040101.java)


