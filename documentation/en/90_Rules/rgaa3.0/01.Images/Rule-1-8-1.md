# RGAA 3.0 -  Rule 1.8.1

## Summary

This test consists in detecting informative images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.8](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-8)

### Test

[1.8.1](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-8-1)

### Description
When an <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mMecaRempl">alternate
  mechanism</a> is missing, each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mImgText">image
  of text</a> (<code>img</code> tag) conveying information must be
    replaced with <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTexteStyle">styled
  text</a>, if possible. Has this rule been followed (except in <a title="Particular cases for criterion 1.8" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit1-8">particular cases</a>)? 


### Level

**AA**

## Technical description

### Scope

**page**

### Decision level

**Semi-decidable**

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

[TestCases files for rule 1.8.1](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010801/) 


