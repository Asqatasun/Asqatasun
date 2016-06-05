# RGAA 3.0 -  Rule 1.9.2

## Summary

This test consists in detecting map images and thus defining the applicability of the test.

Human check will be then needed to determine whether the detected elements containing text can be replaced by styled text.

## Business description

### Criterion

[1.9](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#crit-1-9)

### Test

[1.9.2](http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Criteria_English_version_v1.html#test-1-9-2)

### Description
For each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mImgReactive">image
  map</a> (<code>img</code> or <code>object</code> tag with the <code>usemap</code> attribute),
    each <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mZoneTexte">text
  area</a> (<code>area</code> tag) must be replaced with <a href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Glossary_English_version_v1.html#mTexteStyle">styled
  text</a> if possible. Has this rule been followed (except in <a title="Particular cases for criterion 1.9" href="http://disic.github.io/rgaa_referentiel_en/RGAA3.0_Particular_cases_English_version_v1.html#cpCrit1-9">particular cases</a>)? 


### Level

**AAA**

## Technical description

### Scope

**page**

### Decision level

**Semi-Decidable**

## Algorithm

### Selection

#### Set1

All the `<area>` tags, defined within a `<map>` tag whose the `"id"` attribute corresponds to the `"usemap"` attribute of an `<img>` tag and not identified as captcha (see Notes about captcha detection) 

### Process

#### Test1

For each element of **Set1**, raise a MessageA

##### MessageA 

-    code : **ManualCheckOnElements** 
-    status: Pre-Qualified
-    parameter : `"href"` attribute, tag name, snippet
-    present in source : yes

### Analysis

#### Not Applicable

The page has no `<area>` tag, correctly associated with an image (**Set1** is empty)

#### Pre-qualified

The page has at least one `<area>` tag, correctly associated with an image (**Set1** is not empty)

## Notes

An element is identified as a CAPTCHA when the "captcha" occurrence is found :

- on one attribute of the element
- or within the text of the element
- or on one attribute of one parent of the element
- or within the text of one parent of the element
- or on one attribute of a sibling of the element
- or within the text of a sibling of the element



##  TestCases 

[TestCases files for rule 1.9.2](https://github.com/Asqatasun/Asqatasun/tree/master/rules/rules-rgaa3.0/src/test/resources/testcases/rgaa30/Rgaa30Rule010902/) 


