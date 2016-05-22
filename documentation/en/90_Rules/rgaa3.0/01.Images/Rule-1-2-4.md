# RGAA 3.0 -  Rule 1.2.4

## Summary

This test consists in checking whether the ARIA attribute of each decorative vector image (`<svg>` tag) are implemented correctly and checking each decorative vector image or children have no `"title"` or `"desc"` attribute unless they are empty.

## Business description

### Criterion

[1.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-2)

###Test

[1.2.4](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-2-4)

### Description

Does each decorative vector image (<code>svg</code> tag), not conveying any information, and with an alternative, meet the following conditions:
<ul>
    <li>The <code>svg</code> tag has a <code>role="img"</code></li>
    <li>The <code>svg</code> tag, or one of its children, has no ARIA role, property or state,
        that aims at labeling the vector image (<code>aria-label</code>, <code>aria-describedby</code>, or
        <code>aria-labelledby</code>, for example)</li>
    <li>The <code>title</code> and <code>desc</code> tags are missing, or empty</li>
    <li>The <code>svg</code> tag, or one of its children, has no <code>title</code> attribute</li>
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

All the `<svg>` tags of the page, not within a link not identified as captcha (see Notes about captcha detection) (css selector : `svg:not(a svg)`) 

#### Set2

All the elements of **Set1** identified as decorative image by marker usage (see Notes for details about detection through marker)

#### Set3

All the elements of **Set1** identified neither as informative image, nor as decorative image by marker usage (see Notes for details about detection through marker)

#### Set4

All the elements of **Set1** without a `"role"` attribute equals to  `"img"`.

#### Set5

All the elements of **Set1** with a not empty `<title>` or `<desc>` tag as child tag.

#### Set6

All the elements of **Set1** with a `"aria-label"`, `"aria-labelledby"` or  `"aria-describedby"` attribute on the element or one of its children.

#### Set7

All the elements of **Set1** with a `"title"` attribute.

#### Set8

All the elements of **Set2** with a not empty `<title>` or `<desc>` tag as child tag.

#### Set9

All the elements of **Set2** with a `"aria-label"`, `"aria-labelledby"` or  `"aria-describedby"` attribute on the element or one of its children.

#### Set10

All the elements of **Set2** with a `"title"` attribute.

#### Set11

All the elements of **Set2** without `"title"`, `"aria-label"`, `"aria-labelledby"`, `"aria-describedby"` attributes, without a not empty `<title>` or `<desc>` tag as child tag and with a `"role"` attribute equals to  `"img"`.

### Process

#### Tests

##### Test1

For each element of **Set4**, raise a MessageA.

##### Test2

For each element of **Set5**, raise a MessageB.

##### Test3 

For each element of **Set6**, raise a MessageC.

##### Test4 

For each element of **Set7**, raise a MessageD.

##### Test5 

For each element of **Set8**, raise a MessageE.

##### Test6 

For each element of **Set9**, raise a MessageF.

##### Test7 

For each element of **Set10**, raise a MessageG.

##### Test8 

For each element of **Set11**, raise a MessageH.

#### Messages

##### MessageA : Decorative svg without role img attribute 

-    code : DecorativeSvgWithoutRoleImgAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageB : Decorative svg with a not empty `<title>` or `<desc>` child tag

-    code : DecorativeSvgWithNotEmptyTitleOrDescTags
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageC : Decorative svg or children with Aria attribute

-    code : DecorativeSvgOrChildrenWithAriaAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageD : Decorative svg or children with `title` attribute

-    code : DecorativeSvgWithTitleAttribute
-    status: Failed
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageE : Suspected informative svg with a not empty `<title>` or  `<desc>` child tag

-    code : SuspectedInformativeSvgWithDescOrTitleChildTag
-    status: Pre-Qualified
-    parameter : tag name
-    present in source : yes

##### MessageF : Suspected informative svg with aria attribute on element or child

-    code : SuspectedInformativeSvgWithAriaAttributeDetectedOnElementOrChild
-    status: Pre-Qualified
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageG : Suspected informative svg with title attribute on element or child

-    code : SuspectedInformativeSvgWithTitleAttributeOnElementOrChild
-    status: Pre-Qualified
-    parameter : tag name, Snippet
-    present in source : yes

##### MessageH : Suspected decorative svg without alternative

-    code : SuspectedWellFormedDecorativeSvg
-    status: Pre-Qualified
-    parameter : tag name, Snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag (**Set1** is are empty)

#### Failed

At least one `<svg>` identified as decorative don't have a role img or have a `aria-label`, `aria-describedby`, `aria-labelledby` or `title` attribute or have a not empty `<title>` or `<desc>` as child tag (**Set4** OR **Set5** OR **Set6** OR **Set7** is not empty)

#### Passed

All the `<svg>` identified as decorative, have role img, no `aria-label`, `aria-describedby`, `aria-labelledby` or `title` attributes, no `<title>` or `<desc>` as child tag (**Set4** AND **Set5** AND **Set6** AND **Set7** are empty)

#### Pre-qualified

In all other cases

## Notes

The `<svg>` not identified by marker, without `"role"` attribute equals to "img" are not treated in this test. The test 1.3.5 asks to check that is attribute is present for informative `<svg>`. We can deduce this attribute has to be present for all `<svg>` in any way. To avoid to invalidate a same element twice, we decided to invalid this pattern in the test 1.3.5
 
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

[TestCases files for rule 1.2.4](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010204/) 


