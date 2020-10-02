# RGAA 3.0 -  Rule 1.7.1

## Summary

This test consists in detecting informative images and informative image form inputs and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description.

## Business description

### Criterion

[1.7](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-1-7)

### Test

[1.7.1](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-1-7-1)

### Description
Does each <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mImgInfo">image
  conveying information</a> (<code>img</code> or <code>input</code>  tag with the
    attribute&nbsp; <code>type="image"</code>) with a <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
  description</a> meet one of the following conditions?
    <ul><li> The <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a> referenced via the URL in the
   <code>longdesc</code> attribute is relevant</li>
  <li> The <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a> available in the page and
   identified in the <code>alt</code> attribute is relevant</li>
  <li> The <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mDescDetaillee">detailed
    description</a> available via an <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mLienAdj">adjacent
    link</a> is relevant</li>
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

##### Set2

All the `<input>` tags with the "type" attribute equals to "image" and not identified as captcha (see Notes about captcha detection) (css selector : `input[type=image]`)

#### Set3

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set4

All the elements of **Set2** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set5

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

#### Set6

All the elements of **Set2** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set3** and **Set4**, raise a MessageA.

#### Test2

For each element of **Set5** and **Set6**, raise a MessageB.

##### MessageA : Check detailed description of informative images

-    code : **CheckDescriptionPertinenceOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description 

-    code : **CheckNatureOfImageAndDescriptionPertinence** 
-    status: Pre-Qualified
-    parameter : `"src"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no <img> tag and image form inputs (**Set1** and **Set2** are empty)

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

[TestCases files for rule 1.7.1](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010701/) 


