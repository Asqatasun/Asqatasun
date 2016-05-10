# RGAA 3.0 -  Rule 1.4.2

## Summary

This test consists in detecting captcha area and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-4)

###Test

[1.4.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-4-2)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZone">zone</a> (balise `area`) d'une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgReactive">image r&eacute;active</a>, utilis&eacute;e comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mcaptcha">CAPTCHA</a> ou comme <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgTest">image-test</a>, et ayant un attribut `alt` v&eacute;rifie-t-elle ces conditions ? 
 
 * le contenu de l'attribut `alt` permet d'identifier la nature et la fonction de la zone 
 * s'il est pr&eacute;sent, le contenu de l'attribut `title` est identique au contenu de l'attribut `alt` 


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

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag, with a `"href"` and an `"alt"` attribute 

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"href"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<area>` tag with a `"href"` and an `"alt"` attribute identified as a captcha (**Set2** is empty)

#### Pre-qualified

In all other cases

## Notes

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases 

[TestCases files for rule 1.4.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010402/) 


