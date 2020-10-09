# RGAA 4.0 — Rule 4.2.1

## Summary

This test consists in detecting all the links allowing to download a audio file 
and all the tags allowing to play a audio file.

## Business description

### Criterion

[4.2](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#crit-4-2)

### Test

[4.2.1](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-2-1)

### Description

> Pour chaque [média temporel](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#media-temporel-type-son-video-et-synchronise) pré-enregistré seulement audio, ayant une [transcription textuelle](https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#transcription-textuelle-media-temporel), celle-ci est-elle pertinente (hors cas particuliers) ?

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
- All links to download a audio file
- AND all the following tags:
  - `<audio>`
  - `<bgsound>`
  - `<video>` with audio file
  - `<object>`
  - `<embed>`
  
css selector :
```css
audio[src], 
audio:has(source[src]), 
bgsound, 
audio[src~=(?i)\.(WAV|CDA|MID|MP2|MP3|mp3PRO|MOD|RM|RAM|WMA|Ogg|oga|AIF|AIFF|AA|AAC|M4A|VQF|AU|M3U|RIFF|BWF|CAF|PCM|RAW|FLAC|ALAC|AC3|ACC)] , 
audio:has(source[src~=(?i)\.(WAV|CDA|MID|MP2|MP3|mp3PRO|MOD|RM|RAM|WMA|Ogg|oga|AIF|AIFF|AA|AAC|M4A|VQF|AU|M3U|RIFF|BWF|CAF|PCM|RAW|FLAC|ALAC|AC3|ACC)]) , 
object[data],
embed[src],
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

- The page has no `<audio>`, `<bgsound>`,  `<video>` (with audio file), `<object>` or `<embed>` tag.
- The page has no link to download a audio file.

#### Pre-qualified

In all other cases


## Files

- [TestCases files for rule 4.2.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa4.0/src/test/resources/testcases/rgaa40/Rgaa40Rule040201/)
- [Unit test file for rule 4.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/test/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040201Test.java)
- [Class file for rule 4.2.1](https://gitlab.com/asqatasun/Asqatasun/-/blob/master/rules/rules-rgaa4.0/src/main/java/org/asqatasun/rules/rgaa40/Rgaa40Rule040201.java)


