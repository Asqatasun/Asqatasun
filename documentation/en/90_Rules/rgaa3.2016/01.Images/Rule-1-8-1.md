# RGAA 3.2016 - Rule 1.8.1

## Summary
This test consists in detecting informative images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion
[1.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-8)

### Test
[1.8.1](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-8-1)

### Description
<div lang="fr">Chaque <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-texte">image texte</a> (balise <code lang="en">img</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-porteuse-dinformation">porteuse d&#x2019;information</a>, en l&#x2019;absence d&#x2019;un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#mcanisme-de-remplacement">m&#xE9;canisme de remplacement</a>, doit si possible &#xEA;tre remplac&#xE9; par du <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#texte-styl">texte styl&#xE9;</a>. Cette r&#xE8;gle est-elle respect&#xE9;e (hors <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/cas-particuliers.html#cp-1-8" title="Cas particuliers pour le crit&#xE8;re 1.8">cas particuliers</a>)&nbsp;?</div>

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

All the `<img>` tags of the page not identified as captcha (see Notes about captcha detection)  (css selector : `img`)

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
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and text styled presence

-    code : **CheckNatureOfImageAndStyledTextPresence** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no <img> tag (**Set1** is empty)

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

[TestCases files for rule 1.8.1](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010801/)


