# RGAA 3.2016 - Rule 1.6.6

## Summary
This test consists in detecting informative svg images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion
[1.6](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-6)

### Test
[1.6.6](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-6-6)

### Description
<div lang="fr">Chaque image vectorielle (balise <code lang="en">svg</code>) <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#image-porteuse-dinformation">porteuse d&#x2019;information</a>, qui n&#xE9;cessite une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#description-dtaille-image">description d&#xE9;taill&#xE9;e</a>, v&#xE9;rifie-t-elle une de ces conditions&nbsp;? <ul><li>Il existe une propri&#xE9;t&#xE9; <code lang="en">aria-label</code> contenant une r&#xE9;f&#xE9;rence &#xE0; une description d&#xE9;taill&#xE9;e adjacente &#xE0; l&#x2019;image vectorielle&nbsp;;</li> <li>Il existe une balise <code lang="en">desc</code> contenant une r&#xE9;f&#xE9;rence &#xE0; une description d&#xE9;taill&#xE9;e adjacente &#xE0; l&#x2019;image vectorielle&nbsp;;</li> <li>Il existe une balise <code lang="en">desc</code> contenant la description d&#xE9;taill&#xE9;e&nbsp;;</li> <li>Il existe un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#lien-adjacent">lien adjacent</a> (<i>via</i> une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#url">url</a> ou une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#ancre">ancre</a>) permettant d&#x2019;acc&#xE9;der au contenu de la description d&#xE9;taill&#xE9;e.</li> </ul></div>

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

All the `<svg>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection)  (css selector : svg:not(a svg))

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check detailed description definition of informative images

-    code : **CheckLongdescDefinitionOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description definition

-    code : **CheckNatureOfImageAndLongdescDefinition** 
-    status: Pre-Qualified
-    parameter : text, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no svg image (**Set1** is empty)

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

[TestCases files for rule 1.6.6](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010606/)


