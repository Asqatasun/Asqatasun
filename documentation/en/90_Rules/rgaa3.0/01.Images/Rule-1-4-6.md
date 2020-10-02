# RGAA 3.0 -  Rule 1.4.6

## Summary

This test consists in detecting captcha svg and thus defining the applicability of the test.

Human check will be then needed to determine whether the alternative is pertinent.

## Business description

### Criterion

[1.4](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-1-4)

###Test

[1.4.6](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-1-4-6)

### Description
Does each vector image (<code>svg</code>
    tag), used as a CAPTCHA or test image, with a text alternative provided
    via the <code>aria-label</code> property or the <code>desc</code> tag, meet the following conditions?
    <ul><li>The text alternative provided via the
   <code>aria-label</code> property describes the nature and purpose of the
   image, and matches the value of the <code>title</code>
   attribute of the <code>svg</code> tag, if present
  </li>
  <li>The text alternative provided via the <code>desc</code>
   tag describes the nature and purpose of the
   image, and matches the value of the <code>title</code>
   attribute of the <code>svg</code> tag, if present
  </li>
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

All the `<svg>` tags of the page not within a link with a not empty `"aria-label"` attribute or a not empty `<desc>` child tag  (css selector : svg[aria-label]:not([aria-label~=^\\s*$]:not(a svg), svg:not(a svg):has(desc:not(:matchesOwn(^\\s*$)))

#### Set2

All the elements of **Set1** identified as a CAPTCHA (see Notes for details about CAPTCHA characterisation).

### Process

#### Test1

For each element of **Set2**, raise a MessageA

##### MessageA : Check captcha alternative

-    code : **CheckCaptchaAlternative** 
-    status: Pre-Qualified
-    parameter : `"title"` attribute, `"aria-label"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<svg>` tag with an `"aria-label"` attribute or a `<desc>` child tag identified as a captcha (**Set2** is empty)

#### Pre-qualified

In all other cases

## Notes

### Captcha detection

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases 

[TestCases files for rule 1.4.6](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010406/) 


