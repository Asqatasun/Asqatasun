# RGAA 3.0 -  Rule 1.2.2

## Summary

This test consists in checking whether the `alt` attribute of non clickable area is empty.

## Business description

### Criterion

[1.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-2)

###Test

[1.2.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-2-2)

### Description
Does each non clickable area  (<code>area</code> tag with no
    <code>href</code> attribute), not conveying any  information, and with an <code>alt</code> attribute,
    meet the following conditions:
    <ul><li>The <code>alt</code> attribute has an empty value (<code>alt=""</code>)</li>
  <li>The non clickable area does not have a <code>title</code> attribute</li>
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

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag, and not identified as captcha (see Notes about captcha detection)

#### Set2

All the elements of **Set1** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

### Process

#### Tests

##### Test1 

For each element of **Set2** (decorative `<area>` identified by a html marker), check that the content of the `"alt"` attribute is empty. 

For each element returning false in **Test1**, raise a MessageA.

##### Test2 

For each element of **Set2** (decorative `<area>` identified by a html marker), check that the `"title"` attribute is missing. 

For each element returning false in **Test2**, raise a MessageB. 

##### Test3

For each element of **Set3** (`<area>` tags not identified as decorative image), check that the content of the `"alt"` attribute empty. 

For each element returning false in **Test3**, raise a MessageC.
For each element returning true in **Test3**, raise a MessageD.

##### Test4 

For each element of **Set3** (decorative `<area>` identified by a html marker), check that the `"title"` attribute is missing. 

For each element returning false in **Test4**, raise a MessageC.
For each element returning true in **Test4**, raise a MessageD.

#### Messages

##### MessageA : Decorative image with not empty alternative

-    code : DecorativeElementWithNotEmptyAltAttribute
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, Snippet
-    present in source : yes

##### MessageB : Decorative image with a title attribute

-    code : DecorativeElementWithTitleAttribute
-    status: Failed
-    parameter : `"alt"` attribute, `"title"` attribute, Snippet
-    present in source : yes

##### MessageC : Check the nature of the image with a not empty alternative

-    code : CheckNatureOfElementWithNotEmptyAltAttribute
-    status: Pre-qualified
-    parameter :  Snippet
-    present in source : yes

##### MessageD : Check the nature of the image with a empty alternative

-    code : CheckNatureOfElementWithEmptyAltAttribute
-    status: Pre-qualified
-    parameter : Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<area>` tag with an `"alt"` attribute (**Set1** is empty)

#### Failed

At least one `<area>` identified as decorative has a not empty alternative (**Test1** OR **Test2** returns false for at least one element)

#### Passed

All the `<area>` of the page are identified as decorative and have an empty alternative (**Set3** is empty and **Test1** AND **Test2** returns true for all element)

#### Pre-qualified

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

[TestCases files for rule 1.2.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010202/) 


