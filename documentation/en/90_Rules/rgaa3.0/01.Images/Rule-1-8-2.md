# RGAA 3.0 -  Rule 1.8.2

## Summary

This test consists in detecting informative images map and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.8](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-8)

### Test

[1.8.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-8-2)

### Description

Pour chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgReactive">image r&eacute;active</a> (balise `img` ou `object` avec l'attribut `usemap` ou l'attribut `ismap`), chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZoneTexte">zone texte</a> (balise `area` ou <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZoneCliquable">zone cliquable</a>) porteuse d'information, en l'absence d'un <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mMecaRempl">m&eacute;canisme de remplacement</a>, doit si possible &ecirc;tre remplac&eacute;e par du <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mTexteStyle">texte styl&eacute;</a>. Cette r&egrave;gle est-elle respect&eacute;e (<a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit1-8" title="Cas particuliers pour le crit&egrave;re 1.8">hors cas particuliers</a>) ?

### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**Semi-decidable**

## Algorithm

### Selection

#### Set1

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag and not identified as captcha (see Notes about captcha detection) 

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA

#### Test2

For each element of **Set3**, raise a MessageB

##### MessageA : Check text styled presence of informative image

-    code : **CheckStyledTextPresenceOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"href"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and text styled presence

-    code : **CheckNatureOfImageAndStyledTextPresence** 
-    status: Pre-Qualified
-    parameter : `"href"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no image map (**Set1** is empty)

#### Pre-Qualified

In all other cases

## Notes

### Markers 

**Informative images** markers are set through the **INFORMATIVE_IMAGE_MARKER** parameter.

**Decorative images** markers are set through the **DECORATIVE_IMAGE_MARKER** parameter.

The value(s) passed as marker(s) will be checked against the following attributes:

- `class`
- `id`
- `role`

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases 

[TestCases files for rule 1.8.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010802/) 


