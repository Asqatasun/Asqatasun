# RGAA 3.0 -  Rule 1.6.1

## Summary

This test consists in detecting informative images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description if needed.

## Business description

### Criterion

[1.6](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-1-6)

### Test

[1.6.1](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-1-6-1)

### Description
Does each <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mImgInfo">image
  conveying information</a> (<code>img</code> tag) needing a <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed description</a>, meet one of the following conditions?
    <ul><li> A <code>longdesc</code> attribute providing the <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mUrl">URL</a>
   of a page that contains the <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a> is available</li>
  <li> An <code>alt</code> attribute that contains the reference to a
   <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a> adjacent to the image is available</li>
  <li> There is an <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mLienAdj">adjacent
    link</a> (URL or  <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mAncreNom">anchor</a>) giving access to the <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a></li>
    </ul> 


### Level

**A**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

##### Set1

All the `<img>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection) (css selector : `img:not(a img)`)

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
-    parameter : `"longdesc"` attribute, `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description definition

-    code : **CheckNatureOfImageAndLongdescDefinition** 
-    status: Pre-Qualified
-    parameter : `"longdesc"` attribute, `"alt"` attribute, `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no image (**Set1** is empty)

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

[TestCases files for rule 1.6.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010601/) 


