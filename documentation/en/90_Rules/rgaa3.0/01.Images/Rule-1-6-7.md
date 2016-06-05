# RGAA 3.0 -  Rule 1.6.7

## Summary

This test consists in detecting informative canvas images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion

[1.6](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-6)

###Test

[1.6.7](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-6-7)

### Description
Does each bitmap image
    (<code>canvas</code> tag) conveying information needing a <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
  description</a>, meet one of the following conditions?
    <ul><li>There is a chunk of text between <code>&lt;canvas&gt;</code>
   and <code>&lt;/canvas&gt;</code> referencing a detailed
   description adjacent to the bitmap image</li>
  <li>There is a text content between <code>&lt;canvas&gt;</code> and
   <code>&lt;/canvas&gt;</code> serving as a detailed description</li>
  <li>There is an <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mLienAdj">adjacent
    link</a> (URL or  <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mAncreNom">anchor</a>) giving access to the <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a></li>
    </ul> 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-decidable**

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

[TestCases files for rule 1.6.7](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010607/) 


