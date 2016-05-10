# RGAA 3.0 -  Rule 1.3.2

## Summary

This test consists in checking whether the `alt` attribute of each clickable area that convey information is pertinent.

## Business description

### Criterion

[1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-3)

###Test

[1.3.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-3-2)

### Description

Chaque <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mZone">zone</a> (balise area) d'une <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#mimgReactive">image r&eacute;active</a>, porteuse d'information et ayant un attribut `alt`, v&eacute;rifie-t-elle ces conditions (hors <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#cpCrit1-3" title="Cas particuliers pour le crit&egrave;re 1.3">cas particuliers</a>) ? 
 
 * Le contenu de l'attribut `alt` est pertinent 
 * S'il est pr&eacute;sent, le contenu de l'attribut `title` est identique au contenu de l'attribut `alt` 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Decidable**

## Algorithm

### Selection

#### Set1

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag, with a `"href"` and an `"alt"` attribute 

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests 

##### Test1

For each element of **Set2**, check whether the content of the `"alt"` attribute is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test1**, raise a MessageA.

For each occurrence of false-result of **Test1**, raise a MessageB.

##### Test2

For each element of **Set2**, check whether the content of the `"alt"` attribute is identical to the content of the `"title"` attribute.

For each occurrence of false-result of **Test2**, raise a MessageC.

##### Test3

For each element of **Set2**, check whether the content of the `"alt"` attribute is not relevant (see Notes for details about relevancy test). 

For each occurrence of true-result of **Test3**, raise a MessageD.

For each occurrence of false-result of **Test3**, raise a MessageE.

##### Test4

For each element of **Set2**, check whether the content of the `"alt"` attribute is identical to the content of the `"title"` attribute.

For each occurrence of false-result of **Test4**, raise a MessageD.

#### Messages

##### MessageA 

-    code : **NotPertinentAlt** 
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, `"href"` attribute, tag name
-    present in source : yes

##### MessageB 

-    code : **CheckPertinenceOfAltAttributeOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"href"` attribute, tag name
-    present in source : yes

##### MessageC

-    code : **TitleNotIdenticalToAlt** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"href"` attribute, tag name
-    present in source : yes

##### MessageD

-    code : **CheckNatureOfImageWithNotPertinentAlt** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"href"` attribute, tag name
-    present in source : yes

##### MessageE

-    code : **CheckNatureOfImageAndAltPertinence** 
-    status: Pre-Qualified
-    parameter : `"alt"` attribute, `"title"` attribute, `"href"` attribute, tag name
-    present in source : yes

### Analysis

#### Failed

At least one `<area>` tag identified as informative has an irrelevant `"alt"` attribute (**Test1** returns true for at least one element)

#### Pre-qualified

The alternatives of all the `<area>` tags need to be manually checked (**Test1** returns false for all the elements of **Set1**) 

#### Not Applicable

The page has no `<area>` tag with an `"alt"` attribute (**Set1** is empty)

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

### Alt attribute relevancy 

The content of the `"alt"` attribute is seen as not relevant if :

- empty
- only composed of non-alphanumerical characters
- identical to the `"src"` attribute
- it has an extension of image type (loaded by the nomenclature named **ImageFileExtensions** composed of : jpg, gif, jpeg, png, bmp)



##  TestCases 

[TestCases files for rule 1.3.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010302/) 


