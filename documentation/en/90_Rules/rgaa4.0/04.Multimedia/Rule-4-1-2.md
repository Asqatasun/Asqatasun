# RGAA 4.0 — Rule 4.1.2

## Summary

This test consists in detecting all the links allowing to download a video file 
and all the tags allowing to display a video or an animated cartoon.

## Business description

### Criterion

[4.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-1)

### Test

[4.1.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-1-2)

### Description

> Chaque [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) pré-enregistré seulement vidéo vérifie-t-il, si nécessaire, l’une de ces conditions (hors cas particuliers) ?
> 
> * Il existe une [version alternative « audio seulement »](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#version-alternative-audio-seulement) accessible via un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent).
> * Il existe une [version alternative « audio seulement »](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#version-alternative-audio-seulement) adjacente clairement identifiable.
> * Il existe une [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel) accessible via un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent).
> * Il existe une [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel) adjacente clairement identifiable.
> * Il existe une [audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée.
> * Il existe une version alternative avec une [audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée accessible via un [lien ou bouton adjacent](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#lien-ou-bouton-adjacent).

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

**Semi-Decidable**


## Algorithm

### Selection

#### Set1
- All links to download a video file
- AND all the following tags:
  - `<video>`
  - `<object>`
  - `<embed>`
  - `<svg>`
  - `<canvas>`
    
css selector :
```css
video[src], 
video:has(source[src]), 
object[data],
embed[src],
svg,
canvas,
a[href~=(?i)\.(mp4|avi|wmv|mov|Xvid|mkv|mka|mks|FLV|rmvb|MPA|WMA|MP2|M2P|DIF|DV|VOB|VRO|rmvb|vivo|bik|ASF|ifo|mts|mxf|nds|rv|web|wlmp|wmp|ogv)] 
```

### Process

#### Test1

Test whether **Set1** is not empty. If yes, raise a MessageA.

##### MessageA : Check manually the elements of the scope

- code: ManualCheckOnElements
- status: Pre-qualified
- parameter: snippet
- present in source: yes

### Analysis

#### Not Applicable

- The page has no `<video>`, `<object>`, `<embed>`, `<svg>` or `<canvas>` tag.
- The page has no link to download a video file.

#### Pre-qualified

In all other cases


## Files

- [TestCases files for rule 4.1.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040102/)
- [Unit test file for rule 4.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040102Test.java)
- [Class file for rule 4.1.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040102.java)

