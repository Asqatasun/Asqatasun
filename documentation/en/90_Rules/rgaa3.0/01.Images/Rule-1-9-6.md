# RGAA 3.0 -  Rule 1.9.6

## Summary

This test consists in detecting `<canvas>` tags and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.9](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#crit-1-9)

###Test

[1.9.6](http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Criteria_English_version_v1.html#test-1-9-6)

### Description
Each bitmap image of text
    (<code>canvas</code> tag) must be replaced with <a href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Glossary_English_version_v1.html#mTexteStyle">styled
  text</a> if possible. Has this rule been followed (except in <a title="Particular cases for criterion 1.9" href="http://asqatasun.github.io/RGAA--3.0--EN/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit1-9">particular cases</a>)? 


### Level

**AAA**

## Technical description

### Scope

**Page**

### Decision level

**Semi-Decidable**

## Algorithm

#### Set1

All the `<canvas>` tags of the page not identified as captcha (see Notes about captcha detection) (css selector : `canvas`)

### Process

#### Test1

For each element of **Set1**, raise a MessageA

##### MessageA 

-    code : **ManualCheckOnElements** 
-    status: Pre-Qualified
-    parameter : tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<canvas>` tags (**Set1** is empty)

#### Pre-qualified

The page has at least one `<canvas>` tag (**Set1** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases 

[TestCases files for rule 1.9.6](https://gitlab.com/asqatasun/Asqatasun/-/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010906/) 


