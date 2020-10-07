# RGAA 4.0 — Rule 4.2.2

## Summary

This test consists in detecting all the links allowing to download a video file 
and all the tags allowing to display a video or an animated cartoon.

## Business description

### Criterion

[4.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-2)

### Test

[4.2.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-2-2)

### Description

> Chaque [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) pré-enregistré seulement vidéo vérifie-t-il une de ces conditions (hors cas particuliers) ?
> 
> * La [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel) est pertinente.
> * L’[audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée est pertinente.
> * L’[audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée de la version alternative est pertinente.
> * La version alternative audio seulement est pertinente.

#### Particular cases (criterion 4.2)

> Voir cas particuliers critère 4.1.

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
video:has(source), 
object[data],
embed[src],
svg,
canvas,
a[href~=(?i)\.(mp4|MP4|avi|wmv|mov|Xvid|mkv|mka|mks|FLV|rmvb|MPA|WMA|MP2|M2P|DIF|DV|VOB|VRO|rmvb|vivo|bik|ASF|ifo|mts|mxf|nds|rv|web|wlmp|wmp|ogv)] 
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

- [TestCases files for rule 4.2.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040202/)
- [Unit test file for rule 4.2.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040202Test.java)
- [Class file for rule 4.2.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040202.java)


