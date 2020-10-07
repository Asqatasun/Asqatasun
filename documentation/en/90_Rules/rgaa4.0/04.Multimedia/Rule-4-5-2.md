# RGAA 4.0 — Rule 4.5.2

## Summary

This test consists in detecting all the links allowing to download a time-based media file 
and all the tags allowing to display a time-based media.

## Business description

### Criterion

[4.5](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-5)

### Test

[4.5.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-5-2)

### Description

> Chaque [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) synchronisé pré-enregistré vérifie-t-il, si nécessaire, une de ces conditions (hors cas particuliers) ?
> 
> * Il existe une [audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée.
> * Il existe une version alternative avec une [audiodescription](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#audiodescription-synchronisee-media-temporel) synchronisée.

#### Particular cases (criterion 4.5)

> Voir [cas particuliers critère 4.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#crit-4-1).

### Level

**AA**


## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**


## Algorithm

### Selection

#### Set1
- All links to download a time-based media file
- AND all the following tags:
  - `<audio>`
  - `<bgsound>`
  - `<video>`
  - `<object>`
  - `<embed>`
  - `<svg>`
  - `<canvas>`
    
css selector :
```css
audio[src],
audio:has(source[src]),
bgsound, 
video[src], 
video:has(source[src]), 
object[data],
embed[src],
svg,
canvas,
a[href~=(?i)\.(mp4|avi|wmv|mov|Xvid|mkv|mka|mks|FLV|rmvb|MPA|WMA|MP2|M2P|DIF|DV|VOB|VRO|rmvb|vivo|bik|ASF|ifo|mts|mxf|nds|rv|web|wlmp|wmp|ogv)] 
a[href~=(?i)\.(WAV|CDA|MID|MP2|MP3|mp3PRO|MOD|RM|RAM|WMA|Ogg|oga|AIF|AIFF|AA|AAC|M4A|VQF|AU|M3U|RIFF|BWF|CAF|PCM|RAW|FLAC|ALAC|AC3|ACC)] 
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

- The page has no tags allowing to display a time-based media
- The page has no link to download a time-based media file.

#### Pre-qualified

In all other cases


## Files

- [TestCases files for rule 4.5.2](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040502/)
- [Unit test file for rule 4.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040502Test.java)
- [Class file for rule 4.5.2](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040502.java)


