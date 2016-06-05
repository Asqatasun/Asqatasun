# RGAA 3.0 -  Rule 1.3.4

## Summary

This test consists in detecting informative object images and thus defining the applicability of the test.

Human check will be then needed to determine whether an alternative is present and is relevant.

## Business description

### Criterion

[1.3](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-3)

###Test

[1.3.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-3-4)

### Description
Does each image object
    (<code>object</code> tag with the attribute <code>type="image/&#x2026;"</code>),
    conveying information, meet one of the following
    conditions (except in <a title="Particular cases for criterion 1.3" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit1-3">particular
  cases</a>)?
    <ul><li>The image object is immediately followed by an
   adjacent link giving access to a page or a chunk
   of text containing a relevant alternative</li>
  <li>The user can replace the image
   object by a relevant text alternative, via a provided mechanism</li>
  <li>The user can replace the image
   object by an image with a relevant alternative, via a provided mechanism</li>
    </ul> 


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

All the `<object>` tags with a `"type"` attribute that starts with "image/...", not within a link and not identified as captcha (see Notes about captcha detection)  (css selector : object[type^=image]:not(a object))

#### Set2

All the elements of **Set1** identified as informative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Test1

For each element of **Set2**, raise a MessageA.

#### Test2

For each element of **Set3**, raise a MessageB.

##### MessageA : Check the presence of an alternative mechanism for information image

-    code : **CheckPresenceOfAlternativeMechanismForInformativeImage** 
-    status: Pre-Qualified
-    parameter : text, `"data"` attribute, tag name, snippet
-    present in source : yes

##### MessageB : Check nature of image and the presence of an alternative mechanism

-    code : **CheckNatureOfImageAndPresenceOfAlternativeMechanism** 
-    status: Pre-Qualified
-    parameter : text, `"data"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable 

The page has no object image (**Set1** is empty)

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

[TestCases files for rule 1.3.4](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010304/) 


