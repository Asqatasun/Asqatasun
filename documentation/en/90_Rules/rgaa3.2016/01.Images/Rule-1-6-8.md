# RGAA 3.2016 - Rule 1.6.8

## Summary
This test consists in detecting informative canvas images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion
[1.6](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#crit-1-6)

### Test
[1.6.8](http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-1-6-8)

### Description
<div lang="fr">Chaque image <span lang="en">bitmap</span> (balise <code lang="en">canvas</code>), qui n&#xE9;cessite une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#description-dtaille-image">description d&#xE9;taill&#xE9;e</a>, v&#xE9;rifie-t-elle une de ces conditions&nbsp;? <ul><li>Il existe un passage de texte entre <code lang="en">&lt;canvas&gt;</code> et <code lang="en">&lt;/canvas&gt;</code> contenant une r&#xE9;f&#xE9;rence &#xE0; une description d&#xE9;taill&#xE9;e adjacente &#xE0; l&#x2019;image <span lang="en">bitmap</span>&nbsp;;</li> <li>Il existe un contenu textuel entre <code lang="en">&lt;canvas&gt;</code> et <code lang="en">&lt;/canvas&gt;</code> faisant office de description d&#xE9;taill&#xE9;e&nbsp;;</li> <li>Il existe un <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#lien-adjacent">lien adjacent</a> (<i>via</i> une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#url">url</a> ou une <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/glossaire.html#ancre">ancre</a>) permettant d&#x2019;acc&#xE9;der au contenu de la description d&#xE9;taill&#xE9;e.</li> </ul></div>

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

All the `<canvas>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection) (css selector : canvas:not(a canvas))

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

The page has no canvas image (**Set1** is empty)

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

[TestCases files for rule 1.6.8](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-rgaa3.2016/src/test/resources/testcases/rgaa32016/Rgaa32016Rule010608/)


