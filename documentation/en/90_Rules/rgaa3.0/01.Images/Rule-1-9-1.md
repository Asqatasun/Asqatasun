# RGAA 3.0 -  Rule 1.9.1

## Summary

This test consists in detecting images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.9](http://references.modernisation.gouv.fr/referentiel-technique-0#crit-1-9)

###Test

[1.9.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-9-1)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mImgText">image texte</a> (balise `img`) doit si possible &ecirc;tre remplac&eacute;e par du <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTexteStyle">texte styl&eacute;</a>. Cette r&egrave;gle est-elle respect&eacute;e (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit19-" title="Cas particuliers pour le crit&egrave;re 1.9">hors cas particuliers</a>) ?

### Level

**AAA**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<img>` tags of the page not identified as captcha (see Notes about captcha detection)  (css selector : `img`)

### Process

#### Test1

For each element of **Set1**, raise a MessageA

##### MessageA 

-    code : **ManualCheckOnElements** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<img>` tags (**Set1** is empty)

#### Pre-qualified

The page has at least one `<img>` tag (**Set1** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element
