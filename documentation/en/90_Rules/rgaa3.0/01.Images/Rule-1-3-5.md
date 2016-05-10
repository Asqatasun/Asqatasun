# RGAA 3.0 -  Rule 1.3.5

## Summary

This test consists in detecting informative object images and thus defining the applicability of the test.

Human check will be then needed to determine whether an alternative is present and is relevant.

## Business description

### Criterion

[1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-3)

###Test

[1.3.5](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-3-5)

### Description

Chaque image embarqu&eacute;e (balise `embed` avec l'attribut `type="image/..."`) porteuse d'information v&eacute;rifie-t-elle une de ces conditions (hors <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit1-3" title="Cas particuliers pour le crit&egrave;re 1.3">cas particuliers</a>) ? 
 
 * L'image embarqu&eacute;e est imm&eacute;diatement suivie d'un lien adjacent permettant d'afficher une page ou un passage de texte contenant une alternative pertinente. 
 * Un m&eacute;canisme permet &agrave; l'utilisateur de remplacer l'image embarqu&eacute;e par un texte alternatif pertinent 
 * Un m&eacute;canisme permet &agrave; l'utilisateur de remplacer l'image embarqu&eacute;e par une image poss&eacute;dant une alternative pertinente. 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

##### Set1

All the `<embed>` tags of the page not within a link and with a `"type"` attribute that starts with "image/..."  not identified as captcha (see Notes about captcha detection)  (css selector : embed[type^=image]:not(a embed))

#### Set2

All the elements of **Set2** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set2

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check the presence of an alternative mechanism for information image

-    code : **CheckPresenceOfAlternativeMechanismForInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and the presence of an alternative mechanism

-    code : **CheckNatureOfImageAndPresenceOfAlternativeMechanism** 
-    status: Pre-Qualified
-    parameter : text, `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no embedded image (**Set1** is empty)

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

[TestCases files for rule 1.3.5](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010305/) 


