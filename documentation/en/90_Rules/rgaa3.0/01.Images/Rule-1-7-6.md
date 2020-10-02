# RGAA 3.0 -  Rule 1.7.6

## Summary

This test consists in detecting informative canvas images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements provide a detailed description.

## Business description

### Criterion

[1.7](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-1-7)

###Test

[1.7.6](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-1-7-6)

### Description
Does each bitmap image
    (<code>canvas</code> tag) with a detailed description meet one of the
    following conditions? &nbsp;&nbsp;&nbsp;
    <ul><li>The detailed description adjacent to the bitmap
   image is relevant</li>
  <li>The detailed description available between
   <code>&lt;canvas&gt;</code> and <code>&lt;/canvas&gt;</code> is relevant</li>
  <li>The detailed description available via an adjacent
   link is relevant</li>
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

#### Set1

All the `<canvas>` tags of the page not within a link and not identified as captcha (see Notes about captcha detection)  (css selector : canvas:not(a canvas))

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check detailed description of informative images

-    code : **CheckDescriptionPertinenceOfInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and detailed description 

-    code : **CheckNatureOfImageAndDescriptionPertinence** 
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

[TestCases files for rule 1.7.6](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010706/) 


